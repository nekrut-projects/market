angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/market';

    $scope.showCart = function(){
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function(){
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function(response){
            $scope.showCart();
        });
    };

    $scope.delItemCart = function(id){
        $http({
            url: contextPath + '/api/v1/cart/del/' + id,
            method: 'GET'
        }).then(function(response){
            $scope.showCart();
        });
    };


    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.decreaseQuantity = function(itemId) {
        $http({
            url: contextPath + '/api/v1/cart/item/' + itemId + '/decrease/',
            method: 'GET'
        }).then(function() {
            $scope.showCart();
        });
    }

    $scope.increaseQuantity = function(itemId) {
        $http({
            url: contextPath + '/api/v1/cart/item/' + itemId + '/increase/',
            method: 'GET'
        }).then(function(){
            $scope.showCart();
        });
    }

    $scope.createOrder = function() {
            $http({
                url: contextPath + '/api/v1/orders/add',
                method: 'POST',
                params: {
                    address: $scope.order_info.address,
                    phone: $scope.order_info.phone
                }
            }).then(
                function successCallback(response) {
                    alert('Заказ создан');
                    $scope.showCart();
                },
                function errorCallback(response) {
                    alert(response.data.messages);
                }
            );
        }
    $scope.showCart();
});