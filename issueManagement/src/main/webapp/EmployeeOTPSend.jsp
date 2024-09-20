<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Department Admin - Allocate Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;  /* Light background color */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */
            width: 90%;  /* Increase width */
            padding: 30px;  /* Increase padding */
        }
        .input-group .input-group-text {
            border-right: 0;  /* Remove the right border of the input-group-text */
        }
        .input-group .form-control {
            border-left: 0;  /* Remove the left border of the form-control */
        }
    </style>
<script>
    // Set the countdown time in seconds (5 minutes = 300 seconds)
    var countdownTime = 300;

    function startCountdown() {
        var timerElement = document.getElementById("timer");
        var otpField = document.getElementById("otp");
        var submitButton = document.querySelector("input[type='submit']");

        var countdownInterval = setInterval(function () {
            var minutes = Math.floor(countdownTime / 60);
            var seconds = countdownTime % 60;

            // Add leading zeros to seconds
            seconds = seconds < 10 ? '0' + seconds : seconds;

            // Display the countdown timer
            timerElement.textContent = minutes + ":" + seconds;

            if (countdownTime <= 0) {
                clearInterval(countdownInterval);
                timerElement.textContent = "Time's up!";
                otpField.disabled = true; // Disable OTP field
                submitButton.disabled = true; // Disable submit button
            }

            countdownTime--;
        }, 1000);
    }

    // Start the countdown when the page loads
    window.onload = startCountdown;
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


        <span style="color:green"><strong>${generatedOTP}</strong></span>
                <span style="color:red"><strong>${failed}</strong></span>

                <form action="validateOtp" method="post">

                 <input type="hidden" name="email" value="${sessionScope.email}" required> <!-- Use a hidden field if email is in session -->

                    <div class="mb-3">
                        <label for="otp" class="form-label"><b>OTP</b></label>
                        <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" required>
                    </div>

                    <!-- Timer Display -->
                    <div class="mb-3 text-center">
                        <span class="timer" id="timer">5:00</span> <!-- Initial timer value -->
                    </div>

                    <div><center><input type="submit" class="btn btn-primary" id="signInButton" value="LogIn"></center></div>
                </form>

               <br>
                <form action="resendOtp" method="post">
                 <input type="hidden" name="email" value="${sessionScope.email}" required> <!-- Use a hidden field if email is in session -->

                    <div><center><input type="submit" class="btn btn-primary" id="resendOtpButton" value="Resend OTP"></center></div>
                </form>
               </br>
             </div>
            </div>
        </div>
        </body>
        </html>
