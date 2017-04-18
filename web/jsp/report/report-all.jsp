<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>

<html>
    <head>
    <h2> All Sim report </h1>
    </head>
    <body>
        <br>
        <div class="table-responsive">
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
                            <td>${report.env}_Site${report.site}:${report.chargeType}:${report.usageType}</td>
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
        </div>
        <br>
        <canvas id="reportChart"></canvas>
    </body>
</html>

<script type="text/javascript">
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
</script>

<jsp:include page="../include/inc_footer.jsp"/>