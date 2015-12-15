/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IUserScope extends ng.IScope {
    ctrl: UserController;
    users: m.User[];
    pagingOptions:any;
    gridOptions:any;
  }
  export interface IUserController {
    getUsers();
  }

  export class UserController implements IUserController {
    static $inject = ['$scope', '$location', 'UserService'];

    constructor(private $scope: IUserScope, private $location: ng.ILocationService, private $userService: s.IUserService) {
      $scope.ctrl = this;
      this.getUsers();

      $scope.gridOptions = {
        data: 'users',
        enableFiltering: true,
        paginationTemplate: "views/main/ui-grid-paging.html",
        paginationPageSize: 25,
        paginationPageSizes: [25, 50, 75],
        enableColumnResizing: true,
        columnDefs: [
          {field: 'id'},
          {field: 'sort'},
          {
            field: 'userName',
            sort: {
              direction: "asc",
              priority: 0,
            },
          },
          {field: 'userUri'},
          {field: 'lastName'},
          {field: 'firstName'},
          {field: 'middleName'},
          {field: 'vksNumber'},
          {field: 'mobilePhone'},
          {field: 'workPhone'},
          {field: 'orgName'},
          {field: 'positionName'},
          {field: 'deleted'},
          {field: 'group.name'},
          {
            field: 'id',
            displayName: '',
            enableFiltering: false,
            enableSorting: false,
            cellTemplate: '<div class="action-buttons"><a href="#/user/{{COL_FIELD}}" class="green" ><i class="ace-icon fa fa-pencil bigger-130"></i></a></div>'
          }
        ],
      };
    }

    getUsers() {
      this.$userService.getUsers().then((users) => {
        this.$scope.users = users;
      });
    }
  }

  angular.module('adminApp')
    .controller('UserCtrl', UserController);
}
