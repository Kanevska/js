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
                200: function() {
                    location.hash=setUrl;
                    new Services().controller();
                },
                409: function() {
                    $(result_id).append('already exists');
                },
                500:function() {
                    alert('error');
                },
            }
        });
    }

    getInformation(url) {
        const router = new Router();
        $.ajax({
            type: 'GET',
            url: url,
            dataType:'json',
            success: function (response) {
                router.route(response);
            },
            error: function () {
                alert('getting error');
            }
        });
    }
    deleteInformation(url,setUrl) {
        jQuery.ajax({
            url: url,
            type: 'DELETE',
            statusCode: {
                204: function() {
                    location.hash=setUrl;
                    new Services().controller();
                },
                409: function() {
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
const action = {
    '/employeeList': (url) => {
        const depId = url.substring(url.indexOf('/',2)+1);
        new Services().getInformation(`/back/departments/editDepartment/${depId}`);
    },
    '/departmentList': () => {
        new Services().getInformation('/back/departments/departmentList');
    },
    '/addDepartment': () => {
        new Router().route();
    },
    '/updateDepartment': (url) => {
        const depId = url.substring(url.indexOf('/',2)+1);
        new Services().getInformation(`/back/departments/editDepartment/${depId}`);
    },
    '/updateEmployee': (url) => {
        const empId = url.substring(url.indexOf('/',2)+1);
        new Services().getInformation(`/back/employee/editEmployee/${empId}`);
    },
    '/addEmployee': () => {
        new Services().getInformation('/back/departments/departmentList');
    },
    default: () => {
        new Router().route();
    }
};