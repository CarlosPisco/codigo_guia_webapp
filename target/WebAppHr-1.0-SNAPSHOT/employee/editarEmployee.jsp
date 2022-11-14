<%@ page import="beans.Employee" %>
<%@ page import="beans.Job" %>
<%@ page import="beans.Department" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee e = request.getAttribute("employee") == null ? null : (Employee) request.getAttribute("employee");
%>
<jsp:useBean id="listaTrabajos" type="java.util.ArrayList<beans.Job>" scope="request"></jsp:useBean>
<jsp:useBean id="listaEmpleados" type="java.util.ArrayList<beans.Employee>" scope="request"></jsp:useBean>
<jsp:useBean id="listaDepartamentos" type="java.util.ArrayList<beans.Department>" scope="request"></jsp:useBean>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"></jsp:include>
<div class="container">
    <h1><%=e == null ? "Nuevo empleado" : "Editar empleado"%>
    </h1>
    <form action="<%=request.getContextPath()%>/EmployeeServlet" method="post"
          style="width:500px; margin-left:auto; margin-right:auto">
        <%if (e != null) {%>
        <input type="text" class="form-control" name="idEmployee" value="<%=e.getEmployeeId()%>" hidden>
        <%}%>
        <div class="mb-3">
            <label class="form-label">Nombres</label>
            <input type="text" class="form-control" name="nombres" value="<%=e==null?"":e.getFirstName()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Apellidos</label>
            <input type="text" class="form-control" name="apellidos" value="<%=e==null?"":e.getLastName()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Correo</label>
            <input type="email" class="form-control" name="correo" value="<%=e==null?"":e.getEmail()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Telefono</label>
            <input type="text" class="form-control" name="telefono" value="<%=e==null?"":e.getPhoneNumber()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Job</label>
            <!-- Ya no va el input-->
            <select class="form-select" aria-label="Default select example" name="jobId">
                <%for (Job job : listaTrabajos) {%>
                <option value="<%=job.getJobId()%>"
                        <%=e!=null?(e.getJob().getJobId().equals(job.getJobId())?"selected":""):""%>
                ><%=job.getJobTitle()%>
                </option>
                <%}%>>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Salary</label>
            <input type="text" class="form-control" name="salario" value="<%=e==null?"":e.getSalary()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Comision PCT</label>
            <input type="text" class="form-control" name="comision"
                   value="<%=e==null?"":(e.getCommissionPct()==null?"-":e.getCommissionPct())%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Manager</label>
            <select class="form-select" aria-label="Default select example" name="managerId">
                <option value="-" <%=e!=null?(e.getManager().getEmployeeId()==0?"selected":""):""%>>-</option>
                <%for (Employee m : listaEmpleados) {%>
                    <option value="<%=m.getEmployeeId()%>"
                            <%=e!=null?(e.getManager().getEmployeeId()==m.getEmployeeId()?"selected":""):""%>>

                        <%=m.getFirstName()+" "+m.getLastName()%>
                    </option>
                <%}%>>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Department</label>
            <select class="form-select" aria-label="Default select example" name="departmentId">
                <option value="-" <%=e!=null?(e.getDepartment().getDepartmentId()==0?"selected":""):""%>>-</option>
                <%for (Department d : listaDepartamentos) {%>
                <option value="<%=d.getDepartmentId()%>"
                        <%=e!=null?(e.getDepartment().getDepartmentId()==d.getDepartmentId()?"selected":""):""%>
                >
                    <%=d.getDepartmentName()%>
                </option>
                <%}%>>
            </select>
        </div>

<%--        <input type="text" class="form-control" value="<%=e==null?"":e.getDepartment().getDepartmentId()%>">--%>


        <a class="btn btn-secondary" href="<%=request.getContextPath()%>/EmployeeServlet">Atras</a>
        <button type="submit" class="btn btn-primary">Submit</button>

    </form>
</div>
</body>
</html>
