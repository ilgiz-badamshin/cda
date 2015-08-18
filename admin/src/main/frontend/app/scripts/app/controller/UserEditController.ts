/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IUserEditScope extends ng.IScope {
    ctrl: UserEditController;
    userId: number;
    user: m.User;
    tab: number;
    roles: m.Role[];
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

    addRole(roleId: number): void;

    removeRole(roleId: number): void;

    loadGroups(): void;

    setEditGroup(isEditGroup: boolean): void;

    isEditGroup(): boolean;

    saveUserGroup(): void;

    startWatch() : void;
  }

  interface IRouteParams extends ng.route.IRouteParamsService {
    userId: number;
  }

  export class UserEditController implements IUserEditController {
    static $inject = ['$scope', '$routeParams', '$modal', 'UserService', 'RoleService', 'GroupService'];
    modalServiceInstance: ng.ui.bootstrap.IModalServiceInstance;

    constructor(private $scope: IUserEditScope, private $routeParams: IRouteParams, private $modal: ng.ui.bootstrap.IModalService, private $userService: s.IUserService, private $roleService: s.IRoleService, private $groupService: s.IGroupService) {
      $scope.roles = [];
      $scope.roleIds = [];
      $scope.ctrl = this;
      $scope.userId = $routeParams.userId;
      $scope.isEditGroup = false;

      this.loadUser();
      this.loadGroups();
      this.loadRoles();
      this.setTab(1);
    }

    arrayDiff(a1, a2) {
      var diff = [];
      for (var i = 0; i < a1.length; i++) {
        if (a2.indexOf(a1[i]) < 0) {
          diff.push(a1[i]);
        }
      }

      return diff;
    }

    openEditAvatar() {
      var userId: number = this.$scope.userId;
      this.modalServiceInstance = this.$modal.open({
        templateUrl: 'views/user/user_avatar.html',
        controller: 'UserAvatarCtrl',
        resolve: {
          userId: function () {
            return userId;
          }
        }
      });

      this.modalServiceInstance.result.then((refresh: boolean) => {
        if (refresh) {
          this.$scope.user.avatarUrl = this.$scope.user.avatarUrl + '?' + new Date().getTime();
        }
      });
    }

    private refreshAfterModalClose(refresh: boolean) {
      if (refresh) {
        alert('refresh image');
      }
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
          this.startWatch();
        },
        ()=> alert('Error getUserRoleIds')
      );
    }

    startWatch(): void {
      var that = this;
      this.$scope.$watchCollection('roleIds', function (newValue, oldValue) {
          var inserted = that.arrayDiff(newValue, oldValue);
          for (var k = 0; k < inserted.length; k++) {
            that.addRole(inserted[k]);
          }

          var deleted = that.arrayDiff(oldValue, newValue);
          for (var k = 0; k < deleted.length; k++) {
            that.removeRole(deleted[k]);
          }
        }
      );
    }


    loadGroups() {
      this.$groupService.getGroups().then((groups) => this.$scope.groups = groups);
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
