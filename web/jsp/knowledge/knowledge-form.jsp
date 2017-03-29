<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Knowledge</div>
        <div class="panel-body">
            <a href="${context}/KnowledgeListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/KnowledgeSaveServlet" method="post" class="form-horizontal" style="padding-right: 100px;" enctype="multipart/form-data">
            <div class="form-group">
                <label for="serverName" class="col-sm-2 control-label">serverName</label>
                <div class="col-sm-4">
                    <input type="hidden" value="${knowledge.id}" id="knlId" name="knlId">
                    <input type="text" class="form-control" value="${knowledge.serverName}" id="serverName" name="serverName" placeholder="serverName" required>
                </div>
            </div>
            <div class="form-group">
                <label for="pathFolder" class="col-sm-2 control-label">pathFolder</label>
                <div class="col-sm-10">
                    <input type="file"  id="pathFolder" name="pathFolder">
                </div>      
            </div>
            <div class="form-group">
                <label for="projStatus" class="col-sm-2 control-label">project</label>
                <div class="col-sm-2">
                    <select class="form-control" id="projectId" name="projectId" placeholder="projectId" required>
                        <c:forEach items="${projectList}" var="project">                            
                            <c:choose>
                                <c:when test="${knowledge.projectId == project.projId}">
                                    <option value="${project.projId}" selected>${project.projName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${project.projId}">${project.projName}</option>
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
