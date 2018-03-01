export function AjaxSendForm(result_id,formName,url) {
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

export function AjaxGetInformation(url) {
    console.log('from ajax')
    $.ajax({
        type: 'GET',
        url: url,
        dataType:"json",
        success: function (response) {
            /* render*/
        },
        error: function (response) {

        }
    });
}