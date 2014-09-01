_details_template = """
<div>id: {{ id }}</div>
<div>title: {{ title }}</div>
<div>lat: {{ lat }}</div>
<div>lng: {{ lng }}</div>
"""

window.App = App = {}
App.points = {}
App.template = {}
App.template.MarkerDetails = Handlebars.compile _details_template


show_alert = (type) ->
  $("#alrt-#{type}").fadeIn(500)
  setTimeout () ->
    $("#alrt-#{type}").fadeOut(500)
   ,
    1500

show_marker_info = (id) ->
  $("#marker_info").html(
    App.template.MarkerDetails App.points[id][0]
  )


add_point = (p) ->
  loc = new google.maps.LatLng(p.lat, p.lng)
  marker = new google.maps.Marker {
    position: loc
    map: App.map
  }
  App.points[p.id] = [p, marker]
  google.maps.event.addListener marker, 'click', () ->
    show_marker_info p.id

load_points = () ->
  $.get "/points/", (resp) ->
    for p in resp.points
      add_point(p)

$ () ->
  $.ajaxSetup
    contentType:
      "application/json; charset=utf-8"
    error: (jqXHR, exception) ->
      alert "Something went wrong.\n#{jqXHR.status} - #{jqXHR.statusText}"

  init_map = () ->
    options =
      center: new google.maps.LatLng 47.8333, 35.1667
      zoom: 13
    window.App.map = map = new google.maps.Map document.getElementById('map'), options
    google.maps.event.addListener map, 'click', (e) ->
      if App._current_marker?
        App._current_marker.setMap null
        $("#input_lat").val ''
        $("#input_lng").val ''
        App._current_marker = null
      loc = e.latLng
      App._current_marker = new google.maps.Marker {
        position: loc
        map: map
        icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
      }
      $("#input_lat").val loc.lat()
      $("#input_lng").val loc.lng()
    load_points()
  google.maps.event.addDomListener window, 'load', init_map

  $('#btn_add').click () ->
    lat = $("#input_lat").val()
    lng = $("#input_lng").val()
    title = $("#input_title").val()
    if lat == '' or lng == '' or title == ''
      show_alert 'err'
    else
      data =
        lat: lat
        lng: lng
        title: title
      $.post '/points/', JSON.stringify(data), (resp) ->
        $("#input_lat").val ''
        $("#input_lng").val ''
        $("#input_title").val ''
        if App._current_marker?
          App._current_marker.setMap null
          App._current_marker = null
        show_alert 'ok'
        data.id = resp.id
        add_point data
       ,
        'json'


