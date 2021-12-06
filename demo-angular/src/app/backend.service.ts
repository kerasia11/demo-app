import { Injectable, Type } from '@angular/core';


import { Logger } from './logger.service';
import { Hero } from './hero';

const HEROES = [
        new Hero('Countries', 'Should call backend /gdp/data/countries'),
        new Hero('Max GDP', 'Should call backend /gdp/data/countries/maxGdp'),
        new Hero('Region GDP', 'Should call backend /gdp/data/countries/regions/gdp')
      ];

@Injectable()
export class BackendService {
  constructor(private logger: Logger) {}

  getAll(type: Type<any>): PromiseLike<any[]> {
    if (type === Hero) {
      // TODO: get from the database
      return Promise.resolve<Hero[]>(HEROES);
    }
    const err = new Error('Cannot get object of this type');
    this.logger.error(err);
    throw err;
  }
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/