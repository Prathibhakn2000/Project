<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring Project</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/issueManagement/js/RaiseComplaint.js"></script>

<!-- card designing (highlight)-->
<style>
    .highlight-card {
        border: 2px solid #f8f9fa;
        background-color: #f8f9fa;  /* Light background color */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  /* Shadow effect */
    }
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
    </div>

    <!-- Image display in profile -->
        <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">

  </div>
</nav>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
  <div class="card highlight-card" style="width: 40%; padding: 20px;">
    <div class="card-body">
      <h3><b><center>Raise Complaint</center></b></h3>

      <form action="raise-complaint" method="post">
        <div class="mb-3">
          <span id="complaintTypeError"></span>
          <label for="complaintType" class="form-label"><b>Complaint Type</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-exclamation-triangle"></i></span>
            <select class="form-select custom-select-width" id="complaintType" name="complaintType" required>
                                 <option value="0" ${raiseComplaintDTO.complaintType == null ? 'selected' : ''}>Select</option>
                                 <option value="Electric " ${raiseComplaintDTO.complaintType == 'Electric ' ? 'selected' : ''}>Electric </option>
                                 <option value="Network " ${raiseComplaintDTO.complaintType == 'Network ' ? 'selected' : ''}>Network </option>
                                  <option value="Noise " ${raiseComplaintDTO.complaintType == 'Noise ' ? 'selected' : ''}>Noise </option>
                                 <option value="System " ${raiseComplaintDTO.complaintType == 'System ' ? 'selected' : ''}>System </option>
                                 <option value="Water " ${raiseComplaintDTO.complaintType == 'Water ' ? 'selected' : ''}>Water </option>

                                 </select><br>
                             </div>
        </div>

        <div class="mb-3">
          <span id="countryNameError"></span>
          <label for="countryName" class="form-label"><b>Country</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-globe"></i></span>
            <select class="form-select" id="countryName" name="country" onchange="loadStates(this)">
              <!-- Countries will be loaded here by JavaScript -->
            </select>
          </div>
        </div>

        <div class="mb-3">
          <span id="stateNameError"></span>
          <label for="state" class="form-label"><b>State</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
            <select class="form-select" id="state" name="state">
              <!-- States will be loaded here by JavaScript -->
            </select>
          </div>
        </div>

        <div class="mb-3">
          <span id="cityNameError"></span>
          <label for="city" class="form-label"><b>City</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-city"></i></span>
            <select class="form-select" id="city" name="city">
              <!-- Cities will be loaded here by JavaScript -->
            </select>
          </div>
        </div>

        <div class="mb-3">
          <span id="areaError"></span><br>
          <label for="area" class="form-label"><b>Area</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-map-marker"></i></span>
            <input type="text" class="form-control" id="area" name="area" placeholder="Enter area">
          </div>
        </div>

        <div class="mb-3">
          <span id="errorAddress"></span><br>
          <label for="address" class="form-label"><b>Address</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-address-card"></i></span>
            <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address">${jobFormDTO.address}</textarea>
          </div>
        </div>

        <div class="mb-3">
          <span id="descriptionError" class="text-danger"></span>
          <label for="description" class="form-label"><b>Description</b></label>
          <div class="input-group">
            <span class="input-group-text"><i class="fas fa-info-circle"></i></span>
            <textarea class="form-control" placeholder="Leave a comment here" name="description" id="description" style="height:80px" oninput="updateDescriptionCount()" maxlength="300" onblur="validateDescription()">${complaint.description}</textarea>
          </div>
        </div>

        <div class="form-group p-3">
          <span id="agreeError"></span>
          <input type="checkbox" class="form-check-input" id="agree" name="agree" onchange="agreeValidation()">
          <label class="form-check-label" for="agree"><b>Agree</b></label>
        </div>

        <div class="form-group p-3">
          <center><input type="submit" class="btn btn-primary" id="submit" name="submit" value="Apply"></center>
        </div>

        <center><a href="SignIn.jsp">Existing User? Login Here</a></center>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
