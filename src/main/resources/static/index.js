angular.module('market', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/market';

    $scope.showPage = function(pageNumber = 1){
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {'p': pageNumber}
        }).then(function(response){
            $scope.products = response.data.content;
            $scope.navList = $scope.generatePagesIndexes(1, response.data.totalPages);
            console.log(response);
        });
    };

    $scope.deleteProduct = function(id){
        $http({
            url: contextPath + '/api/v1/products/' + id,
            method: 'DELETE',
            params: {'id': id}
        }).then(function(response){
            $scope.showPage();
        });
    };

    $scope.findById = function(id){
        $http({
            url: contextPath + '/api/v1/products/' + id,
            method: 'GET',
            params: {'id': id}
        }).then(function(response){
            console.log(response.data);
            $scope.product = response.data;
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
            let arr = [];
            for (let i = startPage; i < endPage + 1; i++) {
                arr.push(i);
            }
            return arr;
    }

    $scope.showPage();
});