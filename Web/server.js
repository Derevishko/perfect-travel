const express = require('express');
const app = express();
const fs = require('fs');
const mongoClient = require("mongodb").MongoClient;
const objectId = require("mongodb").ObjectID;
const url = 'mongodb://23.97.131.8:27017';
const bodyParser = require("body-parser");
const urlencodedParser = bodyParser.urlencoded({extended: true});
const error = function (res, client) {
  res.sendStatus(400);
  client.close()
}
const events = require('events')
const myEmit = new events.EventEmitter();

myEmit.on('tourCheked', function(res,client,result){
  result ? myEmit.emit('tourChekComplite',res,client,result) : null
});
myEmit.on('tourChekComplite', function(res,client,result){
  res.send(!result);
  client.close();
});


app.use(bodyParser.json());
app.use('/JS',express.static('JS'));
// app.use('/',express.static(''));
// app.use( '/api/src', express.static('src'));

// открытие страницы
app.get('/api/',function(req,res) {
	console.log('connect')
  res.setHeader('200','ok',{'Content-type' : 'text/html; charset = utf8'});
  res.sendFile( __dirname + '/index.html')
});

// тестовый запрос
app.get('/api/test', function(req, res){

  mongoClient.connect(url, function(err, client){
    client.db('TourAgencyDB').collection('Tour_Places').findOne({f_id: objectId('5b5c377b2ba40c0c385784cc')},function(err,result) {
      console.log(result)
    })
      // let p = 0;
      // let pMax = city.places.length;
      // city.places = result.map(x => ({name:x.place_id, from: x.from, to: x.to, status: x.status}));
      // myEmit.emit('onloadTourPlaces',city.places, res, client)
  })
});


//названия городов --- готово
app.get( '/api/city', function(req, res) {
  mongoClient.connect(url, function(err, client){
    if (!err) {
      let db = client.db('TourAgencyDB');
      let collection = db.collection('Cities');
      collection.find().toArray(function(err, result) {
        if (err || !result) {
          error(res,client)
        } else {
          res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
          let data = result.map(x=>x.name);
          res.json(data)
          client.close();
        }
      })
    } else {
      error(res,client)
    }
  })
});

//город и массив всех мест в нём --- готово
app.get( '/api/city/:name', function(req, res) {
  mongoClient.connect(url, function(err, client) {
    if (!err) {
      let db = client.db('TourAgencyDB');
      let collection = db.collection('Cities');
      collection.findOne({name: req.params.name},function(err, result) {
        if (err || !result) {
          error(res,client)
        } else {

          let city = {name: result.name, description: result.description,
             photos: result.photos, coordinates: result.coordinates};
          let id = result._id;
          db.collection('Places').find({city_id: id})
          .toArray(function(err, result) {
            if (err) {
              error(res,client)
            } else {
              res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
              let places = result.map(x=>({name: x.name, description: x.description,
                 coordinates: x.coordinates, photos: x.photos}));
              client.close();
              res.json({city: city, places: places})
            }
          })
        }
      });
    } else {
      error(res,client)
    }
  })
});

//сохраниние города в бд --- готово
app.post( '/api/savecity', function(req, res) {
  let data = req.body
  mongoClient.connect(url, function(err, client) {
    if (err) {
      error(res,client)
    }
    let db = client.db('TourAgencyDB');
    let city = {name: data.name, description: data.description, photos: data.photos, coordinates: data.coordinates};
    db.collection('Cities').insertOne(city, function(err, result){
      if (err || !result) {
        error(res,client)
      }
      for (let elem of data.places) {
        elem.city_id = objectId(result.insertedId)
      }
      db.collection('Places').insertMany(data.places, function(err, results) {
        if (err || !results) {
          error(res,client)
        }
        res.send('ok');
        client.close()
      })
    })
  })
});


//краткое инфо о турах --- готово
app.get('/api/tour',function(req,res) {
  mongoClient.connect(url,function(err,client) {
    if (!err) {
      let db = client.db('TourAgencyDB');
      let collection = db.collection('Tours');
      collection.find().toArray(function(err,result) {
        if (!err && result) {
          let data = result
          .map(x=>({name: x.name, price: x.price, status: x.status, description: x.description}));
          client.close();
          res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
          res.json(data)
        } else {
          error(res,client)
        }
      });
    } else {
      error(res,client)
    }
  })
});

myEmit.on('tourOnload',function(result,res,client){
  let data = {name: result.name, descriotion: result.descriotion,
    from:result.from,to:result.to,freeSeats:result.freeSeats,
    allSeats:result.allSeats,price:result.price}
    myEmit.emit('addGuid',data,res,client,result._id)
    myEmit.emit('loadTourCities', data, res, client, result._id)
});
myEmit.emit('addGuid',function(data,res,client,id) {
  client.db('TourAgencyDB').collection('Tour_Guide').find({tour_id: objectId(id)}).
  toArray(function(err,result) {
    if(err || !result){
      error(res,client)
    }
    data.guide = result.map(x=>x.guid_id);
    //расписать гида
  })
});

