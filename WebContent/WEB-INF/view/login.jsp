<div class="ui segment">
<script>
function auth(){
	var para = document.createElement("h2");
	var node = document.createTextNode("Πατήστε στο Logout αν θέλετε να συνδεθείτε ως άλλος χρήστης!");
	para.appendChild(node);
	var element = document.getElementById("auth");
	element.innerHTML = '';
	element.appendChild(para);
}
</script>
<br/>
<br/>
<br/>
<br/>
<br/>
<center><h1>ΚΑΛΩΣΟΡΙΣΑΤΕ!</h1></center>
<center>
	<div class="form">
		<form:form action="${pageContext.request.contextPath}/authUser" method="POST">
		<c:if test="${param.error != null}">
				<i>Συγνώμη! Λάθος Χρήστης ή Κωδικός Πρόσβασης!</i>
		</c:if>
		<table id="auth" >
		<tr><td><div class="field-wrap"> <label for="username"> ΨΕΥΔΩΝΥΜΟ ΧΡΗΣΤΗ <span class="req"></span></label> <input type="text" name="username" id="userid" required /></div></td></tr>
		<tr><td><div class="field-wrap"> ΚΩΔΙΚΟΣ ΠΡΟΣΒΑΣΗΣ <span class="req"></span> <input type="password" name="password" required autocomplete="off" /> </div> </td></tr>
		<tr><td><input type="submit" value="ΣΥΝΔΕΣΗ!"/><td> <td> <input type="reset" value="Ακύρωση"/> </td></tr>
		</table>
		</form:form>
		</div>
	</div>
</center>
<sec:authorize access="isAuthenticated()">
<script>
$( document ).ready(auth());
</script>
</sec:authorize>