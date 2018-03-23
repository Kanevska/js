import {Components} from '../Component';

export class ErrorPage extends Components {

    constructor() {
        super('departmentList');
    }

    render() {
        let root = '#root';
        $(root).empty().append($('<h1/>').text('404').class('numberError'));
        $(root).append($('<p/>').text('Error Msg').class('errorMsg'));
    }
}