angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/market/api/v1';

    $scope.showPage = function(pageNumber = 1){
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {'p': pageNumber}
        }).then(function(response){
//            console.log(response);
            $scope.productsPage = response.data.content;
            $scope.navList = $scope.generatePagesIndexes(1, response.data.totalPages);
        });
    };

    $scope.showCart = function(){
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
//            console.log(response);
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function(){
        $http({
            url: contextPath + '/cart/clear',
            method: 'GET'
        }).then(function(response){
            $scope.showCart();
        });
    };

    $scope.delItemCart = function(id){
        $http({
            url: contextPath + '/cart/del/' + id,
            method: 'GET'
        }).then(function(response){
            $scope.showCart();
        });
    };

    $scope.deleteProduct = function(id){
        $http({
            url: contextPath + '/products/' + id,
            method: 'DELETE',
        }).then(function(response){
            $scope.showPage();
        });
    };

    $scope.findById = function(id){
        $http({
            url: contextPath + '/products/' + id,
            method: 'GET',
        }).then(function(response){
//            console.log(response.data);
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

    $scope.decreaseQuantity = function(itemId) {
        $http({
            url: contextPath + '/cart/item/' + itemId + '/decrease/',
            method: 'GET'
        }).then(function() {
            $scope.showCart();
        });
    }

    $scope.increaseQuantity = function(itemId) {
        $http({
            url: contextPath + '/cart/item/' + itemId + '/increase/',
            method: 'GET'
        }).then(function(){
            $scope.showCart();
        });
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $scope.showUserOrders();
                }
            }, function errorCallback(response) {
        });
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $scope.orders;
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadOrders = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.createOrder = function() {
        $http({
            url: contextPath + '/orders/add',
            method: 'POST'
        }).then(function (response) {
            $scope.showUserOrders();
            $scope.showCart();
//            console.log(response);
        });
    }

    $scope.showUserOrders = function() {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders/user',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
//            console.log(response);
        });
    }

    if ($localStorage.User) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
        $scope.showUserOrders();
    }


    $scope.showPage();
    $scope.showCart();
    $scope.showUserOrders();
});