<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="parkInfo">
	<div class="row">
		<div class="col-sm-4">
			<c:set var="parkCode" value="${selectedPark.parkCode}"></c:set>
			<c:url var="parkPhoto"
				value="img/parks/${parkCode.toLowerCase()}.jpg" />
			<img class="img-fluid" src="${parkPhoto}" />


		</div>
		<div class="col-sm-8">
			<h2>
				<c:out value="${selectedPark.parkName}"></c:out>
			</h2>
			<p>
				<c:out value="${selectedPark.parkDescription}"></c:out>
			</p>
			<p>
				<c:out value=" \"${selectedPark.inspirationalQuote}\" "></c:out>
			</p>
			<p>
				<c:out value=" - ${selectedPark.inspirationalQuoteSource}"></c:out>
			</p>

		</div>
	</div>
</div>
<div class="parkTable">
	<div class="table-responsive">
		<table class="table table-sm">
			<thead class="thead-dark">
				<tr>
					<th scope="col">State</th>
					<th scope="col">Acreage</th>
					<th scope="col">Elevation</th>
					<th scope="col">Trail Length</th>
					<th scope="col">Number Of Campsites</th>
					<th scope="col">Climate</th>
					<th scope="col">Year Founded</th>
					<th scope="col">Annual Visitor Count</th>
					<th scope="col">Entry Fee</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td><c:out value="${selectedPark.state}"></c:out></td>
					<td><c:out value="${selectedPark.acreage}"></c:out></td>
					<td><c:out value="${selectedPark.elevationInFeet}"></c:out></td>
					<td><c:out value="${selectedPark.milesOfTrail}"></c:out></td>
					<td><c:out value="${selectedPark.numberOfCampSites}"></c:out></td>
					<td><c:out value="${selectedPark.climate}"></c:out></td>
					<td><c:out value="${selectedPark.yearFounded}"></c:out></td>
					<td><c:out value="${selectedPark.annualVisitorCount}"></c:out></td>
					<td><c:out value="$ ${selectedPark.entryFee}"></c:out></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<c:url value="/parkDetails" var="parkDetailsURL" />

<form action="${parkDetailsURL}" method="POST">
	<input type="hidden" name="parkCode" value="${selectedPark.parkCode}">
	<input type="radio" name="isCelsius" value="false"> Fahrenheit
	<input type="radio" name="isCelsius" value="true"> Celsius <input
		type="submit" value="Submit">
</form>

<h2 style="text-align: center">Five Day Forecast</h2>
<div class="weatherCard">
	<c:forEach var="weather" items="${weatherList}" varStatus="loop">
		<div class="weatherCardItem">
		<c:choose> 
		<c:when test="${loop.index == 0}">
					<h3>Today</h3>
				</c:when>
		</c:choose>
			<c:set var="weatherForecast"
				value="${fn:replace(weather.forecast,' ' , '' )}" />
			<c:url var="weatherPhotoURL"
				value="img/weather/${weatherForecast}.png" />
			<img class="img-fluid" src="${weatherPhotoURL}" />
			<p class="text-capitalize">
				<c:out value="${weather.forecast}"></c:out>
			</p>
			<c:choose>
				<c:when test="${tempControl.isCelsius() == true}">
					<p>
						<c:set var="minusLow" value="${weather.low - 32}" />
						<c:set var="timesLow" value="${minusLow * 5}" />
						<c:set var="divideLow" value="${timesLow / 9}" />
						<fmt:formatNumber var="low" value="${divideLow}"
							maxFractionDigits="0" />
						<c:out value="Low: ${low}°C"></c:out>
					</p>
					<p>
						<c:set var="minusHigh" value="${weather.high - 32}" />
						<c:set var="timesHigh" value="${minusHigh * 5}" />
						<c:set var="divideHigh" value="${timesHigh / 9}" />
						<fmt:formatNumber var="high" value="${divideHigh}"
							maxFractionDigits="0" />
						<c:out value="High: ${high}°C"></c:out>
					</p>
				</c:when>
				<c:otherwise>
					<p>
						<c:out value="Low: ${weather.low}°F"></c:out>
					</p>
					<p>
						<c:out value="High: ${weather.high}°F"></c:out>
					</p>
				</c:otherwise>

			</c:choose>

			<c:set var="weatherForecast" value="${weather.forecast}" />
			<c:set var="weatherHigh" value="${weather.high}" />
			<c:set var="weatherLow" value="${weather.low}" />
			<c:set var="weatherDifference" value="${weatherHigh - weatherLow}" />
			<c:choose>
				<c:when test="${weatherForecast.equals(\"snow\")}">
					<p>*Pack snow shoes</p>
				</c:when>
				<c:when test="${weatherForecast.equals(\"rain\")}">
					<p>*Pack rain gear and wear waterproof shoes</p>
				</c:when>
				<c:when test="${weatherForecast.equals(\"thunderstorms\")}">
					<p>*Seek shelter and avoid hiking on exposed ridges</p>
				</c:when>
				<c:when test="${weatherForecast.equals(\"sunny\")}">
					<p>*Pack sunblock</p>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${weatherHigh > 75}">
					<p>*Bring an extra gallon of water</p>
				</c:when>
				<c:when test="${weatherLow < 20}">
					<p>*Danger of exposure to frigid temperatures</p>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${weatherDifference > 20}">
					<p>*Wear breathable layers</p>
				</c:when>
			</c:choose>
		</div>
	</c:forEach>
</div>


<c:import url="/WEB-INF/jsp/footer.jsp" />