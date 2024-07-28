<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

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
</head>

<body>

<nav class="navbar navbar-light bg-info">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
      </a>

    </div>
  </div>
</nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
      <h3><b><center>Admin</center></b></h3>
      <form action="admin" method="post">

       <span style="color:green"><strong>${adminMessage}</strong></span>
                      <span style="color:red"><strong>${errorAdminMessage}</strong></span>
                  <div class="row mb-3">
                      <span id="emailError" style="color:red;"></span><br>
                      <label for="email" class="form-label"><b>Email:</b></label>
                      <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                          <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" value="" onblur="emailValidation()">
                      </div>
                  </div>


                  <div class="mb-3">
                      <span id="passwordError" style="color:red;"></span><br>
                      <label for="password" class="form-label"><b>Password:</b></label>
                      <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-lock"></i></span>
                          <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" value="" onblur="passwordValidation">
                      </div>
                  </div>

                   <input type="submit" value="Submit" class="btn btn-primary oval-btn bold-text" id="submitButton">
                   </form>
                       </div>
                     </div>
                   </div>
                   </body>
                   </html>