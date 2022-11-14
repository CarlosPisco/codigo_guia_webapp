<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Job" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Job> listaTrabajdos = (ArrayList<Job>) request.getAttribute("listaTrabajdos");
%>
<html>
<head>
    <title>Lista de trabajos</title>
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"></jsp:include>
<div class="container">
    <div class="d-flex justify-content-between">
        <h1>Lista de trabajos</h1>
        <a class="pt-3" href="<%=request.getContextPath()%>/JobServlet?accion=crear">crear trabajo</a>
    </div>
    <%
        if(session.getAttribute("estado")!=null){
            String estado = (String) session.getAttribute("estado");
    %>
    <div class="alert alert-success" role="alert">
        Trabajo <%=estado%> exitosamente!
    </div>
    <%  session.removeAttribute("estado");
        }%>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Job ID</th>
            <th scope="col">Job title</th>
            <th scope="col">Min Salary</th>
            <th scope="col">Max Salary</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <% int
                i
                =
                1;
            for
            (
                    Job
                            job
                    :
                    listaTrabajdos
            ) {
        %>
        <tr>
            <th scope="row"><%=i%>
            </th>
            <td><%=job
                    .
                            getJobId
                                    (
                                    )%>
            </td>
            <td><%=job
                    .
                            getJobTitle
                                    (
                                    )%>
            </td>
            <td><%=job
                    .
                            getMinSalary
                                    (
                                    )%>
            </td>
            <td><%=job
                    .
                            getMaxSalary
                                    (
                                    )%>
            </td>
            <td><a href="<%=request.getContextPath()%>/JobServlet?accion=editar&id=<%=job.getJobId()%>">Editar</a></td>
            <td><a href="<%=request.getContextPath()%>/JobServlet?accion=eliminar&id=<%=job.getJobId()%>">borrar</a>
            </td>
        </tr>
        <% i
                ++
        ;
        } %>
        </tbody>
    </table>
</div>
</body>
</html>
