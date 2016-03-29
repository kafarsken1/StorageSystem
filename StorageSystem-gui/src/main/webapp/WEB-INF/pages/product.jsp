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
<title>Products</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/storagesystem.css"
	rel="stylesheet">

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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Storage
					system</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/customer">Customers</a></li>
					<li><a href="${pageContext.request.contextPath}/order">Orders</a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/product">Products</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a
						href="${pageContext.request.contextPath}/init">Init database</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">

		<h1>Products</h1>

		<table class="table">
			<tr>
				<th>Type</th>
				<th>Name</th>
				<th>Quantity</th>
				<th>Pallets</th>
				<th>Boxes</th>
				<th>Notice</th>
				<th>Shelf space</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${products}" var="p">
				<tr>
					<td>${p.type}</td>
					<td>${p.name}</td>
					<td>${p.quantity}</td>
					<td>${p.pallets}</td>
					<td>${p.boxes}</td>
					<td>${p.notice}</td>
					<td>${p.shelfSpace}</td>
					<td><a
						href="${pageContext.request.contextPath}/product/${p.id}/delete">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	
	<div class="container" id="wrapper">
		<form name="myForm" action="#">  
	    <input class="btn btn-primary" type="button" value="Add product" name="myButton"   
	    onmousedown ="toggleOverlay('specialBox')" />  
	    </form> 
	</div>
	
	<!-- Start Overlay -->
	<div class="container" id="overlay"></div>
	<!-- End Overlay -->
	<!-- Start Special Centered Box -->
	<div id="specialBox">
	  <h1>Add product</h1> 
	  <form name="input"
			action="${pageContext.request.contextPath}/product/new" method="post">
			<table class="table">
				<tr>
					<td>Name:</td>
					<td><input class="form-control" type="text" name="name"
						placeholder="Enter product name" autofocus /></td>
				</tr>
				<tr>
					<td>Type:</td>
					<td><input class="form-control" type="text" name="type"
						placeholder="Enter product type" autofocus /></td>
				</tr>
				<tr>
					<td>Notice:</td>
					<td><input class="form-control" type="text" name="notice"
						placeholder="Enter notice" autofocus /></td>
				</tr>
				<tr>
					<td>Shelf space:</td>
					<td><input class="form-control" type="text" name="shelfSpace"
						placeholder="Enter shelf space" autofocus /></td>
				</tr>
				<tr>
					<td>Quantity:</td>
					<td><input class="form-control" type="text" name="quantity"
						placeholder="Enter quantity" autofocus /></td>
				</tr>
				<tr>
					<td>Boxes:</td>
					<td><input class="form-control" type="text" name="boxes"
						placeholder="Enter boxes" autofocus /></td>
				</tr>
				<tr>
					<td>Pallets:</td>
					<td><input class="form-control" type="text" name="pallets"
						placeholder="Enter pallets" autofocus /></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-success" type="submit" value="Save">
					<input class="btn btn-danger" type="button" value="Cancel" onmousedown="toggleOverlay('specialBox')"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- End Special Centered Box -->


	<!-- Bootstrap core JavaScript
    ================================================== -->

	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="js/storagesystem.js"></script>

</body>
</html>