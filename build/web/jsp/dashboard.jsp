<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="include/inc_header.jsp"/>
<div class="container">
    <h1>Dashboard</h1>
    <c:if test="${!empty status}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Information !</strong> ${status}
        </div>
    </c:if>
</div>
<jsp:include page="include/inc_footer.jsp"/>
