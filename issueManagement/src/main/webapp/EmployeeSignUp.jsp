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
      <h3><b><center>Employee Register</center></b></h3>
      <form action="employee-singup" method="post">

      <center><strong><span style="color:green">${message}</span></strong></center>

      <div class="mb-3">
        <span id="fullNameerror"></span><br>
        <label for="fullName class="form-label"><b>Employee FullName</b></label>
         <div class="input-group">
                  <span class="input-group-text"><i class="fas fa-user"></i></span>
          <input type="text" class="form-control" id="empFullName" name="empFullName" placeholder="Enter fullName" >
      </div>
      </div>

      <div class="mb-3">
           <span id="designationerror"></span><br>
           <label for="designation class="form-label"><b>Designation</b></label>
            <div class="input-group">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
             <input type="text" class="form-control" id="designation" name="designation" placeholder="Enter Designation" >
         </div>
         </div>


      <div class="mb-3">
           <label for="department" class="form-label">Department Type :</label>
            <select class="form-select" name="departmentType" id="departmentType">
                   <c:forEach var="department" items="${departments}">
                       <option value="${department.departmentType}">${department.departmentType}</option>
                   </c:forEach>
               </select>
            </select>
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
        <span id="addressError"></span><br>
        <label for="address" class="form-label"><b>Address</b></label>
        <div class="input-group">
          <span class="input-group-text"><i class="fas fa-home"></i></span>
          <textarea class="form-control" id="address" rows="3" name="address" placeholder="Enter Address" ></textarea>
        </div>
      </div>

      <div><center><input type="submit" class="btn btn-primary" id="submit" value="submit"></i></center></div>


      <div class="mb-3">
        <center>  <p> Have already have account?  <a href="employeeLogin" class="link-primary"><strong>Please Login To get OTP to click here?</strong></a></p></center>
            </div>

      </form>
    </div>
  </div>
</div>

</body>
 </html>