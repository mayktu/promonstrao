<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="message">
  <t:base>
    <jsp:attribute name="title">
      <fmt:message key="title" />
    </jsp:attribute>
    <jsp:body>
      <h1><fmt:message key="site.header"/></h1>

      <jsp:useBean id="usuarioLogado" scope="session" type="br.ufscar.dc.dsw.domain.Usuario"/>
      <fmt:message key="home.greeting"/>,
      <jsp:getProperty name="usuarioLogado" property="email"/>
      <br>
      <a href="${pageContext.request.contextPath}/logout"><fmt:message key="auth.logout"/></a> 
      <br><br>
      <a href="${pageContext.request.contextPath}/">Voltar</a>
      <div>
        <a href="${pageContext.request.contextPath}/site/cadastrar"><fmt:message key="site.create_new"/></a>
      </div>
      <br>
      <table class="styled-table">
        <thead>
        <tr>
          <th><fmt:message key="site.list.name"/></th>
          <th><fmt:message key="site.list.email"/></th>
          <th><fmt:message key="site.list.address"/></th>
          <th><fmt:message key="site.list.phone"/></th>
          <th><fmt:message key="site.list.actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="site" items="${requestScope.listaSites}">
          <tr>
            <td>${site.nome}</td>
            <td>${site.email}</td>
            <td>${site.endereco}</td>
            <td>${site.telefone}</td>
            <td>
              <a href="${pageContext.request.contextPath}/site/editar?id=${site.id}">
                <fmt:message key="site.list.edit_action"/>
              </a>
              <span>   </span>
              <a href="${pageContext.request.contextPath}/site/deletar?id=${site.id}">
                <fmt:message key="site.list.delete_action"/>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </jsp:body>
  </t:base>
</fmt:bundle>
