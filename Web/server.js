const express = require('express');
const app = express();
const fs = require('fs');
const mongoClient = require("mongodb").MongoClient;
const url = 'mongodb://localhost:27017/';


app.use('/api/dist',express.static('dist'));
app.use('/api/DATABASE',express.static('DATABASE'));
app.use( '/api/src', express.static('src'));

// app.get('/DATABASE/text',function(req,res){
//     res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'});
//     let response = {text: 'yghni',id:12};
//     res.json(response);
// })

// app.get('/api/',function(req,res) {
//   res.setHeader('200','ok',{'Content-type' : 'text/html; charset = utf8'})
//   res.sendFile( __dirname + '/index.html')
// });
app.get('/api/test',function(req,res){
  mongoClient.connect(url, function(err, client){
      if(err){
          return console.log(err);
      }
      const db = client.db('TourAgancyDB');
      const collection = db.collection('Cities');
      res.send(collection);
      client.close();
  });
})
app.get( '/api/createtour', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
  let response = {text : 'page create tour'}
  res.json(response)
});
app.post( '/api/createtour', function(req, res) {
  //return json {name:'name',descript:'text',coords:{lat,lng},plases:[place0,place1,...]}
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
  let response = {text : 'place on city'}
  res.json(response)
});
app.put( '/api/createtour', function(req, res) {
  //save tour on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head()
});
app.get( '/api/redactcity', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
  let response = {text : 'open colection city'}
  res.json(response)
});
app.get( '/api/redactcity/:namecity', function(req, res) {
  //return json {name:'name',descript:'text',coords:{lat,lng},plases:[place0,place1,...]}
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'})
  let response = {text : req.params.namecity + 'selected'};
  res.json(response)
});
app.put( '/api/redactcity/:namecity', function(req, res) {
  //save city on database
  res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
  res.head();
});
app.get( '/api/tour/:id', function(req, res) {
  //return json {{city:{0},selectedPlasesOnCity:[0]},{city:{1},selectedPlasesOnCity:[1]}}
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'});
  let responce = {text : `id tour is ${req.params.id}`}
  res.json(responce)
});
app.get( '/api/tour/:id/selectusers', function(req, res) {
  //return json [{fistName:'',lastName:'',id:'',activeTours:[]},{||-||-||}]
  res.setHeader('200','ok',{'Content-type' : 'aplication/json; charset = utf8'});
  let responce = {text: 'users'}
  res.json(responce)
});
app.put( '/api/adduser',function(req, res) {
 //add user on database
 res.setHeader('200','ok',{'Content-type' : 'text/plain; charset = utf8'});
 req.head()
});
app.listen(process.env.PORT);
// app.listen(3000);
