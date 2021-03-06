/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IRoleEditScope extends ng.IScope {
    ctrl: RoleEditController;
    role: m.Role;
    roleId: number;
    $$childTail: any;
    submitted: boolean;
    groups: m.Group[];
  }

  export interface IRoleEditController {
    loadRole(): void;
    saveRole(): void;
    isEdit(): boolean;
    dismissModalWindow(): void;
    isValid(): boolean;
  }

  export class RoleEditController implements IRoleEditController {
    static $inject = ['$scope', '$location', '$modalInstance', 'roleId', 'RoleService', 'GroupService'];

    constructor(private $scope: IRoleEditScope, private $location: ng.ILocationService, private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, private roleId: number, private $roleService: s.IRoleService, private $groupService: s.IGroupService) {
      $scope.groups = [];
      $scope.ctrl = this;
      $scope.roleId = roleId;
      $scope.submitted = false;

      this.loadRole();
      this.loadGroups();
    }

    loadRole(): void {
      if (this.$scope.roleId)
        this.$roleService.getRole(this.$scope.roleId, true).then((role) => this.$scope.role = role);
      else
        this.$scope.role = new m.Role();
    }

    saveRole(): void {
      this.$scope.submitted = true;
      if (this.isValid()) {
        this.$roleService.saveRole(this.$scope.role).then((role: m.Role) => {
          this.$scope.role = role;
          this.$scope.roleId = role.id;
          this.$modalInstance.close(this.$scope.role)
        });
      }
    }

    isEdit(): boolean {
      return !!this.$scope.roleId;
    }

    dismissModalWindow(): void {
      this.$modalInstance.dismiss(this.$scope.role);
    }

    isValid(): boolean {
      return this.$scope.$$childTail.roleForm.$valid;
    }

    loadGroups(): void {
      this.$groupService.getGroups().then(
        (groups) => {
          this.$scope.groups = groups;
        },
        ()=> alert('Error loadGroups')
      );
    }
  }

  angular.module('adminApp')
    .controller('RoleEditCtrl', RoleEditController);
}
