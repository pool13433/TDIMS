<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Configuration ทั้งหมด</div>
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
                    <a href="${context}/ConfigFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                </div>
                <div class="col-md-9">
                    <c:import url="../include/inc_pagination.jsp"/>
                </div>                
            </div>            
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>code</th>
                    <th>name</th>
                    <th>value</th>         
                </tr>
            </thead>
            <tbody>
                <c:forEach var="conf" items="${confList}">
                    <tr>                    
                        <td class="col-sm-1">
                            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                <a href="${context}/ConfigFormServlet?conId=${conf.conId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/ConfigDeleteServlet?conId=${conf.conId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </td>
                        <td class="col-sm-3">${conf.conCode}</td>
                        <td>${conf.conName}</td>
                        <td>${conf.conValue}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
