<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Team</div>
        <div class="panel-body">
            <a href="${context}/TeamListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/TeamSaveFormServlet" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="conCode" class="col-sm-2 control-label">Team Name</label>
                <div class="col-sm-5">
                    <input type="hidden" value="${team.teamId}" id="teamId" name="teamId">
                    <input type="text" class="form-control" value="${team.teamName}" id="teamName" name="teamName" placeholder="Team Name" required maxlength="150">
                </div>
            </div>
            <div class="form-group">
                <label for="conName" class="col-sm-2 control-label">Team Email</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="teamEmail" id="teamEmail" placeholder="Team Email">${team.teamEmail}</textarea>
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
