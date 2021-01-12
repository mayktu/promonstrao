<%@tag pageEncoding="UTF-8" %>
<%@tag isELIgnored="false" %>
<%@attribute name="title" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>
    <jsp:invoke fragment="title"/>
  </title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:doBody/>
</body>
</html>
