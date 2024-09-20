<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Department Admin - Allocate Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"> <!-- Font Awesome -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            padding: 30px;
        }

        .input-group .input-group-text {
            border-right: 0;
        }

        .input-group .form-control {
            border-left: 0;
        }

        .captcha-refresh-icon {
            cursor: pointer;
            font-size: 24px;
            color: #0d6efd;
            margin-left: 10px;
        }

    </style>

    <script>
        function disableButton() {
            var accountLocked = "${accountLocked}";
            if (accountLocked === "true") {
                document.getElementById("signInButton").disabled = true;
            }
        }
        window.onload = disableButton;

        function refreshCaptcha() {
            document.getElementById('captchaImage').src = 'captcha?' + Math.random();
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
            <a class="navbar-brand" href="Profile.jsp"><b>Profile</b></a>
        </div>

    </div>
</nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
        <h3><b><center>LOGIN</center></b></h3>

        <span style="color:green"><strong>${msg}</strong></span>
        <span style="color:red"><strong>${captchaError}</strong></span>
        <span style="color:red"><strong>${accountError}</strong></span>
        <span style="color:red"><strong>${emailNotFound}</strong></span>
        <span style="color:red"><strong>${generatedOTP}</strong></span>
        <span style="color:red"><strong>${failedToGenerateOTPError}</strong></span>

        <form action="generateOtp" method="post">
            <!-- Email Field -->
            <div class="mb-3">
                <label for="emailId" class="form-label"><b>Email ID</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email ID" required>
                </div>
            </div>

            <!-- CAPTCHA Field -->
            <div class="mb-3">
                <label for="captcha" class="form-label"><b>Captcha</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-shield-alt"></i></span> <!-- Icon for CAPTCHA -->
                    <img src="captcha" alt="CAPTCHA Image" id="captchaImage" style="width: 120px; margin-right: 10px;">
                    <input type="text" class="form-control" id="captcha" name="captcha" placeholder="Enter CAPTCHA" required>
                    <span id="refreshCaptcha" onclick="refreshCaptcha()" class="captcha-refresh-icon"><i class="fas fa-sync-alt"></i></span>
                </div>
            </div>

            <div><center><input type="submit" class="btn btn-primary" id="signInButton" value="Send OTP"></center></div>
        </form>

    </div>
  </div>
</div>
</body>
</html>
