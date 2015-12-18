/// <reference path='../../refs.ts' />

module security.controller {
  'use strict';

  export interface IRegisterScope extends ng.IScope {
    credentials:model.Credentials;
    errorMessage:string;
    ctrl: RegisterController;
  }

  export class RegisterController {

    static $inject = ['$scope', '$location', 'AuthService', 'SessionStore'];

    constructor(private $scope: IRegisterScope, private $location: ng.ILocationService, private authService: service.IAuthService, private sessionStore: service.ISessionStore) {
      $scope.ctrl = this;
    }

    register(valid: boolean, credentials: model.Credentials) {
      if (!valid) {
        return null;
      }
      this.authService.register(credentials).then((session: model.Session)=> {
        this.sessionStore.createSession(session);
        this.$location.path("/");
      }, (reason: any)=> {
        this.$scope.errorMessage = "Ошибка регистрации";
      })
    }

    goToLogin() {
      this.$location.url("/login");
    }

  }

  angular.module('adminSecurity').controller('RegisterCtrl', RegisterController);
}
