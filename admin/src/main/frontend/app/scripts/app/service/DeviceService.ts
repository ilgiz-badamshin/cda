'use strict';

module cda.app.service {

  import Device = cda.app.model.Device;

  export interface IDeviceService {
    getDevices(): ng.IPromise<Device[]>;
    getDevice(deviceId: number): ng.IPromise<Device>;
  }

  class DeviceService implements IDeviceService {
    static $inject = ['Restangular', 'locationParams', '$window'];

    constructor(private restangular: restangular.IService, private locationParams, private $window: ng.IWindowService) {
    }

    getDevices(): ng.IPromise<Device[]> {
      return this.restangular.all('device').getList();
    }

    getDevice(deviceId: number): ng.IPromise<Device> {
      return this.restangular.one('device', deviceId).get();
    }
  }

  angular.module("adminApp").service("DeviceService", DeviceService);
}
