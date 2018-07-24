const http = require('http');
const express = require('express');
const app = express();

app.get( '/', function(req, res) {
  //return json {lastTours:[{},{},...]}
  res.json('main page')
});
app.get( '/createtour', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  res.json('page create tour')
});
app.post( '/createtour', function(req, res) {
  //return json {name:'name',descript:'text',coords:{lat,lng},plases:[place0,place1,...]}
  res.json('info abaut city')
});
app.put( '/createtour', function(req, res) {
  //save tour on database
  res.end('save tour')
});
app.get( '/redactcity', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  res.json('cities')
});
app.get( '/redactcity/:namecity', function(req, res) {
  //return json {name:'name',descript:'text',coords:{lat,lng},plases:[place0,place1,...]}
  req.json(req.params.namecity + 'selected')
});
app.put( '/redactcity/:namecity', function(req, res) {
  //save city on database
  res.end(req.params.namecity + 'redacted')
});
app.get( '/tour/:id', function(req, res) {
  //return json {{city:{0},selectedPlasesOnCity:[0]},{city:{1},selectedPlasesOnCity:[1]}}
  res.json(`id tour is ${req.params.id}`)
});
app.get( '/tour/:id/selectusers', function(req, res) {
  //return json [{fistName:'',lastName:'',id:'',activeTours:[]},{||-||-||}]
  req.json('users')
});
app.put( '/adduser',function(req, res) {
 //add user on database
 req.end('add user')
});
app.listen(3000)
