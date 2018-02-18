<sec:authorize access="hasAuthority('admin')">

<div class="ui segment">

	<h3>Change User Credentials</h3>

	<form:form action="${user}/update" modelAttribute="user" method="POST" class="ui form">
		<div class="field">
			<label>Username</label> 
			<form:input path="fullname" required="true" />
		</div>
		<div class="field">
			<label>Password</label>
			<input path="password" type="password" name="password" required="true" />
		</div>
		<div class="field">
			<label>Shop #</label>
			<form:input path="shopId" type="number" name="quantity" min="1" max="5" required="true"/>
		</div>
		<button class="ui button" type="submit">Save</button>
	</form:form>

</div>
</sec:authorize>