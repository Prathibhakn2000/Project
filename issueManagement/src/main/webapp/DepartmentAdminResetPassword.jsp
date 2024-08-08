<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <!--<script src="/issueManagement/js/ResetPasswordValidation.js"></script>-->
    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;  /* Light background color */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */
        }
        .input-group .input-group-text {
            border-right: 0;  /* Remove the right border of the input-group-text */
        }
        .input-group .form-control {
            border-left: 0;  /* Remove the left border of the form-control */
        }
        .input-group-text {
            cursor: pointer;  /* Change cursor to pointer when hovering over the eye icon */
        }
    </style>
</head>

<body>

<nav class="navbar navbar-light bg-info">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
      </a>
      <a class="navbar-brand" href="index.jsp"><b>Home</b></a>
      <a class="navbar-brand" href="Profile.jsp"><b>Profile</b></a>
    </div>
    <!-- Image display in profile -->
    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">
  </div>
</nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
      <h3><b><center>Reset Password</center></b></h3>
      <form action="deptadmin-reset-password" method="post">
        <center><strong><span style="color:green">${passwordResetMessage}</span></strong></center>
        <center><strong><span style="color:green">${passwordResetError}</span></strong></center>


         <div class="mb-3">
                  <span id="emailError" style="color: red;"></span><br>
                  <label for="email"><b>Email address</b></label>
                  <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" id="email" placeholder="name@example.com" name="email" value="${sessionScope.email}" readonly>
                  </div>
                </div>

        <div class="mb-3">
          <span id="oldpasswordError" style="color: red;"></span><br>
          <label for="oldpassword"><b>Old Password</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-lock"></i></span>
            <input type="password" class="form-control" id="oldpassword" placeholder="Enter Password" name="oldpassword" onblur="oldpasswordValidation()">
            <span class="input-group-text" onclick="togglePasswordVisibility('oldpassword')"><i class="fas fa-eye" id="toggleOldPassword"></i></span>
          </div>
        </div>

        <div class="mb-3">
          <span id="newpasswordError" style="color: red;"></span><br>
          <label for="newpassword"><b>New Password</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-key"></i></span>
            <input type="password" class="form-control" id="newpassword" placeholder="Enter Password" name="newpassword" onblur="newpasswordValidation()">
            <span class="input-group-text" onclick="togglePasswordVisibility('newpassword')"><i class="fas fa-eye" id="toggleNewPassword"></i></span>
          </div>
        </div>

        <div class="mb-3">
          <span id="confirmpasswordError" style="color: red;"></span><br>
          <label for="confirmpassword"><b>Confirm Password</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-key"></i></span>
            <input type="password" class="form-control" id="confirmpassword" placeholder="Enter Password" name="confirmpassword" onblur="confirmpasswordValidation()">
            <span class="input-group-text" onclick="togglePasswordVisibility('confirmpassword')"><i class="fas fa-eye" id="toggleConfirmPassword"></i></span>
          </div>
        </div>

        <div>
          <input type="submit" value="Reset Password" class="btn btn-primary" id="submit" disabled>
        </div>

        <div class="mb-3">
          <center><a href="SignIn.jsp" class="link-primary"><strong>Sign In Here?</strong></a></center>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
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
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<script>
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
</script>
