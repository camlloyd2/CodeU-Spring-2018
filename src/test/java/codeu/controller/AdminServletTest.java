package codeu.controller;
import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.ArrayList;
public class AdminServletTest {

  private AdminServlet adminServlet;
  private HttpServletRequest mockRequest;
  private HttpServletResponse mockResponse;
  private RequestDispatcher mockRequestDispatcher;
    private ConversationStore mockConversationStore;
  private MessageStore mockMessageStore;
  private UserStore mockUserStore;

  @Before
    public void setup() throws IOException {
      adminServlet = new AdminServlet();
      mockRequest = Mockito.mock(HttpServletRequest.class);
      mockResponse = Mockito.mock(HttpServletResponse.class);
      mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
    mockConversationStore = Mockito.mock(ConversationStore.class);
    adminServlet.setConversationStore(mockConversationStore);

    mockMessageStore = Mockito.mock(MessageStore.class);
    adminServlet.setMessageStore(mockMessageStore);

    mockUserStore = Mockito.mock(UserStore.class);
    adminServlet.setUserStore(mockUserStore);
      Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/admin.jsp"))
      .thenReturn(mockRequestDispatcher);
    }

    @Test
    public void testDoGet() throws IOException, ServletException {
      adminServlet.doGet(mockRequest, mockResponse);
      List<String> fakeStats = new ArrayList<>();
      fakeStats.addAll(mockUserStore.getUserStats());
      fakeStats.addAll(mockConversationStore.getConversationStats());
      fakeStats.addAll(mockMessageStore.getMessageStats());
      Mockito.verify(mockRequest).setAttribute("statistics", fakeStats);
      Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
    }

  }
