<%@ page import="beans.Employee" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/EmployeeServlet">Sistema HR</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/EmployeeServlet">Empleados</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/JobServlet">Trabajos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Departamentos</a>
                </li>
                <%if (session.getAttribute("usuario") == null) {%>
                <li class="nav-item">
                    <a class="nav-link" style="color: #0d6efd"
                       href="<%=request.getContextPath()%>/LoginServlet">(Login)</a>
                </li>
                <%
                } else {
                    Employee usuario = (Employee) session.getAttribute("usuario");
                %>
                <li class="nav-item">
                    <a class="nav-link"
                       style="color: darkred;">| <%=usuario.getFirstName() + " " + usuario.getLastName()%> |
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: #0d6efd"
                       href="<%=request.getContextPath()%>/LoginServlet?accion=logout">(Logout)</a>
                </li>
                <%}%>
            </ul>
            <div class="d-flex">
                Created by José García
            </div>
        </div>
    </div>
</nav>