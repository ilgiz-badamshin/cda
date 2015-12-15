/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IGroupScope extends ng.IScope {
    ctrl: GroupController;
    groups: m.Group[];
    gridOptions:any;
  }

  export interface IGroupController {
    getGroups();
  }

  export class GroupController implements IGroupController {
    static $inject = ['$scope', '$location', '$modal', 'GroupService'];
    modalServiceInstance: ng.ui.bootstrap.IModalServiceInstance;

    constructor(private $scope: IGroupScope, private $location: ng.ILocationService, private $modal: ng.ui.bootstrap.IModalService, private $groupService: s.IGroupService) {
      $scope.ctrl = this;
      this.getGroups();

      $scope.gridOptions = {
        data: 'groups',
        enableFiltering: true,
        paginationTemplate: "views/main/ui-grid-paging.html",
        paginationPageSize: 25,
        paginationPageSizes: [25, 50, 75],
        enableColumnResizing: true,
        columnDefs: [
          {field: 'id'},
          {field: 'sort'},
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
            cellTemplate: '<div class="action-buttons"><a href ng-click="grid.appScope.ctrl.openGroupEdit(COL_FIELD)"> <i class="ace-icon fa fa-pencil bigger-130"></i></a></div>'
          }
        ],
      };
    }

    getGroups() {
      this.$groupService.getGroups().then((groups) => this.$scope.groups = groups);
    }

    openGroupEdit(groupId: number) {
      this.modalServiceInstance = this.$modal.open({
        templateUrl: 'views/group/group_edit.html',
        controller: 'GroupEditCtrl',
        resolve: {
          groupId: function () {
            return groupId;
          }
        }
      });

      this.modalServiceInstance.result.then((selectedItem: m.Group) => this.refreshAfterModalClose(selectedItem));
    }

    private refreshAfterModalClose(group: m.Group) {
      if (group != null) {
        var replaced: boolean = this.replaceProjectInList(group);
        if (!replaced) {
          this.$scope.groups.push(group);
        }
      }
    }

    private replaceProjectInList(group: m.Group): boolean {
      for (var i = 0; i < this.$scope.groups.length; i++) {
        if (this.$scope.groups[i].id == group.id) {
          this.$scope.groups[i] = group;
          return true;
        }
      }
      return false;
    }

  }

  angular.module('adminApp')
    .controller('GroupCtrl', GroupController);
}
