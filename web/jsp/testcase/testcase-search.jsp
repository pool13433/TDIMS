<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Search Testcase ทั้งหมด</div>
        <div class="panel-body">
        <form id="searching" method="get" action="${context}/TestcaseSearchServlet" class="form-horizontal">   
            <input type="hidden" id="menu" name="menu" value="searching"/>
            <div class="form-group">
                <div class="col-sm-5">
                    <div class="form-group">
                    <label for="projectSelected" class="col-sm-3 control-label">Project</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="projectSelected" name="projectSelected" placeholder="env">
                            <option value="" selected>   All project  </option>                                                       
                            <c:forEach items="${projectCombo}" var="p">                            
                            <c:choose>
                                <c:when test="${projectSelected == p.id}">
                                    <option value="${p.id}" selected><a href="#">${p.value1}</a></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${p.id}" ><a href="#">${p.value1}</a></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </div>
                    </div>
                    <div class="form-group">
                    <label for="system" class="col-sm-3 control-label">System</label>
                    <div class="col-sm-9">
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
                    </div>
                    <div class="form-group">
                    <label for="createDate" class="col-sm-3 control-label">Date </label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control datepicker"  value="${createDate}"  id="createDate" name="createDate" placeholder="createDate" readonly >
                    </div>
                    </div>
                    <div class="form-group">
                    <label for="createBy" class="col-sm-3 control-label">Tester</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="createBy" name="createBy" >
                            <option value="" selected>    All Tester  </option>
                            <c:forEach items="${ownerList}" var="owner">                            
                                <c:choose>
                                    <c:when test="${createBy == owner.profileId}">
                                        <option value="${owner.profileId}" selected>${owner.username}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${owner.profileId}">${owner.username}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>  
                        </select>
                    </div>
                    </div>
                    <div class="form-group">
                        <label for="defectNo" class="col-sm-3 control-label">Ticket/Defect No.</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" name="defectNo" id="defectNo" value="${defectNo}" placeholder="Defect No...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="step" class="col-sm-3 control-label">Step</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" name="step" id="step" value="${step}" placeholder="Step number...">
                        </div>
                    </div>
                </div>

                <div class="col-md-offset-1 col-sm-5">
                    <div class="form-group">
                        <label for="searchBox" class="control-label col-sm-3">Details </label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="7" id="searchBox" name="searchBox" value="${searchBox}">${testcase.testcaseDetails}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                    <label for="env" class="col-sm-3 control-label">Environment </label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="env" name="env" placeholder="env">
                            <option value="" selected>   All environment  </option>                                                       
                            <c:forEach items="${envList}" var="e">                            
                            <c:choose>
                                <c:when test="${env == e.conName}">
                                    <option value="${e.conName}" selected>${e.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${e.conName}">${e.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </div>                    
                    </div>
                    <div class="form-group">
                        <label for="issueNo" class="col-sm-3 control-label ">Issue No. </label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" name="issueNo" id="issueNo" value="${issueNo}" placeholder="Issue No...">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class=" control-label col-sm-3"><button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-search"></i> Search</button></div>
                    </div>
                </div>
                
            </div>
        </form>
        
        </div>
        <div style="overflow-y: scroll;">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ProjectId</th>
                        <th>Create By</th>
                        <th>System</th>
                        <th>Enviroment</th>  
                        <th>IssurNO</th>
                        <th>Path Dir</th>
                        <th>Testcase Details</th>
                        <th>Testcase Title</th>
                        <th>Defect No</th>
                        <th>Step</th>                        
                        <th>Create Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tc" items="${testcaseList}">
                        <tr>
                            <td>${tc.projectId}</td>
                            <td>${tc.createBy}</td> 
                            <td>${tc.systems}</td>
                            <td>${tc.enviroment}</td>
                            <td>${tc.issueNo}</td>
                            <td><a href="TestcaseSearchServlet?pathDir=${tc.pathDir}">${tc.pathDir}</a></td>
                            <td>${tc.testcaseDetails}</td>
                            <td>${tc.testcaseTitle}</td>
                            <td>${tc.defectNo}</td>
                            <td>${tc.step}</td>
                            <td>${tc.createDate}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${testcaseList.isEmpty()}">
                        <tr>                    
                            <td colspan="15"><div class="alert"><span style="padding: 40%">ไม่พบข้อมูลที่ค้นหา</span></div> </td>
                        </tr>
                    </c:if>
                    <c:if test="${testcaseList == null}">
                        <tr>                    
                            <td colspan="17"><div class="alert"><span style="padding: 40%">กรุณาระบุเงื่อนไขในการค้นหา</span></div> </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
            
    $(document).ready(function(){
        $("#searching").submit(function(){
            var projectSelected = $("#projectSelected");
            var system = $("#system"); 
            var createDate = $("#createDate"); 
            var createBy = $("#createBy");
            var defectNo = $("#defectNo");
            var step = $("#step"); 
            var searchBox = $("#searchBox"); 
            var env = $("#env"); 
            var issueNo = $("#issueNo"); 

            if("" === projectSelected.val() && "" === system.val() && "" === createDate.val() && "" === createBy.val()
                    && "" === defectNo.val() && "" === step.val() && "" === searchBox.val() && "" === env.val() 
                    && "" === issueNo.val()){
                alert('กรุณาระบุข้อมูลที่ต้องการค้นหา อย่างน้อย 1 รายการ');
                return false;
            }else{
                $("#searchFlag").val("searchBy");
            }                
        });
    });
    
    /*
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
    */
        
    
</script>
<jsp:include page="../include/inc_footer.jsp"/>