myEmit.on('loadTourCities', function(data,res,client, id){
  client.db('TourAgencyDB').collection('Tour_Cities').
  find({tour_id:objectId(id)}).toArray(function(err,result){
    if (err || !result) {
      error(res,client)
    }
    data.cities = result.map(x=>({name:x.city_id,from:x.from,to:x.to,status:x.status,places:x._id}))
    myEmit.emit('onloadTourCities',data,res,client);
  })
});

myEmit.on('onloadTourCities',function(data,res,client){
  console.log('onloadTourCities');
  let l = data.cities.length;
  for (let city of data.cities) {
    client.db('TourAgencyDB').collection('Cities').findOne({_id: objectId(city.name)},
    function(err,result) {
      if(err || !result) {
        error(res,client)
      }
      city.name = result.name;
      city.description = result.description;
      city.photos = result.photos;
      myEmit.emit('onloadCity',city,res,client);
      l--;
      if (!l) {
        myEmit.emit('loadPlaces',data,res,client)
      }
    })
  }
});
myEmit.on('loadPlaces', function(data,res,client) {
  let l = data.cities.length;
  for (let city of data.cities) {
    client.db('TourAgencyDB').collection('Tour_Places').find({f_id: objectId(city.places)}).toArray(function(err,result) {
      if(err || !result) {
        error(res,client)
      }
      city.places = result.map(x => ({name:x.place_id, from:x.from, to:x.to,status:x.status}));
      l--;
      if(!l) {
        myEmit.emit('onloadPlaces',data,res,client)
      }
    })
  }
});
myEmit.on('onloadPlaces',function(data,res,client) {
  let c = data.cities.length;
  if (!c) {
    myEmit.emit('done',data,res,client)
  }
  for (let city of data.cities) {
    let p = city.places.length;
     if(!p){
       c--
     }
    for( let place of city.places ) {
      client.db('TourAgencyDB').collection('Places').findOne({_id: objectId(place.name)},
      function(err,result) {
        place.name = result.name;
        place.description = result.descriotion;
        place.photos = result.photos;
        p--;
        if (!p) {
          c--;
          if (!c) {
            myEmit.emit('done',data,res,client)
          }
        }
      })
    }
  }
});

myEmit.on('done', function(data,res,client) {
  res.send(data);
  client.close();
})
//полная информация о туре --- готово
app.get('/api/tour/:name', function(req,res){
  mongoClient.connect(url, function(err,client){
    if (err) {
      error(res,client)
    }
    client.db('TourAgencyDB').collection('Tours').
    findOne({name: req.params.name}, function(err,result){
      if (err || !result) {
        error(res,client)
      }
      myEmit.emit('tourOnload', result,res,client);
    })
  })
})
// сохраниние тура в базу данных --- готово
const check = function(name,from,to){
  let flag = true;
  name.match(/^\w{2,60}$/)[0] ? null : flag = false;
  from.match(/^20\d{2}\-\d{2}-\d{2}T\d{2}\:\d{2}\:\d{2}\.\d{3}Z$/)[0] ? null : flag = false;
  to.match(/^20\d{2}\-\d{2}-\d{2}T\d{2}\:\d{2}\:\d{2}\.\d{3}Z$/)[0] ? null : flag = false;
  if(flag) {
    new Date(from) < new Date(to) ? null : flag = false;
  }
  return flag;
}
app.post( '/api/savetour', function(req, res) {
  let flag = true;
  check(req.body.name,req.body.from,req.body.to)
  req.body.guider.match(/^\+?\s*(375|80)\s*\(?(25|29|33|44)\)?\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d$/) ? null : flag = false;
  req.body.allSeats.match(/^\d{1,2}$/) ? null : flag = false;
  req.body.price.match(/^\d{1,}$/)
  if(!flag){
    res.sendStatus(400)
  }
  req.body.cities.forEach(function(city) {
    check(city.name,city.from,city.to);
    if(!flag){
      res.sendStatus(400)
    }
    city.places.forEach(function(place) {
      check(place.name,place.from,place.to)
      if(!flag){
        res.sendStatus(400)
      }
    })
  })
  myEmit.emit('validTour',req.body,res)
});
myEmit.on('validTour',function(tour,res){
  mongoClient.connect(url,function(err,client) {
    if(err) {
      error(res,client);
    }
    client.db('TourAgencyDB').collection('Tours')
    .findOne({name:tour.name},function(err,result){
      if(err){
        error(res,client)
      }
      if(result == {}){
        myEmit.emit('createTour',tour,res,client)
      } else {
        error(res,result)
      }
    })
  })
});
myEmit.on('createTour',function(tour,res,client){
  let dataTour = {name:tour.name,freeSeats:tour.allSeats,allSeats:tour.allSeats,
  price:tour.price,from:tour.from,description:tour.description,to:tour.to,created:(new Date()).toISOString(),status:'Future'}
  client.db('TourAgencyDB').collection('Tours')
  .insertOne(dataTour,function(err,result){
    if(err) {
      error(res,client)
    }
    myEmit.emit('createTourCity',tour.cities,result._id,res,client)
  })
});
myEmit.on('createTourCity',function(cities,id,res,client) {
  let c = cities.length;
  cities.forEach(function(city) {

    client.db('TourAgencyDB').collection('Cities').findOne({name:city.name},function(err,result){
      if(err || !result.name) {
        error(res,client)
      }
      let dataCity = {tour_id:objectId(id),city_id:objectId(result._id),from:city.from,to:city.to,status:'Future'}
      myEmit.emit('addTourCity',dataCity,city.places,c,res,client)
    })
  })
})
myEmit.on('addTourCity',function(city,places,c,res,client){
  client.db('TourAgencyDB').collection('Tour_Cities')
  .insertOne(city,function(err,result){
    if(err){
      error(res,client)
    }
    let p = places.length;
    if(!p){c--}
    let city_id = result._id
    places.forEach(function(place){
      client.db('TourAgencyDB').collection('Places')
      .findOne({name:place.name},function(err,result){
        if(err){
          error(res,client)
        }
        myEmit.emit('addTourPlace',place,result._id,city_id,c,p,res,client);
      })
    })
  })
});
myEmit.on('addTourPlace',function(place,id,city_id,c,p,res,client){
  let dataPlace = {f_id:objectId(city_id),from:place.from,to:place.to,status:'Future',place_id:objectId(id)};
  client.db('TourAgencyDB').collection('Tour_Places')
  .insertOne(dataPlace,function(err,result){
    if(err){
      error(res,client)
    }
    p--;
    if(!p){
      c--;
      if(!c){
        myEmit.emit('createdTour',res,client)
      }
    }
  })
});
myEmit.on('createTour',function(res,client){
  res.sendStatus(200);
  client.close();
})
// пользователи --- готово
app.get( '/api/users', function(req, res) {
  mongoClient.connect(url,function(err, client) {
    if (err) {
      error(res,client)
    }
    client.db('TourAgencyDB').collection('Users').find().toArray(function(err,result) {
      if (err || !result) {
        error(res,client)
      }
      res.json(result.map(x=>({name: x.name, email: x.email})));
      client.close()
    })
  })
});


