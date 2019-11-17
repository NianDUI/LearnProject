<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2019/11/17
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>response</title>
    <script src="js/jquery.min.js"></script>
</head>
<body>

<h3>响应之返回值（几种请求转发或重定向）</h3>

<a href="user/testString">return String</a>
<br>

<a href="user/testVoid">return Void</a>
<br>

<a href="user/testModelAndView">return ModelAndView</a>
<br>

<a href="user/testForwardOrRedirect">testForwardOrRedirect（转发或重定向的方式）</a>
<br>

<button id="btn">发送ajax请求</button>
<script>
    // 页面加载，绑定点击事件
    $(function () {
        $("#btn").click(function () {
            // alert("hello btn");
            // ajax请求
            $.ajax({
                // 编写json格式，设置属性和值
                url : "user/testAjax",
                contentType : "application/json;charset=utf-8",
                data : '{"username":"呵呵","password":"123","age":30}',
                dataType : "json",
                type : "post",
                success : function(data) {
                    // data服务器端响应的json的数据，进行解析
                    alert(data);
                    alert(data.username);
                    alert(data.password);
                    alert(data.age);
                }
            });

        });
    });
</script>

</body>
</html>
