/// <reference path='../refs.ts' />

module security {
  'use strict';

  var adminSecurity: ng.IModule = angular.module("adminSecurity", ['ngCookies', 'ngResource', 'ngSanitize', 'ngRoute', 'restangular']);

  adminSecurity.config(['$routeProvider', ($routeProvider: ng.route.IRouteProvider)=> {
        $routeProvider
        .when("/login", {
          templateUrl: 'views/security/login.html',
          controller: 'LoginCtrl',
          controllerAs: 'ctrl'
        })
        .when("/logout", {
            templateUrl: 'views/security/login.html',
            controller: 'LogoutCtrl',
            controllerAs: 'ctrl'
          })
        .when("/register", {
          templateUrl: 'views/security/register.html',
          controller: 'RegisterCtrl',
          controllerAs: 'ctrl'
        });

      }]).run(['$rootScope', '$location', 'SessionStore', ($rootScope: model.ISecurityScope, $location: ng.ILocationService, sessionStore: service.ISessionStore)=> {

        //Добавляем функцию проверки роли
        $rootScope.hasRole = (role: string): boolean=> {
          var session: model.Session = sessionStore.getSession();
          if (session && session.roles) {
            return session.roles.indexOf(role) >= 0;
          }
          return false;
        };

        //Добавляем функцию проверки авторизованности
        $rootScope.isAuthenticated = (): boolean=> {
          return sessionStore.getSession() != null;
        };


        //Проверяем переходы
        $rootScope.$on("$routeChangeStart", (event: ng.IAngularEvent, next: any, current: any)=> {
          var access: model.Access = next.access;
          if (access) {
            var hasAccess = true;
            if (access.hasRole)
              hasAccess = $rootScope.hasRole(access.hasRole);
            if (access.isAuthenticated)
              hasAccess = $rootScope.isAuthenticated();


            //Если нет доступа то редиректим на login
            if (!hasAccess && next.originalPath != '/login') {
              $location.path("/login");
            }
          }

        });

      }]);

}
