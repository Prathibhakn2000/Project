<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <script src="/issueManagement/js/ResetPasswordValidation.js"></script>
    <style>
        .highlight-card {
            border: 2px solid #f8f9fa;
            background-color: #f8f9fa;  /* Light background color */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */

        }

        /* Icons */
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
      <h3><b><center>UserView</center></b></h3>
      <h5 class="card-title"><i class="fas fa-user"></i> Name: ${signUpDTO.firstName} ${signUpDTO.lastName}</h5>
      <p class="card-text"><i class="fas fa-envelope"></i> <b>Email</b>: ${signUpDTO.email}</p>
      <p class="card-text"><i class="fas fa-phone"></i> <b>Contact Number</b>: ${signUpDTO.contactNumber}</p>
      <p class="card-text"><i class="fas fa-phone-alt"></i> <b>Alternative Contact Number</b>: ${signUpDTO.alternativeContactNumber}</p>
      <p class="card-text"><i class="fas fa-home"></i> <b>Address</b>: ${signUpDTO.address}</p>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
