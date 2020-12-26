var app = angular.module('myApp.ParentController', []);
  app.controller('parentController', function($scope, $http, $state, $stateParams) {

    var sortDir;

    /**
     * Function to call /parents/list API with page no(offset)
     * as param and store response to scope variable 
     * */ 
    var callParent = function () {
      $http.get('/parents/list',{ params: { offset: $scope.pageNo, order: sortDir }}).then( function (response) {
        $scope.data = response.data;
      });
    };

    /**
     * Function to call /parents/total API to get the total 
     * number of entries store response to scope variable 
     * */ 
    var noOfEntries = function () {
      $http.get('/parents/total').then( function (response) {
        $scope.totalPages = response.data/2;
      });
    };

    // Function to change the sort direction
    $scope.changeDir = function () {
      if(sortDir === 'desc') {
        sortDir = 'asc';
      } else {
        sortDir = 'desc';
      }
      callParent();
    };

    // Function to calculate page no and call function to get next page data
    $scope.prevPage = function () {
      $scope.pageNo = $scope.pageNo - 1;
      callParent();
    };

    // Function to calculate page no and call function to get next page data
    $scope.nextPage = function () {
      $scope.pageNo = $scope.pageNo + 1;
      callParent();
    };

    $scope.gotoChildren = function (d) {
      $state.go('child', { id: d.id, pageNo: $scope.pageNo, sortDir: sortDir });
    };

    // Init function to initialize variables
    var init = function () {
      /**
       *  If the page is called from child page it has state params
       *  of page no and the sorting direction stored.
       */
      if($stateParams.pageNo && $stateParams.sortDir ){
        $scope.pageNo = $stateParams.pageNo;
        sortDir = $stateParams.sortDir;
      } else{
        $scope.pageNo = 1;
        sortDir = 'asc';
      }

      callParent();
      noOfEntries();
    };

    init();
  });