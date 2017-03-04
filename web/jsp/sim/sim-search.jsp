<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Sim</div>
        <div class="panel-body">
            <a href="${context}/SimListServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/SimSearchServlet" method="get" class="form-horizontal">
            <div class="form-group">
                <label for="mobileNo" class="col-sm-1 control-label">Mobile No</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control"  id="mobileNo" name="mobileNo" placeholder="mobileNo" required>
                </div>
            </div>            
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </div>
        </form>
            <p>Mobile NO : ${mobileNo}</p>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
