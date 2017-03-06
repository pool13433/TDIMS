<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Knowledge ทั้งหมด</div>
        <div class="panel-body">
        <form id="searching" method="get" action="${context}/KnowledgeSearchServlet">
            <input type="hidden" id="searchFlag" name="searchFlag" />
            <div class="panel-body" >
            <div class="form-group">  
                <div class="row">
                    <h3><span class="label label-primary">Search Criteria</span></h3>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="checkbox-inline">
                            <label><input type="checkbox"  name="createBy" id="createBy" value="${createBy}"/> createBy</label>
                        </div>
                        <div class="checkbox-inline">
                            <label><input type="checkbox"  name="projectId" id="projectId" value="${projectId}"/> project</label>
                        </div> 
                    </div>
                       
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <input class="form-control" type="text" name="searchBox" id="searchBox" value="${searchBox}" placeholder="Search mobile...">
                    </div>
                    <div>
                        <button type="submit" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
                   
                                 
            </div>                      
        </div>
        </form>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>ProjectId</th>
                    <th>ServerName</th>
                    <th>path Folder</th>
                    <th>CreateBy</th>        
                </tr>
            </thead>
            <tbody>
                <c:forEach var="knl" items="${knowledgeList}">
                    <tr>                    
                        <td>
                            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                <a href="${context}/KnowledgeFormServlet?simId=${knl.id}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/KnowledgeDeleteServlet?simId=${knl.id}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </td>
                        <td>${knl.projectId}</td>
                        <td>${knl.serverName}</td>
                        <td><a href="${context}/KnowledgeListServlet?pathDir=${knl.pathFolder}" id="pathOpen" name="pathOpen">${knl.pathFolder}</a></td>
                        <td>${knl.createBy}</td>                  
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