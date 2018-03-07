import {Components} from "./Component";

export class EmployeeForm extends Components {

    constructor() {
        super();
    }

    render(object) {
        super.render();
        $('#root').empty().append(`<h3>ADD/UPDATE EMPLOYEE</h3>
            <a class="home" href="/"><span>&#x2302;</span></a>
            <div class="blocksEmployee">
            <form id="employeeForm">
            
             <input class="fields" type="text" name="fullName"
             value="${(object !== undefined) ? object.fullName : ''}"
               placeholder="Enter your name">
               
                <input class="fields" type="text" name="email"
               value="${(object !== undefined) ? object.email : ''}"
               placeholder="Enter your email">
               
               
                 <input class="fields" type="date" name="birthday"

               value="${(object !== undefined) ? object.birthday : ''}"
               placeholder="Enter your birthday">
               
               
               <input class="fields" type="text" name="phoneNumber"
               value="${(object !== undefined) ? object.phoneNumber : ''}"
               placeholder="Enter your phone number">
               
               
                <input class="fields" type="text" name="salary"
               value="${(object !== undefined) ? object.salary : ''}"
                placeholder="Enter salary">
                
                <select class="fields" id="department" name="departmentId" style="float: none;">
${(object === undefined) ? '<input class="listButton" value="Add employee" onclick="" style="margin-left: 40%; text-align: center; ">'
            : '<input class="listButton"value="Update employee" onclick="" style="margin-left: 40%; text-align: center; clear:both; ">'}`);

        for (let i = 0; i < object["department_array"].length; i++) {
            $('#department')
                .append($("<option></option>")
                    .attr("value", object["department_array"][i].id)
                    .text(object["department_array"][i].departmentName));
        }

    }

}