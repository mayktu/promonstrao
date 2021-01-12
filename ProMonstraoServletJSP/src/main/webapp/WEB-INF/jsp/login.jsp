<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="bodylogin">
<fmt:bundle basename="message">
  <t:base>
    <jsp:attribute name="title">
      <fmt:message key="title"/>
    </jsp:attribute>
    <jsp:body>
      <c:if test="${mensagens.hasErros()}">
        <div id="erro">
          <ul>
            <c:forEach var="erro" items="${mensagens.erros}">
              <li> ${erro} </li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

  <div class="login-page">
    <div class="form">
      <form class="login-form" method="post" >
        <div>
          <input type="text" id="email" name="email" placeholder="Email" value="${param.email}"/>
        </div>
        <div>
          <input type="password" id="password" name="password" placeholder="Senha"/>
        </div>
        <div>
          <input type="submit" class="inputlogin" name="bOK" value="<fmt:message key="login.submit"/>"/>
        </div>
      </form>
      </div>
    </div>
    </jsp:body>
  </t:base>

</fmt:bundle>
</div>
