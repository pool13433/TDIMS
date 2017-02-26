<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">ลงทะเบียนเพื่อขอเข้าใช้งานระบบ</div>
        <div class="panel-body">
            <form action="${context}/RegisterServlet" method="post" class="form-horizontal" style="padding-right: 100px;">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">username</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" id="username" name="username" placeholder="username" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">password</label>
                    <div class="col-sm-4">                   
                        <input type="password" class="form-control" id="password" name="password" placeholder="password" required>
                    </div>
                    <label for="passwordComfirm" class="col-sm-2 control-label">Confirm Password</label>
                    <div class="col-sm-4">                   
                        <input type="password" class="form-control" id="passwordComfirm" name="passwordComfirm" placeholder="passwordComfirm" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">First Name</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="firstName" required>
                    </div>
                    <label for="lastName" class="col-sm-2 control-label">Last Name</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="lastName" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mobile" class="col-sm-2 control-label">Mobile No</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" id="mobile" name="mobile" placeholder="mobile" required>
                    </div>
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-4">                   
                        <input type="email" class="form-control" id="email" name="email" placeholder="email" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="position" class="col-sm-2 control-label">Position</label>
                    <div class="col-sm-4">
                        <div class="col-sm-5">
                            <select class="form-control" id="chargeType" name="position" placeholder="position" required>
                                <c:forEach items="${positionList}" var="position">
                                    <option value="${position.posId}" selected>${position.posName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <label for="gender" class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="gender" name="gender" required>
                            <c:forEach items="${genderList}" var="gender">
                                <option value="${gender.conName}" selected>${gender.conValue}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-10">
                        <button type="submit" class="btn btn-success">Register</button>
                        <button type="reset" class="btn btn-warning">Reset</button>
                    </div>
                </div>
            </form>
        </div>        
    </div>
</div>
<jsp:include page="include/inc_footer.jsp"/>
