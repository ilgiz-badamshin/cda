/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IGroupEditScope extends ng.IScope {
    ctrl: GroupEditController;
    group: m.Group;
    groupId: number;
    $$childTail: any;
    submitted: boolean;
  }

  export interface IGroupEditController {
    loadGroup(): void;
    saveGroup(): void;
    isEdit(): boolean;
    dismissModalWindow(): void;
    isValid(): boolean;
  }

  export class GroupEditController implements IGroupEditController {
    static $inject = ['$scope', '$location', '$modalInstance', 'groupId', 'GroupService'];

    constructor(private $scope: IGroupEditScope, private $location: ng.ILocationService, private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, private groupId: number, private $groupService: s.IGroupService) {
      $scope.ctrl = this;
      $scope.groupId = groupId;
      $scope.submitted = false;

      this.loadGroup();
    }

    loadGroup(): void {
      if (this.$scope.groupId)
        this.$groupService.getGroup(this.$scope.groupId).then((group) => this.$scope.group = group);
      else
        this.$scope.group = new m.Group();
    }

    saveGroup(): void {
      this.$scope.submitted = true;
      if (this.isValid()) {
        this.$groupService.saveGroup(this.$scope.group).then((group: m.Group) => {
          this.$scope.group = group;
          this.$scope.groupId = group.id;
          this.$modalInstance.close(this.$scope.group)
        });
      }
    }

    isEdit(): boolean {
      return !!this.$scope.groupId;

    }

    dismissModalWindow(): void {
      this.$modalInstance.dismiss(this.$scope.group);
    }

    isValid(): boolean {
      return this.$scope.$$childTail.groupForm.$valid;
    }
  }

  angular.module('adminApp')
    .controller('GroupEditCtrl', GroupEditController);
}
