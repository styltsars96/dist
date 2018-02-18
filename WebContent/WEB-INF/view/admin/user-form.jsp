<sec:authorize access="hasAuthority('admin')">

<div class="ui segment">

	<h3>Add a New User</h3>

	<form:form action="saveUser" modelAttribute="user" method="POST" class="ui form">
		<div class="field">
			<label>Username</label> 
			<form:input path="fullname" required="true" />
		</div>
		<div class="field">
			<label>Password</label>
			<input path="password" type="password" name="password" required="true" autocomplete="off" />
		</div>
		<div class="field">
			<label>Shop #</label>
			<form:input path="shopId" type="number" name="quantity" min="0" max="5"/>
		</div>
		<button class="ui button" type="submit">Save</button>
	</form:form>

</div>
</sec:authorize>