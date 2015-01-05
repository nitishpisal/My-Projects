exports.products = function(req,res){
	var db = req.db;
	var tvCollection = db.get('tv');
	var carCollection = db.get("cars");
	var routerCollection = db.get("router");
	
	//var array1 = new Array();	
	tvCollection.find({},{},function(e,docs){
		carCollection.find({},{},function(e,docs2){
			routerCollection.find({},{},function(e,docs3){
		
						res.render('productnew',{"tvs":docs,"cars":docs2,"wifis":docs3});
			});
		});
	});
};

exports.tv = function(req,res){
	var db = req.db;
	var id = req.params.id;
	var collection = db.get('tv');
	var dateNow = new Date();
	console.log(dateNow);
	var available = 0;
	collection.update({'series':id},{$set : {'currentDate' : dateNow}});
	collection.find({'series':id},function(e,docs){
		if (typeof docs[0]["endDate"] == 'undefined'){
			available = 1;
			console.log("available " + available);
		}	
		res.render('tvnew',{"tvs":docs,"available" : available});
	});
};

exports.car = function(req,res){
	var db = req.db;
	var id = req.params.id;
	var dateNow = new Date();
	console.log(dateNow);
	var available = 0;
	
	console.log("Brand" + id);
	var collection = db.get("cars");
	collection.update({'brand':id},{$set : {'currentDate' : dateNow}});
	collection.find({brand: id},function(e,docs){
			console.log("EndDate value " + docs[0]["endDate"]);
			if (typeof docs[0]["endDate"] == 'undefined'){
				available = 1;
				console.log("available " + available);
			}
		res.render('carnew',{"cars":docs, "available" : available});
	});
};

exports.wifi = function(req,res){
	var db = req.db;
	var id = req.params.id;
	var available = 0;
	var dateNow = new Date();
	console.log("Brand" + id);
	var collection = db.get("router");
	collection.update({'brand':id},{$set : {'currentDate' : dateNow}});
	collection.find({key: id},function(e,docs){
		if (typeof docs[0]["endDate"] == 'undefined'){
			available = 1;
			console.log("available " + available);
		}	
		res.render('wifinew',{"wifis":docs, "available" : available});
	});
};

exports.tvCatalog = function(req,res){
	var db = req.db;
	var collection = db.get("tv");
	var array1 = new Array();	
	collection.find({},{}, function(e,docs){
		for (var i=0;i<docs.length;i++){
			array1.push(docs[i]["series"]);	
		}

		var inString = array1.toString();
		console.log(inString);
		var replacer = new RegExp(',', 'g');
		console.log(replacer);
		var delimited = inString.replace(replacer,"','");
		
		console.log(delimited);
		res.render('tvCatalognew',{"tvs":docs,"tv":delimited});
	});
};

exports.carCatalog = function(req,res){
	var db = req.db;
	var collection = db.get("cars");
	collection.find({},{}, function(e,docs){
		res.render('carCatalognew',{"cars":docs});
	});
	
};

exports.wifiCatalog = function(req,res){
	var db = req.db;
	var collection = db.get("router");
	collection.find({},{}, function(e,docs){
		res.render('wifiCatalognew',{"wifis":docs});
		});
};

exports.test = function ( req, res ){
  res.render( 'test', { title : 'Mongo DB test Example' });
};

//Create entries in mongodb
exports.create = function ( req, res ){
	var db = req.db;
	var collection = db.get('usercollection');
	var username = req.body.username;
	var email = req.body.email;
	var phone = req.body.phone;
	
	collection.insert({
			"username": username,"email":email,"phone":phone},
			function(err,doc){
			if(err){//If insert fails
			res.send('There is a problem adding data to db');}
			else{
			res.location('/');
			res.redirect('/');
			}
		});
};

exports.admin = function(req, res){
	res.render('admin', {title: 'Admin page'});
	
};

