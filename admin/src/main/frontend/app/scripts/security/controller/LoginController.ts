/// <reference path='../../refs.ts' />

module security.controller {
  'use strict';

  export interface ILoginScope extends ng.IScope {
    credentials:model.Credentials;
    errorMessage:string;
    ctrl: LoginController;
  }

  export class LoginController {

    static $inject = ['$scope', '$location', 'AuthService', 'SessionStore'];

    constructor(private $scope: ILoginScope, private $location: ng.ILocationService, private authService: service.IAuthService, private sessionStore: service.ISessionStore) {
      $scope.ctrl = this;
    }

    login(credentials: model.Credentials) {
      this.authService.login(credentials).then((session: model.Session)=> {
        this.sessionStore.createSession(session);
        this.$location.path("/");
      }, (reason: any)=> {
        this.$scope.errorMessage = "Неправильный email или пароль";
      })
    }

    goToRegister() {
      this.$location.url("/register");
    }

  }

  angular.module('adminSecurity').controller('LoginCtrl', LoginController);
}
