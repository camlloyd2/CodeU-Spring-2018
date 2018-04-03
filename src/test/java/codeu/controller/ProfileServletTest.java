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
   Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/test_user");

   UUID fakeUserId = UUID.randomUUID();

   User fakeUser = new User(fakeUserId, "test_user", "test_password", Instant.now());

   Mockito.when(mockUserStore.getUser("test_user")).thenReturn(fakeUser);



   profileServlet.doGet(mockRequest, mockResponse);

   Mockito.verify(mockRequest).setAttribute("user", fakeUser);
   Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
 }

 @Test
  public void testDoGet_badConversation() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/bad_user");
    Mockito.when(mockUserStore.getUser("bad_user"))
        .thenReturn(null);

    profileServlet.doGet(mockRequest, mockResponse);

    Mockito.verify(mockResponse).sendRedirect("/register");
  }
}
