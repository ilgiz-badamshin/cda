/// <reference path='../../refs.ts' />

module security.factory {
  'use strict';

  angular.module("adminSecurity").factory('SecurityRestangular', ['$location', 'Restangular', 'SessionStore', ($location: ng.ILocationService, restangular: restangular.IService, sessionStore: service.ISessionStore): restangular.IService=> {
    return restangular.withConfig((restangularProvider: restangular.IProvider)=> {

      restangularProvider.setErrorInterceptor(function (response: restangular.IResponse, deferred: ng.IDeferred<any>) {
        if (response.status === 401 || response.status === 403 || response.status === 419 || response.status === 440 || response.status === 403) {

          sessionStore.clearSession();
          $location.path('/login');

          return false;
        }
        return true;
      });


      //Если есть существующая сессия то передаем на сервер информацию о токен
      restangularProvider.addFullRequestInterceptor((element: any, operation: string, what: string, url: string, headers: any, params: any, httpConfig: restangular.IRequestConfig)=> {
        if (sessionStore.getSession()) {
          headers['X-Auth-Token'] = sessionStore.getSession().token;
        }

        return {headers: headers, params: params, element: element, httpConfig: httpConfig};
      });


    });
  }]);
}
