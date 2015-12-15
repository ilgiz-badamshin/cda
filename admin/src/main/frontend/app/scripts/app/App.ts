'use strict';

module cda.app {
  angular.module('adminApp', ['ngCookies', 'ngResource', 'ngSanitize', 'ngLocale', 'ngRoute', 'restangular', 'ui.bootstrap', 'ui.grid', 'ui.grid.pagination', 'ui.grid.autoResize', 'ui.grid.resizeColumns', 'frapontillo.bootstrap-duallistbox', 'angular-img-cropper'])
    .config(['$routeProvider', ($routeProvider) => {
      $routeProvider
        .when('/', {
          redirectTo: '/user'
        })
        .when('/user', {
          templateUrl: 'views/user/users.html',
          controller: 'UserCtrl',
          controllerAs: 'ctrl',
          access: {
            isAuthenticated: true
          }
        })
        .when('/user/:userId', {
          templateUrl: 'views/user/user_edit.html',
          controller: 'UserEditCtrl',
          controllerAs: 'ctrl',
          access: {
            isAuthenticated: true
          }
        })
        .when('/group', {
          templateUrl: 'views/group/groups.html',
          controller: 'GroupCtrl',
          controllerAs: 'ctrl',
          access: {
            isAuthenticated: true
          }
        })
        .when('/role', {
          templateUrl: 'views/role/roles.html',
          controller: 'RoleCtrl',
          controllerAs: 'ctrl',
          access: {
            isAuthenticated: true
          }
        })
        .when('/role/:roleId', {
          templateUrl: 'views/role/role_edit.html',
          controller: 'RoleEditCtrl',
          controllerAs: 'ctrl',
          access: {
            isAuthenticated: true
          }
        })
        .when('/device', {
          templateUrl: 'views/device/devices.html',
          controller: 'DeviceCtrl',
          controllerAs: 'ctrl',
          access: {
            isAuthenticated: true
          }
        })
        .otherwise({
          redirectTo: '/'
        });
    }
    ]).constant('locationParams', {
      hostname: window.location.hostname,
      port: '8080'
    })
    .config(["RestangularProvider", (Restangular: restangular.IProvider)=> {
      Restangular.setBaseUrl("http://" + window.location.hostname + ":8080/admin/rest/");
    }])
  ;
}
