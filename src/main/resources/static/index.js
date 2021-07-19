angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.currentPage = 1;

    $scope.showPage = function(pageNumber = 1){
        $http({
            url:'http://localhost:8080/market/products',
            method:'GET',
            params:{'p': pageNumber}
        }).then(function(response){
            console.log(response);
            $scope.products = response.data.content;
            $scope.totalProducts = response.data.totalElements;
            $scope.totalPages = response.data.totalPages;
        });
    };

    $scope.nextPage = function(){
        if($scope.currentPage < $scope.totalPages){
            $scope.showPage(++$scope.currentPage);
        }
    };

    $scope.prevPage = function(){
        if($scope.currentPage > 1){
            $scope.showPage($scope.currentPage--);
        }
    };

    $scope.deleteProduct = function(id){
        $http({
            url:'http://localhost:8080/market/products/del',
            method:'GET',
            params:{'id': id}
        }).then(function(response){
            $scope.showPage();
        });
    };
    $scope.showPage();

    //    $scope.getAllProducts = function(){
    //        $http({
    //            url:"http://localhost:8080/market/products",
    //            method:"GET",
    //            params:{}
    //        }).then(function(response){
    //            $scope.products = response.data;
    //        });
    //    };
    //    $scope.getAllProducts();
});