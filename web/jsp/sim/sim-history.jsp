<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">ค้นหา Sim History Log</div>
        <div class="panel-body">
            <form id="searchHistorySim" action="${context}/SimHistoryServlet"   method="get" class="form-horizontal">   
                <input type="hidden" id="menu" name="menu" value="searching"/>
                <div class="form-group">
                    <label for="mobileNo" class="col-sm-2 control-label">MobileNo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" value="${mobileNo}" id="mobileNo" name="mobileNo" placeholder="mobileNo">
                    </div>                    
                </div>
                <div class="form-group"> 
                    <label for="date_from" class="col-sm-2 control-label">Date-Form</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control datepicker"  value="${date_from}"  id="date_from" name="date_from" placeholder="date_from" >
                    </div>
                    <label for="date_to" class="col-sm-1 control-label">Date-To</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control datepicker"  value="${date_to}"  id="date_to" name="date_to" placeholder="date_to" >
                    </div>
                    <div class="col-sm-2"><button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-search"></i> Search</button>          </div>
                </div>
                    
                <table class="table table-bordered table-striped">
                    <div id="msgBox" class="alert alert-warning" hidden="">
                        <strong>Warning! </strong><text id="msg" name="msg" value=""></text>
                    </div>
                    <thead>                    
                        <tr>
                            <th>MOBILE</th>   
                            <th>SYSTEM</th>
                            <th>ENV</th>
                            <th>SITE</th>
                            <th>STATUS</th>
                            <th>ASSIGN_TEAM</th>                  
                            <th>PROJECT</th>                            
                            <th>REMARK</th>    
                            <th>CREATE_DATE</th>
                            <th>CREATE_BY</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="his" items="${simHistoryList}" >                           
                            <tr>
                                <td>${his.mobileNo}</td>
                                <td>${his.system}</td>
                                <td>${his.enviroment}</td>
                                <td>${his.site}</td>
                                <td>${his.status}</td>
                                <td>${his.teamId}</td>
                                <td>${his.projectId}</td>                                   
                                <td>${his.remark}</td>
                                <td>${his.createDate}</td>
                                <td>${his.createBy}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${simHistoryList.isEmpty()}">
                            <tr>                    
                                <td colspan="15"><div class="alert"><span style="padding: 40%">ไม่พบข้อมูลที่ค้นหา</span></div> </td>
                            </tr>
                        </c:if>
                        <c:if test="${simHistoryList == null}">
                            <tr>                    
                                <td colspan="15"><div class="alert"><span style="padding: 40%">กรุณาระบุเงื่อนไขในการค้นหา</span></div> </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </form>
        </div>
    </div>        
</div>   
  
            <script type="text/javascript">
                $(document).ready(function(){
                    
                    $("#searchHistorySim").submit(function(){
                        var mobileNo = $("#mobileNo");
                        var dateFrom = $("#date_from");
                        var dateTo = $("#date_to");                        
                        
                        if("" === mobileNo.val() && "" === dateFrom.val() && "" === dateTo.val()){
                            alert('กรุณาระบุข้อมูลที่ต้องการค้นหา อย่างน้อย 1 รายการ');
                            return false;
                        }
                        
                        if("" !== dateFrom.val() && "" === dateTo.val()){
                            alert('กรุณาระบุ Date-To');
                            return false;
                        }
                        
                        if("" == dateFrom.val() && "" !== dateTo.val()){
                            alert('กรุณาระบุ Date-From');
                            return false;
                        }
                        
                        
                        
                    });
                  
                    
                });
            </script>

<jsp:include page="../include/inc_footer.jsp"/>
