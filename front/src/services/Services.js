


export function AjaxSendInformation(result_id,formName,url) {
    jQuery.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data: jQuery("#" + formName).serialize(),
        success: function (response) {

            /* render*/
        },
        error: function (response) {
            /*do something*/
        }
    });
}

export function AjaxGetInformation(url, component) {

    $.ajax({
        type: 'GET',
        url: url,
        dataType:"json",
        success: function (response) {
            component.render(response);
        },
        error: function (response) {
        }
    });
}