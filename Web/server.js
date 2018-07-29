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
app.use('/api/dist',express.static('dist'));
app.use( '/api/src', express.static('src'));

// открытие страницы
app.get('/api/',function(req,res) {
  res.setHeader('200','ok',{'Content-type' : 'text/html; charset = utf8'});
  res.sendFile( __dirname + '/index.html')
});

// тестовый запрос
app.get('/api/test', function(req, res){
  mongoClient.connect(url, function(err, client){
    client.db('TourAgencyDB').collection("Tours").findOne({name: 'Tour1'},function(err,result) {console.log(result.from.getTime())})
    res.send('nol')
    client.close()
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

//полная информация о туре --- не готово
app.get('/api/tour/:name', function(req, res) {
  mongoClient.connect(url, function(err,client) {
    if (err) {
      error(res,client)
    }
    let db = client.db('TourAgencyDB');
    let data = null;
    db.collection('Tours').findOne({name: req.params.name}, function(err, result) {
      if (err || !result) {
        error(res,client)
      }
      data = {from: result.from, to: result.to, freeSeats: result.freeSeats, allSeats: result.allSeats, cities: result._id};
      db.collection('Tour_Cities').find({tour_id: data.cities}).toArray(function(err, result) {
        if (err || !result) {
          error(res,client)
        }
        data.cities = result.map(x => ({from: x.from, to: x.to, freeSeats: x.freeSeats, status: x.status,
           city: db.collection('Cities').findOne({_id: objectId(x.city_id)}, function(err, result) {
             if (err || !result) {
               error(res,client)
             }
             return {name: result.name};
           })
         }));
         res.json(data);
         client.close()
      })
    })
  })
})

// сохраниние тура в базу данных --- не готово
app.post( '/api/createtour', function(req, res) {
  //save tour on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head()
});

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
      let user_id = result._id;
      client.db('TourAgencyDB').collection('Tours').findOne({email:req.body.name},function(err,result){
        if(err || !result){
          error(res,client)
        }
        let tour_id = result._id;
        client.db('TourAgencyDB').collection('Tours').insertOne({tour_id:tour_id, user_id: user_id},function(err,result){
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
       res.sendStatus(200)
     })
   })
 })
});

// app.listen(process.    env.PORT);
app.listen(3000);
