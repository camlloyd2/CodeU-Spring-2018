/*
* Servlet for logging out
*/

package codeu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Servlet class responsible for logging out */
public class LogoutServlet extends HttpServlet {

  @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws IOException, ServletException {
         HttpSession session = request.getSession();

         if (session != null) {
           session.invalidate();
         }
         
         response.sendRedirect("/login");
   }
}
