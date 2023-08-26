<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    <jsp:include page="../styles/styles.css"></jsp:include>
</style>
<nav class="navbar navbar-light bg-primary sticky-top">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/index.jsp">
            <img src="../assets/img/fasterlogo.png" alt="FasterCard" width="100" height="70" class="img-responsive d-inline-block align-text-top">
            <span class="text-white">FasterCard</span>
        </a>
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/RedirectServlet" class="btn btn-info btn-lg">
                Torna alla pagina personale
            </a>
        </div>
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-danger btn-lg">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </a>
        </div>
    </div>
</nav>