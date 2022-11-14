package servlets;

import beans.Department;
import beans.Employee;
import beans.Job;
import daos.DepartmentDao;
import daos.EmployeeDao;
import daos.JobDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet", ""})
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion") == null ? "lista" : request.getParameter("accion");
        EmployeeDao employeeDao = new EmployeeDao();
        JobDao jobDao = new JobDao();
        DepartmentDao departmentDao = new DepartmentDao();
        RequestDispatcher view;
        switch (accion) {
            case "lista":
                ArrayList<Employee> listaEmpleados = employeeDao.obtenerEmpleados();
                request.setAttribute("listaEmpleados", listaEmpleados);
                view = request.getRequestDispatcher("/employee/listaEmployees.jsp");
                view.forward(request, response);
                break;
            case "crear":


                request.setAttribute("listaTrabajos",jobDao.obtenerTrabajos());
                request.setAttribute("listaEmpleados",employeeDao.obtenerEmpleados());
                request.setAttribute("listaDepartamentos",departmentDao.obtenerDepartamentos());
                view = request.getRequestDispatcher("/employee/editarEmployee.jsp");
                view.forward(request, response);
                break;
            case "editar":
                int idEmployee = Integer.parseInt(request.getParameter("id"));
                Employee e = employeeDao.obtenerEmpleado(idEmployee);
                if (e != null) {
                    request.setAttribute("listaTrabajos",jobDao.obtenerTrabajos());
                    request.setAttribute("listaEmpleados",employeeDao.obtenerEmpleados());
                    request.setAttribute("listaDepartamentos",departmentDao.obtenerDepartamentos());
                    request.setAttribute("employee", e);
                    view = request.getRequestDispatcher("/employee/editarEmployee.jsp");
                    view.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
            case "eliminar":
                int idEmployee2 = Integer.parseInt(request.getParameter("id"));
                employeeDao.eliminarEmployee(idEmployee2);
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        HttpSession session = request.getSession();
        JobDao jobDao = new JobDao();
        DepartmentDao departmentDao = new DepartmentDao();
        RequestDispatcher view;


        String idEmployee = request.getParameter("idEmployee");

        Employee e = new Employee();
        e.setFirstName(request.getParameter("nombres"));
        e.setLastName(request.getParameter("apellidos"));
        e.setEmail(request.getParameter("correo"));
        e.setPhoneNumber(request.getParameter("telefono"));

        Job job = new Job();
        job.setJobId(request.getParameter("jobId"));
        e.setJob(job);

        e.setSalary(new BigDecimal(request.getParameter("salario")));


        Employee manager = new Employee();
        manager.setEmployeeId(Integer.parseInt(request.getParameter("managerId")));
        e.setManager(manager);

        Department department = new Department();
        department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
        e.setDepartment(department);


        try{
            e.setCommissionPct(new BigDecimal(request.getParameter("comision")));

        }catch (Exception e1){
            request.setAttribute("employee",e);
            request.setAttribute("errorCasteo","Pon un numero en comision p huevon");



            request.setAttribute("listaTrabajos",jobDao.obtenerTrabajos());
            request.setAttribute("listaEmpleados",employeeDao.obtenerEmpleados());
            request.setAttribute("listaDepartamentos",departmentDao.obtenerDepartamentos());
            view = request.getRequestDispatcher("/employee/editarEmployee.jsp");
            view.forward(request, response);
        }

        if (idEmployee != null) {
            e.setEmployeeId(Integer.parseInt(idEmployee));
            employeeDao.actualizarEmpleado(e);
            session.setAttribute("estado","actualizado");
        } else {
            employeeDao.guardarEmpleado(e);
            session.setAttribute("estado","creado");
        }

        response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
    }
}
