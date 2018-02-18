<sec:authorize access="hasAuthority('admin')">
<div class="ui segment">
<br/><br/>
<center>
<h1>THIS IS THE ADMIN HOME PAGE!</h1>
<br/>
<h3>List of Users</h3>

<table class="ui celled striped table">
		<thead>
			<th>Username</th>
			<th>Shop #</th>
			<th>Role</th>
			<th>Enabled</th>
			<th>Actions</th>
		</thead>
		<c:forEach var="tempUser" items="${users}">
			<tr>
				<td>${tempUser}</td>
				<td><c:choose>
					<c:when test="${tempUser.shopId == 0}"> -</c:when>
					<c:otherwise>${tempUser.shopId}</c:otherwise>
				</c:choose></td>
				<td>${tempUser.shopRole}</td>
				<td><c:choose>
					<c:when test="${tempUser.enabled == 0}">False</c:when>
					<c:when test="${tempUser.enabled == 1}">True</c:when>
				</c:choose></td>
				<td>
					<a class="ui button" href="<c:url value="/user/assignRole/${tempUser}"></c:url>">Change Role</a>
					<c:choose>
					<c:when test="${tempUser.enabled == 0}">
						<a class="ui button" href="<c:url value="/user/enable/${tempUser}"></c:url>">Enable</a>
					</c:when>
					<c:when test="${tempUser.enabled == 1}">
						<a class="ui button" href="<c:url value="/user/enable/${tempUser}"></c:url>">Disable</a>
					</c:when>
					</c:choose>
					<a class="ui button" href="<c:url value="/user/modify/${tempUser}"></c:url>">Change Credentials</a>
				</td>
			</tr>
		</c:forEach>
</table>
<a class="ui button" href="${pageContext.request.contextPath}/user/showAddForm" class="item"><i	class="add circle icon"></i>Add User</a>
</center>
</div>
</sec:authorize>

