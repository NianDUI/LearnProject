<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/11/17
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>indes.jsp</title>
</head>
<body>
<h3>indes.jsp</h3>
<h3>文件上传</h3>

<form action="user/fileUpload1" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
