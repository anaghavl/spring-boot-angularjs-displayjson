var app = angular.module('myApp.ChildController', []);
app.controller('childController', function($scope, $http, $stateParams, $state) {

  // Function to call /parents/list API with page no(offset) as param and store response to scope variable
  var callChild = function () {
    $http.get('/child/list',{ params: { parentId: $stateParams.id }}).then( function (response) {
        $scope.data = response.data;
    });
  };

  // Function to go back to the parent page retaining the sort order
  $scope.goBack = function () {
    $state.go('parent', { id: $stateParams.id, pageNo: $stateParams.pageNo, sortDir: $stateParams.sortDir })
  };

  // Init function to initialize variables
  var init = function () {
    callChild();
  };

  init();
});