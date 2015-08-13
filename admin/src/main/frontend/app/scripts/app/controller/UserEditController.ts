/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IUserEditScope extends ng.IScope {
    ctrl: UserEditController;
    user: m.User;
    userId: number;
    tab: number;
    roles: m.Role[];
    selectedRoles: m.Role[] ;
    availableRoles: m.Role[];
    roleIds: number[];
    groups: m.Group[];
    selectedGroupId: number;
    isEditGroup: boolean;
  }

  export interface IUserEditController {
    loadUser(): void;

    setTab(tab: number): void;

    isCurrentTabSelected(tab: number) : boolean;

    loadRoles(): void;

    loadUserRoleIds(): void;

    updateRoles(): void;

    addRole(roleId: number): void;

    removeRole(roleId: number): void;

    loadGroups(): void;

    setEditGroup(isEditGroup: boolean): void;

    isEditGroup(): boolean;

    saveUserGroup(): void;
  }

  interface IRouteParams extends ng.route.IRouteParamsService {
    userId: number;
  }

  export class UserEditController implements IUserEditController {
    static $inject = ['$scope', '$routeParams', 'UserService', 'RoleService', 'GroupService'];

    constructor(private $scope: IUserEditScope, private $routeParams: IRouteParams, private $userService: s.IUserService, private $roleService: s.IRoleService, private $groupService: s.IGroupService) {
      $scope.roles = [];
      $scope.roleIds = null;
      $scope.ctrl = this;
      $scope.userId = $routeParams.userId;
      $scope.isEditGroup = false;

      this.setTab(1);
      this.loadUser();
      this.loadGroups();
      this.loadRoles();

      $scope.$watchCollection('roleIds', function (newValue, oldValue) {
        $scope.ctrl.updateRoles();
      });
    }

    loadUser(): void {
      this.$userService.getUser(this.$scope.userId).then((user) => {
        this.$scope.user = user;
        this.$scope.selectedGroupId = user.groupId;
      });
    }

    setTab(tab: number) {
      this.$scope.tab = tab;
    }

    isCurrentTabSelected(tab: number): boolean {
      return this.$scope.tab === tab;
    }

    loadRoles(): void {
      this.$roleService.getRoles().then(
        (roles) => {
          this.$scope.roles = roles;
          this.loadUserRoleIds()
        },
        ()=> alert('Error getRoles')
      );
    }

    loadUserRoleIds(): void {
      this.$userService.getUserRoleIds(this.$scope.userId).then(
        (roleIds) => {
          this.$scope.roleIds = roleIds;
        },
        ()=> alert('Error getUserRoleIds')
      );
    }


    loadGroups() {
      this.$groupService.getGroups().then((groups) => this.$scope.groups = groups);
    }

    updateRoles(): void {
      this.$scope.selectedRoles = [];
      this.$scope.availableRoles = [];

      for (var i = 0; i < this.$scope.roles.length; i++) {
        var role: m.Role = this.$scope.roles[i];
        var inArray = false;
        for (var k = 0; k < this.$scope.roleIds.length; k++) {
          var roleId = this.$scope.roleIds[k];
          if (role.id == roleId) {
            inArray = true;
            break;
          }
        }
        if (inArray) {
          this.$scope.selectedRoles.push(role);
        }
        else {
          this.$scope.availableRoles.push(role);
        }
      }
    }

    addRole(roleId: number): void {
      this.$userService.addRole(this.$scope.userId, roleId).then(
        (reason: any)=> {
          if (reason) {
            alert('Ошибка');
          }
          else {
            this.$scope.roleIds.push(roleId);
          }
        },
        ()=> {
          alert('Ошибка');
        });
    }

    removeRole(roleId: number): void {
      this.$userService.removeRole(this.$scope.userId, roleId).then(
        (reason: any)=> {
          if (reason) {
            alert('Ошибка');
          }
          else {
            for (var index = 0; index < this.$scope.roleIds.length; index++) {
              if (roleId == this.$scope.roleIds[index]) {
                this.$scope.roleIds.splice(index, 1);
                break;
              }
            }
          }
        },
        ()=> {
          alert('Ошибка');
        });
    }

    setEditGroup(isEditGroup: boolean): void {
      this.$scope.isEditGroup = isEditGroup;
    }

    isEditGroup(): boolean {
      return this.$scope.isEditGroup;
    }

    saveUserGroup(): void {
      this.$userService.setGroup(this.$scope.userId, this.$scope.selectedGroupId).then(()=> {
        for (var i = 0; i < this.$scope.groups.length; i++) {
          if (this.$scope.groups[i].id == this.$scope.selectedGroupId) {
            this.$scope.user.groupId = this.$scope.selectedGroupId;
            this.$scope.user.group = this.$scope.groups[i];
            break;
          }
        }
        this.setEditGroup(false);
      });
    }
  }

  angular.module('adminApp')
    .controller('UserEditCtrl', UserEditController);
}
