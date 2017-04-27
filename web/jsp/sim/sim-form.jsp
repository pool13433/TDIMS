<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Sim</div>
        <div class="panel-body">
            <a href="${context}/SimSearchServlet?menu=sim_search" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/SimSaveServlet" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="mobileNo" class="col-sm-1 control-label">MOBILE </label>
                <div class="col-sm-3">
                    <input type="hidden" value="${sim.simId}" id="simId" name="simId">
                    <input type="number" class="form-control" value="${sim.mobileNo}" id="mobileNo" name="mobileNo" placeholder="mobileNo" required>
                </div>
                <label for="serialNo" class="col-sm-1 control-label">SERIAL</label>
                <div class="col-sm-3">
                    <input type="number" class="form-control" value="${sim.serialNo}" id="serialNo" name="serialNo" placeholder="serialNo">
                </div>
                <label for="imsi" class="col-sm-1 control-label">IMSI </label>
                <div class="col-sm-3">
                    <input type="number" class="form-control" value="${sim.imsi}"  id="imsi" name="imsi" placeholder="imsi">
                </div>
            </div>
            <div class="form-group">
                <label for="chargeType" class="col-sm-1 control-label">TYPE</label>
                <div class="col-sm-3">
                    <select class="form-control" id="chargeType" name="chargeType" placeholder="chargeType" required>
                        <c:forEach items="${chargeTypeList}" var="charge">
                            <c:choose>
                                <c:when test="${sim.chargeType == charge.conName}">
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
                    <select class="form-control" class="form-control" id="usageType" name="usageType" placeholder="usageType" required>
                        <c:forEach items="${usageTypeList}" var="usage">                            
                            <c:choose>
                                <c:when test="${sim.usageType == usage.conName}">
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
                    <input type="text" class="form-control"  value="${sim.regionCode}"  id="regionCode" name="regionCode" placeholder="regionCode">
                </div>                
            </div>
            <div class="form-group">     
                <label for="system" class="col-sm-1 control-label">SYSTEM</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="system" name="system"  required>
                        <c:forEach items="${systemList}" var="system">                            
                            <c:choose>
                                <c:when test="${sim.system == system.conName}">
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
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
                        <c:forEach items="${envList}" var="env">                            
                            <c:choose>
                                <c:when test="${sim.enviroment == env.conName}">
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
                        <c:forEach items="${siteList}" var="site">                            
                            <c:choose>
                                <c:when test="${sim.site == site.conName}">
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
                    <select class="form-control" class="form-control" id="status" name="status" placeholder="status" required>
                        <c:forEach items="${simStatusList}" var="status">                            
                            <c:choose>
                                <c:when test="${sim.simStatus == status.conName}">
                                    <option value="${status.conName}" selected>${status.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status.conName}">${status.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-3">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="reset" class="btn btn-warning">Reset</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
