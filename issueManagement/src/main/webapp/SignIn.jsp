<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/issueManagement/js/SignInValidation.js"></script>
    <!-- card desiging(highlight)-->
    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;  /* Light background color */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */
        }
    </style>
 <!--disabled the signin button after 3 incorrect PW--!>
    <script>
        function disableButton() {
            var accountLocked = "${accountLocked}";
            if (accountLocked === "true") {
                document.getElementById("signinButton").disabled = true;
            }
        }
        window.onload = disableButton;
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
      <center><strong><span style="color:green">${msg}</span></strong></center>
        <center><strong><span style="color:green">${msg1}</span></strong></center>
        <center><strong><span style="color:red">${error}</span></strong></center>

        <div class="mb-3">
          <span id="emailError" style="color: red;"></span><br>
          <label for="email" class="form-label"><b>Email address</b></label>
          <input type="email" class="form-control" id="email" placeholder="name@example.com" name="email" onblur="emailValidation()">
        </div>

        <div class="mb-3">
          <span id="passwordError" style="color: red;"></span><br>
          <label for="password" class="form-label"><b>Password</b></label>
          <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password" onblur="passwordValidation()">
        </div>

        <div class="mb-3">
           <a href="ForgotPassword.jsp" class="link-primary">Forgot Password?</a>
         </div>

        <button type="submit" class="btn btn-primary" id="signinButton"/>Sign in</button>

      </form>
    </div>
  </div>
</div>
</body>
</html>
