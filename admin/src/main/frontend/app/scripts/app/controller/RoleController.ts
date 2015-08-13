/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IRoleScope extends ng.IScope {
    ctrl: RoleController;
    roles: m.Role[];
  }

  export interface IRoleController {
    getRoles();
  }

  export class RoleController implements IRoleController {
    static $inject = ['$scope', '$location', '$modal', 'RoleService'];
    modalServiceInstance: ng.ui.bootstrap.IModalServiceInstance;

    constructor(private $scope: IRoleScope, private $location: ng.ILocationService, private $modal: ng.ui.bootstrap.IModalService, private $roleService: s.IRoleService) {
      $scope.ctrl = this;
      this.getRoles();
    }

    getRoles() {
      this.$roleService.getRoles().then((roles) => this.$scope.roles = roles);
    }

    openRoleEdit(roleId: number) {
      this.modalServiceInstance = this.$modal.open({
        templateUrl: 'views/role/role_edit.html',
        controller: 'RoleEditCtrl',
        resolve: {
          roleId: function () {
            return roleId;
          }
        }
      });

      this.modalServiceInstance.result.then((selectedItem: m.Role) => this.refreshAfterModalClose(selectedItem));
    }

    private refreshAfterModalClose(role: m.Role) {
      if (role != null) {
        var replaced: boolean = this.replaceProjectInList(role);
        if (!replaced) {
          this.$scope.roles.push(role);
        }
      }
    }

    private replaceProjectInList(role: m.Role): boolean {
      for (var i = 0; i < this.$scope.roles.length; i++) {
        if (this.$scope.roles[i].id == role.id) {
          this.$scope.roles[i] = role;
          return true;
        }
      }
      return false;
    }

  }

  angular.module('adminApp')
    .controller('RoleCtrl', RoleController);
}
