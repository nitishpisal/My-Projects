//var connection = require('../connection');
var crypto=require('crypto');
var secretKey="KJb14I29CFDu0m1FBTh9JoEYxrgBtB0i";

var getHash=function(state,ts){
	
	var text=state+"|"+ts+"|"+secretKey;
	var hmac=crypto.createHmac("sha256",secretKey);
	hmac.setEncoding('base64');
	hmac.write(text);
	hmac.end();
	var hash=hmac.read();
	return hash;
}

exports.index = function(req,res){
	
	var ts=new Date().getTime();
	var state = 'noCoinState';
	var hash=getHash(state,ts);
	
	var db = req.db;
	var gumball = db.get('Gumball');

	var Client = require('node-rest-client').Client;
	var client = new Client();
	client.get("https://api.mongolab.com/api/1/databases/cmpe281/collections/Gumball?apiKey=B9N_976T7zyj8YZetl1LoFY2fjWZcugk",function(data, resp){
		//console.log(data);
		
		res.render('index',{"posts":data,"state":state, "hash":hash, "ts":ts});
	});

};

exports.gumball = function (req, res){
	var model = req.param('model');
	//console.log(model);
	var ts1=parseInt(req.param('ts'));
	var curTs=new Date().getTime();
	var diff=((curTs-ts1)/1000);
	var serial =  req.param('serial');
	var state = req.param('state');
	var count = req.param('count');
	var event = req.param('event');
	var hash1 = req.param('hash');
	hash2 = getHash(state,ts1);
	if(diff>120 || hash1!=hash2){
		//error(req,res,"********Session Invalid********");
		res.render('index', {
			message : "********Session Invalid********"
		});
	}
	var objToJson = {};
	objToJson.modelNumber  = model;
	objToJson.serialNumber = serial;
	objToJson.countGumballs = count;
	objToJson.event = event;
	
	if(event==='InsertQuarter' && state==='noCoinState'){
		
		//console.log("I am here");
		state = 'hasACoin';
		var ts = curTs;
		var hash=getHash(state,ts);
		
		var rows = [];
		rows.push(objToJson);
		console.log(rows);
		res.render('index',{"posts":rows,"state":state, "hash":hash, "ts":ts});
		
	}
	if(event==='TurnCrank' && state==='hasACoin'){
		
		//Get the actual db value first
		var Client = require('node-rest-client').Client;
		var client = new Client();
		client.get("https://api.mongolab.com/api/1/databases/cmpe281/collections/Gumball?apiKey=B9N_976T7zyj8YZetl1LoFY2fjWZcugk", function(data, resp){
			
			var gumballData = {};
			gumballData = data;
			var newCount = gumballData[0].countGumballs;
			if(newCount!==0){
				newCount=newCount-1;
				/*var db = req.db;
				var collection = db.get('Gumball');
				collection.update({'id':1},{$set : {'currentDate' : dateNow}});*/
				console.log("I am here");
				//gumballData.countGumballs = newCount;
				//console.log(gumballData);
            	args = {
            			  data: JSON.stringify( { "$set" : { "countGumballs" : newCount } } ),
            			  headers:{"Content-Type": "application/json"} 
            			};
            	client.put("https://api.mongolab.com/api/1/databases/cmpe281/collections/Gumball?q={'id':1}&apiKey=B9N_976T7zyj8YZetl1LoFY2fjWZcugk", args, function(data, response){
            		state = 'noCoinState';
            		var ts = curTs;
            		var hash=getHash(state,ts);
            		
            		objToJson.countGumballs = newCount;
            		console.log(newCount);
            		var rows = [];
            		rows.push(objToJson);
            		console.log(rows);
            		res.render('index',{"posts":rows,"state":state, "hash":hash, "ts":ts});
            	});
            	
			}
		
		});
		
	}
	
	
};

