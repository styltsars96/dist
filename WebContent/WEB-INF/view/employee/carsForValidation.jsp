<sec:authorize access="hasAuthority('employee')">
<br/>
<br/>
<center>
<h2>Here are all the delivered cars.</h2>
<table border=1>
	<tr>
	
		<td>ID</td>
		<td>LON</td>
		<td>LAT</td>
		<td>Κατάσταση</td>
		<td>Μοντέλο</td>
		<td>Αρ.Κυκλοφορίας</td>
		<td>Καύσιμο</td>
	</tr>
<c:forEach var="delcar" items="${delcars}">

		<tr>
				<td>${delcar.id}</td>
				<td>${delcar.lon }</td>
				<td>${delcar.lat}</td>
		 		<td>${delcar.status }</td>
		 		<td>${delcar.process.car.model}</td>
		 		<td>${delcar.process.car.licencePlate}</td>
		 		<td>${delcar.process.car.fuelType}</td>
		 		<td><form:form action="deliverCar" method="GET">
		 				<input type="submit" value="EDIT"/>
		 				<input type="hidden" name="plate" value="${delcar.process.car.licencePlate}"/>
		 			</form:form>
		 		</td>
		</tr>
	</c:forEach>
</table>
<br/><br/><br/>
<h3><a class="ui button" href="<c:url value='/employee/' context="${pageContext.request.contextPath}"/>"  >ΑΡΧΙΚΗ ΣΕΛΙΔΑ</a></h3>
</center>
</sec:authorize>