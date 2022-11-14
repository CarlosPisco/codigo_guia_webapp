package servlets;

import beans.Job;
import com.mysql.cj.Session;
import daos.JobDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", value = "/JobServlet")
public class JobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion")==null?"listar":request.getParameter("accion");
        RequestDispatcher view;
        JobDao jobDao = new JobDao();
        switch (accion) {
            case "listar":
                ArrayList<Job> listaTrabajos = jobDao.obtenerTrabajos();

                request.setAttribute("listaTrabajdos", listaTrabajos);

                view = request.getRequestDispatcher("/job/listaTrabajos.jsp");
                view.forward(request, response);
                break;
            case "crear":
                HttpSession session = request.getSession();
                session.setAttribute("estado","creado");


                view = request.getRequestDispatcher("/job/nuevoTrabajo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String idTrabajo = request.getParameter("id");
                Job job = jobDao.obtenerTrabajo(idTrabajo);

                request.setAttribute("trabajo", job);

                view = request.getRequestDispatcher("/job/editarTrabajo.jsp");
                view.forward(request, response);
                break;
            case "eliminar":
                HttpSession session2 = request.getSession();
                session2.setAttribute("estado", "eliminado");

                String idTrabajo2 = request.getParameter("id");
                jobDao.eliminarTrabajo(idTrabajo2);
                response.sendRedirect(request.getContextPath()+"/JobServlet");
                break;
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion")==null?"crear":request.getParameter("accion");

        JobDao jobDao = new JobDao();
        switch (accion){
            case "crear":
                HttpSession session = request.getSession();
                session.setAttribute("estado", "creado");

                String jobId = request.getParameter("jobId");
                String jobTitle = request.getParameter("jobTitle");
                String minSalary = request.getParameter("minSalary");
                String maxSalary = request.getParameter("maxSalary");

                jobDao.crearTrabajo(jobId, jobTitle, Integer.parseInt(minSalary), Integer.parseInt(maxSalary));

                response.sendRedirect(request.getContextPath()+"/JobServlet");
                break;

            case "editar":
                HttpSession session2 = request.getSession();
                session2.setAttribute("estado", "editado");

                String jobId2 = request.getParameter("jobId");
                String jobTitle2 = request.getParameter("jobTitle");
                String minSalary2 = request.getParameter("minSalary");
                String maxSalary2 = request.getParameter("maxSalary");

                jobDao.editarTrabajo(jobId2, jobTitle2, Integer.parseInt(minSalary2), Integer.parseInt(maxSalary2));

                response.sendRedirect(request.getContextPath()+"/JobServlet");
                break;
        }
    }
}
