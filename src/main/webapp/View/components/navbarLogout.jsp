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
        <div class="dropdown">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">Menù</button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item profileitem" href="${pageContext.request.contextPath}/RedirectServlet"><i class="bi bi-person-circle"></i>Profilo</a></li>
                <li><a class="dropdown-item logoutitem" href="${pageContext.request.contextPath}/LogoutServlet"><i class="bi bi-box-arrow-left"></i>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
