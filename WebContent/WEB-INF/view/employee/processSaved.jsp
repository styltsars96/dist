<br/>
<sec:authorize access="hasAuthority('employee')">
<center>
<h1 style="color:green">Επιτυχία</h1>
<h2>Διαδικασία για το αυτοκίνητο ${process.car.licencePlate} δημιουργήθηκε.
<br>
<br>
Λεπτομέρειες:</h2>
<h2><table border=2>

<tr><th><b>Μοντέλο</b></th><td> ${process.car.model}</td></tr>
<tr><th><b>Αρ. Κυκλοφορίας</b></th><td>${process.car.licencePlate}</td></tr>
<tr><th><b>Καύσιμα</b></th><td>${process.car.fuelType}</td></tr>
<tr><th><b>Ιδιοκτήτης</b></th><td> ${process.car.customer.fullname}</td></tr>
<tr><th><b>ΑΦΜ</b></th><td>${process.car.customer.afm}</td></tr>
</table>
</h2>
<h1><a href="<c:url value='/employee'/>" >Αρχική!</a></h1>
</center>
</sec:authorize>