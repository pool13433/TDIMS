<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="../include/inc_header.jsp"/>
<div id="exam" class="container" style="padding-right: 100px;">    
    <div id="2" class="panel panel-ais">        
        <div id="3" class="panel-heading">Manage Sim</div>
        <div id="4" class="panel-body">

            <form id="searchSim" action="${context}/SimSearchServlet"   method="get" class="form-horizontal">          
                <input type="hidden" id="menu" name="menu" value="searching"/>
                <input type="hidden" name="offset" value="${recordCurrent}"/>
                <div class="form-group">
                    <label for="mobileNo" class="col-sm-1 control-label">MOBILE </label>
                    <div class="col-sm-3">
                        <input type="hidden" value="${sim.simId}" id="simId" name="simId">
                        <input type="text" class="form-control" value="${criteria.mobileNo}" id="mobileNo" name="mobileNo" placeholder="mobileNo" >
                    </div>
                    <label for="serialNo" class="col-sm-1 control-label">SERIAL</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" value="${criteria.serialNo}" id="serialNo" name="serialNo" placeholder="serialNo" >
                    </div>
                    <label for="imsi" class="col-sm-1 control-label">IMSI </label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" value="${criteria.imsi}"  id="imsi" name="imsi" placeholder="imsi" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="chargeType" class="col-sm-1 control-label">TYPE</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="chargeType" name="chargeType" placeholder="chargeType" >
                            <option value="" selected>--เลือก--</option>
                            <c:forEach items="${chargeTypeList}" var="charge">
                                <c:choose>
                                    <c:when test="${criteria.chargeType == charge.conName}">
                                        <option value="${charge.conName}" selected>${charge.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${charge.conName}">${charge.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="usageType" class="col-sm-1 control-label">CHARGE</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="usageType" name="usageType" placeholder="usageType" >
                            <option value="" selected>--เลือก--</option>
                            <c:forEach items="${usageTypeList}" var="usage">                            
                                <c:choose>
                                    <c:when test="${criteria.usageType == usage.conName}">
                                        <option value="${usage.conName}" selected>${usage.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${usage.conName}">${usage.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="regionCode" class="col-sm-1 control-label">REGION</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control"  value="${criteria.regionCode}"  id="regionCode" name="regionCode" placeholder="regionCode" >
                    </div>                
                </div>
                <div class="form-group">     
                    <label for="system" class="col-sm-1 control-label">SYSTEM</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="system" name="system"  >
                            <option value="" selected>--เลือก--</option>
                            <c:forEach items="${systemList}" var="system">                            
                                <c:choose>
                                    <c:when test="${criteria.system == system.conName}">
                                        <option value="${system.conName}" selected>${system.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${system.conName}">${system.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="env" class="col-sm-1 control-label">ENV</label>
                    <div class="col-sm-3 ">
                        <select class="form-control" class="form-control" id="env" name="env" placeholder="env" >
                            <option value="" selected>--เลือก--</option>
                            <c:forEach items="${envList}" var="env">                            
                                <c:choose>
                                    <c:when test="${criteria.enviroment == env.conName}">
                                        <option value="${env.conName}" selected>${env.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${env.conName}">${env.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="site" class="col-sm-1 control-label">SITE</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="usageType" name="site" >
                            <option value="" selected>--เลือก--</option>
                            <c:forEach items="${siteList}" var="site">                            
                                <c:choose>
                                    <c:when test="${criteria.site == site.conName}">
                                        <option value="${site.conName}" selected>${site.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${site.conName}">${site.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                </div>
                <div class="form-group">

                    <label for="status" class="col-sm-1 control-label">STATUS</label>
                    <div class="col-sm-3">
                        <select class="form-control" class="form-control" id="status" name="status" placeholder="status" >
                            <option value="" selected>--เลือก--</option>
                            <c:forEach items="${simStatusList}" var="status">                            
                                <c:choose>
                                    <c:when test="${criteria.simStatus == status.conName}">
                                        <option value="${status.conName}" selected>${status.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${status.conName}">${status.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-6">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" class="btn btn-success">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </button>
                            <a href="${context}/SimSearchServlet?menu=sim_search" class="btn btn-warning">
                                <i class="glyphicon glyphicon-erase"></i> Reset
                            </a>
                            <a href="${context}/SimFormServlet" class="btn btn-default btn-primary">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col-md-12">
                    <c:import url="../include/inc_pagination.jsp"/>
                </div>
            </div>     
            <div id="msgBox" class="alert alert-warning" hidden="">
                <strong>Warning! </strong><text id="msg" name="msg" value=""></text>
            </div>
            <form id="bookingSim" action="${context}/SimSearchServlet"   method="get" class="form-horizontal">
                <input type="hidden" id="menu" name="menu" value="booking"/>
                <input type="hidden" id="cancelBooking" name="cancelBooking" value=""/>
                <div style="overflow-y: scroll;max-height: 400px;">                    
                    <table id="sim_search_table" class="table table-bordered table-striped">                        
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
                                    <td  nowrap>
                                        <label class="btn btn-success btn-sm">
                                            <input type="checkbox" autocomplete="off" id="simSelected" name="simSelected" value="${sim.simId}">                                                                                 
                                        </label>         
                                        <a href="${context}/SimFormServlet?simId=${sim.simId}" class="btn btn-default btn-info btn-sm"><i class="glyphicon glyphicon-pencil"></i></a>
                                        <a href="${context}/SimDeleteServlet?simId=${sim.simId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger btn-sm" ><i class="glyphicon glyphicon-trash"></i></a>
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
                <div class="row">
                    <div class="col-md-4">
                        <c:if test="${simList != null}">
                            <div class="panel-body">
                                <button id="ok" type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-pencil"></i> booking</button>   
                                <button id="cancel"     class="btn btn-default btn-danger"> cancel booking</button>

                                <div id="dialog" title="ยืนยันการคืนซิม?" hidden="">
                                    </br>กด OK เพื่อยืนยันการ reset SIM สู่สถานะ Available</br>                             
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="col-md-8">
                        <c:import url="../include/inc_pagination.jsp"/>
                    </div>
                </div>     
            </form>

        </div>        
    </div>        
</div>   

<script type="text/javascript">

    $(document).ready(function () {
        $("#searchSim").submit(function () {
            var criteria = {};
            $.each($(this).serializeArray(), function (_, kv) {
                console.log('kv ::==', kv);
                if (kv.value !== "") {
                    criteria[kv.name] = kv.value;
                }
            });
            var len = Object.keys(criteria).length;
            /*if (len == 1) {
             alert('กรุณาระบุข้อมูลที่ต้องการค้นหา อย่างน้อย 1 รายการ');
             return false;
             }*/
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
