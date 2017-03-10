<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">ค้นหา Sim</div>
        <div class="panel-body">
            <form id="bookingSim" action="${context}/SimSearchServlet"   method="get">   
                <input type="hidden" id="menu" name="menu" value="booking"/>
                <table class="table table-bordered table-striped">
                    <div id="msgBox" class="alert alert-warning" hidden="">
                        <strong>Warning! </strong><text id="msg" name="msg" value=""></text>
                    </div>
                    <thead>                    
                        <tr>
                            <th>#</th>
                            <th>mobile</th>                    
                            <th>imsi</th>
                            <th>serail</th>
                            <th>chargeType</th>
                            <th>regionCode</th>
                            <th>env</th>
                            <th>type</th>
                            <th>status</th>
                            <th>assign team</th>
                            <th>email contact</th>                    
                            <th>project</th>
                            <th>valid_Date</th>
                            <th>expire_Date</th>
                            <th>remark</th>               
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sim" items="${simList}">
                            <tr>                    
                                <td>
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-info">
                                            <input type="checkbox" autocomplete="off" id="simSelected" name="simSelected" value="${sim.simId}">
                                            <span class="glyphicon glyphicon-ok"></span>                                        
                                        </label>                                
                                    </div>
                                </td>
                                <td>${sim.mobileNo}</td>
                                <td>${sim.imsi}</td>
                                <td>${sim.serialNo}</td>
                                <td>${sim.chargeType}</td>                        
                                <td>${sim.regionCode}</td>
                                <td>${sim.enviroment}</td>
                                <td>${sim.usageType}</td>
                                <td>${sim.simStatus}</td>
                                <td>${sim.teamId}</td>
                                <td>${sim.emailContact}</td>
                                <td>${sim.projectId}</td>                                                
                                <td>${sim.validDate}</td>
                                <td>${sim.expireDate}</td>
                                <td>${sim.remark}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
                <div class="panel-body">
                <button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-pencil"></i> booking</button>          
            </form>
        </div>        
    </div>        
</div>   
  
            <script type="text/javascript">
                $(document).ready(function(){
                    $("#bookingSim").submit(function(){
                        var checkBox = $("#simSelected").prop('checked');
                        if(!checkBox){
                            $("#msg").text("กรุณาเลือกอย่างน้อย 1 รายการ");
                            $("#msgBox").show();
                            return false;
                        }
                    });
                });
            </script>

<jsp:include page="../include/inc_footer.jsp"/>
