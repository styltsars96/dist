<sec:authorize access="hasAuthority('employee')">
<div class="ui segment">
<br/>
<br/>

	<center>
	<div class="form">
	<h3>ΠΕΛΑΤΗΣ</h3>	
	<form:form action="saveProcess" modelAttribute="newProcess" method="POST"  accept-encoding="utf-8" >
		<table>
			<tr>
				<td>ΟΝΟΜΑΤΕΠΩΝΥΜΟ</td>
				<td><form:input  path="car.customer.fullname" id="fullname"/></td> 
			</tr>
			
			<tr>
				<td>ΑΦΜ</td>
				<td><form:input path="car.customer.afm" /></td>
			</tr>
			<tr>
				<td>ΤΗΛΕΦΩΝΟ</td>
				<td><form:input path="car.customer.telephone" /></td>
			</tr>
		</table>
		<h3>ΑΥΤΟΚΙΝΗΤΟ</h3>
		<table>
			<tr>
				<td>ΜΟΝΤΕΛΟ</td>
				<td><form:input path="car.model" /></td>
			</tr>
			<tr>
				<td>ΑΡ. ΚΥΚΛΟΦΟΡΙΑΣ</td>
				<td><form:input path="car.licencePlate" /></td>
			</tr>
			<tr>
				<td>ΤΥΠΟΣ ΚΑΥΣΙΜΟΥ</td>
				<td>ΠΕΤΡΕΛΑΙΟ<form:radiobutton path="car.fuelType"
						value="Petrol" /></td>
			</tr>
			<tr>
				<td></td>
				<td>ΑΕΡΙΟ<form:radiobutton path="car.fuelType" value="Gas" /></td>
			<tr>
				<td></td>
				<td>ΒΕΝΖΙΝΗ<form:radiobutton path="car.fuelType"
						value="Gasoline" /></td>
			</tr>
			<tr>
				<td>ΕΤΟΣ ΠΡΩΤΗΣ ΚΥΚΛΟΦΟΡΙΑΣ</td>
				<td><form:input  path="car.firstRelease" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Δημιουργία"/></td>
				<td> <input type="reset" value="Ακύρωση"/> </td>
			</tr>
		</table>
	</form:form>
	</div>
	
</center>
</div>
</sec:authorize>
