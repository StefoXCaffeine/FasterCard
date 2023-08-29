<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    <jsp:include page="../styles/styles.css"></jsp:include>
</style>
<nav class="navbar navbar-light bg-primary sticky-top">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/View/assets/img/fasterlogo.png" alt="FasterCard" width="100" height="70" class="img-responsive d-inline-block align-text-top">
            <span class="text-white">FasterCard</span>
        </a>
        <div class="btn-group">
            <a href="/View/registerPage.jsp" class="btn btn-round">Registrati</a>
            <button type="button" class="btn btn-round" data-bs-toggle="modal" data-bs-target="#myModal">
                Accedi
            </button>
        </div>
    </div>
</nav>
<!-- The Modal -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Benvenuto!</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                    <section class="vh-50" style="background-color: #508bfc;">
                        <div class="container py-5 h-100">
                            <div class="row d-flex justify-content-center align-items-center h-100">
                                <div class="col-9">
                                    <div class="card shadow-2-strong" style="border-radius: 1rem;">
                                        <div class="card-body p-5 text-center">

                                            <h3 class="mb-5">Effettua login</h3>

                                            <div class="form-outline mb-4">
                                                <input type="email" id="email" name="email" class="form-control form-control-lg" />
                                                <label class="form-label" for="email">Email</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="password" id="password" name="password" class="form-control form-control-lg" />
                                                <label class="form-label" for="password">Password</label>
                                            </div>
                                            <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="hashPSW()">Login</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </form>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Chiudi</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/md5-js-tools@1.0.2/lib/md5.min.js"></script>
<script>
    function hashPSW(){
        if(document.getElementById("password").value != ""){
            document.getElementById("password").value = MD5.generate(document.getElementById("password").value);
            document.getElementById("password2").value = MD5.generate(document.getElementById("password2").value);
        }
    }
</script>