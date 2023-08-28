<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <title>Registrazione</title>
        <link href="styles/styles.css" rel="stylesheet" />
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/md5-js-tools@1.0.2/lib/md5.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <jsp:include page="components/navbar.jsp"></jsp:include>
        <form action="${pageContext.request.contextPath}/RegisterServlet" method="POST"> <!-- la funzione validaForm inseriscila nella Servlet di registrazione -->
            <section class="h-100">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col">
                            <div class="card card-registration my-4">
                                <div class="row g-0">
                                    <div class="col-xl-6 d-none d-xl-block">
                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
                                             alt="Sample photo" class="img-fluid"
                                             style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem; width: 557px;height: 579.59px; overflow: hidden" />
                                    </div>
                                    <div class="col-xl-5">
                                        <div class="card-body p-md-6 text-black">
                                            <h3 class="mb-5 text-uppercase">Registrati!</h3>

                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                                                        <label class="form-label" for="nome">Nome</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="text" id="cognome" name="cognome" class="form-control form-control-lg" required />
                                                        <label class="form-label" for="cognome">Cognome</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="email" id="email" name="email" class="form-control form-control-lg" required />
                                                <label class="form-label" for="email">Email</label>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="password" id="password" name="password" class="form-control form-control-lg" required />
                                                        <label class="form-label" for="password">Password</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="password" id="password2" name="password2" class="form-control form-control-lg" />
                                                        <label class="form-label" for="password2">Ripeti Password</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" id="tipoUtente" name="tipoUtente" value="2">
                                            <div class="d-flex justify-content-end pt-3">
                                                <button type="submit" id="btn-submit" class="btn btn-primary btn-lg ms-2" onclick="hashPSW()">Registrati</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
        <jsp:include page="components/footer.jsp"></jsp:include>

        <script>
            //Funzione che controlla se email e password sono validi(cioè se rispettano il formato delle regex definite di seguito)
            function _validaForm(){
                let valid=true;
                //8 caratteri minimo, almeno una lettera maiuscola e una minuscola, un numero e un carattere speciale
                let passRegex=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                let emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;

                if($("#email").val().match(emailRegex)===null){
                    //$("#email").addClass('is-invalid');
                    valid=false;
                }
                let password=document.getElementById("password").value;
                let password2=document.getElementById("password2").value;
                if($("#password").val().match(passRegex)===null || password !== password2){
                    console.log("Le password non matchano o la password non rispetta la Regex")
                    valid=false;
                }

                return valid;
            }

            function hashPSW(){
                if(document.getElementById("password").value !== ""){
                document.getElementById("password").value = MD5.generate(document.getElementById("password").value);
                document.getElementById("password2").value = MD5.generate(document.getElementById("password2").value);
                }
            }

            setTimeout(function (){
                if(${successRegister =='false'}){
                swal("Errore!", "Questa email è già stata registrata", "error");
                }
            }, 500);
        </script>
    </body>
</html>
