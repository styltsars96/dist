<sec:authorize access="hasAuthority('admin')">
<div class="ui segment">

	<h3>Assign Role to ${user}</h3>
	<c:if test="${not empty roles}">

		<form method="post"
			action="${pageContext.request.contextPath}/user/assignRole/${user.fullname}">
			<table class="ui celled striped table">
			<tr>
			<td>
			<div class="field">

				<select name="role">
					<c:forEach items="${roles}" var="role">
						<option value="${role}">${role}</option>
					</c:forEach>
				</select>
			</div>
			</td>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<td>
			<div class="field">

				<input type="submit" value="Change" />
			</div>
			</td>
			</tr>
			</table>
		</form>
	</c:if>
	
</div>
</sec:authorize>