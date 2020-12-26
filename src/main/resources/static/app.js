var app = angular.module('myApp', ['ui.router', 'myApp.ChildController', 'myApp.ParentController']);

app.config(function($stateProvider, $urlRouterProvider) {

  // Add states of the application
  var states = [
  {
    name: 'parent',
    url: '/parent',
    templateUrl: 'parent.html',
    controller: 'parentController'
  },
  {
    name: 'child',
    url: '/child/:id',
    templateUrl: 'child.html',
    controller: 'childController',
    params: {
      id: null
    }
  }];
  states.forEach((state) => $stateProvider.state(state));
  $urlRouterProvider.otherwise('/parent');
});