// проверка пользователя перед туром --- готово
app.post( '/api/chekuser',function(req, res) {
 mongoClient.connect(url, function(err, client) {
   if(err){
     error(res,client);
   }
   client.db('TourAgencyDB').collection('Users').findOne({email: req.body.email}, function(err,result){
     if(err || !result) {
       error(res,client)
     }
     client.db('TourAgencyDB').collection('Tour_User').find({user_id: objectId(result._id)}).toArray(function(err,result){
       if(err || !result) {
         error(res,client)
       }

       let l = result.length;
       for(let i = 0; i < l; i++) {
         client.db('TourAgencyDB').collection('Tours').findOne({_id: result[i].tour_id}, function(err,result){
           if(err || !result) {
             error(res,client);
           }
           myEmit.emit('tourCheked',res,client
           ,Math.min(req.body.to.getTime(), result.to.getTime()) - Math.max(req.body.from.getTime(), result.from.getTime()) < 0)
            if(l - i == 1){
              myEmit.emit('tourChekComplite',res,client,false)
            }
         })
       }
     })
   })
 })
});
//добавление пользователя в тур --- готово
app.post('/api/adduser',function(req,res){
  mongoClient.connect(url,function(err,client){
    if(err) {
      error(res,client)
    }
    client.db('TourAgencyDB').collection('Users').findOne({email:req.body.email},function(err,result){
      if(err || !result){
        error(res,client)
      }
      console.log(result)
      let user_id = result._id;
      client.db('TourAgencyDB').collection('Tours').findOne({name:req.body.name},function(err,result){
        if(err || !result){
          error(res,client)
        }
        console.log(result)
          // res.send(200);
          // client.close();
        let tour_id = result._id;
        client.db('TourAgencyDB').collection('Tour_Users').insertOne({tour_id:tour_id, user_id: user_id},function(err,result){
          if(err || !result){
            error(res,client)
          }
          res.sendStatus(200);
          client.close();
        })
      })
    })
  })
})

//добавление гида в  базу данных ---  готово
app.post( '/api/addguid',function(req, res) {
 mongoClient.connect(url, function(err,client){
   let flag = true;
   req.body.email.match(/^[a-z][\w\d\_\.]*\@[a-z][a-z]*\.\w{2,10}\.?$/) ? null : flag = false;
   req.body.phone.match(/^\+?\s*(375|80)\s*\(?(25|29|33|44)\)?\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d\s*\-*\d$/) ? null : flag = false
   if(err && flag) {
     error(res,client)
   }
   client.db('TourAgencyDB').collection('Users').findOne({email: req.body.email}, function(err, result) {
     if(err || !result) {
       error(res,client)
     }

     let guid = {user_id: result._id, phone: req.body.phone}
     client.db('TourAgencyDB').collection('Guides').insertOne(guid, function(err,result){
       if(err || !result) {
         error(res,client)
       }
       res.sendStatus(200);
       client.close();
     })
   })
 })
});

// app.listen(process.    env.PORT);
app.listen(3000);
