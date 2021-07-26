angular.module('market', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/market/api/v1';

    $scope.showPage = function(pageNumber = 1){
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {'p': pageNumber}
        }).then(function(response){
            console.log(response);
            $scope.productsPage = response.data.content;
            $scope.navList = $scope.generatePagesIndexes(1, response.data.totalPages);
        });
    };

    $scope.showCart = function(){
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    }

    $scope.deleteProduct = function(id){
        $http({
            url: contextPath + '/products/' + id,
            method: 'DELETE',
            params: {'id': id}
        }).then(function(response){
            $scope.showPage();
        });
    };

    $scope.findById = function(id){
        $http({
            url: contextPath + '/products/' + id,
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


    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.placeAnOrder = function() {
        $http({
            url: contextPath + '/cart/order',
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.showCart();
        });
    }

    $scope.showPage();
    $scope.showCart()
});