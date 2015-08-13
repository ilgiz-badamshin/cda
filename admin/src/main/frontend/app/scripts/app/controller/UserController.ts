/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IUserScope extends ng.IScope {
    ctrl: UserController;
    users: m.User[];
  }
  export interface IUserController {
    getUsers();
  }

  export class UserController implements IUserController {
    static $inject = ['$scope', '$location', 'UserService'];

    constructor(private $scope: IUserScope, private $location: ng.ILocationService, private $userService: s.IUserService) {
      $scope.ctrl = this;
      this.getUsers();
    }

    getUsers() {
      this.$userService.getUsers().then((users) => this.$scope.users = users);
    }
  }

  angular.module('adminApp')
    .controller('UserCtrl', UserController);
}
