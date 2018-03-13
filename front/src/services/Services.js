import {Router, routing} from '../router/Router';

let router = new WeakMap();

export class Services{
    constructor(){
        router.set(this, new Router());
    }

    sendInformation(result_id,formName,url,component) {
        jQuery.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            data: jQuery('#' + formName).serialize(),
            statusCode: {
                200: function() {
                    component.rendering();
                },
                409: function() {
                    alert('error');
                }
            }
        });
    }

    getInformation(url,component,router) {
console.log(url);
        $.ajax({
            type: 'GET',
            url: url,
            dataType:'json',
            success: function (response) {
                router.route(component,response);
                //component.render(response);

            },
            error: function () {
                alert('getting error');
            }
        });
    }
    deleteInformation(url,component) {
        jQuery.ajax({
            url: url,
            type: 'DELETE',
            statusCode: {
                204: function() {
                    component.rendering();
                },
                409: function() {
                    alert('error');
                }
            }
        });
    }
}





export function sendInformation(result_id,formName,url,endUrl) {
    jQuery.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: jQuery('#' + formName).serialize(),
        statusCode: {
            200: function() {
                history.pushState({}, '',endUrl);
                routing();
            },
            409: function() {
                alert('error');
            }
        }
    });
}

export function getInformation(url, component) {

    $.ajax({
        type: 'GET',
        url: url,
        dataType:'json',
        success: function (response) {
            component.render(response);
        },
        error: function (response) {
            alert('error');
        }
    });
}
export function deleteInformation(url,endUrl) {
    jQuery.ajax({
        url: url,
        type: 'DELETE',
        statusCode: {
            204: function() {
                history.pushState({}, '',endUrl);
                routing();
            },
            409: function() {
                alert('error');
            }
        }
    });
}
