var connection = require('../connection');

exports.index = function(req,res){

var query = connection.query(
        'SELECT model_number, serial_number, version FROM Gumball',function(err, rows)
        {
            if (err) {
                throw err;
            }
            console.log('rows', rows);
            result = rows[0];
            res.render('index',{"posts":rows});
        });

	
};

exports.event = function(req,res){
	console.log(req.body.event);
	var eventNm = req.body.event;
	//var path = 'http://nodegumball.cfapps.io/gumballMachine/index/' + eventNm;
	console.log ("Event name " + eventNm);
	var path = 'http://localhost:7000/MyGumball/index/' + eventNm;
	console.log(path);
    res.redirect(path);
};

exports.gumball = function (req, res){
	var model = req.params.model;
	var serial =  req.params.serial;
	var state = req.params.state;
	var objToJson = {};
	objToJson.model_number  = model;
	objToJson.serial_number = serial;
	objToJson.state = state;
	objToJson.version = 0;
	console.log(objToJson);
	var rows = [];
	rows.push(objToJson);
	console.log(rows);
	
	res.render('index',{"posts":rows});
};

