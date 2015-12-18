/// <reference path='../../refs.ts' />

module security.controller {
  'use strict';

  export interface IAuthScope extends ng.IScope {
    ctrl: AuthController;
    isAuthenticated(): boolean;
  }

  export class AuthController {

    static $inject = ['$scope', '$location', 'SessionStore'];

    constructor(private $scope: IAuthScope, private $location: ng.ILocationService, private sessionStore: service.ISessionStore) {
      $scope.ctrl = this;
    }

    logout() {
      this.$location.path('/logout');
    }

    getFullName() {
      if (this.$scope.isAuthenticated()) {
        return this.sessionStore.getSession().fullName;
      }
      else {
        return '';
      }
    }

    goToRegister() {
      this.$location.url("/register");
    }

  }

  angular.module('adminSecurity')
      .controller('AuthCtrl', AuthController)
      .directive('logOut', function () {
        return {
          templateUrl: 'views/security/log_out.html'
        };
      });
}
