<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">แก้ไขข้อมูลส่วนตัว</div>
        <div class="panel-body">
            <form action="${context}/ChangeProfileServlet" method="post" class="form-horizontal" style="padding-right: 100px;">
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">First Name</label>
                    <div class="col-sm-4">                   
                        <input type="hidden" name="profileId" value="${profile.profileId}" >
                        <input type="text" class="form-control" value="${profile.firstName}" id="firstName" name="firstName" placeholder="firstName" required>
                    </div>
                    <label for="lastName" class="col-sm-2 control-label">Last Name</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" value="${profile.lastName}"  id="lastName" name="lastName" placeholder="lastName" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mobile" class="col-sm-2 control-label">Mobile No</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" value="${profile.mobile}"  id="mobile" name="mobile" placeholder="mobile" required>
                    </div>
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-4">                   
                        <input type="email" class="form-control" value="${profile.email}"  id="email" name="email" placeholder="email" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="position" class="col-sm-2 control-label">Position</label>
                    <div class="col-sm-4">
                        <div class="col-sm-5">
                            <select class="form-control" id="chargeType" name="position" placeholder="position" required>
                                <c:forEach items="${positionList}" var="position">
                                    <c:choose>
                                        <c:when test="${profile.position == position.posId}">
                                            <option value="${position.posId}" selected>${position.posName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${position.posId}">${position.posName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <label for="gender" class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="gender" name="gender" required>
                            <c:forEach items="${genderList}" var="gender">
                                <c:choose>
                                    <c:when test="${profile.gender == gender.conName}">
                                        <option value="${gender.conName}" selected>${gender.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${gender.conName}">${gender.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-10">
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="reset" class="btn btn-warning">Reset</button>
                    </div>
                </div>
            </form>
        </div>        
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
