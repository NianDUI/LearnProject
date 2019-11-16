<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/11/16
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用注解</title>
</head>
<body>

<h3>常用注解</h3>

<a href="anno/testRequestParam?name=哈哈">@RequestParam</a>

<h3>@RequestBody</h3>
<form action="anno/testRequestBody" method="post">
    用户的姓名：<input type="text" name="username"/></br>
    用户的年龄：<input type="text" name="age"/></br>
    <input type="submit" value="提交表单"/>
</form>

<a href="anno/testPathVariable/10">@PathVariable</a>

</body>
</html>
