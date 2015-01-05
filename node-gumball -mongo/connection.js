var mysql      = require('mysql');
var connection = mysql.createConnection({
  host     : "us-cdbr-aws-east-105.cleardb.net",
  port	   : "3306",
  user     : "bb76fcb43f807b",
  password : "6776566b",
  database : 'ad_033c9629217c759'
});

module.exports = connection;