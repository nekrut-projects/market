angular.module('market').controller('statisticsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/market';

    $scope.showStatistics = function(){
        $http({
            url: contextPath + '/statistics',
            method: 'GET',
        }).then(function(response){
//            console.log(response);
//            $scope.showStatistics();
        });
    };

});