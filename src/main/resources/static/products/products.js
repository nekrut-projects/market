angular.module('market').controller('productsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/market';

    $scope.showPage = function(pageNumber = 1){
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                page: pageNumber,
                minPrice: $scope.filters ? $scope.filters.minPrice : null,
                maxPrice: $scope.filters ? $scope.filters.maxPrice : null,
                title: $scope.filters ? $scope.filters.title : null
            }
        }).then(
              function successCallback(response) {
                  console.log(response);
                  $scope.productsPage = response.data.content;
                  $scope.navList = $scope.generatePagesIndexes(1, response.data.totalPages);
              },
              function errorCallback(response) {
                  alert(response.data.messages);
              }
          );
    };

    $scope.deleteProduct = function(id){
        $http({
            url: contextPath + '/api/v1/products/' + id,
            method: 'DELETE',
        }).then(function(response){
            $scope.showPage();
        });
    };

    $scope.findById = function(id){
        $http({
            url: contextPath + '/api/v1/products/' + id,
            method: 'GET',
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
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
        console.log(response)
            console.log(response)
        });
    }

    $scope.showPage();
});