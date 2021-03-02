<head>
    <jsp:directive.include
            file="/WEB-INF/jsp/prelude/include-head-meta.jspf"/>
    <title>用户注册</title>
</head>
<body>
<div class="container-lg">
    <form action=${pageContext.request.contextPath}/submit method="post">
        <table>
            <tr>
                <td>UserName：<input name="name" type="text"></td>
            </tr>
            <tr>
                <td>Password：<input name="password" type="password"></td>
            </tr>
            <tr>
                <td>email：<input name="email" type="email"></td>
            </tr>
            <tr>
                <td>phoneNumber：<input name="phoneNumber" type="text"></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
            </tr>
        </table>
    </form>
</div>
</body>