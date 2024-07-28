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


    </div>

    <div class="dropdown">
          <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
          Admin

          </div>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

            <li><a class="dropdown-item" href="view-raise-complaint"><strong>ViewRaiseComplaintDetails</strong></a></li>

          </ul>
        </div>

              </div>
          </nav>

          <div class="container mt-5 mb-5">
              <div class="card">
                  <div class="card-header">
                      <h3><b>View User Details </b></h3>
                  </div>
                  <div class="card-body">
                      <table class="table table-bordered">
                          <thead>
                              <tr>
                                  <th>Serial Number</th>
                                  <th>ID</th>
                                  <th>Name</th>
                                  <th>Email</th>
                                  <th>Contact Number</th>
                                  <th>Alternative Contact Number</th>
                                  <th>Address</th>


                              </tr>
                          </thead>
                          <tbody>
                              <c:forEach var="viewUsers" items="${ViewUserDetails}" varStatus="status">
                                  <tr>
                                      <td>${status.index + 1}</td>
                                      <td>${viewUsers.id}</td>
                              <td>${viewUsers.firstName} ${viewUsers.lastName}</td>
                                      <td>${viewUsers.email}</td>
                                      <td>${viewUsers.contactNumber}</td>
                                      <td>${viewUsers.alternativeContactNumber}</td>
                                      <td>${viewUsers.address}</td>

                                    <!-- <td><a href="${pageContext.request.contextPath}/edit-complaint/${viewRaiseComplaint.complaintId}">Edit</a></td>--!>

                                  </tr>
                              </c:forEach>
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>

          </body>
          </html>

