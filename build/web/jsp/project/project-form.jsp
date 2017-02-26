<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Project</div>
        <div class="panel-body">
            <a href="${context}/ProjectListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/ProjectSaveServlet" method="post" class="form-horizontal" style="padding-right: 100px;">
            <div class="form-group">
                <label for="projName" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-4">
                    <input type="hidden" value="${project.projId}" id="projId" name="projId">
                    <input type="text" class="form-control" value="${project.projName}" id="projName" name="projName" placeholder="name" required>
                </div>
            </div>
            <div class="form-group">
                <label for="projDesc" class="col-sm-2 control-label">Description</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="projDesc" id="projDesc" placeholder="description">${project.projDesc}</textarea>
                </div>      
            </div>
            <div class="form-group">
                <label for="projStatus" class="col-sm-2 control-label">Status</label>
                <div class="col-sm-2">
                    <select class="form-control" id="projStatus" name="projStatus" placeholder="projStatus" required>
                        <c:forEach items="${projectStatusList}" var="status">
                            <c:choose>
                                <c:when test="${project.projStatus == status.conName}">
                                    <option value="${status.conName}" selected>${status.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status.conName}">${status.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
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
