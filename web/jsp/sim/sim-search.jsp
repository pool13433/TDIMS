<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="../include/inc_header.jsp"/>
<div id="exam" class="container" style="padding-right: 100px;">    
    <div id="2" class="panel panel-ais">        
        <div id="3" class="panel-heading">ค้นหา Sim</div>
        <div id="4" class="panel-body">
            <form id="searchSim" action="${context}/SimSearchServlet"   method="get" class="form-horizontal">   
                <input type="hidden" id="menu" name="menu" value="searching"/>
                <input type="hidden" name="offset" value="${recordCurrent}"/>
                <div class="form-group">
                    <label for="mobileNo" class="col-sm-1 control-label">MobileNo</label>
                    <div class="col-sm-3">
                        <input type="hidden" value="${sim.simId}" id="simId" name="simId">
                        <input type="text" class="form-control" value="${sim.mobileNo}" id="mobileNo" name="mobileNo" placeholder="mobileNo">
                    </div>
                    <label for="mobileNo" class="col-sm-1 control-label">Environment</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="env" name="env" placeholder="env">
                            <option value="" selected>   All environment  </option>                                                       
                            <c:forEach items="${envList}" var="e">                            
                                <c:choose>
                                    <c:when test="${env == e.conName}">
                                        <option value="${e.conName}" selected>${e.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${e.conName}">${e.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group"> 
                    <label for="system" class="col-sm-1 control-label">System</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="system" name="system" >
                            <option value="" selected>    All system  </option>
                            <c:forEach items="${systemList}" var="sys">                            
                                <c:choose>
                                    <c:when test="${system == sys.conName}">
                                        <option value="${sys.conName}" selected>${sys.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${sys.conName}">${sys.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>    
                        </select>
                    </div>
                    <label for="status" class="col-sm-1 control-label">Status</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="status" name="status" placeholder="status">                           
                            <option value="" selected>    All status</option>                                                      
                            <c:forEach items="${simStatusList}" var="s">                            
                                <c:choose>
                                    <c:when test="${status == s.conName}">
                                        <option value="${s.conName}" selected>${s.conName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${s.conName}">${s.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2"><button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-search"></i> Search</button>          </div>
                </div>
            </form>        
            <div class="row">
                <div class="col-md-3"> <a href="${context}/SimFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a></div>
                <div class="col-md-9">
                    <c:import url="../include/inc_pagination.jsp"/>
                </div>
            </div>     
            <form id="bookingSim" action="${context}/SimSearchServlet"   method="get" class="form-horizontal">
                <input type="hidden" id="menu" name="menu" value="booking"/>
                <input type="hidden" id="cancelBooking" name="cancelBooking" value=""/>
                <div style="overflow-y: scroll;">
                    <table id="sim_search_table" class="table table-bordered table-striped">
                        <div id="msgBox" class="alert alert-warning" hidden="">
                            <strong>Warning! </strong><text id="msg" name="msg" value=""></text>
                        </div>
                        <thead>                    
                            <tr>
                                <th>#</th>
                                <th>MOBILE</th>   
                                <th>SYSTEM</th>
                                <th>IMSI</th>
                                <th>SERIAL</th>
                                <th>CHARGE_TYPE</th>
                                <th>REGION_CODE</th>
                                <th>ENV</th>
                                <th>SITE</th>
                                <th>TYPE</th>
                                <th>STATUS</th>
                                <th>ASSIGN_TEAM</th>
                                <th>EMAIL_CONTACT</th>                    
                                <th>PROJECT</th>
                                <th>VALID_DATE</th>
                                <th>EXPIRE_DATE</th>
                                <th>REMARK</th>               
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="sim" items="${simList}" >                           
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
                                    <td>${sim.system}</td>
                                    <td>${sim.imsi}</td>
                                    <td>${sim.serialNo}</td>
                                    <td>${sim.chargeType}</td>                        
                                    <td>${sim.regionCode}</td>
                                    <td>${sim.enviroment}</td>
                                    <td>${sim.site}</td>
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
                            <c:if test="${simList.isEmpty()}">
                                <tr>                    
                                    <td colspan="17"><div class="alert"><span style="padding: 40%">ไม่พบข้อมูลที่ค้นหา</span></div> </td>
                                </tr>
                            </c:if>
                            <c:if test="${simList == null}">
                                <tr>                    
                                    <td colspan="17"><div class="alert"><span style="padding: 40%">กรุณาระบุเงื่อนไขในการค้นหา</span></div> </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <c:if test="${simList != null}">
                    <div class="panel-body">
                        <button id="ok" type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-pencil"></i> booking</button>   
                        <button id="cancel"     class="btn btn-default btn-danger"> cancel booking</button>

                        <div id="dialog" title="ยืนยันการคืนซิม?" hidden="">
                            </br>กด OK เพื่อยืนยันการ reset SIM สู่สถานะ Available</br>                             
                        </div>​
                    </div>
                </c:if>

            </form>
            <div class="row">                
                <div class="col-md-12">
                    <c:import url="../include/inc_pagination.jsp"/>
                </div>
            </div>  
        </div>        
    </div>        
</div>   

<script type="text/javascript">

    $(document).ready(function () {
        $("#searchSim").submit(function () {
            var mobileNo = $("#mobileNo");
            var env = $("#env");
            var system = $("#system");
            var status = $("#status");

            if ("" === mobileNo.val() && "" === env.val() && "" === system.val() && "" === status.val()) {
                alert('กรุณาระบุข้อมูลที่ต้องการค้นหา อย่างน้อย 1 รายการ');
                return false;
            }

        });

        $("#bookingSim").submit(function () {
            var checkBox = $("input#simSelected").is(':checked');
            if (!checkBox) {
                $("#msg").text("กรุณาเลือกอย่างน้อย 1 รายการ");
                $("#msgBox").show();
                return false;
            }

        });




        $("#dialog").dialog({
            autoOpen: false,
            modal: true,
            buttons: [
                {
                    text: "OK",
                    click: function () {
                        document.getElementById("cancelBooking").value = "Y";
                        $(this).dialog("close");
                        $("#bookingSim").submit();
                    }
                },
                {
                    text: "cancel",
                    btnClass: 'btn-blue',
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            ]
        });

        $("#cancel").on("click", function (e) {
            e.preventDefault();
            $("#dialog").dialog("open");
        });


    });
</script>

<jsp:include page="../include/inc_footer.jsp"/>
