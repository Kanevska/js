import {Services} from "../services/Services";

export class Util{

    constructor(component, url){
        alert('from constructor');
        this.component = component;
        this.url = url;
    }


    render(){
        if(this.url == null){
            this.component.rendering();
        }
        else{
            const service = new Services();
            service.getInformation(this.url,this.component);
        }
    }
}