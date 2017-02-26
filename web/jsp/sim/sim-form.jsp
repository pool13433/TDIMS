<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Sim</div>
        <div class="panel-body">
            <a href="${context}/SimListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/SimSaveServlet" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="mobileNo" class="col-sm-1 control-label">Mobile No</label>
                <div class="col-sm-3">
                    <input type="hidden" value="${sim.simId}" id="simId" name="simId">
                    <input type="text" class="form-control" value="${sim.mobileNo}" id="mobileNo" name="mobileNo" placeholder="mobileNo" required>
                </div>
                <label for="serialNo" class="col-sm-1 control-label">Serial No</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${sim.serialNo}" id="serialNo" name="serialNo" placeholder="serialNo" required>
                </div>
                <label for="imsi" class="col-sm-1 control-label">Imsi</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${sim.imsi}"  id="imsi" name="imsi" placeholder="imsi" required>
                </div>
            </div>
            <div class="form-group">
                <label for="chargeType" class="col-sm-1 control-label">Charge Type</label>
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
                <label for="regionCode" class="col-sm-1 control-label">Region Code</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control"  value="${sim.regionCode}"  id="regionCode" name="regionCode" placeholder="regionCode" required>
                </div>
                <label for="env" class="col-sm-1 control-label">Environment</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
                        <c:forEach items="${envList}" var="env">                            
                            <c:choose>
                                <c:when test="${sim.env == env.conName}">
                                    <option value="${env.conName}" selected>${env.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${env.conName}">${env.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="site" class="col-sm-1 control-label">Site</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control"  value="${sim.site}" id="site" name="site" placeholder="site" required>
                </div>                
                <label for="usageType" class="col-sm-1 control-label">Usage Type</label>
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
                <label for="assignTeam" class="col-sm-1 control-label">Assign Team</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${sim.assignTeam}"  id="assignTeam" name="assignTeam" placeholder="assignTeam" required>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-1 control-label">Email</label>
                <div class="col-sm-3">
                    <input type="email" class="form-control" value="${sim.emailContact}"  id="email" name="email" placeholder="email" required>
                </div>                
                <label for="project" class="col-sm-1 control-label">Project</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="project" name="project" placeholder="project" required>
                        <c:forEach items="${projectList}" var="project">                            
                            <c:choose>
                                <c:when test="${sim.project == project.projId}">
                                    <option value="${project.projId}">${project.projName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${project.projId}">${project.projName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label for="status" class="col-sm-1 control-label">Status</label>
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
            </div>

            <div class="form-group">
                <label for="validDate" class="col-sm-1 control-label">Valid Date</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control datepicker"  value="${sim.validDate}"  id="validDate" name="validDate" placeholder="validDate" readonly required>
                </div>         
                <label for="expireDate" class="col-sm-1 control-label">Expire Date</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control datepicker"  value="${sim.expireDate}"  id="expireDate" name="expireDate" placeholder="expireDate" readonly required>
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-sm-1 control-label">Remark</label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="remark" id="remark" placeholder="remark">${sim.remark}</textarea>
                </div>      
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
