<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="message">
  <t:base>
    <jsp:attribute name="title">
      <fmt:message key="title" />
    </jsp:attribute>
    <jsp:body>
      <div>
        <h1><fmt:message key="promotion.create.title" /></h1>

        <c:if test="${erros.hasErros()}">
          <div id="erro">
            <ul>
              <c:forEach var="erro" items="${erros.erros}">
                <li> ${erro} </li>
              </c:forEach>
            </ul>
          </div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/promocao/cadastrar">
          <div>
            <label for="site">
              <fmt:message key="promotion.create.site-label" />
            </label>
            <select id="site" name="id_site">
              <option value="">--<fmt:message key="promotion.create.site-default" />--</option>
              <c:forEach items="${sites}" var="site">
                <option value="${site.id}">${site.nome}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label for="nome_peca"><fmt:message key="promotion.create.play-name-label" />: </label>
            <input type="text" id="nome_peca" name="nome" size="45" placeholder="Peça XXXXX YYYYY" required value="${promocao.nome}"/>
          </div>

          <div>
            <label for="preco_peca"><fmt:message key="promotion.create.price-label" />: </label>
            <input type="number" id="preco_peca" name="preco" size="45" placeholder="Ex: 50" required value="${promocao.preco}"/>
          </div>

          <div>
            <label for="data_peca"><fmt:message key="promotion.create.date-label" />: </label>
            <input type="date" id="data_peca" name="data" size="45" required value="${promocao.data}"/>
          </div>

          <div>
            <button name="submit"><fmt:message key="promotion.create.submit-label" /></button>
          </div>
        </form>
      </div>
    </jsp:body>
  </t:base>
</fmt:bundle>
