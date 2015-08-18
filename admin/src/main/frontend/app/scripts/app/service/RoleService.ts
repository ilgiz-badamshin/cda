'use strict';

module cda.app.service {

  import Role = cda.app.model.Role;

  export interface IRoleService {
    getRoles(): ng.IPromise<Role[]>;
    getRole(roleId: number, withGroupIds ?: boolean): ng.IPromise<Role>;
    saveRole(role: Role): ng.IPromise<Role>;
  }

  class RoleService implements IRoleService {
    static $inject = ['Restangular'];

    constructor(private restangular: restangular.IService) {
    }

    getRoles(): ng.IPromise<Role[]> {
      return this.restangular.all('role').getList();
    }

    getRole(roleId: number, withGroupIds: boolean = false): ng.IPromise<Role> {
      return this.restangular.one('role', roleId).get({"withGroupIds": withGroupIds});
    }

    saveRole(role: Role): ng.IPromise<Role> {
      return this.restangular.all('role').post(role);
    }
  }

  angular.module("adminApp").service("RoleService", RoleService);
}
