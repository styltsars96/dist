<sec:authorize access="hasAuthority('employee')">
<div class="ui segment">
<br/>
<br/>
<center>
${deliveredCar }
<h2>Λεπτομέριες για το αυτοκίνητο: ${deliveredCar.process.car.model }, ${deliveredCar.process.car.licencePlate }</h2>

<p>Συμπληρώστε τη φόρμα.</p>


<form:form action="validateCar" method="POST">
<input type="hidden" name="plate" value="${deliveredCar.process.car.licencePlate}" />

<table>

<tr>
<th>ΜΟΝΤΕΛΟ</th><td><input type="text" value="${deliveredCar.process.car.model }" name="model"/></td>
</tr>
<tr>
<th>ΚΑΥΣΙΜΟ (Δηλώθηκε ως: ${deliveredCar.process.car.fuelType})</th><td>
		<input type="radio" value="Αέριο" name="fuelType"/>Αέριο
		<input type="radio" value="Πετρέλαιο" name="fuelType"/>Πετρέλαιο
		<input type="radio" value="Βενζίνη" name="fuelType"/>Βενζίνη
</td>
<tr>
<th>ΕΤΟΣ ΠΡΩΤΗΣ ΚΥΚΛΩΦΟΡΙΑΣ</th><td><input type="number"  value="${deliveredCar.process.car.firstRelease}" name="firstRelease"/></td>
</table>
<input type="submit" value="Submit"/>

</form:form>
</center>
</div>
</sec:authorize>