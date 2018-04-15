/*
 * AdminServlet.java
 * This code uses the request.getRequestDispatcher() function to output the
 * register.jsp file
 */

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* Servlet class resposible for admin page.
*/

public class AdminServlet extends HttpServlet {

// store classes giving access to users, conversations, and messages
 private UserStore userStore; 
  private ConversationStore conversationStore;
  private MessageStore messageStore;

 @Override
 public void init() throws ServletException {
   super.init();
  setConversationStore(ConversationStore.getInstance());
  setMessageStore(MessageStore.getInstance());
  setUserStore(UserStore.getInstance());
 }


  void setConversationStore(ConversationStore conversationStore) {
    this.conversationStore = conversationStore;
  }

  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  void setUserStore(UserStore userStore) {
   this.userStore = userStore;
 }


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
      List<String> stats = new ArrayList<>();
      stats.addAll(userStore.getUserStats());
      stats.addAll(conversationStore.getConversationStats());
      stats.addAll(messageStore.getMessageStats());
      request.setAttribute("statistics", stats);
      request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
  }
}
