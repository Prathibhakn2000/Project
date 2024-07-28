let fieldsChecks = {
  "oldpassword": false,
  "newpassword": false,
  "confirmpassword": false
};

function validate() {
  let allFieldsValid = Object.values(fieldsChecks).every(value => value);
  let submitButton = document.getElementById("submit");

  console.log(fieldsChecks); // Debug statement to check the state of fieldsChecks
  console.log("All fields valid: " + allFieldsValid); // Debug statement to check if all fields are valid

  if (allFieldsValid) {
      submitButton.removeAttribute("disabled");
  } else {
      submitButton.setAttribute("disabled", "true");
  }
}

function oldpasswordValidation() {
  let oldPassword = document.getElementById("oldpassword");
  let error = document.getElementById("oldpasswordError");
  let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;

  if (oldPasswordRegex.test(oldPassword.value)) {
      error.innerHTML = "";
      fieldsChecks["oldpassword"] = true;
  } else {
      error.innerHTML = "Password must be at least 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
      error.style.color = "red";
      fieldsChecks["oldpassword"] = false;
  }

  validate();
}

function newpasswordValidation() {
  let newPassword = document.getElementById("newpassword");
  let error = document.getElementById("newpasswordError");
  let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;

  if (newPasswordRegex.test(newPassword.value)) {
      error.innerHTML = "";
      fieldsChecks["newpassword"] = true;
  } else {
      error.innerHTML = "Password must be at least 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
      error.style.color = "red";
      fieldsChecks["newpassword"] = false;
  }

  confirmpasswordValidation();
  validate();
}

function confirmpasswordValidation() {
  let newPassword = document.getElementById("newpassword");
  let confirmPassword = document.getElementById("confirmpassword");
  let error = document.getElementById("confirmpasswordError");

  if (confirmPassword.value === newPassword.value && fieldsChecks["newpassword"]) {
      error.innerHTML = "";
      fieldsChecks["confirmpassword"] = true;
  } else {
      error.innerHTML = "Passwords do not match.";
      error.style.color = "red";
      fieldsChecks["confirmpassword"] = false;
  }

  validate();
}

function togglePasswordVisibility(id) {
  const passwordField = document.getElementById(id);
  const icon = document.querySelector(`#toggle${id.charAt(0).toUpperCase() + id.slice(1)}`);

  if (passwordField.type === "password") {
      passwordField.type = "text";
      icon.classList.remove("fa-eye");
      icon.classList.add("fa-eye-slash");
  } else {
      passwordField.type = "password";
      icon.classList.remove("fa-eye-slash");
      icon.classList.add("fa-eye");
  }
}
