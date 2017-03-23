<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอก ข้อมูลการยืม Sim</div>
        <div class="panel-body">
            <a href="${context}/SimSearchServlet?menu=sim_search" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/SimSearchServlet" method="post" class="form-horizontal">
            <input type="hidden" id="simSelected" name="simSelected" value="${simSelected}"/>                
            <div class="form-group">
                <label for="teamId" class="col-sm-2 control-label">Assign to team</label>
                <div class="col-sm-3">
                    <select class="form-control" id="team" name="team" placeholder="team" required>
                        <option value="" selected>   Please select team   </option>
                        <c:forEach items="${teamList}" var="team">
                            <option value="${team.teamId}|${team.teamEmail}" >${team.teamName}
                           
                                </option>
                            <c:set var="teamEmail" value="${team.teamEmail}"/>
                        </c:forEach>
                    </select>                    
                </div>
                <label for="projectId" class="col-sm-1 control-label">Project</label>
                <div class="col-sm-3">
                    <select class="form-control" id="project" name="project" placeholder="project" required>
                        <option value="" selected>   Please select project   </option>
                        <c:forEach items="${projectList}" var="pj">
                            <option value="${pj.projId}">${pj.projName}</option>
                        </c:forEach>
                    </select>
                </div>               
            </div>
            <div class="form-group">                   
                <label for="emailContact" class="col-sm-2 control-label">Email contact</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" value="${teamEmail}" id="emailContact" name="emailContact"  required>
                </div>                
            </div>            
            <div class="form-group">                
                <label for="status" class="col-sm-2 control-label">Status</label>
                <div class="col-sm-3">
                    <select class="form-control" class="form-control" id="status" name="status" placeholder="status" required>
                        <option value="" selected>   Please select status   </option>      
                            <option value="Avaliable" >Avaliable</option>
                            <option value="Unavaliable" >Unavaliable</option>
                            <option value="Inused" >Inused</option>
                            <option value="Pending" >Pending</option>
                            <option value="Lost" >Lost</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="validDate" class="col-sm-2 control-label">Valid Date</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control datepicker"  value="${sim.validDate}"  id="validDate" name="validDate" placeholder="validDate" readonly required>
                </div>         
                <label for="expireDate" class="col-sm-2 control-label">Expire Date</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control datepicker"  value="${sim.expireDate}"  id="expireDate" name="expireDate" placeholder="expireDate" readonly required>
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-sm-2 control-label">Remark</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="remark" id="remark" placeholder="remark">${sim.remark}</textarea>
                </div>      
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-save"></i> Submit</button>
                </div>                
            </div> 
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $("#team").change(function(){
        var str = "";
        var email = "";
        $("#team option:selected").each(function(){
            str = $("#team").val()+"";
        });
        var subTeam = str.split('|');
        if(subTeam.length > 1){
            email = subTeam[1].toString();
        }
        $("#emailContact").val(email);
    })
    .change();
});
</script>
<jsp:include page="../include/inc_footer.jsp"/>
