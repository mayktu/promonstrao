<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="message">
  <t:base>
    <jsp:attribute name="title">
      <fmt:message key="title"/>
    </jsp:attribute>
    <jsp:body>
      <h2><fmt:message key="title"/></h2>

      <c:if test="${usuarioLogado != null}">
        <fmt:message key="home.greeting"/>, ${usuarioLogado.email}
        <a href="${pageContext.request.contextPath}/logout">
          <fmt:message key="auth.logout"/>
        </a>
      </c:if>
      <c:if test="${usuarioLogado == null}">
        <a href="${pageContext.request.contextPath}/login">
          <fmt:message key="auth.login"/>
        </a>
      </c:if>

      <div>
        <h1><fmt:message key="promotion.title"/></h1>
        <c:if test="${usuarioLogado.papel == \"TEATRO\"}">
          <a href="${pageContext.request.contextPath}/promocao/cadastrar">
            <fmt:message key="promotion.create-action"/>
          </a>
        </c:if>
        <table class="styled-table">
          <thead>
          <tr>
            <th><fmt:message key="promotion.table.website-name"/></th>
            <th><fmt:message key="promotion.table.theater-name"/></th>
            <th><fmt:message key="promotion.table.play-name"/></th>
            <th><fmt:message key="promotion.table.price"/></th>
            <th><fmt:message key="promotion.table.date"/></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="promocao" items="${requestScope.listaPromocao}">
            <tr>
              <td>${promocao.nomeSite}</td>
              <td>${promocao.nomeTeatro}</td>
              <td>${promocao.nome}</td>
              <td>R$ ${promocao.preco}</td>
              <td>${promocao.data}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </jsp:body>
  </t:base>
</fmt:bundle>
