/*
 * ProfileServletTest.java
 * @author Huihan Li
 */

package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import codeu.model.data.Message;
import codeu.model.store.basic.MessageStore;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
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
 private HttpSession mockSession;
 private RequestDispatcher mockRequestDispatcher;
 private UserStore mockUserStore;
  private MessageStore mockMessageStore;
 //private User mockUser;


 @Before
 public void setup() throws IOException {
   profileServlet = new ProfileServlet();

   mockRequest = Mockito.mock(HttpServletRequest.class);
   mockResponse = Mockito.mock(HttpServletResponse.class);
   mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
   mockSession = Mockito.mock(HttpSession.class);
   Mockito.when(mockRequest.getSession()).thenReturn(mockSession);

   //mockUser = Mockito.mock(User.class);

   mockUserStore = Mockito.mock(UserStore.class);
   mockMessageStore = Mockito.mock(MessageStore.class);
   profileServlet.setUserStore(mockUserStore);
   profileServlet.setMessageStore(mockMessageStore);


   Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/profile.jsp"))
   .thenReturn(mockRequestDispatcher);

 }

 @Test
 public void testDoGet() throws IOException, ServletException {
   Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/test_user");
  List<Message> fakeMessages = new ArrayList<>();
   UUID fakeUserId = UUID.randomUUID();

   User fakeUser = new User(fakeUserId, "test_user", "test_password", "test_profile", Instant.now(), false);

   Mockito.when(mockUserStore.getUser("test_user")).thenReturn(fakeUser);
   Mockito.when(mockRequest.getSession().getAttribute("user")).thenReturn("test_user");
    Mockito.when(mockMessageStore.getMessagesOfUser(fakeUser.getId())).thenReturn(fakeMessages);
  Mockito.when(mockRequest.getSession().getAttribute("messages")).thenReturn(fakeMessages);

   profileServlet.doGet(mockRequest, mockResponse);

   Mockito.verify(mockRequest).setAttribute("user", fakeUser);
   Mockito.verify(mockRequest).setAttribute("messages", fakeMessages);
   Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
 }

  @Test
  public void testDoGet_UserNotLoggedIn() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/user_not_login");
    
    UUID fakeUserId = UUID.randomUUID();

   User fakeUser = new User(fakeUserId, "user_not_login", "test_password", "test_profile", Instant.now(), false);

   Mockito.when(mockUserStore.getUser("user_not_login")).thenReturn(fakeUser);
   Mockito.when(mockRequest.getSession().getAttribute("user")).thenReturn(null);

   profileServlet.doGet(mockRequest, mockResponse);

   Mockito.verify(mockResponse).sendRedirect("/login");
  }

 @Test
  public void testDoGet_badUser() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/bad_user");
    Mockito.when(mockUserStore.getUser("bad_user"))
        .thenReturn(null);

    profileServlet.doGet(mockRequest, mockResponse);

    Mockito.verify(mockResponse).sendRedirect("/login");
  }


  @Test
  public void testDoPost_InvalidUser() throws IOException, ServletException {
    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_username");
    Mockito.when(mockUserStore.getUser("test_username")).thenReturn(null);

    profileServlet.doPost(mockRequest, mockResponse);

    Mockito.verify(mockUserStore, Mockito.never()).updateUserProfile(Mockito.any(User.class), Mockito.any(String.class));

    Mockito.verify(mockResponse).sendRedirect("/login");
  }

    @Test
  public void testDoPost_StoresProfile() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/test_username");
    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_username");

    User fakeUser = new User(UUID.randomUUID(), "test_username", "password", "---", Instant.now(), false);
    Mockito.when(mockUserStore.getUser("test_username")).thenReturn(fakeUser);

    String fakeProfile = "sghow eihgo w48g92 iegw1";
    Mockito.when(mockRequest.getParameter("profile")).thenReturn(fakeProfile);

    profileServlet.doPost(mockRequest, mockResponse);


    ArgumentCaptor<String> profileArgumentCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    Mockito.verify(mockUserStore).updateUserProfile(userArgumentCaptor.capture(), profileArgumentCaptor.capture());

    Assert.assertEquals("sghow eihgo w48g92 iegw1", profileArgumentCaptor.getValue());


    Mockito.verify(mockResponse).sendRedirect("/profile/test_username");
  }

  @Test
  public void testDoPost_CleansHtmlContent() throws IOException, ServletException {
   Mockito.when(mockRequest.getRequestURI()).thenReturn("/profile/test_username");
    Mockito.when(mockSession.getAttribute("user")).thenReturn("test_username");

    User fakeUser = new User(UUID.randomUUID(), "test_username", "password", "---", Instant.now(), false);
    Mockito.when(mockUserStore.getUser("test_username")).thenReturn(fakeUser);

    String fakeProfile = "Contains <b>html</b> and <script>JavaScript</script> content.";
 
    Mockito.when(mockRequest.getParameter("profile")).thenReturn(fakeProfile);

    profileServlet.doPost(mockRequest, mockResponse);

    ArgumentCaptor<String> profileArgumentCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    Mockito.verify(mockUserStore).updateUserProfile(userArgumentCaptor.capture(), profileArgumentCaptor.capture());
    Assert.assertEquals("Contains html and  content.", profileArgumentCaptor.getValue());


    Mockito.verify(mockResponse).sendRedirect("/profile/test_username");
  }
  
}
