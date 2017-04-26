<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>

<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ User ทั้งหมด</div>
        <div class="panel-body">
            <a href="${context}/UserFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                <c:if test="${!empty message}">
                    <p>Status : ${message}</p>
                </c:if>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    
                    <th>username</th>
                    <th>password</th>
                    <th>fname</th>
                    <th>lname</th>
                    <th>gender</th>
                    <th>mobile</th>
                    <th>email</th>
                    <th>position</th>
                    <th>createdate</th>
                    <th>createby</th>
                    <th>updatedate</th>
                    <th>updateby</th>
                    <th>status</th>  
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${profileList}">
                    <tr>                    
                        <td>
                            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                <a href="${context}/UserFormServlet?profileId=${user.profileId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/UserDeleteServlet?profileId=${user.profileId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </td>
                        <td>${user.username}</td>
                       <td>${user.password}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.gender}</td>
                        <td>${user.mobile}</td>
                        <td>${user.email}</td>
                        <td>${user.position}</td>                        
                        <td>${user.createDate}</td>
                        <td>${user.createByUsername}</td>   
                         <td>${user.updateDate}</td>  
                          <td>${user.updateByUsername}</td>  
                           <td>${user.status}</td>  
                      
                </c:forEach>
            </tbody>
        </table>
    </div>
<jsp:include page="../include/inc_footer.jsp"/>
