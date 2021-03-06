<%--
  Copyright codeu-sp18-team17.

  Where users manage their personal profile and conversations

  @author Huihan Li
  @since March 31, 2018
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="codeu.model.store.basic.MessageStore" %>
<%
  User user = (User)request.getAttribute("user");
  List<Message> messages = (List<Message>)request.getAttribute("messages");
  //String currentUsername = request.getSession().getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="/css/main.css">
</head>

<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } %>

    <a href="/about.jsp">About</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a href="/logout">Logout</a>
    <% }  %>
  </nav>


  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if(request.getAttribute("user") != null){ %>
      <h1><%= user.getName() %>'s Profile Page</h1>

      <hr/>

      <h2>About <%= user.getName() %></h2>
      <p style="width: 800px; word-wrap: break-word;">
        <%
          String profile = UserStore.getInstance().getUser(user.getName()).getProfile();

          if (profile==null)
            profile = user.getName() + " has not set their \"About me\" yet.";

          System.out.println("Profile:"+ profile);
        %>
        <%= profile %>
      </p>

      <% if (request.getSession().getAttribute("user").equals(user.getName())) { %>

      <form action="/profile/<%= user.getName()%>" method="POST">
        <div class="form-group">
          <h3>Edit your <b>About Me</b> (only you can see this)</h3>

          <textarea style="height:100px;width:100%;font-family:Arial;border:1px solid #a6a6a6; background-color:white; resize:none" wrap="hard" size="1000" placeholder="1000 character limit..." maxlength="1000" type="text" name="profile"></textarea>

        </div>
        <br>
        <button type="submit">Submit</button>
      </form>
      <% } %>

      <hr/>
    <% } %>
    <h2> Recently sent messages</h2>
    <ul>
      <%
            for (Message m : messages) {
          %>
          <li><%= m.getCreationTime().toString() + " " %>  <%= m.getContent() %></li>
          <%
         }
          %>
         </ul>

  </div>


</body>
</html>
