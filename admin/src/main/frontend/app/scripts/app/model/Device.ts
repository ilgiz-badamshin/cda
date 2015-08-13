/// <reference path='../../refs.ts'/>
'use strict';

module cda.app.model {

  export class Device {
    id: number;
    udsId: string;
    udsUserId: string;
    userId: number;
    name: string;
    description: string;
    model: string;
    deleted: boolean;
    user: User;

  }

}
