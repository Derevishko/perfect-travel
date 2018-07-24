const express = require('express');
const app = express();
const fs = require('fs');

app.use('/dist',express.static('dist'));
app.use('/DATABASE',express.static('DATABASE'));

app.get('/api/',function(req,res) {
  res.sendFile( __dirname + '/index.html')
});
app.get( '/api/createtour', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  res.json('page create tour')
});
app.post( '/api/createtour', function(req, res) {
  //return json {name:'name',descript:'text',coords:{lat,lng},plases:[place0,place1,...]}
  res.json('info abaut city')
});
app.put( '/api/createtour', function(req, res) {
  //save tour on database
  res.send('save tour')
});
app.get( '/api/redactcity', function(req, res) {
  //return json {nameCities:['name0',name1,...]}
  res.json('cities')
});
app.get( '/api/redactcity/:namecity', function(req, res) {
  //return json {name:'name',descript:'text',coords:{lat,lng},plases:[place0,place1,...]}
  req.json(req.params.namecity + 'selected')
});
app.put( '/api/redactcity/:namecity', function(req, res) {
  //save city on database
  res.send(req.params.namecity + 'redacted')
});
app.get( '/api/tour/:id', function(req, res) {
  //return json {{city:{0},selectedPlasesOnCity:[0]},{city:{1},selectedPlasesOnCity:[1]}}
  res.json(`id tour is ${req.params.id}`)
});
app.get( '/api/tour/:id/selectusers', function(req, res) {
  //return json [{fistName:'',lastName:'',id:'',activeTours:[]},{||-||-||}]
  req.json('users')
});
app.put( '/api/adduser',function(req, res) {
 //add user on database
 req.send('add user')
});
app.listen(3000)
