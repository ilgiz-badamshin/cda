/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IDeviceScope extends ng.IScope {
    ctrl: DeviceController;
    devices: m.Device[];
  }

  export interface IDeviceController {
    getDevices();
  }

  export class DeviceController implements IDeviceController {
    static $inject = ['$scope', '$location', 'DeviceService'];

    constructor(private $scope: IDeviceScope, private $location: ng.ILocationService, private $deviceService: s.IDeviceService) {
      $scope.ctrl = this;
      this.getDevices();
    }

    getDevices() {
      this.$deviceService.getDevices().then((devices) => this.$scope.devices = devices);
    }



  }

  angular.module('adminApp')
    .controller('DeviceCtrl', DeviceController);
}
