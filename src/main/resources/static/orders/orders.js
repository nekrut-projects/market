angular.module('market').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/market';

    $scope.createOrder = function() {
        $http({
            url: contextPath + '/api/v1/orders/add',
            method: 'POST'
        }).then(function (response) {
            $scope.showUserOrders();
            $scope.showCart();
            console.log(response);
        });
    }

    $scope.showUserOrders = function() {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
            console.log(response);
        });
    }

    $scope.showInfoOrder = function(orderId){
        $http({
            url: contextPath + '/api/v1/order/' + orderId,
            method: 'GET'
        }).then(function(response){
            $scope.orderItems = response.data;
            console.log(response);
        });
    }

    $scope.showUserOrders();
});