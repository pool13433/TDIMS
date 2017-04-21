<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Module ทั้งหมด</div>
        <div class="panel-body">
            <a href="${context}/ModuleFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>name</th>
                    <th>description</th>
                    <th>team</th>
            </thead>
            <tbody>
                <c:forEach var="module" items="${moduleList}">
                    <tr>                    
                        <td class="col-sm-1">
                            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                <a href="${context}/ModuleFormServlet?moduleId=${module.id}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/ModuleDeleteServlet?moduleId=${module.id}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </td>
                        <td class="col-sm-3">${module.moduleName}</td>
                        <td>${module.moduleDesc}</td>
                        <td>${module.teamId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
