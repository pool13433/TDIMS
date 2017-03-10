<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Department</div>
        <div class="panel-body">
            <a href="${context}/DepertmentListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/DepartmentSaveServlet" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="conCode" class="col-sm-1 control-label">Name</label>
                <div class="col-sm-5">
                    <input type="hidden" value="${dep.depId}" id="depId" name="depId">
                    <input type="text" class="form-control" value="${dep.depName}" id="depName" name="depName" placeholder="Department Name" required maxlength="100">
                </div>
            </div>
            <div class="form-group">
                <label for="conName" class="col-sm-1 control-label">Description</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" value="${dep.depDesc}" id="depDesc" name="depDesc" placeholder="Department Description" required maxlength="100">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
