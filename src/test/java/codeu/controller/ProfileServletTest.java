/*
 * ProfileServletTest.java
 * @author Huihan Li
 */

package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ProfileServletTest {

 private ProfileServlet profileServlet;
 private HttpServletRequest mockRequest;
 private HttpServletResponse mockResponse;
 private RequestDispatcher mockRequestDispatcher;
 private UserStore mockUserStore;

 @Before
 public void setup() throws IOException {
   profileServlet = new ProfileServlet();
   mockRequest = Mockito.mock(HttpServletRequest.class);
   mockResponse = Mockito.mock(HttpServletResponse.class);
   mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
   mockUserStore = Mockito.mock(UserStore.class);
   profileServlet.setUserStore(mockUserStore);
   Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/profile.jsp"))
   .thenReturn(mockRequestDispatcher);

 }

 @Test
 public void testDoGet() throws IOException, ServletException {

   profileServlet.doGet(mockRequest, mockResponse);

   Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
 }
}
