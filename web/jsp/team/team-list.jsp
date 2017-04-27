<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Team ทั้งหมด</div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3"> 
                    <a href="${context}/TeamFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                </div>
            </div>    
            <div class="">
                <c:if test="${!empty message}">
                    <p>Status : ${message}</p>
                </c:if>
            </div>
        </div>
           
        <div class="table-responsive">    
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>team name</th>
                        <th>team email</th>
                </thead>
                <tbody>
                    <c:forEach var="team" items="${teamList}">
                        <tr>                    
                            <td class="col-sm-1">
                                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                    <a href="${context}/TeamFormServlet?teamId=${team.teamId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                    <a href="${context}/TeamDeleteServlet?teamId=${team.teamId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                                </div>
                            </td>
                            <td class="col-sm-3">${team.teamName}</td>
                            <td>${team.teamEmail}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
