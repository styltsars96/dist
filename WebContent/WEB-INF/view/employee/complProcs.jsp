 <sec:authorize access="hasAuthority('employee')">
<div class="ui segment">
	<center>
 ${processes }<h1>Αυτά είναι όλα τα αποσυρμένα αυτοκίνητα:</h1>
                      <table border= 2 >
                                <tr>
                                        <th>ΚΑΤΑΣΤΑΣΗ</th>
                                        <th>ΜΟΝΤΕΛΟ</th>
                                        <th>ΑΡ. ΚΥΚΛΟΦΟΡΙΑΣ</th>
                                        <th>ΕΤΟΣ ΠΡΩΤΗΣ ΚΥΚΛΟΦΟΡΙΑΣ</th>
                                        <th>ΙΔΙΟΚΤΗΤΗΣ</th>
                                        <th>ΠΟΣΟ ΕΠΙΒΡΑΒΕΥΣΗΣ</th>
                                        
                                </tr>
                                <c:forEach var="tempProc" items="${processes}">
                                        <tr>
                                                <td>${tempProc.status}</td>
                                                <td>${tempProc.car.model}</td>
                                                <td>${tempProc.car.licencePlate }</td>
                                                <td>${tempProc.car.firstRelease}</td>
                                                <td>${tempProc.car.customer.fullname}</td>
                                                <td>${tempProc.car.discount}</td>
                                        </tr>
                                </c:forEach>
                        </table>
   </center>
</div>
</sec:authorize>