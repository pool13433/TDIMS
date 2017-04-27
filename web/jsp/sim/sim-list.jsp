<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Sim ทั้งหมด</div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3"> <a href="${context}/SimFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a></div>
                <div class="col-md-9">

                    <c:import url="../include/inc_pagination.jsp"/>
                </div>
            </div>          
            <c:if test="${!empty message}">
                <p>Status : ${message}</p>
            </c:if>
        </div>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>MOBILE_NO</th>
                        <th>SERIAL_NO</th>
                        <th>IMSI</th>
                        <th>CHARGE_TYPE</th>
                        <th>REGION_CODE</th>                    
                        <th>SYSTEM</th>
                        <th>ENV</th>
                        <th>SITE</th>
                        <th>TYPE</th>
                        <th>STATUS</th>    
                        <th>OWNER</th>    
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="sim" items="${simList}">
                        <tr>                    
                            <td>
                                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                    <a href="${context}/SimFormServlet?simId=${sim.simId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                    <a href="${context}/SimDeleteServlet?simId=${sim.simId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                                </div>
                            </td>
                            <td>${sim.mobileNo}</td>
                            <td>${sim.serialNo}</td>
                            <td>${sim.imsi}</td>
                            <td>${sim.chargeType}</td>
                            <td>${sim.regionCode}</td>
                            <td>${sim.system}</td>                        
                            <td>${sim.enviroment}</td>                        
                            <td>${sim.site}</td>                  
                            <td>${sim.usageType}</td>    
                            <td>${sim.simStatus}</td>                    
                            <td>${sim.createBy}</td>     
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">                
            <div class="col-md-12">
                <c:import url="../include/inc_pagination.jsp"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
