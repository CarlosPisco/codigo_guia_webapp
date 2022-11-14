<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear trabajo:</title>
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"></jsp:include>
    <div class="container">
        <h1>Crear nuevo trabajo:</h1>
        <form method="POST" action="<%=request.getContextPath()%>/JobServlet?accion=crear">
            <div class="mb-3">
                <label class="form-label">Job ID:</label>
                <input type="text" class="form-control" name="jobId">
            </div>
            <div class="mb-3">
                <label class="form-label">Job title:</label>
                <input type="text" class="form-control" name="jobTitle">
            </div>
            <div class="mb-3">
                <label class="form-label">Min Salary:</label>
                <input type="text" class="form-control" name="minSalary">
            </div>
            <div class="mb-3">
                <label class="form-label">Max Salary:</label>
                <input type="text" class="form-control" name="maxSalary">
            </div>
            <button type="submit" class="btn btn-primary">Crear</button>
        </form>
    </div>
</body>
</html>
