<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Search Testcase ทั้งหมด</div>
        <div class="panel-body">
        <form id="searching" method="get" action="${context}/TestcaseSearchServlet">
            <input type="hidden" id="searchFlag" name="searchFlag" />
            <div class="panel-body" >
            <div class="form-group">  
                <div class="row">
                    <h3><span class="label label-primary">Search Criteria</span></h3>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                         
                    </div>
                       
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <input class="form-control" type="text" name="searchBox" id="searchBox" value="${searchBox}" placeholder="Search Testcase...">
                    </div>
                    <div>
                        <button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
                   
                                 
            </div>                      
        </div>
        </form>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ProjectId</th>
                    <th>UserId</th>
                    <th>System</th>
                    <th>Enviroment</th>  
                    <th>IssurNO</th>
                    <th>Path Dir</th>
                    <th>Testcase Details</th>
                    <th>Testcase Title</th>
                    <th>Defect No</th>
                    <th>Create Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tc" items="${testcaseList}">
                    <tr>
                        <td>${tc.projectId}</td>
                        <td>${tc.userId}</td> 
                        <td>${tc.systems}</td>
                        <td>${tc.enviroment}</td>
                        <td>${tc.issueNo}</td>
                        <td><a href="TestcaseSearchServlet?pathDir=${tc.pathDir}">${tc.pathDir}</a></td>
                        <td>${tc.testcaseDetails}</td>
                        <td>${tc.testcaseTitle}</td>
                        <td>${tc.defectNo}</td>
                        <td>${tc.createDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
<script style="javascript">
    $("#searching").submit(function(){
        $("#searchFlag").val("searchBy");
    });
    
    $(window).load(function(){
        var e = $("#projectId").val();
        alert("Hello "+e);
        if($("#projectId").val() === true){
            $("#projectId").attr('checked'));
        }
        if($("#createBy").val() === true){
            $("#createBy").attr('checked'));
        }
    });
        
    
</script>