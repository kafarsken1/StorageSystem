function getStudentData() {

	$.ajax({
		type : "GET",
		url : "http://localhost:8080/assignment2-gui/api/student.json",
		success : function(json) {
			populateStudentTable(json);
			populateStudentLocationForm(json);
		}
	});
}

// This function gets called when you press the Set Location button
function get_location() {
	if (Modernizr.geolocation) {
		navigator.geolocation.getCurrentPosition(location_found, error_response);
	} else {
		// no native support; maybe try a fallback?
		alert("Browser not supported");
	}
}
function error_response(geo_error) {
	// deal with error
	alert("error");
}

// Call this function when you've succesfully obtained the location.
function location_found(position) {
	var e = document.getElementById("selectedStudent");
	var id = e.options[e.selectedIndex].value;
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/assignment2-gui/api/student/"+id+"/location",
		data : {
			latitude : position.coords.latitude,
			longitude : position.coords.longitude
		},
		dataType : 'json',
	})
	.done(function(json){
		populateStudentTable(json);		
	});
}

// No need to change javascript below this line, unless you want to...

function populateStudentTable(json) {

	$('#studentTable').empty();

	for ( var s = 0; s < json.length; s++) {
		var student = json[s];
		student = explodeJSON(student);
		var tableString = "<tr>";
		console.log('Student');
		console.log(student);
		// Name
		tableString += "<td>" + student.name + "</td>";

		// Courses
		tableString += "<td>";
		for ( var c = 0; c < student.courses.length; c++) {
			var course = student.courses[c];
			course = explodeJSON(course);
			tableString += course.courseCode + ' ';
			/*
			 * tableString += '<a href="/assignment2-gui/student/' + student.id +
			 * '/unenrollcourse/' + course.id + '"><img
			 * src="/assignment2-gui/images/Button-Delete-icon.png"></a>';
			 */
		}
		tableString += '</td>';

		// Location
		if (student.latitude != null && student.longitude != null) {
			tableString += '<td>' + student.latitude + ' ' + student.longitude
					+ '</td>';
			var myLatlng = new google.maps.LatLng(student.latitude,
					student.longitude);

			var marker = new google.maps.Marker({
				position : myLatlng,
				map : map,
				title : student.name
			});
		} else {
			tableString += '<td>No location</td>';
		}

		tableString += '</tr>';
		$('#studentTable').append(tableString);
	}

}

function populateStudentLocationForm(json) {
	var formString = '<tr><td><select id="selectedStudent" name="students">';
	for ( var s = 0; s < json.length; s++) {
		var student = json[s];
		student = explodeJSON(student);
		formString += '<option value="' + student.id + '">' + student.name
				+ '</option>';
	}
	formString += '</select></td></tr>';
	// += '<tr><td><input class="btn btn-primary" type="submit" value="Set
	// location"></td></tr>';
	$('#studentLocationTable').append(formString);
}

$('#locationbtn').on('click', function(e) {
	e.preventDefault();
	get_location();
});

var objectStorage = new Object();

function explodeJSON(object) {
	if (object instanceof Object == true) {
		objectStorage[object['@id']] = object;
		console.log('Object is object');
	} else {
		console.log('Object is not object');
		object = objectStorage[object];
		console.log(object);
	}
	console.log(object);
	return object;
}

var map;

function initialize_map() {

	var mapOptions = {
		zoom : 10,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

	// Try HTML5 geolocation

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = new google.maps.LatLng(position.coords.latitude,
			position.coords.longitude);
			map.setCenter(pos);
		}, function() {
			handleNoGeolocation(true);
		});

	} else {
		alert("Browser not supported");
	}
}
