<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="../include/inc_header.jsp"/>
<div id="exam" class="container" style="padding-right: 100px;">    
    <div id="2" class="panel panel-ais">        
        <div id="3" class="panel-heading">Manage Knowledge</div>
        <div id="4" class="panel-body">

            <form id="searching" method="get" action="${context}/TestcaseSearchServlet" class="form-horizontal">          
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
                                    <input class="form-control" type="text" name="team" id="team" value="${team}" placeholder="dropdown...">
                                </div>                            
                                <label for="type" class="col-sm-2 control-label">Type</label>
                                <div class="col-sm-3">
                                    <input class="form-control" type="text" name="type" id="type" value="${type}" placeholder="dropdown...">
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
                
                
                
                <!-- 
                TO BE CONTINUED
                -->
                
                
                
                
                    <div class="form-group">
                        <div class="col-sm-9">
                            <button type="submit" class="btn btn-success">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </button>
                            <button type="reset" class="btn btn-warning">
                                <i class="glyphicon glyphicon-erase"></i> Reset
                            </button>
                            <a href="${context}/TestcaseFormServlet" class="btn btn-default btn-primary">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
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
                        <th>ProjectId</th>
                        <th>Create By</th>
                        <th>System</th>
                        <th>Enviroment</th>  
                        <th>IssurNO</th>
                        <th>Path Dir</th>
                        <th>Testcase Details</th>
                        <th>Testcase Title</th>
                        <th>Defect No</th>
                        <th>Step</th>                        
                        <th>Create Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tc" items="${testcaseList}">
                        <tr>
                            <td  nowrap>        
                                <a href="${context}/TestcaseFormServlet?testcaseId=${testcase.testcaseId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/TestcaseDeleteServlet?testcaseId=${testcase.testcaseId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                            <td>${tc.projectId}</td>
                            <td>${tc.createBy}</td> 
                            <td>${tc.systems}</td>
                            <td>${tc.enviroment}</td>
                            <td>${tc.issueNo}</td>
                            <td><a id="gotofile" href="${tc.pathDir}">${tc.pathDir}</a></td>
                            <td>${tc.testcaseDetails}</td>
                            <td>${tc.testcaseTitle}</td>
                            <td>${tc.defectNo}</td>
                            <td>${tc.step}</td>
                            <td>${tc.createDate}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${testcaseList.isEmpty()}">
                        <tr>                    
                            <td colspan="15"><div class="alert"><span style="padding: 40%">ไม่พบข้อมูลที่ค้นหา</span></div> </td>
                        </tr>
                    </c:if>
                    <c:if test="${testcaseList == null}">
                        <tr>                    
                            <td colspan="17"><div class="alert"><span style="padding: 40%">กรุณาระบุเงื่อนไขในการค้นหา</span></div> </td>
                        </tr>
                    </c:if>
                </tbody>
                    </table>
                </div>                
                    
            </form>

        </div>        
    </div>        
</div>   

<script type="text/javascript">

    $(document).ready(function () {
        $("#searching").submit(function () {
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
            $.post({
                url:"TestcaseSearchServlet?pathDir="+name
                        ,success:function(){
                            return true;
                        }
            })
            return false;
        });
        


    });
</script>

<jsp:include page="../include/inc_footer.jsp"/>
