<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>

<h2> Report </h2>
<br>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">
        <div id="3" class="panel-heading">Sim Report</div>
        <br>
        <form action="${context}/ReportServlet" method="get" class="form-horizontal">
            <input type="hidden" id="type" name="type" value="sim"/>
            <div class="form-group">
                <label class="col-sm-1 control-label">Environment</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env">
                        <option value="" selected>   All  </option>                                                       
                        <c:forEach items="${envSelectList}" var="e">                            
                            <c:choose>
                                <c:when test="${env == e.conValue}">
                                    <option value="${e.conValue}" selected>${e.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${e.conValue}">${e.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-2 control-label">Site</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="site" name="site" placeholder="site">
                        <option value="" selected>   All  </option>                                                       
                        <c:forEach items="${siteSelectList}" var="s">                            
                            <c:choose>
                                <c:when test="${site == s.conValue}">
                                    <option value="${s.conValue}" selected>${s.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${s.conValue}">${s.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-0 col-sm-10">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">
        <div id="3" class="panel-heading">Test Case Report</div>
        <br>
        <form action="${context}/ReportServlet" method="get" class="form-horizontal">
            <input type="hidden" id="type" name="type" value="testcase"/>
            <div class="form-group">
                <label class="col-sm-1 control-label">Test Case</label>
                <div class="col-sm-3">
                    <input type="radio" value="isTestcase" id="cases" name="cases" placeholder="cases">
                </div>
                <label class="col-sm-2 control-label">Step</label>
                <div class="col-sm-3">
                    <input type="radio" value="isStep" id="cases" name="cases" placeholder="cases">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-0 col-sm-10">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>
<br>
<c:if test="${not empty cases}">
    <div class="table-responsive">
        <c:if test="${cases eq 'sim'}">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Summary All Env</th>
                        <th>Total</th>
                        <th>Available</th>
                        <th>In Use</th>
                        <th>Lost</th>
                        <th>Unavailable</th>                    
                        <th>Pending</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="report" items="${reportList}">
                        <tr>
                            <td>${report.env}_SITE${report.site}:${report.chargeType}:${report.usageType}</td>
                            <td>${report.total}</td>
                            <td>${report.available}</td>
                            <td>${report.inUse}</td>
                            <td>${report.lost}</td>
                            <td>${report.unAvailable}</td>                        
                            <td>${report.pending}</td>  
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${cases eq 'isTestcase'}">
               <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Type</th>
                        <th>Test Case</th>
                        <th>Manual Step</th>
                        <th>Auto Step</th>
                        <th>Defect</th>
                        <th>Issue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="step" items="${testcaseDetList}">
                        <tr>
                            <td>
                                <c:if test="${empty temptest}">
                                    <c:set var="temptest" value="${step.year}" />
                                    ${step.year}
                                </c:if>

                                <c:if test="${not empty temptest}">
                                    <c:if test="${temptest ne step.year}">
                                        <c:set var="temptest" value="${step.year}" />
                                        ${step.year}
                                    </c:if>
                                </c:if>
                            </td>
                            <td>${step.type}</td>
                            <td>${step.testcase}</td>
                            <td>${step.manualStep}</td>
                            <td>${step.autoStep}</td>
                            <td>${step.defect}</td>
                            <td>${step.issue}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${cases eq 'isStep'}">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Type</th>
                        <th>Manual Step</th>
                        <th>Auto Step</th>
                        <th>Total Step</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="step" items="${testcaseDetList}">
                        <tr>
                            <td>
                                <c:if test="${empty temptest}">
                                    <c:set var="temptest" value="${step.year}" />
                                    ${step.year}
                                </c:if>

                                <c:if test="${not empty temptest}">
                                    <c:if test="${temptest ne step.year}">
                                        <c:set var="temptest" value="${step.year}" />
                                        ${step.year}
                                    </c:if>
                                </c:if>
                            </td>
                            <td>${step.type}</td>
                            <td>${step.manualStep}</td>
                            <td>${step.autoStep}</td>
                            <td>${step.allStep}</td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</c:if>
<br>
<canvas id="reportChart"></canvas>
    
    
<script type="text/javascript">
    if ("${cases}" == "sim") {
        var ctx = document.getElementById("reportChart").getContext('2d');
        var reportChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ${labelList},
                datasets: [{
                    label: 'Available',
                    data: ${availableList},
                    backgroundColor: [
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)'
                    ],
                    borderColor: [
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: 'In Used',
                    data: ${inUsedList},
                    backgroundColor: [
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)'
                    ],
                    borderColor: [
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: 'Lost',
                    data: ${lostList},
                    backgroundColor: [
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)'
                    ],
                    borderColor: [
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)',
                        'rgba(224, 0, 0, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: 'Pending',
                    data: ${pendingList},
                    backgroundColor: [
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)'
                    ],
                    borderColor: [
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: 'Unavailable',
                    data: ${unavailableList},
                    backgroundColor: [
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)'
                    ],
                    borderColor: [
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)',
                        'rgba(0, 0, 0, 1)'
                    ],
                    borderWidth: 1
                }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    } else if ("${cases}" == "isTestcase") {
        var ctx = document.getElementById("reportChart").getContext('2d');
        var year = new Date().getFullYear()
        var reportChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ${labelList},
                datasets: [{
                    label: year - 1,
                    data: ${previousYearList},
                    backgroundColor: [
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)'
                    ],
                    borderColor: [
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: year,
                    data: ${thisYearList},
                    backgroundColor: [
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)'
                    ],
                    borderColor: [
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: year + 1,
                    data: ${nextYearList},
                    backgroundColor: [
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)'
                    ],
                    borderColor: [
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)',
                        'rgba(66, 227, 245, 1)'
                    ],
                    borderWidth: 1
                }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    } else if ("${cases}" == "isStep") {
        var ctx = document.getElementById("reportChart").getContext('2d');
        var reportChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ${labelList},
                datasets: [{
                    label: 'Manual',
                    data: ${manualStepList},
                    backgroundColor: [
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)'
                    ],
                    borderColor: [
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)',
                        'rgba(41, 152, 52, 1)'
                    ],
                    borderWidth: 1
                }, {
                    label: 'Auto',
                    data: ${autoStepList},
                    backgroundColor: [
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)'
                    ],
                    borderColor: [
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)',
                        'rgba(41, 60, 152, 1)'
                    ],
                    borderWidth: 1
                }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    }
</script>

<jsp:include page="../include/inc_footer.jsp"/>