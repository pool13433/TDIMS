<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="menu" value="${param.menu}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test & Devices Information Management System (i-Test)</title>        
        <link href='https://fonts.googleapis.com/css?family=Marmelad' rel="stylesheet" type="text/css">
        <link href="${context}/asset/jquery-ui/jquery-ui.css" rel="stylesheet" type="text/css">        
        <link href="${context}/asset/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="${context}/asset/bootstrap/css/dashboard.css" rel="stylesheet" type="text/css" />
        <link href="${context}/asset/css/app-style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${context}/asset/js/jquery.2.2.4.js"></script>
        <script type="text/javascript" src="${context}/asset/bootstrap/js/bootstrap.js"></script>        
        <script type="text/javascript" src="${context}/asset/jquery-ui/jquery-ui.js"></script>    
        <script type="text/javascript" src="${context}/asset/js/app-core.js"></script>
        <script type="text/javascript" src="${context}/asset/js/Chart.bundle.min.js"></script>
        <script type="text/javascript" src="${context}/asset/js/Chart.bundle.js"></script>
        <style type="text/css">
            body *{font-family: 'Marmelad', sans-serif;}
            a{color: #0B0F00;font-weight: bold}

            /*boostrap checkbox*/
            .btn span.glyphicon{
                opacity: 0;
            }
            .btn.active span.glyphicon{
                opacity: 1;
            }

        </style>
    </head>
    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #B2D234;">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#" style="color: #0B0F00;">Test & Devices Information Management System (i-Test) ${menu}</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${empty USER_PROFILE}">
                            <li><a href="${context}/jsp/login.jsp?menu=login" style="color: #0B0F00;">Login</a></li>
                            <li><a href="${context}/RegisterServlet" style="color: #0B0F00;">Register</a></li>
                            </c:if>
                            <c:if test="${!empty USER_PROFILE}">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #0B0F00;">ยินดีต้อนรับคุณ ${USER_PROFILE.username} <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${context}/jsp/authen/password.jsp">เปลี่ยนรหัสผ่าน</a></li>
                                    <li><a href="${context}/ChangeProfileServlet">แก้ไขข้อมูลส่วนตัว</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="${context}/LogoutServlet">Sign Out</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <form class="navbar-form navbar-right">
                        <input type="text" class="form-control" placeholder="Search...">
                    </form>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">


                <div class="col-sm-3 col-md-2 sidebar">
                    <c:if test="${empty USER_PROFILE}">
                        <ul class="nav nav-sidebar">
                            <li><a href="${context}/jsp/login.jsp?menu=login" style="color: #0B0F00;">Login</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${!empty USER_PROFILE}">
                        <ul class="nav nav-sidebar">
                            <li class="<c:out value="${menu == 'dashboard' ? 'active': ''}"/>">
                                <a href="${context}/jsp/dashboard.jsp?menu=dashboard"><i class="glyphicon glyphicon-dashboard"></i> Dashboard <span class="sr-only">(current)</span></a>                            
                            </li>
                        </ul>
                        <ul class="nav nav-sidebar">
                            <li class="<c:out value="${menu == 'sim' ? 'active': ''}"/>">
                                <!--<a href="${context}/SimListServlet?menu=sim"><i class="glyphicon glyphicon-credit-card"></i> Manage SIM</a>-->
                                <a href="${context}/SimSearchServlet?menu=sim_search"><i class="glyphicon glyphicon-credit-card"></i> Manage SIM</a>
                            </li>
                            <li class="<c:out value="${menu == 'expiredsim' ? 'active': ''}"/>">
                                <a href="${context}/ExpiredSimServlet?menu=expiredsim"><i class="glyphicon glyphicon-credit-card"></i> Expired SIM</a>
                            </li>
                            <!--<li class="<c:out value="${menu == 'sim_search' ? 'active': ''}"/>">
                                <a href="${context}/SimSearchServlet?menu=sim_search"><i class="glyphicon glyphicon-credit-card"></i> ค้นหา SIM</a>
                            </li>  -->
                            <li class="<c:out value="${menu == 'sim_history' ? 'active': ''}"/>">
                                <a href="${context}/SimHistoryServlet?menu=sim_history"><i class="glyphicon glyphicon-credit-card"></i> SIM History Log</a>
                            </li> 
                        </ul>
                        <ul class="nav nav-sidebar">
                            <li class="<c:out value="${menu == 'knowledge' ? 'active': ''}"/>">                                                            
                                <a href="${context}/KnowledgeListServlet?menu=knowledge"><i class="glyphicon glyphicon-education"></i> จัดการ Knowledge</a>
                            </li>
                            <li class="<c:out value="${menu == 'knowledge_search' ? 'active': ''}"/>">
                                <a href="${context}/KnowledgeSearchServlet?menu=knowledge"><i class="glyphicon glyphicon-education"></i> ค้นหา Knowledge</a>
                            </li>
                        </ul>
                        <ul class="nav nav-sidebar">
                            <li class="<c:out value="${menu == 'testcase' ? 'active': ''}"/>">
                                <a href="${context}/TestcaseListServlet?menu=testcase"><i class="glyphicon glyphicon-folder-open"></i> จัดการ Testcase</a>
                            </li>
                            <li class="<c:out value="${menu == 'testcase_search' ? 'active': ''}"/>">
                                <a href="${context}/TestcaseSearchServlet?menu=testcase_search"><i class="glyphicon glyphicon-folder-open"></i> ค้นหา Testcase</a>
                            </li>
                        </ul>
                        <ul class="nav nav-sidebar">
                            <li class="<c:out value="${menu == 'user' ? 'active': ''}"/>">
                                <a href="${context}/UserListServlet?menu=user"><i class="glyphicon glyphicon-user"></i> จัดการ User</a>
                            </li>                      
                            <li class="<c:out value="${menu == 'project' ? 'active': ''}"/>">
                                <a href="${context}/ProjectListServlet?menu=project"><i class="glyphicon glyphicon-ban-circle"></i> จัดการ Project</a>
                            </li>

                            <li class="<c:out value="${menu == 'department' ? 'active': ''}"/>">
                                <a href="${context}/DepertmentListServlet?menu=department"><i class="glyphicon glyphicon-list-alt"></i> จัดการ Department</a>
                            </li>
                            <li class="<c:out value="${menu == 'position' ? 'active': ''}"/>">
                                <a href="${context}/PositionListServlet?menu=position"><i class="glyphicon glyphicon-map-marker"></i> จัดการ Position</a>
                            </li>
                            <li class="<c:out value="${menu == 'team' ? 'active': ''}"/>">
                                <a href="${context}/TeamListServlet?menu=team"><i class="glyphicon glyphicon-tasks"></i> จัดการ Team</a>
                            </li>
                            
                            <li class="<c:out value="${menu == 'configuration' ? 'active': ''}"/>">
                                <a href="${context}/ConfigListServlet?menu=configuration"><i class="glyphicon glyphicon-cog"></i> จัดการ Configuration</a>
                            </li>
                        </ul>
                        <ul class="nav nav-sidebar">                       
                            <li class="<c:out value="${menu == 'report' ? 'active': ''}"/>">
                                <a href="${context}/ReportServlet?menu=report"><i class="glyphicon glyphicon-file"></i> รายงาน</a>
                            </li>
                        </ul>
                    </c:if>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">      
