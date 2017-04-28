<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล User</div>
        <div class="panel-body">
            <form action="${context}/UserSaveServlet" method="post" id="userFrom" class="form-horizontal" style="padding-right: 100px;">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">username</label>
                    <div class="col-sm-4">     
                         <input type="hidden" class="form-control" id="profileId" name="profileId" value="${profile.profileId}" >
                        <input type="text" class="form-control" id="username" name="username" value="${profile.username}" placeholder="username" required>
                    </div>
                     <label for="mobile" class="col-sm-2 control-label">Mobile No</label>
                    <div class="col-sm-4">                   
                        <input type="number" class="form-control" id="mobile" name="mobile" placeholder="mobile" value="${profile.mobile}" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">password</label>
                    <div class="col-sm-4">                   
                        <input type="password" class="form-control" id="password" name="password" value="${profile.password}" placeholder="password" required>
                    </div>
                    <label for="passwordComfirm" class="col-sm-2 control-label">Confirm Password</label>
                    <div class="col-sm-4">                   
                        <input type="password" class="form-control" id="passwordComfirm" name="passwordComfirm" value="${profile.password}" placeholder="passwordComfirm" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">First Name</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${profile.firstName}" placeholder="firstName" required>
                    </div>
                    <label for="lastName" class="col-sm-2 control-label">Last Name</label>
                    <div class="col-sm-4">                   
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="lastName" value="${profile.lastName}"required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="department" class="col-sm-2 control-label">Department</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="department" name="department" required>
                            <c:forEach items="${depList}" var="dep">
                                <c:choose>
                                <c:when test="${profile.department == dep.depId}">
                                   <option value="${dep.depId}" selected>${dep.depName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${dep.depId}">${dep.depName}</option>
                                </c:otherwise>
                            </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                   
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-4">                   
                        <input type="email" class="form-control" id="email" name="email" value="${profile.email}" placeholder="email" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="position" class="col-sm-2 control-label">Position</label>
                    <div class="col-sm-4">
                        <div class="col-sm-4">
                            <select class="form-control" id="position" name="position" placeholder="position" required>
                                <c:forEach items="${positionList}" var="position">
                                    <c:choose>
                                <c:when test="${profile.position == position.posId}">
                                    <option value="${position.posId}" selected>${position.posName}</option>
                                </c:when>
                                <c:otherwise>
                                      <option value="${position.posId}" >${position.posName}</option>
                                </c:otherwise>
                            </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="role" class="col-sm-2 control-label">Role</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="gender" name="role" required>
                            <c:forEach items="${roleList}" var="role">
                                <c:choose>
                                <c:when test="${profile.status == role.conValue}">
                                   <option value="${role.conName}" selected>${role.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${role.conName}">${role.conValue}</option>
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
                        <script>
                            
        $(document).ready(function () {
           getPosition();
           
            
            function getPosition(){
                var depId = $('select[id="department"] option:selected').val();
                console.log(depId);
            $.post({
                url:"UserFormServlet?depId="+depId,
                datatype: 'json',
                success:function(response){                    
                    if(response != null){
                        var select = $("#position");
                        select.find("option").remove();
                        $.each(response, function(index, value){
                            $("#position").append($('<option>').text(value.posName).attr('value',value.posId));
                        });
                        
                        $('select[id="position"] option[value=${profile.position}]').attr('selected','selected');
                        
                    }
                    
                    //$("#changed").val("changed");
                   //$("#searchKnl").submit();
                }
           
            });
        }
            
        
        
         $('select[id="department"]').change(function (event){            
            event.preventDefault();
            var depId = $(this).val();
            $.post({
                url:"UserFormServlet?depId="+depId,
                datatype: 'json',
                success:function(response){                    
                    if(response != null){
                        var select = $("#position");
                        select.find("option").remove();
                        $.each(response, function(index, value){
                            $("#position").append($('<option>').text(value.posName).attr('value',value.posId));
                        });
                        
                        
                    }
                    
                    //$("#changed").val("changed");
                   //$("#searchKnl").submit();
                }
            })
            return false;
        });
         


    });

$( "#userFrom" ).validate({
  rules: {
    password: "required",
    passwordComfirm: {
      equalTo: "#password"
    }
  }
});
</script>
<jsp:include page="../include/inc_footer.jsp"/>