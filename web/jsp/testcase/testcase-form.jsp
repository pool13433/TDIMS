<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<<<<<<< OURS
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Testcase</div>
        <div class="panel-body">
            <a href="${context}/TestcaseListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/TestcaseAddServlet" method="post" class="form-horizontal">
            <div class="form-group">
            <div class="col-sm-offset-1 col-sm-5">
                 <div class="form-group">
                <label for="env" class="col-sm-3 control-label">Systems</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
                        <c:forEach items="${envList}" var="env">                            
                            <c:choose>
                                <c:when test="${sim.enviroment == env.envId}">
                                    <option value="${env.envId}" selected>${env.envCode}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${env.envId}">${env.envCode}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                 <div class="form-group">
                <label for="env" class="col-sm-3 control-label">Systems</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
                        <c:forEach items="${envList}" var="env">                            
                            <c:choose>
                                <c:when test="${sim.enviroment == env.envId}">
                                    <option value="${env.envId}" selected>${env.envCode}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${env.envId}">${env.envCode}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                 <div class="form-group">
                <label for="env" class="col-sm-3 control-label">Systems</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
                        <c:forEach items="${envList}" var="env">                            
                            <c:choose>
                                <c:when test="${sim.enviroment == env.envId}">
                                    <option value="${env.envId}" selected>${env.envCode}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${env.envId}">${env.envCode}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                 <div class="form-group">
                <label for="env" class="col-sm-3 control-label">Systems</label>
                <div class="col-sm-9">
                    <select class="form-control" class="form-control" id="env" name="env" placeholder="env" required>
                        <c:forEach items="${envList}" var="env">                            
                            <c:choose>
                                <c:when test="${sim.enviroment == env.envId}">
                                    <option value="${env.envId}" selected>${env.envCode}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${env.envId}">${env.envCode}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                 </div>
                <div class="form-group">
                <label for="date" class="col-sm-2 control-label">Date</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" value="${sim.mobileNo}" id="mobileNo" name="mobileNo" placeholder="mobileNo" required>
                </div>  
                <label for="serialNo" class="col-sm-2 control-label">Project</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" value="${sim.serialNo}" id="serialNo" name="serialNo" placeholder="serialNo" required>
                </div>
                </div>
                
            </div>
            
              
            <div class="col-sm-offset-1 col-sm-5">
                 <label for="comment"  class="control-label">Detail</label>
                         <div class="form-group">
  <div class="col-sm-12">
                   <textarea class="form-control" rows="7" id="detail"></textarea>
                </div>
 
</div>
                </div>
                  </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </div>
            </div>
               
                
             </div>
        </form>
    </div>
=======
<div class="container">
    <h1>testcase-form</h1>
>>>>>>> THEIRS
</div>
<jsp:include page="../include/inc_footer.jsp"/>
