<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.png">
<title>Location</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">


<style>
html,body {
	height: 100%;
}

#map-canvas {
	height: 50%;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
	width: 80%;
}
</style>
</head>
<body>

	<!-- Static navbar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/StorageSystem-gui/">Storage system</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="customer">Customers</a></li>
					<li><a href="order">Orders</a></li>
					<li><a href="product">Products</a></li>
				<!--  	<li class="active"><a
						href="${pageContext.request.contextPath}/">Customers</a></li>
						-->
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="init">Init database</a></li>
				</ul>


			</div>
			<!--/.nav-collapse -->
		</div>
	</div>





	<!-- Bootstrap core JavaScript
    ================================================== -->

	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/storagesystem.js"></script>


</body>
</html>
