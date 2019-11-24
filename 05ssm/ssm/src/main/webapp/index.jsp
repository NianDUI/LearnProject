<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/11/24
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="account/findAll">测试查询 account/findAll</a><br>


<h3>测试保存 </h3><br>
<form action="account/saveAccount" method="post">
    名称：<input type="text" name="name"><br>
    金额：<input type="text" name="money"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
