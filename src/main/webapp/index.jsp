<%@ page import="Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>FasterCard</title>
        <link href="/View/styles/styles.css" rel="stylesheet" />
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
        <!-- Google fonts-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <%
            session = request.getSession(false);
            if(session.getAttribute("currentUser") != null){
        %>
        <jsp:include page="/View/components/navbarLogout.jsp" />
        <% }else{%>
        <jsp:include page="/View/components/navbar.jsp" />
        <% } %>
        <header class="masthead">
            <div class="container position-relative">
                <div class="row justify-content-center">
                    <div class="col-xl-6">
                        <div class="text-center text-white">
                            <!-- Page heading-->
                            <h1 class="mb-5">Gestisci le tue carte in maniera veloce ed efficiente con FasterCard!</h1>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Icons Grid-->
        <section class="features-icons text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                            <div class="features-icons-icon d-flex"><i class="fa-solid fa-user m-auto text-white"></i></div>
                            <h3>Titolare Carta</h3>
                            <p class="lead mb-0">Registra la tua carta per controllare lo stato attuale e le transazioni effettuate</p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                            <div class="features-icons-icon d-flex"><i class="fa-solid fa-cart-shopping m-auto text-white"></i></div>
                            <h3>Negoziante</h3>
                            <p class="lead mb-0">Effettua addebiti e bonifici sulle carte e controlla tutte le tue operazioni</p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-0 mb-lg-3">
                            <div class="features-icons-icon d-flex"><i class="fa-solid fa-terminal m-auto text-white"></i></div>
                            <h3>Admin</h3>
                            <p class="lead mb-0">Registra nuove carte modifica lo stato di quelle esistenti</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="/View/components/footer.jsp" />
        <script>
            setTimeout(function (){
                if(${successRegister =='true'}){
                    swal("L'utente è stato registrato!", "Effettua il login per continuare", "success");
                }

                if(${merchantBlock =='true'}){
                    swal("Errore!", "Il tuo profilo è stato bloccato, contatta un amministratore", "error");
                }
            }, 500);
        </script>
    </body>
</html>