<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Pagina Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="styles/styles.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/md5-js-tools@1.0.2/lib/md5.min.js"></script>
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
        <div class="vstack gap-2 mx-auto">
            <div class="row w-50 offset-md-3 align-items-center">
                <div class="row spacingCards">
                    <div class="col-sm-6">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Controlla credito</h1></div>
                            <div class="card-body d-flex justify-content-center">
                                <form action="${pageContext.request.contextPath}/CheckCreditServlet" method="GET">
                                    <label for="checkCredit">Inserisci Numero Carta:</label><br>
                                    <input type="text" id="checkCredit" name="cardNumber" class="form-control form-control-lg" autocomplete="off"><br>
                                    <button type="submit" class="btn btn-primary" id="submitCheckCredit">Controlla</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Controlla Stato Carta</h1></div>
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <form action="${pageContext.request.contextPath}/CardStatusServlet" method="POST">
                                    <label for="cardNumberStatus">Inserisci Numero Carta:</label><br>
                                    <input type="text" id="cardNumberStatus" name="cardNumber" class="form-control form-control-lg" autocomplete="off"><br>
                                    <label for="statusList">Scegli l'operazione:</label><br>
                                    <input list="operation" name="operation" id="statusList" class="form-control form-control-lg" autocomplete="off"><br>
                                    <datalist id="statusOperations">
                                        <option value="Blocca">
                                        <option value="Sblocca">
                                    </datalist>
                                    <button type="submit" class="btn btn-primary" id="btn-submit">Effettua Operazione</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row spacingCards">
                    <div class="col-sm-6">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Stato Negoziante</h1></div>
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <form action="${pageContext.request.contextPath}/MerchantStatusServlet" method="POST">
                                    <label for="checkMerchant">Inserisci Email:</label><br>
                                    <input type="text" id="checkMerchant" name="email" class="form-control form-control-lg" autocomplete="off"><br>
                                    <label for="operationList">Scegli l'operazione:</label><br>
                                    <input list="operation" name="operation" id="operationList" autocomplete="off" class="form-control form-control-lg"><br>
                                    <datalist id="operation">
                                        <option value="Blocca">
                                        <option value="Sblocca">
                                    </datalist>
                                    <button type="submit" class="btn btn-primary" id="btn-submit-merchant">Effettua Operazione</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Crea Nuova Carta</h1></div>
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <form action="${pageContext.request.contextPath}/CreateCardServlet" method="POST">
                                    <label for="email">Inserire email titolare:</label><br>
                                    <input type="text" name="email" id="email" class="form-control form-control-lg" autocomplete="off"><br>
                                    <label for="valore">Inserire accredito iniziale:</label><br>
                                    <input type="text" name="valore" id="valore" class="form-control form-control-lg" autocomplete="off"><br>
                                    <button type="submit" class="btn btn-primary">Crea carta</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row spacingCards">
                    <div class="col-sm-12">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Registra Nuovo Negoziante</h1></div>
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <form action="${pageContext.request.contextPath}/RegisterServlet" onsubmit="return _validaForm()" method="POST">
                                    <input type="text" id="nome" name="nome" class="form-control form-control-lg" autocomplete="off" required />
                                    <label class="form-label" for="nome">Nome</label>
                                    <input type="text" id="cognome" name="cognome" class="form-control form-control-lg" autocomplete="off" required />
                                    <label class="form-label" for="cognome">Cognome</label>
                                    <input type="email" id="emailReg" name="email" class="form-control form-control-lg" autocomplete="off" required />
                                    <label class="form-label" for="emailReg">Email</label>
                                    <input type="password" id="passwordMain2" name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" title="Almeno 8 caratteri, una lettera minuscola, una maiuscola, un numero e un carattere speciale" class="form-control form-control-lg" autocomplete="off" required />
                                    <label class="form-label" for="passwordMain2">Password</label>
                                    <input type="hidden" id="tipoUtente" name="tipoUtente" value="1">
                                    <div class="d-flex justify-content-end pt-3">
                                        <button type="submit" id="btn-submitReg" class="btn btn-primary btn-lg ms-2">Registrati</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/View/components/footer.jsp"></jsp:include>
        <script>
            //Funzione che effettua l'hashing MD5 della password
            function _validaForm(){
                var password=document.getElementById("passwordMain2").value;
                document.getElementById("passwordMain2").value = MD5.generate(document.getElementById("passwordMain2").value);
            }
        </script>
    </body>
</html>
