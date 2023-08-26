<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Pagina Mercante</title>
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
        <div class="vstack gap-2 mx-auto">
            <div class="row w-50 offset-md-3 align-items-center" style="height: 100vh">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-header d-flex justify-content-center"><h1>Controlla credito</h1></div>
                            <div class="card-body d-flex justify-content-center">
                                <form action="${pageContext.request.contextPath}/CheckCreditServlet" method="GET">
                                    <label for="checkCredit">Inserisci Numero Carta:</label><br>
                                    <input type="text" id="checkCredit" name="cardNumber">
                                    <button type="submit" class="btn btn-primary" id="submitCheckCredit">Controlla</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Report Operazioni</h1></div>
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <a href="${pageContext.request.contextPath}/userTransactionsServlet" class="btn btn-primary">Genera Report</a><br>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-center"><h1>Addebito/Accredito</h1></div>
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <form action="${pageContext.request.contextPath}/UpdateBalanceServlet" method="POST">
                                    <label for="cardNumber">Inserisci Numero Carta:</label><br>
                                    <input type="text" id="cardNumber" name="cardNumber"><br>
                                    <label for="operationList">Scegli l'operazione:</label><br>
                                    <input list="operation" name="operation" id="operationList" autocomplete="off"><br>
                                    <datalist id="operation">
                                        <option value="Bonifico">
                                        <option value="Addebito">
                                    </datalist>
                                    <label for="valore">Inserire valore:</label><br>
                                    <input type="text" name="valore" id="valore" autocomplete="off"><br>
                                    <button type="submit" id="submitoperation" class="btn btn-primary">Effettua Operazione</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>
