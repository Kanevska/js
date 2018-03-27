import {Router} from '../router/Router';

export class Services{
    constructor(){

    }

    sendInformation(result_id,formName,url,setUrl) {
        jQuery.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            data: jQuery('#' + formName).serialize(),
            statusCode: {
                200: () => {
                    location.hash=setUrl;
                    new Services().controller();
                },
                409: () => {
                    $(result_id).append('already exists');
                },
                500:() => {
                    alert('error');
                },
            }
        });
    }

    getInformation(url) {
        $.ajax({
            type: 'GET',
            url: url,
            dataType:'json',
            success: (response) => {
                new Router().route(response);
            },
            error: () => {
                alert('getting error');
            }
        });
    }
    deleteInformation(url,setUrl) {
        jQuery.ajax({
            url: url,
            type: 'DELETE',
            statusCode: {
                204: () => {
                    location.hash=setUrl;
                    new Services().controller();
                },
                409: () => {
                    alert('error');
                }
            }
        });
    }
    controller(){
        const hash = location.hash;
        let end = hash.indexOf('/',2);
        if(end<0){
            end = hash.length;
        }
        const url = hash.substring(1,end);
        action[url](hash);
    }
}
const service = new Services();
const router = new Router();
const action = {
    '/employeeList': (url) => {
        const depId = url.substring(url.indexOf('/',2)+1);
        service.getInformation(`/back/departments/editDepartment/${depId}`);
    },
    '/departmentList': () => {
        service.getInformation('/back/departments/departmentList');
    },
    '/addDepartment': () => {
        router.route();
    },
    '/updateDepartment': (url) => {
        const depId = url.substring(url.indexOf('/',2)+1);
        service.getInformation(`/back/departments/editDepartment/${depId}`);
    },
    '/updateEmployee': (url) => {
        const empId = url.substring(url.indexOf('/',2)+1);
        service.getInformation(`/back/employee/editEmployee/${empId}`);
    },
    '/addEmployee': () => {
        service.getInformation('/back/departments/departmentList');
    },
    default: () => {
        router.route();
    }
};