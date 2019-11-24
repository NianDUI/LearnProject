<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/11/24
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>查询了所有的账户信息</h3>

${list}<br>

<c:forEach items="${list}" var="account">
    ${account.name}, ${account.money}<br>
</c:forEach>

</body>
</html>
