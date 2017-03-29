<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>

<div class="container" style="padding-right: 100px;">    
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Testcase ทั้งหมด</div>
        <div class="panel-body">
            <a href="${context}/TestcaseFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                <c:if test="${!empty message}">
                    <p>Status : ${message}</p>
                </c:if>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    
                    <th>testcase_title</th>
                    <th>testcase_detail</th>
                    <th>systems</th>
                    <th>enviroment</th>
                    <th>defect_no</th>
                    <th>issue_no</th>
                    <th>project_id</th>
                    <th>path_dir</th>
                    <th>create_by</th>
                    <th>createdate</th>               
                </tr>
            </thead>
            <tbody>
                <c:forEach var="testcase" items="${testcaseList}">
                    <tr>                    
                        <td>
                            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                <a href="${context}/TestcaseFormServlet?testcaseId=${testcase.testcaseId}" class="btn btn-default btn-info"><i class="glyphicon glyphicon-pencil"></i></a>
                                <a href="${context}/TestcaseDeleteServlet?testcaseId=${testcase.testcaseId}" onclick="return confirm('ยืนยันการลบข้อมูลนี้')" class="btn btn-default btn-danger" ><i class="glyphicon glyphicon-trash"></i></a>
                            </div>
                        </td>
                        <td>${testcase.testcaseTitle}</td>
                        <td>${testcase.testcaseDetails}</td>
                        <td>${testcase.systems}</td>
                        <td>${testcase.enviroment}</td>
                        <td>${testcase.defectNo}</td>
                        <td>${testcase.issueNo}</td>
                        <td>${testcase.projectId}</td>                        
                        <td>${testcase.pathDir}</td>
                        <td>${testcase.createBy}</td>
                        <td>${testcase.createDate}</td>                    
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<jsp:include page="../include/inc_footer.jsp"/>
