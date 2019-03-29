<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />


<section>

	<c:forEach var="park" items="${parks}">

		<c:url value="/parkDetails" var="parkDetailsURL">
			<c:param name="parkCode" value="${park.parkCode}" />
		</c:url>

		<div class="flexParkCard">
			<div class="row">
				<div class="col-sm-4">
					<c:set var="parkCode" value="${park.parkCode}"></c:set>
					<c:url var="parkPhoto"
						value="img/parks/${parkCode.toLowerCase()}.jpg" />
					<a href="${parkDetailsURL}"><img class="img-fluid" src="${parkPhoto}" /></a>
				</div>
				<div class="col-sm-8">
					<div class="parkCard">
					<h2>
						<c:out value="${park.parkName}"></c:out>
					</h2>
					<h3>
						<c:out value="${park.state}"></c:out>
					</h3>
					<div id="summary">
       				 <p class="collapse" id="collapseSummary">
						<c:out value="${park.parkDescription}"></c:out>
					</p>
					<a class="collapsed" data-toggle="collapse" href="#collapseSummary" aria-expanded="false" aria-controls="collapseSummary"></a>
					</div>
				</div>
			</div>
			</div>
			</div>
	</c:forEach>
</section>



<c:import url="/WEB-INF/jsp/footer.jsp" />