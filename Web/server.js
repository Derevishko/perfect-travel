const express = require('express');
const app = express();
const fs = require('fs');
const mongoClient = require("mongodb").MongoClient;
const url = 'mongodb://localhost:27017/';
const db = client.db('TourAgencyDB');

app.use('/api/dist',express.static('dist'));
app.use( '/api/src', express.static('src'));

app.get('/api/*',function(req,res) {
  res.setHeader('200','ok',{'Content-type' : 'text/html; charset = utf8'})
  res.sendFile( __dirname + '/index.html')
});

app.get('/api/',function(req,res) {
    res.setHeader('200','ok',{'Content-type' : 'aplication.json; charset = utf8'})
    let data = [];
    mongoClient.connect(url,function(err,client) {
      let collection = db.collection('Tours');
      collection.find().toArray(function(err,result) {
        data = JSON.parse(result);
        client.close();
        res.json(data)
      });

    })
});

app.get( '/api/createtour', function(req, res) {
  let data = [];
  mongoClient.connect(url, function(err, client){
    let collection = db.collection('Cities');
    collection.find().toArray(function(err, result) {
      if (err) {
        res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
        res.send(err)
      } else {
        res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
        data = JSON.parse(result);
      }
      client.close();
      res.json(data)
    })
  })

});
app.get( '/api/createtour/:name', function(req, res) {
  let data = {city: null, places: null};
  mongoClient.connect(url, function(err, client) {
    let collection = db.collection('Cities');
    collection.findOne({name: req.params.name})
    .toArray(function(err, result) {
      if (err) {
        res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
        res.send(err)
      } else {
        res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
        data.city = JSON.parse(result);
      }
    });
    collection = db.collection('Places');
    colection.find({city_id: data.id})
    .toArray(function(err, result) {
      if (err) {
        res.setHeader('400','error',{'Content-type' : 'text/plain; charset = utf8'});
        res.send(err)
      } else {
        res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
        data.places = JSON.parse(result);
        client.close()
        res.json(data)
      }
    })
  })
});

app.put( '/api/createtour', function(req, res) {
  //save tour on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head()
});

app.get( '/api/redactcity', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  let data = []
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
  mongoClient.connect(url,function(err,client) {
    let collection = db.collection('Cities');
    collection.find().toArray(function(err,result) {
      result = JSON.parse(result);
      for ( let city of result ) {
        data.push(city.name)
      }
      client.close();

    })
  });
  res.json(data)
});

app.get( '/api/redactcity/:namecity', function(req, res) {
  let data = [];
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
  mongoClient.connect(url,fnction(err, client) {
    let collection = db.collection('Cities');
    collection.findOne({name: req.params.namecity}).toArray(function(err,result) {
      data = JSON.parse(result);
      client.close();
    })
  })
  res.json(data)
});

app.put( '/api/redactcity/:namecity', function(req, res) {
  //save city on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head();
});

app.get( '/api/tour/:id', function(req, res) {
  //return json {{city:{0},selectedPlasesOnCity:[0]},{city:{1},selectedPlasesOnCity:[1]}}
  let data = null;
  let tourId = null;
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'});
  mongoClient.connect(url,function(err,client) {
    let collection = db.collection('Tours');
    collection.find({_id:req.params.id}).toArray(function(err,result){
      data = JSON.parse(result);
      tourId = data.id;
      client.close();
      res.json(data)
    });
  })

});

app.get( '/api/selectusers', function(req, res) {
  //return json [{fistName:'',lastName:'',id:'',activeTours:[]},{||-||-||}]
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'});
  let responce = {text: 'users'}
  res.json(responce)
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
app.listen(process.env.PORT);
// app.listen(3000);
