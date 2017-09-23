<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Planets</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<form action="GetPlanetData.do" method="GET">
		<c:choose>
			<c:when test="${! empty planetData}">
				<ul>
					<li>${planetData.planetName}</li>
					<li>${planetData.planetDiameter}miles</li>
					<li>${planetData.planetLengthOfDays}hours</li>
					<li>${planetData.planetDistanceFromSun}miles</li>
				</ul>
			</c:when>
			<c:otherwise>
				<p>No planet found</p>
			</c:otherwise>
		</c:choose>
	</form>
</body>
<!-- <form action="GetPlanetNext.do" method="GET">
	<button type="submit" name="button" value="previous">Previous</button>
	<button type="submit" name="button" value="next">Next</button>
</form> -->
<br></br>
<a href="GetAllPlanets.do">Return to Main Menu</a>
<br></br>

</html>

<!-- form action="GetPlanetData.do" method="GET">
		Name: <input type="text" name="planetname" /> <input type="submit"
			value="Get Planet Data" />
	</form> -->
