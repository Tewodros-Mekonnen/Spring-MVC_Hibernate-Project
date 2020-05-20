<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- the above jstl core tag and form tag are important to add  -->


<!DOCTYPE html>

<html>

<head>

<title>List Customers</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<!-- add button for "Add Customer" when we click add customer button, the method 'showFormForAdd' mapping will run -->
		<input type="button" value="Add Customer"
			onclick="window.location.href='showFormForAdd'; return false;"
			class="add-button">

		<!-- add search field to search customers by name -->
		<form:form action="search" method="GET">
		Search Customer: <input type="text" name="theSearchName" />
			<input type="submit" value="Search" class="add-button">
		</form:form>

		<div id="content">

			<!-- add html table -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print customers -->
				<c:forEach var="tempCustomers" items="${customers}">
					<!-- the above " items="${customers} ", "customers" creates link with the Model in the controller,
					      e.x. theModel.addAttribute("customers", theCustomers);  -->

					<!-- constructing an update link with customer id -->
					<c:url var="updateLink" value="/myCustomers/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomers.id}"></c:param>
					</c:url>

					<!-- constructing a delete link with customer id -->
					<c:url var="deleteLink" value="/myCustomers/deleteCustomer">
						<c:param name="customerId" value="${tempCustomers.id}"></c:param>
					</c:url>

					<tr>
						<td>${tempCustomers.firstName}</td>
						<td>${tempCustomers.lastName}</td>
						<td>${tempCustomers.email}</td>
						<td>
							<!-- displaying the update link --> 
							<a href="${updateLink }">Update</a>
							| <!-- displaying the delete link, with a JavaScript confirmation pop up!-->
							<a href="${deleteLink }"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>

						</td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>




</body>



</html>