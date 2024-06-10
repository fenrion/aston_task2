package servlet;

import dto.ActorSingleDTO;
import dto.ActorUpdateDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ActorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ActorServletTest {
    private static ActorService mockActorService;
    @InjectMocks
    private static ActorServlet actorServlet;
    private static ActorService oldInstance;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private BufferedReader mockBufferedReader;
    private static void setMock(ActorService mock) {
        try {
            Field instance = ActorService.class.getDeclaredField("instance");
            instance.setAccessible(true);
            oldInstance = (ActorService) instance.get(instance);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    static void beforeAll() {
        mockActorService = Mockito.mock(ActorService.class);
        setMock(mockActorService);
        actorServlet = new ActorServlet();
    }

    @AfterAll
    static void afterAll() throws Exception {
        Field instance = ActorService.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(instance, oldInstance);
    }

    @BeforeEach
    void setUp() throws IOException {
        Mockito.doReturn(new PrintWriter(Writer.nullWriter())).when(mockResponse).getWriter();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(mockActorService);
    }
    @Test
    void doGetAll() throws IOException, ServletException {
        Mockito.doReturn("actor/all").when(mockRequest).getPathInfo();

        actorServlet.doGet(mockRequest, mockResponse);

        Mockito.verify(mockActorService).showActors();
    }
    @Test
    void doGetById() throws IOException, RuntimeException, ServletException {
        Mockito.doReturn("actor/3").when(mockRequest).getPathInfo();

        actorServlet.doGet(mockRequest, mockResponse);

        Mockito.verify(mockActorService).showActor(Mockito.anyInt());
    }
    @Test
    void doDelete() throws IOException, RuntimeException {
        Mockito.doReturn("actor/2").when(mockRequest).getPathInfo();
        Mockito.when(mockActorService.delete(Mockito.anyInt())).thenReturn("removed!");

        actorServlet.doDelete(mockRequest, mockResponse);

        Mockito.verify(mockActorService).delete(Mockito.anyInt());
        Mockito.verify(mockResponse).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
    @Test
    void doPut() throws IOException, RuntimeException {
        String name = "New name";


        Mockito.doReturn(mockBufferedReader).when(mockRequest).getReader();
        Mockito.doReturn("{\"name\":\""+ name+ "\",\"phoneNumber\":[{\"number\":\"52151\"},{\"number\":\"52151231\"}]}",
                null
        ).when(mockBufferedReader).readLine();

        Mockito.doReturn("actor/2").when(mockRequest).getPathInfo();

        actorServlet.doPut(mockRequest, mockResponse);

        ArgumentCaptor<ActorUpdateDTO> argumentCaptor = ArgumentCaptor.forClass(ActorUpdateDTO.class);
        Mockito.verify(mockActorService).update(argumentCaptor.capture(),Mockito.anyInt());

        ActorUpdateDTO result = argumentCaptor.getValue();
        Assertions.assertEquals(name, result.getName());
    }
    @Test
    void doPost() throws IOException, ServletException {
        String expectedName = "New Name";

        Mockito.doReturn(mockBufferedReader).when(mockRequest).getReader();
        Mockito.doReturn("{\"name\":\""+expectedName+"\",\"phoneNumberDTOList\":[{\"number\":\"3775217\"}],\"movieDTOList\":[{\"name\":\"Film1\"}]}",
                null
        ).when(mockBufferedReader).readLine();

        actorServlet.doPost(mockRequest, mockResponse);

        ArgumentCaptor<ActorSingleDTO> argumentCaptor = ArgumentCaptor.forClass(ActorSingleDTO.class);
        Mockito.verify(mockActorService).saveActor(argumentCaptor.capture());

        ActorSingleDTO result = argumentCaptor.getValue();
        Assertions.assertEquals(expectedName, result.getName());
    }
}