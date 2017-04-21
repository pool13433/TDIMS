<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="include/inc_header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-ais">
                <div class="panel-heading">                    
                    <div class="panel-title pull-left">
                        ยอดรวมสถานะซิมในแต่ละเดือน
                    </div>
                    <div class="panel-title pull-right form-horizontal">
                        <label class="radio-inline">
                            <input type="radio" name="simStatus" id="simStatus" value="Inused" checked> ยืม
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="simStatus" id="simStatus" value="Available"> คืน
                        </label>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="panel-body">
                    <canvas id="barChart" width="400" height="300"></canvas>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="panel panel-ais">
                <div class="panel-heading">                    
                    <div class="panel-title pull-left">
                        สถานะซิมจากซิมทั้งหมดในระบบ
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="panel-body">
                    <canvas id="radarChart" width="400" height="300"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var myBarChart = null;
        renderBarChart('Inused');
        renderRadarChart();
        $('input[name="simStatus"]').on('change', function () {
            var simStatus = $(this).val();
            renderBarChart(simStatus);
        });
        function renderRadarChart() {
            var radarChart = document.getElementById("radarChart");
            $.get('${context}/DashboardDataServlet', {chartType: 'radar'}, function (data) {
                var labels = [];
                var values = [];
                var label = new Date().getFullYear();
                $.each(data, function (key, value) {
                    values.push(value);
                    labels.push(key);
                });
                var myRadarChart = new Chart(radarChart, {
                    type: "doughnut",
                    options: {
                        animation: {
                            animateScale: true
                        }
                    },
                    data: {
                        labels: labels,
                        datasets: [{
                                data: values,
                                backgroundColor: ["#C6FF00","#FF9800","#0091EA","#FF4081","#8E24AA"]
                            }]
                    },
                });
            }, 'json');
        }

        function renderBarChart(simStatus) {
            var barChart = document.getElementById("barChart");
            $.get('${context}/DashboardDataServlet', {simStatus: simStatus, chartType: 'bar'}, function (data) {
                var labels = [];
                var values = [];
                var label = '';
                $.each(data, function (key, value) {
                    if (key == 'Year') {
                        label = value;
                    } else {
                        values.push(value);
                        labels.push(key);
                    }
                });
                if (myBarChart != null) {
                    myBarChart.destroy();
                }
                myBarChart = new Chart(barChart, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: '# ภายในปี ' + label,
                                data: values,
                                backgroundColor: ['#F44336', '#E91E63',
                                    '#9C27B0', '#673AB7', '#2196F3', '#3F51B5', '#03A9F4', '#009688', '#00BCD4',
                                    '#4CAF50', '#CDDC39', '#8BC34A'],
                                borderWidth: 2
                            }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                        }
                    }
                });
            }, 'json');
        }

    });
</script>
<jsp:include page="include/inc_footer.jsp"/>

