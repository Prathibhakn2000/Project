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
      <h3><b><center>Department Admin</center></b></h3>
      <form action="department-admins" method="post">

      <center><strong><span style="color:green">${msg}</span></strong></center>

      <div class="mb-3">
        <span id="fullName"></span><br>
        <label for="fullName class="form-label"><b>Admin FullName</b></label>
         <div class="input-group">
                  <span class="input-group-text"><i class="fas fa-user"></i></span>
          <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Enter fullName" >
      </div>
      </div>

      <div class="row mb-3">
        <span id="departmentNameError"></span>
         <label for="departmentName" class="form-label"><b>Department</b></label>
         <select class="form-select custom-select-width" id="departmentName" name="departmentName" required>
         <option value="0" ${raiseComplaintDTO.complaintType == null ? 'selected' : ''}>Select</option>
         <option value="Electric Problem" ${raiseComplaintDTO.complaintType == 'Electric Problem' ? 'selected' : ''}>Electric Problem</option>
         <option value="Network Problem" ${raiseComplaintDTO.complaintType == 'Network Problem' ? 'selected' : ''}>Network Problem</option>
         <option value="Noise Problem" ${raiseComplaintDTO.complaintType == 'Noise Problem' ? 'selected' : ''}>Noise Problem</option>
         <option value="System Problem" ${raiseComplaintDTO.complaintType == 'System Problem' ? 'selected' : ''}>System Problem</option>
         <option value="Water Problem" ${raiseComplaintDTO.complaintType == 'Water Problem' ? 'selected' : ''}>Water Problem</option>
          </select><br>
      </div>

      <div class="mb-3">
        <span id="emailError" style="color: red;"></span><br>
        <label for="email" class="form-label"><b>Email address</b></label>
        <div class="input-group">
          <span class="input-group-text"><i class="fas fa-envelope"></i></span>
          <input type="email" class="form-control" id="email" placeholder="name@example.com" name="email"  />
        </div>
      </div>

      <div class="mb-3">
        <span id="contactNumberError" style="color: red;"></span><br>
        <label for="contactNumber" class="form-label"><b>Contact Number</b></label>
        <div class="input-group">
          <span class="input-group-text"><i class="fas fa-phone"></i></span>
          <input type="tel" class="form-control" id="contactNumber" name="contactNumber" placeholder="Enter Contact Number"  />
        </div>
      </div>

      <div class="mb-3">
        <span id="altContactNbrError"></span><br>
        <label for="alternativeContactNumber" class="form-label"><b>Alternative Contact Number</b></label>
        <div class="input-group">
          <span class="input-group-text"><i class="fas fa-phone-alt"></i></span>
          <input type="tel" class="form-control" id="alternativeContactNumber" name="alternativeContactNumber" placeholder="Enter Alternative Contact Number" />
        </div>
      </div>

      <div class="mb-3">
        <span id="addressError"></span><br>
        <label for="address" class="form-label"><b>Address</b></label>
        <div class="input-group">
          <span class="input-group-text"><i class="fas fa-home"></i></span>
          <textarea class="form-control" id="address" rows="3" name="address" placeholder="Enter Address" ></textarea>
        </div>
      </div>

      <center><input type="submit" class="btn btn-primary" id="submit" value="Register"></i></center>
      </form>
    </div>
  </div>
</div>

</body>
 </html>