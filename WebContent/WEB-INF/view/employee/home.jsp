<sec:authorize access="hasAuthority('employee')">
	<div class="ui segment">
	<center><h2>ΑΡΧΙΚΗ ΥΠΑΛΛΗΛΟΥ</h2></center>
	
	<center><h3>ΟΝΟΜΑ: ${cookie.fullName.value}<br>
		ΚΑΤΑΣΤΗΜΑ: ${cookie.shopId.value}</h3></center>
	<h4>Επιλογές</h4>
	<a href="<c:url value='/employee/completedProcs' context="${pageContext.request.contextPath}"/>" >ΟΛΟΚΛΗΡΟΜΕΝΕΣ ΔΙΑΔΙΚΑΣΙΕΣ</a>
	<br>
	<a href="<c:url value='/employee/newProcess' context="${pageContext.request.contextPath}"/>" >ΔΗΜΙΟΥΡΓΙΑ ΝΕΑΣ ΔΙΑΔΙΚΑΣΙΑΣ</a>
	<br>
	<a href="<c:url value='/employee/deliveredCars' context="${pageContext.request.contextPath}"/>" >ΑΥΤΟΚΙΝΗΤΑ ΠΡΟΣ ΕΓΚΡΙΣΗ</a>
	<br>
	<a href="<c:url value='/employee/forTransit' context="${pageContext.request.contextPath}"/>"  >ΝΕΕΣ ΗΛΕΚΤΡΟΝΙΚΕΣ ΑΙΤΗΣΕΙΣ</a>
	
	</div>
</sec:authorize>