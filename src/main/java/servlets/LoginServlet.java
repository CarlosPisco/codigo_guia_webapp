package servlets;

import beans.Employee;
import daos.EmployeeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion")==null?"login": request.getParameter("accion");
        switch (accion){
            case "login":
                HttpSession session = request.getSession();
                if(session.getAttribute("usuario")==null){
                    RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                    view.forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
                }

                break;
            case "logout":
                HttpSession session2 = request.getSession();
                session2.invalidate();
                response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");

        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.ingresarLogin(username, password);
        if(employee!=null){
            //Ingresa
            HttpSession session = request.getSession();
            session.setAttribute("usuario", employee);
            response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
        }else{
            //rechaza
            response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
        }
    }
}
