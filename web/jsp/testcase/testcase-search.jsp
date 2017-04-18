<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="../include/inc_header.jsp"/>
<div id="exam" class="container" style="padding-right: 100px;">    
    <div id="2" class="panel panel-ais">        
        <div id="3" class="panel-heading">Manage Testcase</div>
        <div id="4" class="panel-body">

            <form id="searching" method="get" action="${context}/TestcaseSearchServlet" class="form-horizontal">          
                <input type="hidden" id="menu" name="menu" value="searching"/>
                <input type="hidden" name="offset" value="${recordCurrent}"/>
                
                <div class="form-group">
                <div class="col-sm-5">
                    <div class="form-group">
                    <label for="projectSelected" class="col-sm-3 control-label">Project</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="projectSelected" name="projectSelected" placeholder="env">
                            <option value="" selected>   All project  </option>                                                       
                            <c:forEach items="${projectCombo}" var="p">                            
                            <c:choose>
                                <c:when test="${projectSelected == p.id}">
                                    <option value="${p.id}" selected><a href="#">${p.value1}</a></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${p.id}" ><a href="#">${p.value1}</a></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </div>
                    </div>
                    <div class="form-group">
                    <label for="type" class="col-sm-3 control-label">Type</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="type" name="type" >
                            <option value="" selected>    All Type  </option>
                            <c:forEach items="${typeList}" var="t">                            
                                <c:choose>
                                    <c:when test="${type == t.conName}">
                                        <option value="${t.conName}" selected>${t.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${t.conName}">${t.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>    
                        </select>
                    </div>
                    </div>
                    <div class="form-group">
                    <label for="system" class="col-sm-3 control-label">System</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="system" name="system" >
                            <option value="" selected>    All system  </option>
                            <c:forEach items="${systemList}" var="sys">                            
                                <c:choose>
                                    <c:when test="${system == sys.conName}">
                                        <option value="${sys.conName}" selected>${sys.conValue}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${sys.conName}">${sys.conValue}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>    
                        </select>
                    </div>
                    </div>
                    <div class="form-group">
                    <label for="env" class="col-sm-3 control-label">Environment </label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="env" name="env" placeholder="env">
                            <option value="" selected>   All environment  </option>                                                       
                            <c:forEach items="${envList}" var="e">                            
                            <c:choose>
                                <c:when test="${env == e.conName}">
                                    <option value="${e.conName}" selected>${e.conValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${e.conName}">${e.conValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </div>                    
                    </div>
                    <div class="form-group">
                    <label for="startDate" class="col-sm-3 control-label">Start Date </label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control datepicker"  value="${startDate}"  id="startDate" name="startDate" placeholder="startDate" readonly >
                    </div>
                    <label for="toDate" class="col-sm-1 control-label">To</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control datepicker"  value="${toDate}"  id="toDate" name="toDate" placeholder="toDate" readonly >
                    </div>
                    </div>
                </div>

                <div class="col-md-offset-1 col-sm-5">
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">TestCase</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" name="title" id="title" value="${title}" placeholder="testcase...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="details" class="control-label col-sm-3">Details </label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="7" id="details" name="details" value="${details}">${testcase.testcaseDetails}</textarea>
                        </div>
                    </div>
                     
                    <div class="form-group">
                    <label for="createBy" class="col-sm-3 control-label">Tester</label>
                    <div class="col-sm-9">
                        <select class="form-control" class="form-control" id="createBy" name="createBy" >
                            <option value="" selected>    All Tester  </option>
                            <c:forEach items="${ownerList}" var="owner">                            
                                <c:choose>
                                    <c:when test="${createBy == owner.profileId}">
                                        <option value="${owner.profileId}" selected>${owner.username}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${owner.profileId}">${owner.username}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>  
                        </select>
                    </div>
                    </div>
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
