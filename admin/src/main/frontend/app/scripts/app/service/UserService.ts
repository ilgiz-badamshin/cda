'use strict';

module cda.app.service {

  import User = cda.app.model.User;

  export interface IUserService {
    getUsers(): ng.IPromise<User[]>;
    getUser(userId: number): ng.IPromise<User>;
    getUserRoleIds(userId: number): ng.IPromise<number[]>;
    addRole(userId: number, roleId: number): ng.IPromise<void>;
    removeRole(userId: number, roleId: number):  ng.IPromise<void>;
    setGroup(userId: number, groupId: number): ng.IPromise<void>;
  }

  class UserService implements IUserService {
    static $inject = ['Restangular', 'locationParams', '$window'];

    constructor(private restangular: restangular.IService, private locationParams, private $window: ng.IWindowService) {
    }

    getUsers(): ng.IPromise<User[]> {
      return this.restangular.all('user').getList();
    }

    getUser(userId: number): ng.IPromise<User> {
      return this.restangular.one('user', userId).get();
    }

    getUserRoleIds(userId: number): ng.IPromise<number[]> {
      return this.restangular.one('user', userId).all('roleIds').getList();
    }

    addRole(userId: number, roleId: number): ng.IPromise<void> {
      return this.restangular.one('user', userId).one('role', roleId).put();
    }

    removeRole(userId: number, roleId: number): ng.IPromise<void> {
      return this.restangular.one('user', userId).one('role', roleId).remove();
    }

    setGroup(userId: number, groupId: number): ng.IPromise<void> {
      return this.restangular.one('user', userId).one('group', groupId).put();
    }
  }

  angular.module("adminApp").service("UserService", UserService);
}
