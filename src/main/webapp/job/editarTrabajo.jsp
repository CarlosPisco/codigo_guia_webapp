<%@ page import="beans.Job" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Job job = (Job) request.getAttribute("trabajo");
%>
<html>
<head>
    <title>Crear trabajo:</title>
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"></jsp:include>
<div class="container">
    <h1>Editar trabajo:</h1>
    <form method="POST" action="<%=request.getContextPath()%>/JobServlet?accion=editar">
        <input type="text" class="form-control" name="jobId" value="<%=job.getJobId()%>" hidden>
        <div class="mb-3">
            <label class="form-label">Job title:</label>
            <input type="text" class="form-control" name="jobTitle" value="<%=job.getJobTitle()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Min Salary:</label>
            <input type="text" class="form-control" name="minSalary" value="<%=job.getMinSalary()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Max Salary:</label>
            <input type="text" class="form-control" name="maxSalary" value="<%=job.getMaxSalary()%>">
        </div>
        <button type="submit" class="btn btn-primary">Editar</button>
    </form>
</div>
</body>
</html>
