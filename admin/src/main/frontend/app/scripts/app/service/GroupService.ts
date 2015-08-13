'use strict';

module cda.app.service {

  import Group = cda.app.model.Group;

  export interface IGroupService {
    getGroups(): ng.IPromise<Group[]>;
    getGroup(groupId: number): ng.IPromise<Group>;
    saveGroup(group: Group): ng.IPromise<Group>;
  }

  class GroupService implements IGroupService {
    static $inject = ['Restangular', 'locationParams', '$window'];

    constructor(private restangular: restangular.IService, private locationParams, private $window: ng.IWindowService) {
    }

    getGroups(): ng.IPromise<Group[]> {
      return this.restangular.all('group').getList();
    }

    getGroup(groupId: number): ng.IPromise<Group> {
      return this.restangular.one('group', groupId).get();
    }

    saveGroup(group: Group): ng.IPromise<Group> {
      return this.restangular.all('group').post(group);
    }
  }

  angular.module("adminApp").service("GroupService", GroupService);
}
