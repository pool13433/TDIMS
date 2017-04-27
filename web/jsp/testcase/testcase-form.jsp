<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
  
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Testcase</div>
        <div class="panel-body">
            <a href="${context}/TestcaseSearchServlet?menu=testcase_search" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/TestcaseAddServlet" method="post" class="form-horizontal"   enctype="multipart/form-data">
            <input type="hidden" value="${testcase.testcaseId}" id="testcaseId" name="testcaseId">
            <div class="form-group">
            <div class="col-sm-offset-1 col-sm-5">
                 <div class="form-group">
                <label for="testcase" class="col-sm-3 control-label">Testcase</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" value="${testcase.testcaseTitle}" id="testcase" name="testcase" placeholder="testcase" required>
                </div>
                 </div>
                <div class="form-group">
                <label for="tester" class="col-sm-3 control-label">Tester</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="tester" name="tester" placeholder="tester" required>
                        <c:forEach items="${ownerList}" var="tester">                            
                            <c:choose>
                                <c:when test="${testcase.userId == tester.profileId}">
                                    <option value="${env.profileId}" selected>${env.firstName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${tester.profileId}">${tester.firstName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                <div class="form-group">
                <label for="date" class="col-sm-3 control-label">Date</label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" value="${testcase.createDate}" id="date" name="date" placeholder="date" required>
                </div>
                 </div>
                <div class="form-group">
                    <label for="type" class="col-sm-3 control-label">Type</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="type" name="type" >
                            <option value="" selected>    All Type  </option>
                            <c:forEach items="${typeList}" var="t">                            
                                <c:choose>
                                    <c:when test="${testcase.type == t.conName}">
                                        <option value="${t.conName}" selected>${t.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${t.conName}">${t.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>    
                        </select>
                    </div>
                </div>
                 <div class="form-group">
                <label for="project" class="col-sm-3 control-label">Project</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="project" name="project" placeholder="project" required>
                        <c:forEach items="${projectList}" var="project">                            
                            <c:choose>
                                <c:when test="${testcase.projectId == project.projId}">
                                    <option value="${project.projId}" selected>${project.projName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${project.projId}">${project.projName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                 <div class="form-group">
                <label for="systems" class="col-sm-3 control-label">Systems</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="systems" name="systems" placeholder="systems" required>
                        <c:forEach items="${systemList}" var="systems">                            
                            <c:choose>
                                <c:when test="${testcase.systems == systems.conValue}">
                                    <option value="${systems.conValue}" selected>${systems.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${systems.conValue}">${systems.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                <div class="form-group">           
                <label for="env" class="col-sm-3 control-label">ENV</label>
                <div class="col-sm-9 ">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
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
 
            </div>
            
              
            <div class="col-sm-offset-1 col-sm-5">
                 <label for="detail"  class="control-label">Detail</label>
                         <div class="form-group">
  <div class="col-sm-12">
                   <textarea class="form-control" rows="7" id="detail" name="detail" value="${testcase.testcaseDetails}">${testcase.testcaseDetails}</textarea>
                </div>
 
</div>
                <div class="form-group">
                <label for="step" class="col-sm-2 control-label">Step</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${testcase.step}" id="manual" name="manual" placeholder="Manual" onchange="funtionStep();" required>
                </div>  
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${testcase.automate}" id="automate" name="automate" placeholder="Automate" onchange="funtionStep();" required>
                </div>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${totalStep}" id="all" name="all" placeholder="All" required>
                </div>
                </div>
                <div class="form-group">
                <label for="issue" class="col-sm-2 control-label">Issue No.</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" value="${testcase.issueNo}" id="issue" name="issue" placeholder="issue" required>
                </div>  
                <label for="td" class="col-sm-2 control-label">Ticket/Defect No.</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" value="${testcase.defectNo}" id="td" name="td" placeholder="td" required>
                </div>
                </div>
                </div>
                  </div>
               
            <div class="form-group">
                <label for="td" class="col-sm-2 control-label">MyCom</label>
                <div class="col-sm-9">
                   <input type="text" class="form-control" value="${testcase.pathDir}" id="path" name="path" placeholder="path" required>
                </div>
            </div>
            </div>
                <div class="form-group">
                <div class="col-sm-offset-10 col-sm-2">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </div>
                
             </div>
        </form>
    </div>
    <script>
function funtionStep() {
  var manaul = document.getElementById('manual').value 
  var automate = document.getElementById('automate').value 
  if(manaul==""){
      manaul=0;
  }
  if(automate==""){
      automate=0;
  }
  document.getElementById('all').value = (parseInt(manaul)+parseInt(automate));
}
</script>
<jsp:include page="../include/inc_footer.jsp"/>
