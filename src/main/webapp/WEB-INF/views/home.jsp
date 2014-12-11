<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Print number as English word, Roman numeral 
</h1>


<form:form modelAttribute="inputField" method="POST"  id="number-form">
<p>Enter number: <form:input path="numberValue"/>
</p>
<p><input type="submit" name="english" value="Print number as English word"/></p>
<p><input type="submit" name="roman" value="Print number as Roman numeral"/></p>
</form:form>

<c:choose>
  <c:when test="${errorMsg!=null}">
    <p style="color:red"><strong>${errorMsg}</strong></p>
  </c:when>
  <c:when test="${returnValue!=null}">
    <p>Entered number: <strong>${numberValue}</strong></p>
    <p>${returnLabel}: <strong>${returnValue}</strong></p>
  </c:when>
  <c:otherwise>
	<p style="color:red"><strong>Please enter number between 1 and 3999.</strong></p>
  </c:otherwise>
</c:choose>
</body>
</html>
