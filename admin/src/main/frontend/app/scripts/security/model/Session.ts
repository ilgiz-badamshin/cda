/// <reference path='../../refs.ts' />

module security.model {
  export class Session {
    token: string;
    roles: string[];
    fullName: string;
  }
}