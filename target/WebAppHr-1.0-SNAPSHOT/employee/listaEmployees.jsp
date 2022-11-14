<%@ page import="beans.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaEmpleados" type="java.util.ArrayList<beans.Employee>" scope="request"></jsp:useBean>
<html>
<head>
    <title>Lista Empleados</title>
    <meta charset="UTF-8">
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"></jsp:include>
<div class="container">
    <div class="d-flex justify-content-between">
        <h1>Lista de empleados</h1>
        <a class="pt-3" href="<%=request.getContextPath()%>/EmployeeServlet?accion=crear">Nuevo empleado</a>
    </div>

    <% if (session.getAttribute("estado")!=null){%>
        <div class="alert alert-success" role="alert">
            <%=session.getAttribute("estado")%>
        </div>

    <%
    session.removeAttribute("estado");
    }
    %>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombres</th>
            <th scope="col">Correo</th>
            <th scope="col">Telefono</th>
            <th scope="col">Fecha contrato</th>
            <th scope="col">Job</th>
            <th scope="col">Salario</th>
            <th scope="col"><Com class=""></Com> pct</th>
            <th scope="col">Manager</th>
            <th scope="col">Department</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Employee e : listaEmpleados) {
        %>
        <tr>
            <th scope="row"><%=i%>
            </th>
            <td><%=e.getFirstName() + " " + e.getLastName()%>
            </td>
            <td><%=e.getEmail()%>
            </td>
            <td><%=e.getPhoneNumber()%>
            </td>
            <td><%=e.getHireDate()%>
            </td>
            <td><%=e.getJob().getJobTitle()%>
            </td>
            <td><%=e.getSalary()%>
            </td>
            <td><%=e.getCommissionPct() == null ? "-" : e.getCommissionPct()%>
            </td>
            <%boolean tieneJefe = (e.getManager().getFirstName() == null && e.getManager().getLastName() == null) ? false : true;%>
            <td><%=tieneJefe ? e.getManager().getFirstName() + " " + e.getManager().getLastName() : "-"%>
            </td>
            <td><%=e.getDepartment().getDepartmentName() == null ? "-" : e.getDepartment().getDepartmentName()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/EmployeeServlet?accion=editar&id=<%=e.getEmployeeId()%>">Editar</a>
            </td>
            <td><a href="<%=request.getContextPath()%>/EmployeeServlet?accion=eliminar&id=<%=e.getEmployeeId()%>">Eliminar</a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
