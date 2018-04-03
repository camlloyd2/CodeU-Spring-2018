<%--
  Copyright codeu-sp18-team17.

  Where users manage their personal profile and conversations

  @author Huihan Li
  @since March 31, 2018
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.store.basic.UserStore" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="/css/main.css">
  <style>
   label {
     display: inline-block;
     width: 100px;
   }
  </style>
</head>

<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } %>

    <a href="/about.jsp">About</a>
  </nav>


  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red">Error!</h2>
        <%--<h2 style="color:red"><%= request.getAttribute("error") %></h2>--%>
    <% } %>

    <% if(request.getSession().getAttribute("user") != null){ %>
      <h1><%= request.getSession().getAttribute("user") %>'s Profile Page</h1>

      <h2>About <%= request.getSession().getAttribute("user") %></h2>
      <p>
        <%
          String profile = "Hello";
          //UserStore.getInstance().getUser(request.getSession().getAttribute("user")).getProfile();
        %>
        <%= profile %>
      </p>

      <form action='/profile/<%= request.getSession().getAttribute("user") %>' method="POST">
          <div class="form-group">
            <h3>Edit your About Me (only you can see this)</h3>
          <input type="text" name="userAboutMe">
        </div>

        <button type="submit">Submit</button>
      </form>

      <hr/>
    <% } %>

    <%--

    <h1>Conversations</h1>

    <%
    List<Conversation> conversations =
      (List<Conversation>) request.getAttribute("conversations");
    if(conversations == null || conversations.isEmpty()){
    %>
      <p>Create a conversation to get started.</p>
    <%
    }
    else{
    %>
      <ul class="mdl-list">
    <%
      for(Conversation conversation : conversations){
    %>
      <li><a href="/chat/<%= conversation.getTitle() %>">
        <%= conversation.getTitle() %></a></li>
    <%
      }
    %>
      </ul>
    <%
    }
    %>
      --%>
    <hr/>
  </div>


</body>
</html>