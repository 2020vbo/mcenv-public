geo = navigator.geolocation;

function getLocation(req, res, next) {
    if(geo) {
       geo.getCurrentPosition(getSuccess, getError);
    }
}

function getSuccess(position) {
    userLong = position.coords.longitude;
    userLat = position.coords.latitude;
    showPosition(position);
}

function getError() {
    document.getElementById("geo").innerHTML = "Geolocating failed"
}

function showPosition(position) {
  document.getElementById("geo").innerHTML = "Latitude: " + position.coords.latitude + 
  "<br>Longitude: " + position.coords.longitude;
}