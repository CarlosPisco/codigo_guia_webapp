<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Ingreso al sistema</title>
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>

<body class="text-center">
<div class="container" style="width: 500px; margin-top: 50px">
    <form class="form-signin pt-3" method="POST" action="<%=request.getContextPath()%>/LoginServlet">
        <h1 class="h3 mb-3 font-weight-normal">Ingreso al sistema HR</h1>
        <input type="text" name="inputEmail" class="form-control mt-3" placeholder="Correo" autofocus="">
        <input type="password" name="inputPassword" class="form-control mt-1" placeholder="Password">
        <% if (request.getParameter("error") != null) { %>
        <div class="text-danger mb-2">Error en el email o contraseña</div>
        <% } %>
        <button class="btn btn-lg btn-secondary btn-block mt-3" type="submit">Sign in</button>
    </form>
</div>

</body>
</html>
