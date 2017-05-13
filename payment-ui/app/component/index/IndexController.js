paymentApp.controller('IndexController', ['$scope', '$http', function IndexController($scope, $http) {
    $http.get('/environment.json')
        .then(function(response) {
            $scope.environment = JSON.parse(JSON.stringify(response.data));
        });

    $scope.payment = {};
    $scope.pay = function () {

        $scope.isProcessing = true;
        $http.post(this.environment.apiUrl + '/payment', this.payment)
            .then(
                function(response) {
                    $scope.hasSuccess = true;
                }, function(response) {
                    $scope.hasSuccess = false;
                }
            ).finally(function () {
                $scope.isProcessing = false;
            }).then(function () {
                setTimeout(function () {
                    $scope.$apply(function () {
                        $scope.hasSuccess = false;
                        $scope.hasError = false;
                        $scope.errorMsg = '';
                    });
                }, 3000);
            });
    }


}]);
