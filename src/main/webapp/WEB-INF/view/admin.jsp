<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%
List<String> statistics = (List<String>) request.getAttribute("statistics");
%>

<!DOCTYPE html>
<html>
<head>
 <title>Admin</title>
</head>
<body>
 <h1>Admin</h1>
 <p>Temporary jsp. Bigger and better coming soon!</p>
 <div id-"stats">
     <ul>
  <%
        for (String stat : statistics) {
      %>
      <li><%= stat %></li>
      <%
     }
      %>
     </ul>
 </div>
</body>
</html>
