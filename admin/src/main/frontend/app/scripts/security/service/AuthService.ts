/// <reference path='../../refs.ts' />

module security.service {
  'use strict';

  export interface IAuthService {
    login(credentials: model.Credentials): ng.IPromise<model.Session>;
    register(credentials: model.Credentials): ng.IPromise<model.Session>;
  }

  class AuthService implements IAuthService {

    static $inject = ['Restangular'];

    constructor(private restangular: restangular.IService) {
    }

    login(credentials: model.Credentials): ng.IPromise<model.Session> {
      var check: restangular.IElement = this.restangular.all("authenticate");

      return check.post(credentials);
    }

    register(credentials: model.Credentials): ng.IPromise<model.Session> {
      var check: restangular.IElement = this.restangular.all("register");

      return check.post(credentials);
    }

  }
  angular.module("adminSecurity").service("AuthService", AuthService);

}
