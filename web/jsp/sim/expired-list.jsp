<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<c:if test="${isFromSendMail == 'Y'}">
    <div style="background-color: #6ef442; height: 30px;">
        <b> Email(s) has/have Sent!! </b>
    </div>
</c:if>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">
        <div id="3" class="panel-heading">ค้นหา Expired Sim</div>
        <br>
        <form action="${context}/ExpiredSimServlet" method="get" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">Team</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="team" name="team" placeholder="team">
                        <option value="" selected>   Select Team  </option>                                                       
                        <c:forEach items="${teamList}" var="t">                            
                            <c:choose>
                                <c:when test="${team == t.teamId}">
                                    <option value="${t.teamId}" selected>${t.teamName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${t.teamId}">${t.teamName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-2 control-label">System</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="system" name="system" placeholder="system">
                        <option value="" selected>   Select System  </option>                                                       
                        <c:forEach items="${systemList}" var="s">                            
                            <c:choose>
                                <c:when test="${system == s.conName}">
                                    <option value="${s.conName}" selected>${s.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${s.conName}">${s.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-2 control-label">Mobile No</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="" id="number" name="number" placeholder="number" >
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-0 col-sm-10">
                    <button type="submit" class="btn btn-success">Search</button>
                </div>
            </div>
        </form>
        <div class="panel-heading">Expired List</div>
        <form action="${context}/SendMailServlet" method="post" class="form-horizontal">
            <input type="hidden" value="${team}" id="team" name="team">
            <input type="hidden" value="${system}" id="system" name="system">
            <c:if test="${fn:length(simList) > 0}">
                <div class="form-group">
                    <div class="col-sm-offset-0 col-sm-10">
                        <button type="submit" class="btn btn-success">Send Mail</button>
                    </div>
                </div>
            </c:if>
            <c:if test="${fn:length(simList) == 0}">
                No expired List
            </c:if>
            <c:if test="${fn:length(simList) != 0}">
                Team : ${teamName} System : ${system}
            <div class="table-responsive">
                <table class="table table-bordered table-striped" style="table-layout: fixed;">
                    <thead>
                        <tr>
                            <th style="word-wrap: break-word;">CheckBox</th>
                            <th style="word-wrap: break-word;">No.</th>
                            <th style="word-wrap: break-word;">ASSIGN_TEAM</th>
                            <th style="word-wrap: break-word;">EMAIL_CONTACT</th>
                            <th style="word-wrap: break-word;">PROJECT</th>
                            <th style="word-wrap: break-word;">ENV</th>
                            <th style="word-wrap: break-word;">SITE</th>
                            <th style="word-wrap: break-word;">CHARGE_TYPE</th>
                            <th style="word-wrap: break-word;">TYPE</th>
                            <th style="word-wrap: break-word;">MOBILE_NO</th>
                            <th style="word-wrap: break-word;">SERIAL_NO</th>
                            <th style="word-wrap: break-word;">VALID_DATE</th>
                            <th style="word-wrap: break-word;">EXPIRED_DATE</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sim" items="${simList}" varStatus="myIndex">
                            <tr>                    
                                <td style="word-wrap: break-word;">
                                    <div>
                                        <input type="checkbox" name="expireds" value="${sim.emailContact}">
                                    </div>
                                </td>
                                <td style="word-wrap: break-word;">${myIndex.index + 1}</td>
                                <td style="word-wrap: break-word;">${sim.teamName}</td>
                                <td style="word-wrap: break-word;">${sim.emailContact}</td>
                                <td style="word-wrap: break-word;">${sim.projectName}</td>
                                <td style="word-wrap: break-word;">${sim.enviroment}</td>
                                <td style="word-wrap: break-word;">${sim.site}</td>
                                <td style="word-wrap: break-word;">${sim.chargeType}</td>                        
                                <td style="word-wrap: break-word;">${sim.usageType}</td>
                                <td style="word-wrap: break-word;">${sim.mobileNo}</td>
                                <td style="word-wrap: break-word;">${sim.serialNo}</td>
                                <td style="word-wrap: break-word;">${sim.validDate}</td>
                                <td style="word-wrap: break-word;">${sim.expireDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:import url="../include/inc_pagination.jsp"/>
            </c:if>
        </form>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>

