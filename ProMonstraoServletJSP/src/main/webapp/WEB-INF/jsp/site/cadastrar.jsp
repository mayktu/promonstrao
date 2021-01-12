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
      <h2>Cadastrar Novo Site</h2>

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
          <label for="email"><fmt:message key="site.create.email"/>: </label>
          <input type="email" id="email" name="email" placeholder="fulano@ciclano.com" value="${site.email}"/>
        </div>
        <div>
          <label for="password"><fmt:message key="site.create.password"/>: </label>
          <input type="password" id="password" name="senha" placeholder="senha" value="${site.password}"/>
        </div>
        <div>
          <label for="name"><fmt:message key="site.create.name"/>: </label>
          <input type="text" id="name" name="nome" placeholder="Fulano" value="${site.nome}"/>
        </div>
        <div>
          <label for="endereco"><fmt:message key="site.create.address"/>: </label>
          <input type="text" id="endereco" name="endereco" placeholder="Rua exemplo, XXX -  Bairro OLOLO" value="${site.endereco}"/>
        </div>
        <div>
          <label for="telefone"><fmt:message key="site.create.phone"/>: </label>
          <input type="tel" id="telefone" name="telefone" placeholder="12345-6788" value="${site.telefone}"/>
        </div>
        <div>
          <input type="submit" name="submit" value="<fmt:message key="site.create.submit"/>"/>
        </div>
      </form>
    </jsp:body>
  </t:base>
</fmt:bundle>
