/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.model {

  export class User {
    id: number;
    udsId: string;
    groupId: number;
    userName: string;
    userUri: string;
    lastName: string;
    firstName: string;
    middleName: string;
    vksNumber: string;
    mobilePhone: string;
    workPhone: string;
    orgName: string;
    positionName: string;
    avatarUrl: string;
    insertedAt: number;
    updatedAt: number;
    deleted: boolean;
    group: Group;
  }

}
