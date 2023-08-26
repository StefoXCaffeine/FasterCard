<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Carta Creata</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="styles/styles.css" rel="stylesheet" />
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="./assets/img/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
    <!-- Google fonts-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
  </head>
  <body>
  <jsp:include page="components/navbarLogout.jsp"></jsp:include>
  <div class="vstack gap-4 align-items-center justify-content-center" style="height: 100vh">
    <div class="card" style="max-width: 400px;">
      <div class="card-header d-flex justify-content-center"><h1>Riepilogo Bilancio</h1></div>
      <div class="card-body d-flex justify-content-center">
        ${message}
      </div>
    </div>
    <div class="d-grid" style="max-width: 400px;">
      <input type="button" value="Indietro" class="btn btn-primary btn-lg" onClick="history.back()">
    </div>
  </div>
  <jsp:include page="/View/components/footer.jsp"></jsp:include>
  </body>
</html>
