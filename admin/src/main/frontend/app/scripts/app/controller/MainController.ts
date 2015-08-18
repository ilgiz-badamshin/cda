/// <reference path='../../refs.ts'/>
'use strict';

module studio.app.controller {

  export interface IMainScope extends ng.IScope {
    ctrl: MainController;
  }

  export class MainController {
    static $inject = ['$scope', '$location'];

    constructor(private $scope: IMainScope, private $location: ng.ILocationService) {
      $scope.ctrl = this;
    }

    headerMenuclass(path) {
      if (this.$location.path().substr(0, path.length) == path) {
        return "active"
      }
      else {
        return ""
      }
    }

    getPageName(): string {
      return this.$location.path();
    }

    getPageDescr(): string {
      return this.$location.path();
    }

  }

  angular.module('adminApp')
    .controller('MainCtrl', MainController)
    .directive('adminHeader', function () {
      return {
        templateUrl: 'views/main/admin_header.html'
      };
    })
    .directive('pageHeader', function () {
      return {
        templateUrl: 'views/main/page_header.html'
      };
    })
    .directive('sidebar', function () {
      return {
        templateUrl: 'views/main/sidebar.html'
      };
    });
}
