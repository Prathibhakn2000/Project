function firstNameValidation() {
    let element = document.getElementById("firstName");
    let error = document.getElementById("firstNameError");
    let nameRegex = /^[A-Za-z]+$/;

    if (element.value.length >= 3 && element.value.length < 30 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["firstName"] = true;
    } else {
        error.innerHTML = "Invalid first name. Should be alphabetic characters only and length should be between 3 and 30.";
        error.style.color = "red";
        fieldsChecks["firstName"] = false;
    }

    validate(); // Call validate to update the submit button state
}

function lastNameValidation() {
    let element = document.getElementById("lastName");
    let error = document.getElementById("lastNameError");
    let nameRegex = /^[A-Za-z]+$/;

    if (element.value.length >= 1 && element.value.length <=2 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["lastName"] = true;
    } else {
        error.innerHTML = "Invalid last name. Should be alphabetic characters only and length should be between 3 and 30.";
        error.style.color = "red";
        fieldsChecks["lastName"] = false;
    }

    validate(); // Call validate to update the submit button state
}

function contactNumberValidation() {
    let element = document.getElementById("contactNumber");
    let error = document.getElementById("contactNumberError");
    let numberRegex = /^[6-9]\d{9}$/;

    if (numberRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["contactNumber"] = true;
    } else {
        error.innerHTML = "Invalid contact number. Should be 10 digits starting with 6, 7, 8, or 9.";
        error.style.color = "red";
        fieldsChecks["contactNumber"] = false;
    }

    validate(); // Call validate to update the submit button state
}

function alternateContactNumberValidation() {
    let element = document.getElementById("alternateContactNumber");
    let error = document.getElementById("altContactNbrError");
    let numberRegex = /^[6-9]\d{9}$/;

    if (element.value === "" || numberRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["alternateContactNumber"] = true;
    } else {
        error.innerHTML = "Invalid alternative contact number. Should be empty or 10 digits starting with 6, 7, 8, or 9.";
        error.style.color = "red";
        fieldsChecks["alternateContactNumber"] = false;
    }

    validate(); // Call validate to update the submit button state
}

function addressValidation() {
    let element = document.getElementById("address");
    let error = document.getElementById("addressError");

    if (element.value.length >= 3 && element.value.length <= 300) {
        error.innerHTML = "";
        fieldsChecks["address"] = true;
    } else {
        error.innerHTML = "Invalid address. Length should be between 3 and 300 characters.";
        error.style.color = "red";
        fieldsChecks["address"] = false;
    }

    validate(); // Call validate to update the submit button state
}


document.getElementById("firstName").addEventListener("blur", firstNameValidation);
document.getElementById("lastName").addEventListener("blur", lastNameValidation);
document.getElementById("contactNumber").addEventListener("blur", contactNumberValidation);
document.getElementById("alternateContactNumber").addEventListener("blur", alternateContactNumberValidation);
document.getElementById("address").addEventListener("blur", addressValidation);


function validate() {
    let isValid = true;

    // Check each field validity
    for (let field in fieldsChecks) {
        if (!fieldsChecks[field]) {
            isValid = false;
            break;
        }
    }

    // Enable/disable submit button based on overall form validity
    document.getElementById("submit").disabled = !isValid;
}