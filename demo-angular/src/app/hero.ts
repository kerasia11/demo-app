let nextId = 1;

export class Hero {
  id: number;
  constructor(
    public name: string,
    public power?: string) {
      this.id = nextId++;
    }
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/