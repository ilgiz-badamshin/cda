/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.controller {

  import s = cda.app.service;
  import m = cda.app.model;

  export interface IDeviceScope extends ng.IScope {
    ctrl: DeviceController;
    devices: m.Device[];
    gridOptions:any;
  }

  export interface IDeviceController {
    getDevices();
  }

  export class DeviceController implements IDeviceController {
    static $inject = ['$scope', '$location', 'DeviceService'];

    constructor(private $scope: IDeviceScope, private $location: ng.ILocationService, private $deviceService: s.IDeviceService) {
      $scope.ctrl = this;
      this.getDevices();


      $scope.gridOptions = {
        data: 'devices',
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
          {field: 'description'},
          {field: 'model'},
          {field: 'deleted'},
          {
            field: 'user',
            cellTemplate: '<span>{{COL_FIELD.lastName}} {{COL_FIELD.firstName}} {{COL_FIELD.middleName}}</span>'
          }
        ],
      };
    }

    getDevices() {
      this.$deviceService.getDevices().then((devices) => this.$scope.devices = devices);
    }
  }

  angular.module('adminApp')
    .controller('DeviceCtrl', DeviceController);
}
