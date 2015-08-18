/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IUserAvatarScope extends ng.IScope {
    ctrl: UserAvatarController;
    userId: number;
    sourceImage:string;
    croppedImage:string;
  }

  export interface IUserAvatarController {
    saveAvatar(): void;
    dismissModalWindow(): void;

  }

  export class UserAvatarController implements IUserAvatarController {
    static $inject = ['$scope', '$modalInstance', 'userId', 'UserService'];

    constructor(private $scope: IUserAvatarScope, private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, private userId: number, private $userService: s.IUserService) {
      $scope.ctrl = this;
      $scope.userId = userId;
      $scope.sourceImage = null;
      $scope.croppedImage = null;
    }


    saveAvatar(): void {
      this.$userService.setAvatar(this.$scope.userId, this.$scope.croppedImage).then(
        (reason: any)=> {
          if (reason) {
            alert('Ошибка' + reason);
          }
          else {
            this.$modalInstance.close(true)
          }
        },
        ()=> {
          alert('Ошибка');
        });
    }

    dismissModalWindow(): void {
      this.$modalInstance.dismiss(false);
    }

  }

  angular.module('adminApp')
    .controller('UserAvatarCtrl', UserAvatarController);
}
