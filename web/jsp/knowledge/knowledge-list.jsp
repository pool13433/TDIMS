<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="../include/inc_header.jsp"/>
<div class="container">
    <div class="panel panel-ais">        
        <div class="panel-heading">แสดงรายการ Knowledge ทั้งหมด</div>
        <div class="panel-body">
            <a href="${context}/KnowledgeFormServlet" class="btn btn-default btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>#</th>
                    <th>#</th>
                    <th>#</th>
                    <th>#</th>        
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
                        <td>XXX</td>
                        <td>XXX</td>
                        <td>XXX</td>
                        <td>XXX</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include/inc_footer.jsp"/>
<script style="javascript">
  
   
</script>