/*
 * ProfileServlet.java
 * This code uses the request.getRequestDispatcher() function to output the
 * profile.jsp file 
 */

package codeu.controller;

import codeu.model.store.basic.UserStore;
import codeu.model.data.User;
import java.time.Instant;
import java.util.UUID;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet class responsible for user registration.
*/
public class ProfileServlet extends HttpServlet {

 /**
  * Store class that gives access to Users.
  */
 private UserStore userStore;
 
 /**
  * Set up state for handling registration-related requests. This method is only called when
  * running in a server, not when running in a test.
  */
 @Override
 public void init() throws ServletException {
   super.init();
   setUserStore(UserStore.getInstance());
 }
 
 /**
  * Sets the UserStore used by this servlet. This function provides a common setup method
  * for use by the test framework or the servlet's init() function.
  */
 public void setUserStore(UserStore userStore) {
   this.userStore = userStore;
 }

 @Override
 public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws IOException, ServletException {
    String requestUrl = request.getRequestURI();
    System.out.println("requestUrl:" + requestUrl);

    String userName = requestUrl.substring("/profile/".length());
    System.out.println("userName:" + userName);

    User user = userStore.getUser(userName);
    if (user == null) {
      // couldn't find user, redirect to registration page
      System.out.println("User does not exist: " + userName);
      response.sendRedirect("/register");
      return;
    }

    request.setAttribute("user", user);
    request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
  }

   
}