<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring Project</title>
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
      <a class="navbar-brand" href="index.jsp"><b>Home</b></a>
      <a class="navbar-brand" href="Profile.jsp"><b>Profile</b></a>
    </div>

   <div class="dropdown">
         <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
         Admin

         </div>
         <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

           <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>

         </ul>
       </div>

              </div>
          </nav>

          <div class="container mt-5 mb-5">
              <!-- Search Form for Complaints -->

              <form action="search-complaint" method="post">
                 <h3><b>Raise Complaint</b></h3>
                 <div class="row mb-3">
                   <span id="complaintTypeError"></span>
                   <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                   <select class="form-select custom-select-width" id="complaintType" name="complaintType" required>
                                 <option value="0" ${raiseComplaintDTO.complaintType == null ? 'selected' : ''}>Select</option>
                                  <option value="Electric issue" ${raiseComplaintDTO.complaintType == 'Electric Problem' ? 'selected' : ''}>Electric Problem</option>
                                  <option value="Network Problem" ${raiseComplaintDTO.complaintType == 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                                   <option value="Noise Problem" ${raiseComplaintDTO.complaintType == 'Noise Problem' ? 'selected' : ''}>Noise Problem</option>
                                  <option value="System Problem" ${raiseComplaintDTO.complaintType == 'System Problem' ? 'selected' : ''}>System Problem</option>
                                  <option value="Water Supply" ${raiseComplaintDTO.complaintType == 'Water Problem' ? 'selected' : ''}>Water Problem</option>

                                  </select><br>
                 </div>
                 <div class="mb-3">
                   <span id="cityError"></span><br>
                   <label for="city" class="form-label"><b>City</b></label>
                   <input type="text" class="form-control" id="city" name="city" placeholder="Enter city">
                 </div>
                 <div>
                   <input type="submit" id="submit" value="Submit">
                 </div>
               </form>

                 <!-- View of Raised Complaints -->
              <div class="card">
                  <div class="card-header">
                      <h3><b>Complaint Details </b></h3>
                  </div>
                  <div class="card-body">

                  <table class="table table-bordered">
                                  <thead>
                                  <tr>
                                      <th>Serial Number</th>
                                      <th>ID</th>
                                      <th>Complaint Type</th>
                                      <th>Country</th>
                                      <th>State </th>
                                      <th>City </th>
                                      <th>Area</th>
                                      <th>Address</th>
                                      <th>Description</th>
                                      <th>UserId</th>
                                      <th>Allocate Department</th>


                                  </tr>
                                  </thead>
                                  <tbody>
                                  <c:forEach var="viewRaiseComplaintUsers" items="${viewRaiseComplaint}" varStatus="status">  <!--items is used in add attribute in controller-->
                                      <tr>
                                          <td>${status.index + 1}</td>
                                          <td>${viewRaiseComplaintUsers.complaintId}</td>
                                          <td>${viewRaiseComplaintUsers.complaintType}</td>
                                          <td>${viewRaiseComplaintUsers.country}</td>
                                          <td>${viewRaiseComplaintUsers.state}</td>
                                          <td>${viewRaiseComplaintUsers.city}</td>
                                          <td>${viewRaiseComplaintUsers.area}</td>
                                          <td>${viewRaiseComplaintUsers.address}</td>
                                          <td>${viewRaiseComplaintUsers.description}</td>
                                          <td>${viewRaiseComplaintUsers.signUpDTO.id}</td>
                                    </tr>
                                  </c:forEach>

                                      <!-- Iterating over the second collection  search complaint-->
                                   <c:forEach items="${listOfComplaintType}" var="complaint" varStatus="status">
                                               <tr>
                                                 <td>${status.index + 1}</td>
                                                 <td>${complaint.complaintId}</td>
                                                 <td>${complaint.complaintType}</td>
                                                 <td>${complaint.country}</td>
                                                 <td>${complaint.state}</td>
                                                 <td>${complaint.city}</td>
                                                 <td>${complaint.area}</td>
                                                 <td>${complaint.address}</td>
                                                 <td>${complaint.description}</td>
                                                 <td>${complaint.signUpDTO.id}</td>
                                               </tr>
                                             </c:forEach>

                                  </tbody>
                              </table>
                          </div>
                      </div>
                  </div>



                  </body>
                  </html>