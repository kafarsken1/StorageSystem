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
<title>Orders</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
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
					<li class="active"><a
						href="${pageContext.request.contextPath}/order">Orders</a></li>
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

	<div class="container">
		<h1>Orders</h1>

		<table class="table">
		<tr>
				<th>Order number</th>
				<th>Customer</th>
				<th>Date created</th>
				<th>Date updated</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${orders}" var="c">
				<tr>
					<td>${c.orderNo}</td>
					<td>${c.customer.name}</td>
					<td>${c.created}</td>
					<td>${c.updated}</td>
					<td><a
						href="${pageContext.request.contextPath}/order/${c.id}/delete">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="container">
		<h2>New order:</h2>
		<form name="input"
			action="${pageContext.request.contextPath}/order/new" method="post">
			<table class="table">
				<tr>
					<td>Order number:</td>
					<td><input  class="form-control" type="text" name="orderNo" placeholder="Order number"></input></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-primary" type="submit" value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->

    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    
</body>
</html>