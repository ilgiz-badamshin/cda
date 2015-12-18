/// <reference path='../../refs.ts'/>
'use strict';

module security.model {

  export interface ISecurityScope extends ng.IScope {
    hasRole(role: string): boolean;

    isAuthenticated(): boolean;

    session: Session;
  }
}