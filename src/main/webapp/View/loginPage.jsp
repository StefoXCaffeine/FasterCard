<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/md5-js-tools@1.0.2/lib/md5.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  </head>

  <body>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
      <label for="email">Email:</label>
      <input type="text" id="email" name="email" required><br>
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>
      <br>
      <button type="submit" id="btn-submit" onclick="hashPSW()">Login</button>
    </form>

    <script>
      function hashPSW(){
        if(document.getElementById("password").value != ""){
          document.getElementById("password").value = MD5.generate(document.getElementById("password").value);
          document.getElementById("password2").value = MD5.generate(document.getElementById("password2").value);
        }
      }
    </script>

  </body>
</html>
