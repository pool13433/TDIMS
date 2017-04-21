<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="../include/inc_header.jsp"/>
<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">Manage Knowledge</div>
        <div class="panel-body">

            <form id="searchKnl" method="get" action="${context}/KnowledgeSearchServlet" class="form-horizontal">          
                <input type="hidden" id="menu" name="menu" value="searching"/>
                <input type="hidden" name="offset" value="${recordCurrent}"/>
                <div class="container">
                    <div class="row">
                        <div class="col-sm-10" >
                            <div class="form-group">
                                <label for="file_name" class="col-sm-2 control-label">File Name</label>
                                <div class="col-sm-8">
                                    <input class="form-control" type="text" name="file_name" id="file_name" value="${file_name}" placeholder="file name...">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-10" >
                            <div class="form-group">
                                <label for="team" class="col-sm-2 control-label">Team</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="team" name="team" >
                                        <option value="" selected>    All Team  </option>
                                        <c:forEach items="${teamList}" var="tm">                            
                                            <c:choose>
                                                <c:when test="${team == tm.teamId}">
                                                    <option value="${tm.teamId}" selected>${tm.teamName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${tm.teamId}">${tm.teamName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>    
                                    </select>
                                </div>                            
                                <label for="type" class="col-sm-2 control-label">Type</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="type" name="type" >
                                        <option value="" selected>    All Type  </option>
                                        <c:forEach items="${typeList}" var="t">                            
                                            <c:choose>
                                                <c:when test="${type == t.id}">                                                    
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
                    <div class="row">
                        <div class="col-sm-10" >
                            <div class="form-group">
                                <label for="details" class="col-sm-2 control-label">Details</label>
                                <div class="col-sm-8">
                                    <input class="form-control" type="text" name="details" id="details" value="${details}" placeholder="detail...">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-sm-offset-6">
                            <button type="submit" class="btn btn-success">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </button>
                            <button type="reset" class="btn btn-warning">
                                <i class="glyphicon glyphicon-erase"></i> Reset
                            </button>
                            <a href="${context}/KnowledgeFormServlet?menu=knowledge_form" class="btn btn-default btn-primary">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>
                </div>
                
            </form>
            <div class="row">
                <div class="col-md-12">
                    <c:import url="../include/inc_pagination.jsp"/>
                </div>
            </div>     
            <div id="msgBox" class="alert alert-warning" hidden="">
                <strong>Warning! </strong><text id="msg" name="msg" value=""></text>
            </div>
            <form id="bookingSim" action="${context}/SimSearchServlet"   method="get" class="form-horizontal">
                <input type="hidden" id="menu" name="menu" value="booking"/>
                <input type="hidden" id="cancelBooking" name="cancelBooking" value=""/>
                <div style="overflow-y: scroll;max-height: 400px;">                    
                    <table id="sim_search_table" class="table table-bordered table-striped">                        
                        <tr>
                        <th>#</th>
                        <th>FileName</th> 
                        <th>Team</th>
                        <th>Type</th>
                        <th>Details</th>
                        <th>Path</th>  
                        <th>CreateBy</th>
                        <th>CreateDate</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="k" items="${knowledgeList}">
                        <tr>
                            <td  nowrap>        
                                <a href="${context}/KnowledgeFormServlet?knlId=${k.id}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/KnowledgeDeleteServlet?knlId=${k.id}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                            <td>${k.fileName}</td>
                            <td>${k.teamId}</td> 
                            <td>${k.type}</td>
                            <td>${k.details}</td>
                            <td><a id="gotofile" href="${k.path}">${k.path}</a></td>
                            <td>${k.createBy}</td>
                            <td>${k.createDate}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${knowledgeList.isEmpty()}">
                        <tr>                    
                            <td colspan="15"><div class="alert"><span style="padding: 40%">ไม่พบข้อมูลที่ค้นหา</span></div> </td>
                        </tr>
                    </c:if>
                    <c:if test="${knowledgeList == null}">
                        <tr>                    
                            <td colspan="17"><div class="alert"><span style="padding: 40%">กรุณาระบุเงื่อนไขในการค้นหา</span></div> </td>
                        </tr>
                    </c:if>
                </tbody>
                    </table>
                </div>                
                <div class="row">
                    <div class="col-md-12">
                        <c:import url="../include/inc_pagination.jsp"/>
                    </div>
                </div>     
            </form>

        </div>        
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
        /*
         $('select[id="team"]').change(function (event){            
            event.preventDefault();
            var teamId = $(this).val();
            $.get({
                url:"KnowledgeSearchServlet?teamId="+teamId
                ,success:function(response){
                    $("#changed").val("changed");
                    $("#searchKnl").submit();
                }
            })
            return false;
        });
         */


    });
</script>

<jsp:include page="../include/inc_footer.jsp"/>
