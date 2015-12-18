/// <reference path='../../refs.ts' />

module security.model {
  export class Credentials {
    username: string;
    password: string;
    repeatPassword: string;
    fullName: string;
  }
}