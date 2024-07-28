<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <script src="/issueManagement/js/SignInValidation.js"></script>
    <!-- card designing (highlight)-->
    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;  /* Light background color */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */
        }

        <!--icons--!>
        .input-group .input-group-text {
            border-right: 0;  /* Remove the right border of the input-group-text */
        }
        .input-group .form-control {
            border-left: 0;  /* Remove the left border of the form-control */
        }
    </style>
 <!--disabled the sign-in button after 3 incorrect PW-->
    <script>
        function disableButton() {
            var accountLocked = "${accountLocked}";
            if (accountLocked === "true") {
                document.getElementById("signinButton").disabled = true;
            }
        }
        window.onload = disableButton;

        function togglePasswordVisibility() {
            var passwordField = document.getElementById("password");
            var toggleIcon = document.getElementById("togglePasswordIcon");
            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                passwordField.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }
    </script>
</head>

<body>

<nav class="navbar navbar-light bg-info">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
      </a>
      <a class="navbar-brand" href="index.jsp"><b>Home</b></a>
    </div>

  </div>
</nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
      <h3><b><center>SIGNIN</center></b></h3>
      <form action="sign-in" method="post">
        <center><strong><span style="color:green">${message}</span></strong></center>
        <center><strong><span style="color:green">${msg1}</span></strong></center>
        <center><strong><span style="color:red">${error}</span></strong></center>

        <div class="mb-3">
          <span id="emailError" style="color: red;"></span><br>
          <label for="email" class="form-label"><b>Email address</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-envelope"></i></span>
            <input type="email" class="form-control" id="email" placeholder="name@example.com" name="email" onblur="emailValidation()">
          </div>
        </div>

        <div class="mb-3">
          <span id="passwordError" style="color: red;"></span><br>
          <label for="password" class="form-label"><b>Password</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-lock"></i></span>
            <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password" onblur="passwordValidation()">
            <span class="input-group-text"><i class="fas fa-eye" id="togglePasswordIcon" onclick="togglePasswordVisibility()"></i></span>
          </div>
        </div>

      <center>  <input type="submit" class="btn btn-primary" id="signinButton" value="Sign in"> </center>
      <br>

      <center> <div class="mb-3">
           <a href="ForgotPassword.jsp" class="link-primary">Forgot Password?</a>
       </div></center>


        <center> <div class="mb-3">
                  <a href="SignUp.jsp" class="link-primary">SignUp here?</a>
        </div></center>

      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
