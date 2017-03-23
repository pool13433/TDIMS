<%-- 
    Document   : borrow-list
    Created on : 12 มี.ค. 2560, 22:42:07
    Author     : Parinya
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<c:if test="${isFromSendMail == 'N'}">
    <div style="background-color: #6ef442; height: 30px;">
        <b> Email(s) has/have Sent!! </b>
    </div>
</c:if>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">Expired List</div>
        <form action="${context}/SendMailServlet" method="post" class="form-horizontal">
            <c:if test="${fn:length(simList) > 0}">
                <div class="form-group">
                    <div class="col-sm-offset-0 col-sm-10">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>
            </c:if>
            <c:if test="${fn:length(simList) == 0}">
                No expired List
            </c:if>
            <c:forEach var="subSimList" items="${simList}">
                Team : ${subSimList.team} System : ${subSimList.system}
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
                        <c:forEach var="sim" items="${subSimList.subSimList}" varStatus="myIndex">
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
            </c:forEach>
        </form>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>

