exports.index = function(req, response){
	
	var client = require('node-rest-client').Client;
	var Client = new client();
	Client.get("http://pivotalgumball.cfapps.io/gumballs.json", function(data, res){
		
		var state = 'noCoinState';
		console.log(data);
		response.render('index',{"data":data,"state":state});
		
	});
}


exports.controller = function(req, response){
	
	var client = require('node-rest-client').Client;
	var Client = new client();
	
	var model = req.param('model');
	var serial = req.param('serial');
	var count = req.param('count');
	var state = req.param('state');
	var event = req.param('event');
	
	var data1 = {};
	data1.modelNumber = model;
	data1.serialNumber = serial;
	data1.countGumballs = count;
	var indexData = [];
	
	
	if(state === 'noCoinState' && event === 'InsertQuarter'){
		state = 'hasCoinState';
		indexData.push(data1);
		response.render('index',{"data":indexData,"state":state});
		
	}
	
	if(state === 'hasCoinState' && event === 'TurnCrank'){
		Client.get("http://pivotalgumball.cfapps.io/gumballs.json", function(data, res){
				 	var count = data[0].countGumballs;
				 	if(count!==0){
					count = count -1;
				 }
				 var arg = {
					data1:{	countGumballs: count},
					headers:{"Content-Type": "application/json"} 
				 };
				 console.log(arg);
				 Client.put("http://pivotalgumball.cfapps.io/gumballs.json/1", arg, function(data, res){
					
					data1.countGumballs = count;
					indexData.push(data1);
					state = 'noCoinState';
					response.render('index',{"data":indexData,"state":state});
					 
				 });
			
			
		});
	}
};