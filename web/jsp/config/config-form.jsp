<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Configuration</div>
        <div class="panel-body">
            <a href="${context}/ConfigListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/ConfigSaveServlet" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="conCode" class="col-sm-1 control-label">Code</label>
                <div class="col-sm-5">
                    <input type="hidden" value="${config.conId}" id="conId" name="conId">
                    <input type="text" class="form-control" value="${config.conCode}" id="conCode" name="conCode" placeholder="Configuration Code" required maxlength="100">
                </div>
            </div>
            <div class="form-group">
                <label for="conName" class="col-sm-1 control-label">Name</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" value="${config.conName}" id="conName" name="conName" placeholder="Configuration Name" required maxlength="100">
                </div>
            </div>
            <div class="form-group">
                <label for="conValue" class="col-sm-1 control-label">Value</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" value="${config.conValue}"  id="conValue" name="conValue" placeholder="Configuration Value" maxlength="255">
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
