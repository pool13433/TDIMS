<%-- 
    Document   : testSomething
    Created on : Mar 9, 2017, 4:22:53 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-app="myApp">
            <div ng-controller="myCtrl">
                <input type="text" ng-change="myFunc()" ng-model="myValue">
                <p>something change is : {{count}}</p>
            </div> 
        
        <script type="text/javascript">
            angular.module('myApp', [])
                    .controller('myCtrl', ['$scope', function($scope){
                         $scope.count = 0;
                         $scope.myFunc = function(){
                             $scope.count++;
                         };
            }]);
        </script>
    </body>
</html>
