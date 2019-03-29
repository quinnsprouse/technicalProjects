<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/header.jsp" />


<section>

	<H1>Daily Survey</H1>
	<c:url var="addsurveyUrl" value="/addsurvey" />
	<form:form action="${addsurveyUrl}" method="POST"
		modelAttribute="survey">
		<div class="form-group">

			<label for="parkCode">Favorite National Park:</label> <select
				class="form-control" name="parkCode">
				<option value="CVNP">Cuyahoga Valley National Park</option>
				<option value="ENP">Everglades National Park</option>
				<option value="GCNP">Grand Canyon National Park</option>
				<option value="GNP">Glacier National Park</option>
				<option value="GSMNP">Great Smoky Mountains National Park</option>
				<option value="GTNP">Grand Teton National Park</option>
				<option value="MRNP">Mount Rainier National Park</option>
				<option value="RMNP">Rocky Mountain National Park</option>
				<option value="YNP">Yellowstone National Park</option>
				<option value="YNP2">Yosemite National Park</option>
			</select> <br />
			<form:errors path="parkCode" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<label for="emailAddress">Your Email: </label> <input
				class="form-control" type="email" name="emailAddress"
				id="emailAddress" placeholder="name@example.com" required/> <br />
			<form:errors path="emailAddress" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<label for="state">State:</label> <select class="form-control"
				name="state">
				<option value="AL">Alabama</option>
				<option value="AK">Alaska</option>
				<option value="AZ">Arizona</option>
				<option value="AR">Arkansas</option>
				<option value="CA">California</option>
				<option value="CO">Colorado</option>
				<option value="CT">Connecticut</option>
				<option value="DE">Delaware</option>
				<option value="DC">District Of Columbia</option>
				<option value="FL">Florida</option>
				<option value="GA">Georgia</option>
				<option value="HI">Hawaii</option>
				<option value="ID">Idaho</option>
				<option value="IL">Illinois</option>
				<option value="IN">Indiana</option>
				<option value="IA">Iowa</option>
				<option value="KS">Kansas</option>
				<option value="KY">Kentucky</option>
				<option value="LA">Louisiana</option>
				<option value="ME">Maine</option>
				<option value="MD">Maryland</option>
				<option value="MA">Massachusetts</option>
				<option value="MI">Michigan</option>
				<option value="MN">Minnesota</option>
				<option value="MS">Mississippi</option>
				<option value="MO">Missouri</option>
				<option value="MT">Montana</option>
				<option value="NE">Nebraska</option>
				<option value="NV">Nevada</option>
				<option value="NH">New Hampshire</option>
				<option value="NJ">New Jersey</option>
				<option value="NM">New Mexico</option>
				<option value="NY">New York</option>
				<option value="NC">North Carolina</option>
				<option value="ND">North Dakota</option>
				<option value="OH">Ohio</option>
				<option value="OK">Oklahoma</option>
				<option value="OR">Oregon</option>
				<option value="PA">Pennsylvania</option>
				<option value="RI">Rhode Island</option>
				<option value="SC">South Carolina</option>
				<option value="SD">South Dakota</option>
				<option value="TN">Tennessee</option>
				<option value="TX">Texas</option>
				<option value="UT">Utah</option>
				<option value="VT">Vermont</option>
				<option value="VA">Virginia</option>
				<option value="WA">Washington</option>
				<option value="WV">West Virginia</option>
				<option value="WI">Wisconsin</option>
				<option value="WY">Wyoming</option>
			</select> <br />
			<form:errors path="state" cssClass="error"></form:errors>
		</div>

		<label for="activityLevel">Activity Level:</label>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				value="Inactive" checked> <label class="form-check-label"
				for="inlineRadio1">Inactive</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				value="Sedentary"> <label class="form-check-label"
				for="inlineRadio2">Sedentary</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				value="Active"> <label class="form-check-label"
				for="inlineRadio3">Active</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				value="Extremely Active"> <label class="form-check-label"
				for="inlineRadio4">Extremely Active</label>
			<form:errors path="activityLevel" cssClass="error"></form:errors>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>

	</form:form>



</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />