package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ActorAllDTO;
import dto.ActorSingleDTO;
import dto.ActorUpdateDTO;
import service.ActorService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/task2/actor/*")
public class ActorServlet extends HttpServlet {
    private final ObjectMapper objectMapper;

    public ActorServlet() {
        this.objectMapper = new ObjectMapper();
    }

    private final transient ActorService actorService = ActorService.getInstance();

    private static void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private static String getJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = req.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseString = "all actors: ";
        try {
            String[] pathPart = request.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<ActorAllDTO> actorDtoList = actorService.showActors();
                response.setStatus(HttpServletResponse.SC_OK);
                responseString += objectMapper.writeValueAsString(actorDtoList);
            } else {
                Integer actorId = Integer.parseInt(pathPart[1]);
                ActorSingleDTO actor = actorService.showActor(actorId);
                response.setStatus(HttpServletResponse.SC_OK);
                responseString = objectMapper.writeValueAsString(actor);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(responseString);
        printWriter.write("testq");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setJsonHeader(response);
        String json = getJson(request);

        String responseString = null;
        Optional<ActorSingleDTO> actorResponse;
        try {
            actorResponse = Optional.ofNullable(objectMapper.readValue(json, ActorSingleDTO.class));
            ActorSingleDTO actorDTO = actorResponse.orElseThrow(IllegalArgumentException::new);
            responseString = objectMapper.writeValueAsString(actorService.saveActor(actorDTO));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseString = "Incorrect actor Object.";
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(responseString);
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setJsonHeader(response);
        String json = getJson(request);

        String responseString = "";
        Optional<ActorUpdateDTO> actorResponse;
        try {
            String[] pathPart = request.getPathInfo().split("/");
            Integer actorId = Integer.parseInt(pathPart[1]);
            actorResponse = Optional.ofNullable(objectMapper.readValue(json, ActorUpdateDTO.class));
            ActorUpdateDTO actorUpdateDto = actorResponse.orElseThrow(IllegalArgumentException::new);
            actorService.update(actorUpdateDto, actorId);
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseString = e.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseString = "Incorrect actor Object.";
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(responseString);
        printWriter.flush();
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String responseString = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            Integer actorId = Integer.parseInt(pathPart[1]);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            responseString = actorService.delete(actorId);
        } catch (RuntimeException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseString = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseString = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseString);
        printWriter.flush();
    }
}
