<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Search Testcase ทั้งหมด</div>
        <div class="panel-body">
        <form id="searching" method="get" action="${context}/TestcaseSearchServlet" class="form-horizontal">   
            <input type="hidden" id="menu" name="menu" value="searching"/>
            <div class="form-group">
                <label for="projectSelected" class="col-sm-1 control-label">Project</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="projectSelected" name="projectSelected" placeholder="env">
                        <option value="" selected>   All project  </option>                                                       
                        <c:forEach items="${projectCombo}" var="p">                            
                        <c:choose>
                            <c:when test="${projectSelected == p.id}">
                                <option value="{p.id}" selected><a href="#">${p.id} - ${p.value1}</a></option>
                            </c:when>
                            <c:otherwise>
                                <option value="{p.id}" ><a href="#">${p.id} - ${p.value1}</a></option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </select>
                </div>
                <label for="searchBox" class="col-sm-1 control-label">Details </label>
                <div class="col-sm-4">
                    <input class="form-control" type="text" name="searchBox" id="searchBox" value="${searchBox}" placeholder="Search testcase details...">
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
                <label for="createDate" class="col-sm-1 control-label">Date </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control datepicker"  value="${createDate}"  id="createDate" name="createDate" placeholder="createDate" readonly >
                </div>
            </div>
            <div class="form-group">
                <label for="userId" class="col-sm-1 control-label">Tester</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="userId" name="userId" >
                        <option value="" selected>    All Tester  </option>
                        <c:forEach items="" var="t">                            
                            <c:choose>
                                <c:when test="${userId == ''}">
                                    <option value="" selected></option>
                                </c:when>
                                <c:otherwise>
                                    <option value=""></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>    
                    </select>
                </div>
                <label for="issueNo" class="col-sm-1 control-label">Issue No. </label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" name="issueNo" id="issueNo" value="${issueNo}" placeholder="Issue No...">
                </div>
            </div>
            <div class="form-group">
                <label for="defectNo" class="col-sm-1 control-label">Ticket/Defect No.</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" name="defectNo" id="defectNo" value="${defectNo}" placeholder="Defect No...">
                </div>
                <label for="env" class="col-sm-1 control-label">Environment </label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env">
                        <option value="" selected>   All environment  </option>                                                       
                        <c:forEach items="${envList}" var="e">                            
                        <c:choose>
                            <c:when test="${env == e.envId}">
                                <option value="${e.envId}" selected>${e.envCode}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${e.envId}">${e.envCode}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </select>
                </div>
                <div class="col-sm-2"><button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-search"></i> Search</button></div>
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