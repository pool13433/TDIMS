<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">เปลี่ยนรหัสผ่าน</div>
        <div class="panel-body">
            <c:if test="${!empty status}">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Warning!</strong> ${status}
                </div>
            </c:if>
            <form class="form-horizontal" action="${context}/ChangePasswordServlet" method="post">
                <div class="form-group">
                    <label for="passwordOld" class="col-lg-2 control-label">Password Old</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control col-lg-2" id="oldPassword" name="passwordOld" placeholder="oldPassword" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordNew" class="col-lg-2 control-label">Password New</label>
                    <div class="col-sm-3">
                        <input type="password" class="form-control" id="password" name="passwordNew" placeholder="Password New" required>
                    </div>
                     <label for="confirmPassword" class="col-lg-2 control-label">Confirm Password New</label>
                    <div class="col-sm-3">
                        <input type="password" class="form-control" id="confirmPasswordNew" name="confirmPasswordNew" placeholder="Confirm Password New" required>
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
<jsp:include page="../include/inc_footer.jsp"/>
