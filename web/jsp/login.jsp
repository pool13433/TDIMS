<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ลงชื่อเข้าใช้งานระบบ</div>
        <div class="panel-body">
            <c:if test="${!empty status}">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Warning!</strong> ${status}
                </div>
            </c:if>
            <form class="form-horizontal" action="${context}/LoginServlet" method="post">
                <div class="form-group">
                    <label for="username" class="col-lg-2 control-label">username</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control col-lg-2" id="username" name="username" placeholder="username" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-lg-2 control-label">password</label>
                    <div class="col-sm-3">
                        <input type="password" class="form-control" id="password" name="password" placeholder="password" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </div>        
            </form>
        </div>

    </div>

</div>
<jsp:include page="include/inc_footer.jsp"/>
