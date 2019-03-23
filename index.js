#!/usr/bin/nodejs


// -------------- load packages -------------- //

var express = require('express');
var request = require('request');
var cookieSession = require('cookie-session');
var path = require('path');
var hbs = require('hbs');
var app = express();

// -------------- express initialization -------------- //
app.set('port', process.env.PORT || 8080 );  // set up the port
app.set('view engine', 'hbs');  // set up handlebars
app.set('trust proxy', 1);  // trust first proxy <-- not sure what this means...but I'll use it for now

// -------------- serve static folders -------------- //
app.use('/js', express.static(path.join(__dirname, 'js')));
app.use('/css', express.static(path.join(__dirname, 'css')));
app.use('/img', express.static(path.join(__dirname, 'img')));

// -------------- cookie session initialization -------------- //
// app.use(cookieSession({
//     name: 'loginCookie',
//     keys: ['baDstuFFtHAtisBAd', 'whatsupbobdoc']
// }));
//--------------- variable definition --------------//
// -------------- middleware functions -------------- //

app.get('/', function(req, res){
    res.render('index');
});

// -------------- listener -------------- //
// The listener is what keeps node 'alive.' 

var listener = app.listen(app.get('port'), function() {
  console.log( 'Express server started on port: '+listener.address().port );
});