package codeu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;


public class LogoutServletTest {

  private LogoutServlet logoutServlet;
  private HttpServletRequest mockRequest;
  private HttpServletResponse mockResponse;

  @Before
    public void setup() throws IOException {
      logoutServlet = new LogoutServlet();
      mockRequest = Mockito.mock(HttpServletRequest.class);
      mockResponse = Mockito.mock(HttpServletResponse.class);
    }

  @Test
   public void testDoGet() throws IOException, ServletException {
     HttpSession mockSession = Mockito.mock(HttpSession.class);

     logoutServlet.doGet(mockRequest, mockResponse);
     Mockito.verify(mockResponse).sendRedirect("/login");
   }
 }
