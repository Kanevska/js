import {Router} from '../router/Router';

export class Services{
    constructor(){
        this.router = new Router();
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
                    $(result_id).append('already exists');
                },
                500:function() {
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
            success: function (response) {
                this.router.route(response);
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
