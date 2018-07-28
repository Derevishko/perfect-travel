const express = require('express');
const app = express();
const fs = require('fs');
const mongoClient = require("mongodb").MongoClient;
const objectId = require("mongodb").ObjectID;
const url = 'mongodb://23.97.131.8:27017';
let flag = true;

app.use('/api/dist',express.static('dist'));
app.use( '/api/src', express.static('src'));

// открытие страницы
// app.get('/api*',function(req,res) {
//   if ( flag ) {
//     flag = false;
//     res.setHeader('200','ok',{'Content-type' : 'text/html; charset = utf8'});
//     res.sendFile( __dirname + '/index.html')
//   }
// });
//
app.get('/api/test', function(req,res) {
  mongoClient.connect(url,function(err, client) {
    let collection = client.db('TourAgencyDB').collection('Users');
    // collection.findOne({}, function(err, result) {
    //   res.json(result);
    //   client.close();
    // })
    collection.find().toArray(function(err,result) {
      res.json(result);
      client.close()
    })
  })
})

//названия городов
app.get( '/api/city', function(req, res) {
  mongoClient.connect(url, function(err, client){
    if (!err) {
      let db = client.db('TourAgencyDB');
      let collection = db.collection('Cities');
      collection.find().toArray(function(err, result) {
        if (err) {
          res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
          res.send({err:'connect error'})
        } else {
          res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
          let data = result.map(x=>x.name);
          res.json(data)
          client.close();

        }
      })
    } else {
      res.setHeader('400','err',{'Content-type' : 'text/plain; charset = utf8'});
      res.send({err:'connect error'})
    }
  })
});

app.get( '/api/city/:name', function(req, res) {
  mongoClient.connect(url, function(err, client) {
    if (!err) {
      let db = client.db('TourAgencyDB');
      let collection = db.collection('Cities');
      collection.findOne({name: req.params.name},function(err, result) {
        if (err) {
          res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
          res.send({err: 'connect error'})
        } else {
          res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
          let city = {name: result.name, descript: result.descript,
             photos: result.photos, coords: result.coords};
          let id = result._id;

          let collection2 = db.collection('Places');
          collection2.find({city_id: objectId(id)})
          .toArray(function(err, result) {
            if (err) {
              res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
              res.send({err: 'connect error'})
            } else {
              res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
              let places = result.map(x=>{name: x.name; descript: x.descript;
                 coords: x.coords; photos: x.photos});
              client.close();
              res.json({city: city, places: places})
            }
          })
        }
      });
    } else {
      res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
      res.send({err: 'connect error'})
    }
  })
});

app.put( '/api/savecity/:name', function(req, res) {
  //save city on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head();
});



// app.get( '/api/redactcity', function(req, res) {
//   //return json {nameCities:['name0',name1,...]}
//   let data = []
//   res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
//   mongoClient.connect(url,function(err,client) {
//     if (!err) {
//       let db = client.db('TourAgencyDB');
//       let collection = db.collection('Cities');
//       collection.find().toArray(function(err,result) {
//         if (!err) {
//           let data = JSON.parse(result).map(x=>x.name);
//           client.close();
//           res.json(data)
//         } else {
//           res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
//           res.send({err: 'connect error'})
//         }
//       })
//     } else {
//       res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
//       res.send({err: 'connect error'})
//     }
//   });
// });

// app.get( '/api/city/:namecity', function(req, res) {
//   res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
//   mongoClient.connect(url,fnction(err, client) {
//     let db = client.db('TourAgencyDB');
//     let collection = db.collection('Cities');
//     collection.findOne({name: req.params.namecity}).toArray(function(err,result) {
//       let data = JSON.parse(result).map(x=>{name: x.name, descript: x.descript, coords: x.coords, photos: x.photos});
//       let id = JSON.parse(result).map(x=>x.id);
//
//       client.close();
//       res.json(data)
//     })
//   })
// });



//краткое инфо о турах
app.get('/api/tour',function(req,res) {
  mongoClient.connect(url,function(err,client) {
    if (!err) {
      let db = client.db('TourAgencyDB');
      let collection = db.collection('Tours');
      collection.find().toArray(function(err,result) {
        if (!err) {
          let data = result
          .map(x=>({name: x.name, price: x.price, status: x.status, descript: x.description}));
          client.close();
          res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
          res.json(data)
        } else {
          res.status(400).send({err:'connect error'})
        }
      });
    } else {
      res.status(400).send({err:'connect error'})
    }
  })
});

app.get( '/api/tour/:name', function(req, res) {
  //return json {{city:{0},selectedPlasesOnCity:[0]},{city:{1},selectedPlasesOnCity:[1]}}
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'});
  mongoClient.connect(url,function(err,client) {
    let db = client.db('TourAgencyDB');
    let collection = db.collection('Tours');
    collection.findOne({name:req.params.name},function(err,result){
      let data = {name: result.name, freeSeats: result.freeSeats, allSeats: result.allSeats,
      price: result.price, from: result.from, to: result.to};
      let collection2 = db.collection('Tour_Cities');
      collection2.find({tour_id : result._id}).toArray(function(err, result) {

      });
      client.close();
      res.json(data)
    })
  })
});

app.put( '/api/createtour', function(req, res) {
  //save tour on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head()
});

app.get( '/api/users', function(req, res) {
  mongoClient.connect(url,function(err, client) {
    let collection = client.db('TourAgencyDB').collection('Users');
    collection.find().toArray(function(err,result) {
      res.json(result.map(x=>({name: x.name, email: x.email})));
      client.close()
    })
  })
});

app.put( '/api/selectusers',function(req, res) {
 //add user on database
 res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
 req.head()
});

app.put( '/api/adduser',function(req, res) {
 //add user on database
 res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
 req.head()
});
// app.listen(process.env.PORT);
app.listen(3000);
