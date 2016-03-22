<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<jsp:include page="header.jsp" />
<head>
	<title>Home</title>
	<spring:url value="/sessionController/login" var="url_LoginForm"/>
</head>
<body>


<div class="container">

      <form:form id="homePage" name="homePage" modelAttribute="sessionForm" action="${url_LoginForm}" method="post" class="form-signin">
        <h1 class="form-signin-heading" style="text-align: center;">¡BIENVENIDO A WOLFMATHS!</h1>
        <h2 class="form-signin-heading">Por favor, identifiquese</h2>
        <label for="userNameLogin" class="sr-only">Username</label>
        <form:input id="userNameLogin" name="userNameLogin" path="userNameLogin" class="form-control" placeholder="Username" required="" autofocus=""/>
        <label for="passwordLogin" class="sr-only">Password</label>
        <form:password id="passwordLogin" name="passwordLogin" path="passwordLogin" class="form-control" placeholder="Password" required=""/>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Recordar
          </label>
        </div>
        <form:button class="btn btn-lg btn-primary btn-block" type="submit">Log In</form:button>
      </form:form>

</div>
</body>
</html>
