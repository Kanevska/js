import {routing} from "../router/Router";


export function sendInformation(result_id,formName,url,endUrl) {
    jQuery.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data: jQuery("#" + formName).serialize(),
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
        dataType:"json",
        success: function (response) {
            component.render(response);
        },
        error: function (response) {
            alert("error");
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