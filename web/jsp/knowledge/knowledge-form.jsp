<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">ฟอร์มกรอกข้อมูล Knowledge</div>
        <div class="panel-body">
            <a href="${context}/KnowledgeSearchServlet?menu=knowledge-search" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-arrow-left"></i></a>
        </div>
        <form action="${context}/KnowledgeSaveServlet" method="post" class="form-horizontal" style="padding-right: 100px;" enctype="multipart/form-data">
            <div class="form-group">
                <label for="fileName" class="col-sm-2 control-label">fileName</label>
                <div class="col-sm-10">
                    <input type="hidden" value="${knowledge.id}" id="knlId" name="knlId">
                    <input type="text" class="form-control" value="${knowledge.fileName}" id="fileName" name="fileName" placeholder="fileName" required>
                </div>
            </div>
             <div class="row">
                        <div class="col-sm-12" >
                            <div class="form-group">
                                <label for="team" class="col-sm-2 control-label">Team</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="team" name="team" >
                                        <option value="" selected>    All Team  </option>
                                        <c:forEach items="${teamList}" var="tm">                            
                                            <c:choose>
                                                <c:when test="${knowledge.teamId == tm.teamName}">
                                                    <option value="${tm.teamId}" selected>${tm.teamName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${tm.teamId}">${tm.teamName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>    
                                    </select>
                                </div>                            
                                <label for="type" class="col-sm-2 control-label">Module</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="type" name="type" >
                                        <option value="" selected>    All Module  </option>
                                        <c:forEach items="${typeList}" var="t">                            
                                            <c:choose>
                                                <c:when test="${knowledge.type == t.moduleName}">                                                    
                                                    <option value="${t.id}" selected>${t.moduleName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${t.id}">${t.moduleName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>    
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="form-group">
                <label for="detail" class="col-sm-2 control-label">Detail</label>
                <div class="col-sm-10">
                                      <textarea class="form-control" rows="7" id="detail" name="detail" value="${knowledge.details}">${knowledge.details}</textarea>

                </div>      
            </div>
            <div class="form-group">
                <label for="pathFolder" class="col-sm-2 control-label">pathFolder</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control"  id="path" name="path" value="${knowledge.path}">
                </div>      
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>
                    <script type="text/javascript">

    $(document).ready(function () {
        $("#changed").val("");
        $("#searchKnl").submit(function () {
            var criteria = {};
            $.each($(this).serializeArray(), function (_, kv) {
                if (kv.value !== "") {
                    criteria[kv.name] = kv.value;
                }
            });
        });
        
        $('a[id="gotofile"]').click(function (event){            
            event.preventDefault();
            var name = $(this).attr('href');
            $.get({
                url:"KnowledgeSearchServlet?path="+name
                        ,success:function(){
                            return true;
                        }
            })
            return false;
        });
        
         $('select[id="team"]').change(function (event){            
            event.preventDefault();
            var teamId = $(this).val();
            $.post({
                url:"KnowledgeSearchServlet?teamId="+teamId,
                datatype: 'json',
                success:function(response){                    
                    if(response != null){
                        var select = $("#type");
                        select.find("option").remove();
                        $.each(response, function(index, value){
                            $("#type").append($('<option>').text(value.moduleName).attr('value',value.id));
                        });
                        
                        
                    }
                    
                    //$("#changed").val("changed");
                   //$("#searchKnl").submit();
                }
            })
            return false;
        });
         


    });
</script>
<jsp:include page="../include/inc_footer.jsp"/>
