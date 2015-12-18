/// <reference path='../../refs.ts' />
'use strict';

module security.controller {

  export interface ILogoutScope extends ng.IScope {
    ctrl: LogoutController;
  }

  export class LogoutController {
    static $inject = ['$scope', 'SessionStore', '$location'];

    constructor(private $scope: ILogoutScope, private sessionStore: service.ISessionStore, private $location: ng.ILocationService) {
      $scope.ctrl = this;
      this.sessionStore.clearSession();
      this.$location.url('/');
    }
  }

  angular.module('adminSecurity').controller('LogoutCtrl', LogoutController);
}
