let fieldsChecks = {

	    "firstName" : false,
	    "lastName" : false,
	    "email":false,
	    "contactNumber":false,
	    "alternateContactNumber":false,
	    "address":false,
	    "agree":false,

	}

function validate(){

    let flag = false;

    for(let [key, value] of Object.entries(fieldsChecks)){

        if(value === false){
            flag = true;
            break;
        }
    }

    if(!flag){
        document.getElementById("submit").removeAttribute("disabled");
    }else{
        document.getElementById("submit").setAttribute("disabled","");
    }
}

function firstNameValidation()
{
console.log("First name validation")
 let element = document.getElementById("firstName");
                let error = document.getElementById("firstNameError");
                let nameRegex = /^[A-Za-z]+$/;

                if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
                    error.innerHTML = "";
                    fieldsChecks["firstName"] = true;
                } else {
                    error.innerHTML = "Invalid firstName. firstName should be alphabetic characters only and length should be greater than 3 and less than 30.";
                    error.style.color = "red";
                    fieldsChecks["firstName"] = false;

    }
    validate();
}

function lastNameValidation()
{
 let element = document.getElementById("lastName");
                let error = document.getElementById("lastNameError");
                let nameRegex = /^[A-Za-z]+$/;

                if (element.value.length >= 1 && element.value.length <=2 && nameRegex.test(element.value)) {
                    error.innerHTML = "";
                    fieldsChecks["lastName"] = true;
                } else {
                    error.innerHTML = "Invalid lastName. lastName should be greater than 1 and less than equal 2.";
                    error.style.color = "red";
                    fieldsChecks["lastName"] = false;

    }
    validate();
}

function emailValidation() {
               let element = document.getElementById("email");
              let error = document.getElementById("emailError");

                              <!----Regular expression pattern for validating email address--!>
      let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

                                  <!---Check if the email is valid--!>
      if (emailRegex.test(element.value)) {
                                   //Email is valid
                                  error.innerHTML = "";
                                  fieldsChecks["email"] = true;
                              } else {
                            //email is Invalid
                                  error.innerHTML = "Invalid email address.";
                                  error.style.color = "red";
                                  fieldsChecks["email"] = false;
                              }
                              validate();
                          }

function contactNumberValidation() {
    let element = document.getElementById("contactNumber");
    let error = document.getElementById("contactNumberError");

    // Regular expression pattern for validating a mobile number starting with 6, 7, 8, or 9 and 10 to 12 digits long
    let mobileRegex = /^[6-9]\d{9}$/;

    // Check if the mobile number is valid
    if (mobileRegex.test(element.value)) {
        // Mobile number is valid
        error.innerHTML = "";
        error.style.color = "";  // Reset error message style
        fieldsChecks["contactNumber"] = true;  // Assuming fieldsChecks is a global variable for form validation
    } else {
        // Mobile number is invalid
        error.innerHTML = "Invalid contact number. Please Enter Valid Number ";
        error.style.color = "red";
        fieldsChecks["contactNumber"] = false;
    }
    validate();  // Call a function to validate the entire form, if needed
}

function alternateContactNumberValidation() {
    let element = document.getElementById("alternateContactNumber");
    let error = document.getElementById("altContactNbrError");

    // Regular expression pattern for validating a mobile number starting with 6, 7, 8, or 9 and 10 to 12 digits long
    let mobileRegex = /^[6-9]\d{9}$/;

    // Check if the mobile number is valid
    if (mobileRegex.test(element.value)) {
        // Mobile number is valid
        error.innerHTML = "";
        error.style.color = "";  // Reset error message style
        fieldsChecks["alternateContactNumber"] = true;  // Assuming fieldsChecks is a global variable for form validation
    } else {
        // Mobile number is invalid
        error.innerHTML = "Invalid alternateContactNumber. Please Enter Valid Number";
        fieldsChecks["alternateContactNumber"] = false;
    }
    validate();  // Call a function to validate the entire form, if needed
}


function addressValidation()
{
 let element = document.getElementById("address");
                let error = document.getElementById("addressError");
                let nameRegex = /^[A-Za-z]+$/;

                if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
                    error.innerHTML = "";
                    fieldsChecks["address"] = true;
                } else {
                    error.innerHTML = "Invalid address. address should be alphabetic characters only and length should be greater than 3 and less than 300.";
                    error.style.color = "red";
                    fieldsChecks["address"] = false;
  }
     validate();
 }

 // Agree
 function agreeValidation() {
     let element = document.getElementById("agree");
     let error = document.getElementById("agreeError");

     if (element.checked) {
         error.innerHTML = "";
         fieldsChecks["agree"] = true;
     } else {
         error.innerHTML = "Please agree to the terms.";
         error.style.color = "red";
         fieldsChecks["agree"] = false;
     }
     validate();
 }
