import {Components} from "../Component";


export class DepartmentForm extends Components {
    constructor(){
        super();
    }

    render(object){
        super.render();
        for(var department in object["department_array"][0]){
            console.log(department.id);
        }
       console.log(object["department_array"][0].id);

        $('#root').append('<body>\n' +
            '\n' +
            '<h3> DEPARTMENTS </h3>\n' +
            '<a class="home" href="/"><span>&#x2302;</span></a>\n' +
            '<form class="addFunction" method="get" action="getDepartmentForm">\n' +
            '    <input class="add" type="submit" value="+">\n' +
            '</form>\n' +
            '\n' +
            '\n' +
            '        <div class="blocks">\n' +
            '\n' +
            '            <table class="text">\n' +
            '                <tr>\n' +
            '                    <td>  <p class="name">'+'</p></td>\n' +
            '                    <td> <p class="address"> Address: '+'</p></td>\n' +
            '                    <td> <p class="address"> Description: '+'</p></td>\n' +
            '                </tr>\n' +
            '            </table>\n' +
            '            <div class="buttons">\n' +
            '\n' +
            '                <form method="get" action="deleteDepartment">\n' +
            '                    <input type="hidden" name="id" '+'>\n' +
            '                    <input class="deleteButton" type="submit" value="X">\n' +
            '                </form>\n' +
            '\n' +
            '\n' +
            '                <form method="get" action="editDepartment">\n' +
            '                    <input type="hidden" name="id" '+'\n' +
            '                    <input class="addButton" type="submit" value="edit">\n' +
            '                </form>\n' +
            '\n' +
            '                <form method="get" action="employeeList">\n' +
            '                    <input type="hidden" name="id" '+'>\n' +
            '                    <input class="listButton" type="submit" value="employee list">\n' +
            '                </form>\n' +
            '            </div>\n' +
            '\n' +
            '        </div>\n' +
            '\n' +
            '</body>');
    }
}