/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IRoleScope extends ng.IScope {
    ctrl: RoleController;
    roles: m.Role[];
    gridOptions:any;
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

      $scope.gridOptions = {
        data: 'roles',
        enableFiltering: true,
        paginationTemplate: "views/main/ui-grid-paging.html",
        paginationPageSize: 25,
        paginationPageSizes: [25, 50, 75],
        enableColumnResizing: true,
        columnDefs: [
          {field: 'id'},
          {
            field: 'name',
            sort: {
              direction: "asc",
              priority: 0,
            },
          },
          {
            field: 'id',
            displayName: '',
            enableFiltering: false,
            enableSorting: false,
            cellTemplate: '<div class="action-buttons"><a href ng-click="grid.appScope.ctrl.openRoleEdit(COL_FIELD)"> <i class="ace-icon fa fa-pencil bigger-130"></i></a></div>'
          }
        ],
      };
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
