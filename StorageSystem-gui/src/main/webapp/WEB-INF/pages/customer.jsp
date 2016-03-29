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
<title>Customers</title>

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
					<li class="active"><a
						href="${pageContext.request.contextPath}/customer">Customers</a></li>
					<li><a href="${pageContext.request.contextPath}/order">Orders</a></li>
					<li><a href="${pageContext.request.contextPath}/product">Products</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a
						href="${pageContext.request.contextPath}/init">Init database</a></li>
				</ul>


			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	s
	<div class="container" id="wrapper1">

		<h1>Customers</h1>
		<p class="lead text-danger">${message}</p>

		<form name="input"
			action="" method="post">
			<table class="table">
				<tr>
					<td>Search:</td>
					<td><input class="form-control" type="text" name="name"
						placeholder="Enter customer name" autofocus /></td>
				</tr>
			</table>
		</form>

		<table class="table" onMouseOut="highlightTableRowVersionA(0);">
			<tr>
				<th>Name</th>
				<th>Phone</th>
				<th>Address</th>
				<th>Order(s)</th>
				<th>Add order</th>
				<th>Edit customer</th>
				<th>Delete customer</th>
			</tr>

			<c:forEach items="${customers}" var="s">
				<tr onMouseOver="highlightTableRowVersionA(this, '#8888FF');" onClick="toggleOverlayID('customerView')">
					<td>${s.name}</td>
					<td>${s.phone}</td>
					<td>${s.address}</td>
					<td><c:forEach items="${s.orders}" var="sc">
                            ${sc.orderNo} 
                            <a
								href="${pageContext.request.contextPath}/customer/${s.id}/removeorder/${sc.id}">
								<img
								src="${pageContext.request.contextPath}/images/Button-Delete-icon.png">
							</a>
						</c:forEach></td>
					<td>
						<input type="submit" class="btn btn-default" value="Add">				
					</td>
					<td>
						<input class="btn btn-default" type="submit" value="Edit">

					</td>
					<td><a
						href="${pageContext.request.contextPath}/customer/${s.id}/delete">Delete</a></td>
				</tr>

			</c:forEach>
		</table>

	</div>

	<div class="container" id="wrapper">
		<form name="myForm" action="#">  
	    <input class="btn btn-primary" type="button" value="Add customer" name="myButton"   
	    onmousedown ="toggleOverlay('specialBox')" />  
	    </form> 
	</div>
	
	<!-- Start Overlay -->
	<div class="container" id="overlay"></div>
	<!-- End Overlay -->
	<!-- Start Special Centered Box -->
	<div id="specialBox">
	  <h1>Add customer</h1> 
	  <form name="input"
			action="${pageContext.request.contextPath}/customer/new" method="post">
			<table class="table">
				<tr>
					<td>Name:</td>
					<td><input class="form-control" type="text" name="name"
						placeholder="Enter customer name" autofocus /></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td><input class="form-control" type="text" name="phone"
						placeholder="Enter phone number" autofocus /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input class="form-control" type="text" name="email"
						placeholder="Enter email" autofocus /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input class="form-control" type="text" name="address"
						placeholder="Enter address" autofocus /></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-success" type="submit" value="Save" >
					<input class="btn btn-danger" type="reset" value="Cancel" onClick="toggleOverlay('specialBox')"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="customerView">
	  <h1>Customer</h1> 
	  <form name="input1"
			action="${pageContext.request.contextPath}/customer/new" method="post">
			<table class="table">
				<tr>
					<td>Name:</td>
					<td><input class="form-control" type="text" name="name"
						placeholder="Enter customer name" autofocus /></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td><input class="form-control" type="text" name="phone"
						placeholder="Enter phone number" autofocus /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input class="form-control" type="text" name="email"
						placeholder="Enter email" autofocus /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input class="form-control" type="text" name="address"
						placeholder="Enter address" autofocus /></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-success" type="submit" value="Save" >
					<input class="btn btn-danger" type="reset" value="Cancel" onClick="toggleOverlay('customerView')"></td>
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
