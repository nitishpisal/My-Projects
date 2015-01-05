/*var express = require('express');
var router = express.Router();*/

/* GET home page. */
/*router.get('/', function(req, res) {
  res.render('index', { title: 'Express' });
});*/

exports.index = function(req,res){
	res.render('index', { title: 'Hello' });
};

exports.userlist = function(req,res){
	res.render('userlist',{title2: 'User list page'});
};

exports.home = function(req,res){
	res.render('home');
};

/*router.get('/helloWorld', function(req,res){
  res.render('helloWorld',{title: 'Hello World'});
});
*/
//Get Userlist Page
/*router.get('/userlist',function(req,res){
var db = req.db;
var collection = db.get('usercollection');
collection.find({},{},function(e,docs){res.render('userlist',{"userlist":docs})});
	res.render('userlist',{title: 'Hello'});
});*/
/*
Get new user page
router.get('/newuser',function(req,res){
res.render('newuser',{title: 'Add User'});
});

Post to add user service
router.post('/adduser',function(req,res){
//set our internal db variable
var db = req.db;

//get our form values
var userName = req.body.username;
var userEmail = req.body.useremail;

//set our collection
var collection = db.get('usercollection');

//submit to db
collection.insert({
"name": userName,"email":userEmail},
function(err,doc){
if(err){//If insert fails
res.send('There is a problem adding data to db');}
else{
res.location("userlist");
res.redirect("userlist");
}
});
});
*/
//module.exports = router;
