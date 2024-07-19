<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring Project</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script  src="/issueManagement/js/RaiseComplaint.js"></script>

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
 <!-- <a class="navbar-brand " href="SignIn.jsp"><b>SignIn</b></a> -->


           </div>
        </div>
    </nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
      <h3><b><center>Raise Complaint</center></b></h3>

      <form action="raise-complaint" method="post">

                        <div class="row mb-3">
                         <span id="countryNameError"></span>
                         <label for="countryName" class="form-label"><b>Complaint Type</b></label>
                         <select class="form-select custom-select-width" id="complaintType" name="complaintType"  placeholder="Complaint Type">
                               <option ${dto.collegeName==null ? 'selected' : ''}  selected value=" "></option>
                               <option value="Water Supply" ${dto.collegeName eq 'Water Supply' ? 'selected' : ''}>Water Supply</option>
                               <option value="System Problem"  ${dto.collegeName eq 'System Problem' ? 'selected' : ''}>System Problem</option>
                               <option value="Network Problem"  ${dto.collegeName eq 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                               <option value="Electrical Problem"  ${dto.collegeName eq 'Electrical Problem' ? 'selected' : ''}>Electrical Problem</option>
                               <option value="Noise Problem"  ${dto.collegeName eq 'GMIT' ? 'selected' : ''}>Noise Problem</option>
                           </select>

                       </div>

                       <!----Country ---!>

                                       <div class="row mb-3">
                                           <span id="countryNameError"></span>
                                           <label for="countryName" class="form-label"><b>Country</b></label>
                                           <select class="form-select custom-select-width" id="countryName" name="country" onchange="loadStates(this)"  placeholder="Enter country">
                                               <!-- Countries will be loaded here by JavaScript -->
                                           </select><br>
                                       </div>

                                       <!----State ---!>

                                       <div class="row mb-3">
                                           <span id="stateNameError"></span>
                                           <label for="state" class="form-label"><b>State</b></label>
                                           <select class="form-select custom-select-width" id="state" name="state" placeholder="Enter State" >
                                               <!-- States will be loaded here by JavaScript -->
                                           </select><br>
                                       </div>

                                       <!----City ---!>

                                       <div class="row mb-3">
                                           <span id="cityNameError"></span>
                                           <label for="city" class="form-label"><b>City</b></label>
                                           <select class="form-select custom-select-width" id="city" name="city" placeholder="Enter city">
                                               <!-- Cities will be loaded here by JavaScript -->
                                           </select><br>
                                       </div>


                       <div class="row mb-3">
                                 <span id="areaError"></span><br>
                                 <label for="area" class="form-label"><b>Area</b></label>
                                 <input type="text" class="form-control" id="area" name="area" placeholder="Enter area">
                        </div>



                       <div class="mb-3">
                                <span id="errorAddress"></span><br>
                                <label for="area" class="form-label"><b>Address</b></label>
                                <label for="address" class="form-floating"></label>
                                <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address">${jobFormDTO.address}</textarea>
                       </div>



                       <div class="mb-3">
                                       <span id="descriptionError" class="text-danger"></span>
                                     <label for="area" class="form-label"><b>Description</b></label>
                                       <div class="form-floating">
                                           <textarea class="form-control" placeholder="Leave a comment here" name="description" id="description"  style="height:80px"  oninput="updateDescriptionCount()" maxlength="300" onblur="validateDescription()">${complaint.description}</textarea>
                                           <label for="description">Description</label>
                                       </div>
                                   </div>


                       <div class="form-group p-3">
                                   <span id="agreeError"></span>
                                  <input type="checkbox" class="form-check-input" id="agree"  name="agree" onchange="agreeValidation()">
                                       <label class="form-check-label" for="agree"><b>Agree</b></label>
                        </div>



                  <div class="form-group p-3">
                      <center><input type="submit" class="btn btn-primary" id="submit" name="submit" value="Apply" ></center>
                  </div>

                  <center> <a href="SignIn.jsp"/>Existing User?Login Here</a></center>




</div>
  </div>
    </div>
</body>
</html>
