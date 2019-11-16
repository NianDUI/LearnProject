<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/11/13
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求参数绑定讲解</title>
</head>
<body>

<h3>请求参数绑定讲解</h3>

<a href="param/testParam?username=hehe&password=123">请求参数绑定</a>

<h3>JavaBean接収数据（数据封装到Account类中）</h3>
<form action="param/saveAccount" method="post">
    姓名：<input type="text" name="username"/></br>
    密码：<input type="text" name="password"/></br>
    金额：<input type="text" name="money"/></br>
    用户的姓名：<input type="text" name="user.uname"/></br>
    用户的年龄：<input type="text" name="user.age"/></br>
    <input type="submit" value="提交表单"/>
</form>

<h3>JavaBean中list和map接収数据</h3>
<h3>（数据封装到Account类中，类中存在list和map集合）</h3>
<form action="param/saveAccount" method="post">
    姓名：<input type="text" name="username"/></br>
    密码：<input type="text" name="password"/></br>
    金额：<input type="text" name="money"/></br>
    </hr>
    用户的姓名：<input type="text" name="list[0].uname"/></br>
    用户的年龄：<input type="text" name="list[0].age"/></br>
    </hr>
    用户的姓名：<input type="text" name="map['one'].uname"/></br>
    用户的年龄：<input type="text" name="map['one'].age"/></br>

    <input type="submit" value="提交表单"/>
</form>

<h3>自定义类型转化器</h3>
<form action="param/saveUser" method="post">
    用户的姓名：<input type="text" name="uname"/></br>
    用户的年龄：<input type="text" name="age"/></br>
    用户的生日：<input type="text" name="date"/></br>
    <input type="submit" value="提交表单"/>
</form>


<a href="param/testServlet?username=hehe&password=123">获取到原生的Servlet的API</a>
</body>
</html>
