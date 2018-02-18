<sec:authorize access="hasAuthority('employee')">
<br/><br/><br/>
<center>
<h2>Εδώ είναι όλα τα αυτοκίνητα προς συλλογή.</h2>

<table border=1 >
	<tr>
	
		<td>  ID  </td>
		<td>  LON  </td>
		<td>  LAT  </td>
		<td>  STATUS  </td>
		<td>  LICENCE PLATE  </td>
		<td>  OWNER  </td>
		<td>  Action  </td>
	</tr>
<c:forEach var="del" items="${carsForTransit}">

		<tr>
				<td>${del.id}</td>
				<td>${del.lon }</td>
				<td>${del.lat}</td>
		 		<td>${del.status }</td>
		 		<td>${del.process.car.licencePlate}</td>
		 		<td>${del.process.car.customer.fullname}</td>
		 		<form:form action="forTransit" method="POST">
		 				<input type="hidden" name="plate" value="${del.process.car.licencePlate}"/>
		 			<td><input type="submit" value="Arrive"/></td>
		 		</form:form>
		</tr>
	</c:forEach>
</table>
</center>
</sec:authorize>