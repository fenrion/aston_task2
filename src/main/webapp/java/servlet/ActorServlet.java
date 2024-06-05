package servlet;

import dto.ActorDTO;
import service.ActorService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/actor")
public class ActorServlet extends HttpServlet {
    private final transient ActorService actorService=ActorService.getInstance();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        PrintWriter printWriter = response.getWriter();
        printWriter.println("doGet before TRY");
        try {
            switch (action) {
                case "/new":
                    //showNewForm(request, response);
                    break;
                case "/insert":
                    //insertUser(request, response);
                    break;
                case "/delete":
                    //deleteUser(request, response);
                    break;
                case "/edit":
                    //showEditForm(request, response);
                    break;
                case "/update":
                    //updateUser(request, response);
                    break;
                default:
                    showActors(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void showActors(HttpServletRequest request, HttpServletResponse response)  throws SQLException, IOException, ServletException {

        List<ActorDTO> listActors = actorService.showActors();
        PrintWriter pw = response.getWriter();
        for(ActorDTO actor:listActors){
            pw.println(actor.getName());
        }
    }
//request.setAttribute("listActors", listActors);
//RequestDispatcher dispatcher = request.getRequestDispatcher("actors-list.jsp");
//dispatcher.forward(request, response);
}
