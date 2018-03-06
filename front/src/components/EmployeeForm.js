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
             value="${(object != undefined) ? object.departmentName : ''}"
               placeholder="Enter your name">
               
                <input class="fields" type="text" name="email"
               value="<c:out value="${param['email'] eq null ? employee.email : param['email']}"/>"
               placeholder="Enter your email">
               
               
                 <input class="fields" type="date" name="birthday"

               value="${param['birthday'] eq null ? employee.birthday : param['birthday']}"
               placeholder="Enter your birthday">
               
               
               <input class="fields" type="text" name="phoneNumber"
               value="<c:out value="${param['phoneNumber'] eq null ? employee.phoneNumber : param['phoneNumber']}"/>"
               placeholder="Enter your phone number">
               
               
                <input class="fields" type="text" name="salary"
               value="<c:out value="${param['salary'] eq null ? employee.salary : param['salary']}"/>"
               placeholder="Enter salary">
            <input class="fields" type="text" name="departmentName" value="${(object != undefined) ? object.departmentName : ''}" placeholder="Enter department name">
            <input class="fields" type="text" name="description" value="${(object != undefined) ? object.description : ''}" placeholder="Enter department description">
            <input class="fields" type="text" name="address" value="${(object != undefined) ? object.address : ''}" placeholder="Enter department address">
           
           ${(object == undefined) ? '<input class="listButton"value="Add employee" onclick="" style="margin-left: 40%; text-align: center;">'
            : ' <input class="listButton"value="Update employee" onclick="" style="margin-left: 40%; text-align: center;text-align: center; ">'}
           
            </form>
            </div>`);
    }

}