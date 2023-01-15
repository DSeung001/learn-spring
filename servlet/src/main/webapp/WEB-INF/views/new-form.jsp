<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    상대 경로 사용
    /로 시작이 아니기에 상대 경로로 탐색
    /save이면 localhost/save

    http://localhost:8080/front-controller/v1/members/new-form
    => http://localhost:8080/front-controller/v1/members/save
--%>

<form action="save" method="post">
    username: <input type="text" name="username"/>
    age:      <input type="text" name="age"/>
    <button type="submit">전송</button>
</form>
</body>
</html>