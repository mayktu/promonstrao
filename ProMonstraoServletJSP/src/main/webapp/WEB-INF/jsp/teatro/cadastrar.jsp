<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="message">
  <t:base>
    <jsp:attribute name="title">
      <fmt:message key="title"/>
    </jsp:attribute>
    <jsp:body>
      <h1>Cadastrar novo teatro</h1>

      <c:if test="${erros.hasErros()}">
        <div id="erro">
          <ul>
            <c:forEach var="erro" items="${erros.erros}">
              <li> ${erro} </li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <form method="post">
        <div>
          <label for="email"><fmt:message key="theater.create.email"/>: </label>
          <input type="text" id="email" name="email" placeholder="fulano@ciclano.com" value="${teatro.email}"/>
        </div>
        <div>
          <label for="password"><fmt:message key="theater.create.password"/>: </label>
          <input type="password" id="password" name="senha"  placeholder="senha" value="${teatro.password}"/>
        </div>
        <div>
          <label for="name"><fmt:message key="theater.create.name"/></label>
          <input type="text" id="name" name="nome" placeholder="Fulano" value="${teatro.nome}"/>
        </div>
        <div>
          <label for="cnpj"><fmt:message key="theater.create.cnpj"/>: </label>
          <input type="text" id="cnpj" name="cnpj" placeholder="EX: 12.345.678/0001-99" value="${teatro.cnpj}"/>
        </div>
        <div>
          <label for="city"><fmt:message key="theater.create.city"/>: </label>
          <input type="text" id="city" name="cidade" placeholder="Ex: Uberlandia" value="${teatro.cidade}"/>
        </div>
        <div>
          <input type="submit" name="submit" value="<fmt:message key="theater.create.submit"/>"/>
        </div>
      </form>
    </jsp:body>
  </t:base>
</fmt:bundle>
