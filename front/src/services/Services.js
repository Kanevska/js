export class Services{
    constructor(){

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

    getInformation(url,component,router) {
        $.ajax({
            type: 'GET',
            url: url,
            dataType:'json',
            success: function (response) {
                if(router!=null)
                router.route(component,response);

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
