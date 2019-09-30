
<sec:authorize access="hasAuthority('mechanic')">
	<div class="ui segment">
 <center><h2>Εδώ είναι τα αυτκίνητα προς αξιολόγηση:</h2>
 </center>
		<center>
			<table border="2">
				<tr>
					<th>ΚΑΤΑΣΤΑΣΗ ΔΙΑΔΙΚΑΣΙΑΣ</th>
					<th>ΜΟΝΤΕΛΟ</th>
					<th>ΑΡ. ΚΥΚΛΟΦΟΡΙΑΣ</th>
					<th>ΚΑΤΑΣΤΑΣΗ ΑΥΤΟΚΙΝΗΤΟΥ</th>
				</tr>
				<c:forEach var="tempProc" items="${processes}">
					<tr>
						<form:form action='update' method="POST">
							<input name="procId" value="${tempProc.id }" type="hidden" />
							<td>${tempProc.status}</td>
							<td>${tempProc.car.model}</td>
							<td>${tempProc.car.licencePlate }</td>
							<td>Καλή<input type="radio" name="carstatus" value="Καλή" />
								Μέτρια<input type="radio" name="carstatus" value="Μέτρια" />
								Κακή<input type="radio" name="carstatus" value="Κακή" />
							</td>
							<td><input type="submit" value="ΑΠΟΘΗΚΕΥΣΗ!" /></td>
						</form:form>
					</tr>
				</c:forEach>
			</table>
		</center>
	</div>
</sec:authorize>
