
  function emailValidate() {
    console.log("Validate email");
    let email = document.getElementById("email").value;
    console.log(email);
    let error = document.getElementById("emailError");
    if (email == "") {
      document.getElementById("emailError").innerHTML = "Please Enter Email";
    } else {
      const request = new XMLHttpRequest();
      request.open("GET", "http://localhost:8081/issueManagement/validateEmail/" + email);
      request.send();
      console.log(request);
      request.onload = function() {
        var ref = this.responseText;
        console.log(ref);
        error.innerHTML = ref;

        if (ref === "") {
          fieldsChecks["email"] = true;
        } else {
          fieldsChecks["email"] = false;
        }

        validate();
      }
    }
  }

  function contactNumberValidate() {
    console.log("Validate contact number");
    let contactNumber = document.getElementById("contactNumber").value;
    console.log(contactNumber);
    let error = document.getElementById("contactNumberError");
    if (contactNumber == "") {
      document.getElementById("contactNumberError").innerHTML = "Please Enter contactNumber";
    } else {
      const request = new XMLHttpRequest();
      request.open("GET", "http://localhost:8081/issueManagement/validateContactNumber/" + contactNumber);
      request.send();
      console.log(request);

      request.onload = function() {
        var ref = this.responseText;
        console.log(ref);
        error.innerHTML = ref;

        if (ref === "") {
          fieldsChecks["contactNumber"] = true;
        } else {
          fieldsChecks["contactNumber"] = false;
        }

        validate();
      }

     /* request.onerror = function() {
        console.error("Request failed");
        error.innerHTML = "<span style='color:red;'>Validation failed. Please try again.</span>";
        fieldsChecks["contactNumber"] = false;
        validate();
      }*/
    }
  }
