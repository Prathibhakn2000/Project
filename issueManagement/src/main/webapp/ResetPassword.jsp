<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/issueManagement/js/ResetPasswordValidation.js"></script>
    <!-- card desiging(highlight)-->
    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;  /* Light background color */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */
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
  </div>
</nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
      <h3><b><center>RestPassword</center></b></h3>
      <form action="reset-password" method="post">
      <center><strong><span style="color:green">${passwordResetMessage}</span></strong></center>
              <center><strong><span style="color:green">${passwordResetError}</span></strong></center>
            <div class="mb-3">
                      <span id="emailError" style="color: red;"></span><br>
                      <label for="email" class="form-label"><b>Email address</b></label>
                      <input type="email" class="form-control" id="email" placeholder="name@example.com" name="email" onblur="emailValidation()">
                    </div>

                    <div class="mb-3">
                      <span id="oldpasswordError" style="color: red;"></span><br>
                      <label for="oldpassword" class="form-label"><b>Old Password</b></label>
                      <input type="password" class="form-control" id="oldpassword" placeholder="Enter Password" name="oldpassword" onblur="oldpasswordValidation()">
                    </div>

                    <div class="mb-3">
                       <span id="newpasswordError" style="color: red;"></span><br>
                       <label for="newpassword" class="form-label"><b>New Password</b></label>
                       <input type="password" class="form-control" id="newpassword" placeholder="Enter Password" name="newpassword" onblur="newpasswordValidation()">
                     </div>

                    <div class="mb-3">
                       <span id="confirmpasswordError" style="color: red;"></span><br>
                       <label for="confirmpassword" class="form-label"><b>Confirm Password</b></label>
                       <input type="password" class="form-control" id="confirmpassword" placeholder="Enter Password" name="confirmpassword" onblur="confirmpasswordValidation()">
                     </div>

                     <div>
                        <input type="submit" value="Reset Password" class="btn btn-primary" id="submit" disabled>
                     </div>

                     <div class="mb-3">
                      <center><a href="SignIn.jsp" class="link-primary"><strong>SignIn Here?</strong></a></center>
                     </div>
        </form>
    </div>
</div>
</div>
</body>
</html>