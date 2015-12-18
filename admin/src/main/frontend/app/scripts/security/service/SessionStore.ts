/// <reference path='../../refs.ts' />

module security.service {
  'use strict';

  export interface ISessionStore {

    createSession(user: model.Session)

    getSession():model.Session;

    clearSession();
  }

  class SessionStore implements ISessionStore {
    static $inject = ['$rootScope', '$cookieStore'];

    private static SESSION_KEY: string = "session";

    constructor(private $rootScope: model.ISecurityScope, private $cookieStore: ng.cookies.ICookieStoreService) {
      var session: model.Session = this.$cookieStore.get(SessionStore.SESSION_KEY);
      if (session)
        this.createSession(session);
    }

    createSession(session: model.Session) {
      this.$rootScope.session = session;
      this.$cookieStore.put(SessionStore.SESSION_KEY, session);
    }

    getSession(): model.Session {
      return this.$rootScope.session;
    }

    clearSession() {
      this.$rootScope.session = undefined;
      this.$cookieStore.remove(SessionStore.SESSION_KEY);
    }

  }
  angular.module("adminSecurity").service("SessionStore", SessionStore);

}
