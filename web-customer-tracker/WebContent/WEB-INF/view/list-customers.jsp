<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- the above jstl core tag is important to add  -->
<!DOCTYPE html>

<html>

<head>

<title>List Customers</title>

<link  type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css"
/>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<!-- add button for "Add Customer" -->
		<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;" class="add-button" >
		
		<div id="content">

			<!-- add html table -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>

				<!-- loop over and print customers -->
				<c:forEach var="tempCustomers" items="${customers }">

					<tr>
						<td>${tempCustomers.firstName}</td>
						<td>${tempCustomers.lastName}</td>
						<td>${tempCustomers.email}</td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>




</body>



</html>