require("jquery-validation");
export class Validator{
  constructor(){
      $.validator.addMethod(
          "regex",
          function(value, element, regexp) {
              let re = new RegExp(regexp);
              return this.optional(element) || re.test(value);
          },""
      );
  }


    departmentValidate(){

        return $("form[name='departmentForm']").validate({
            rules: {
                departmentName: {
                    required: true,
                    minlength:2,
                    maxlength:24,
                    regex:/^[A-ZА-Я][a-zа-я\d\s]+/
                },
                description: "required",
                address: {
                    required: true,
                    maxlength:29,
                     regex:/^[А-яA-z\s.;]+\d+/
                }
            },
            messages: {
                departmentName: {
                    required: 'enter department name',
                    minlength:'length should be more than 2',
                    maxlength:'length should be less than 25',
                    regex:'name should look like \'Name\''

                },
                description:'enter description',
                address:{
                    required: 'enter the address',
                    maxlength:'length should be less than 30',
                    regex:'address should look like \'Str. Kosmichna 47\''
                }
            }
        }).form();

    }


    employeeValidate(){
        return $("form[name='employeeForm']").validate({
            rules: {
                fullName: {
                    required: true,
                    maxlength:55,
                    regex:/^[A-ZА-Я][a-zа-я]+[\s-][A-ZА-Я][a-zа-я]+[\s-][A-ZА-Я][a-zа-я]+/
                },
                email: {
                    required: true,
                    maxlength:29,
                    regex:/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/
                },
                birthday: {
                    required: true,
                    data:true
                },
                phoneNumber:{
                    required: true,
                    regex:/^[+][0-9]{10,12}$/
                },
                salary:{
                    required: true,
                    regex:/^([0-9]*[.])?[0-9]+/
                },

            },
            messages: {
                departmentName: {
                    required: 'enter department name',
                    minlength:'length should be more than 2',
                    maxlength:'length should be less than 25',
                    regex:'name should look like \'Name\''

                },
                description:'enter description',
                address:{
                    required: 'enter the address',
                    maxlength:'length should be less than 30',
                    regex:'address should look like \'Str. Kosmichna 47\''
                }
            }
        }).form();

    }
}