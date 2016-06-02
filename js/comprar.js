

/* GEOLOCALIZACION */
var options = {
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
};

function success(pos) {
  var crd = pos.coords;
  
  main(crd.longitude, crd.latitude, true);
};

function error(err) {
  main(undefined, undefined, false);
  console.warn('ERROR(' + err.code + '): ' + err.message);
};

navigator.geolocation.getCurrentPosition(success, error, options);

function main(longitud, latitud, geo){
  if (geo) {
    var geoJson = [{
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ -3.7017011, 40.4537651 ]
      },
      "properties": {
        "title": "Madrid",
        "description": "Recogida en Madrid",
        "marker-color": "#8e44ad",
      }
    },
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ -3.7699982, 40.3326371 ]
      },
      "properties": {
        "title": "Leganes",
        "description": "Recogida en Leganes",
        "marker-color": "#8e44ad",
      }
    },
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ -3.7319331, 40.3048596 ]
      },
      "properties": {
        "title": "Getafe",
        "description": "Recogida en Getafe",
        "marker-color": "#8e44ad",
      }
    },
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ longitud, latitud ]
      },
      "properties": {
        "title": "Usted",
        "description": "Usted está aquí",
        "marker-color": "#c0392b",
      }
    }
    ];
  } else {
    var geoJson = [{
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ -3.7017011, 40.4537651 ]
      },
      "properties": {
        "title": "Madrid",
        "description": "Recogida en Madrid",
        "marker-color": "#8e44ad",
      }
    },
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ -3.7699982, 40.3326371 ]
      },
      "properties": {
        "title": "Leganes",
        "description": "Recogida en Leganes",
        "marker-color": "#8e44ad",
      }
    },
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [ -3.7319331, 40.3048596 ]
      },
      "properties": {
        "title": "Getafe",
        "description": "Recogida en Getafe",
        "marker-color": "#8e44ad",
      }
    }
    ];
  }

  L.mapbox.accessToken = 'pk.eyJ1Ijoic2VyZ2lvY2FsZGU5NCIsImEiOiJjaW56dzM1dWMwMHRvdmRseW9tZm9jYngzIn0.jP7bnlXx9xwdo6gvthh2EA';
  var map = L.mapbox.map('map', 'mapbox.streets')
            .setView([40.4537651,-3.7017011], 10)
            .featureLayer.setGeoJSON(geoJson);

  var myLayer = L.mapbox.featureLayer().addTo(map);

}