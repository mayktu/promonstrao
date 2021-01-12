<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:base>
  <jsp:attribute name="title">
    <fmt:message key="title"/>
  </jsp:attribute>
  <jsp:body>
    <h1><fmt:message key="no-auth.title"/></h1>
    <c:if test="${mensagens.hasErros()}">
      <div id="erro">
        <ul>
          <c:forEach var="erro" items="${mensagens.erros}">
            <li> ${erro} </li>
          </c:forEach>
        </ul>
      </div>
    </c:if>
  </jsp:body>
</t:base>
