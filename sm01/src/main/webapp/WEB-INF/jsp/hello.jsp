<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2021/11/26
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" isErrorPage="false" contentType="text/html; charset=UTF-8" %>
<%--   <%@ page      contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<%--<body><h1>hello,张如孟 平胸妹${sessionScope.name}！</h1></body>--%>
<%--<body><h1>hello,张如孟 平胸妹${sessionScope.msg}！</h1></body>--%>
<body><h1>hello,张如孟 平胸妹${sessionScope.user.username}！</h1>
<div>
    <p>${CRUDmsg}</p>
    <a href="${pageContext.request.contextPath}/user/welcome.do">返回欢迎页面</a>
</div>

<div>
    <form action="${pageContext.request.contextPath}/user/upload.do" enctype="multipart/form-data" method="post">
        上传头像：<input type="file" name="image"><br>
                <input type="submit" value="上传">
    </form>
</div>

div>
<form action="${pageContext.request.contextPath}/user/upload2.do" enctype="multipart/form-data" method="post">
    上传图片1：<input type="file" name="images"><br>
    上传图片2：<input type="file" name="images"><br>
    上传图片3：<input type="file" name="images"><br>
    <input type="submit" value="上传">
</form>
</div>

<h2> 点击图片完成下载</h2>
<div>
    <a href="${pageContext.request.contextPath}/user/load.do/blossom1.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom1.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load.do/blossom2.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom2.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load.do/菊花.jpg">
        <img src="${pageContext.request.contextPath}/images/菊花.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load.do/Desert.jpg">
        <img src="${pageContext.request.contextPath}/images/Desert.jpg" width="300px">
    </a>
</div>
</body>
</html>
