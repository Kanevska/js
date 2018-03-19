'use strict';


export class Router {


    constructor() {
    }

    route(component){
        component.render();
    }

    route(component, object){
        component.render(object);
    }

}
