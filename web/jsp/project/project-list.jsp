<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Project ทั้งหมด</div>  
        <div class="panel-body">
            <div class="row">
                <!-- Alert Message -->
                <c:if test="${!empty MessageUI}">
                    <div class="alert alert-${MessageUI.cssClass} alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>${MessageUI.title}!</strong> ${MessageUI.message}
                    </div>
                    <c:remove var="MessageUI" scope="session" />
                </c:if>            
                <!-- Alert Message -->
                <div class="col-md-3"> 
                    <a href="${context}/ProjectFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                </div>
            </div>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>name</th>
                    <th>desc</th>
                    <th>status</th>      
                </tr>
            </thead>
            <tbody>
                <c:forEach var="project" items="${projectList}">
                    <tr>                    
                        <td>
                            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                <a href="${context}/ProjectFormServlet?projectId=${project.projId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/ProjectDeleteServlet?projectId=${project.projId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </td>
                        <td>${project.projName}</td>
                        <td>${project.projDesc}</td>
                        <td>${project.projStatus}</td>                    
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
<script lang="javascript">



</script>
