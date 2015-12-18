/// <reference path='../../refs.ts' />

module security.service {
  'use strict';

  export interface ILoginService {
    login(credentials: model.Credentials): ng.IPromise<model.Session>;
  }

  class LoginService implements ILoginService {

    static $inject = ['Restangular'];

    constructor(private restangular: restangular.IService) {
    }

    login(credentials: model.Credentials): ng.IPromise<model.Session> {
      var check: restangular.IElement = this.restangular.all("authenticate");

      return check.post(credentials);
    }

  }
  angular.module("adminSecurity").service("LoginService", LoginService);

}
