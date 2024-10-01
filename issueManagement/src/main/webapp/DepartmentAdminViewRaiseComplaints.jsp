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
</head>

<body>

<nav class="navbar navbar-light bg-info">
    <div class="container-fluid">
        <div class="navbar-header">
           <!-- <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>-->
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

<!-- View of Raised Complaints -->

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
    <div class="card highlight-card">
        <div class="card-body">
            <h3><b><center>DeptAdminComplaintView</center></b></h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Serial Number</th>
                        <th>ID</th>
                        <th>Complaint Type</th>
                        <th>Country</th>
                        <th>State</th>
                        <th>City</th>
                        <th>Area</th>
                        <th>Address</th>
                        <th>Description</th>
                        <th>UserId</th>
                        <th>Allocate Employee</th>
                        <th>Submit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop to display complaints -->
                    <c:forEach var="deptAdminViewComplaints" items="${viewDepartmentComplaints}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${deptAdminViewComplaints.complaintId}</td>
                            <td>${deptAdminViewComplaints.complaintType}</td>
                            <td>${deptAdminViewComplaints.country}</td>
                            <td>${deptAdminViewComplaints.state}</td>
                            <td>${deptAdminViewComplaints.city}</td>
                            <td>${deptAdminViewComplaints.area}</td>
                            <td>${deptAdminViewComplaints.address}</td>
                            <td>${deptAdminViewComplaints.description}</td>
                            <td>${deptAdminViewComplaints.signUpDTO.id}</td>

                            <!-- Form to allocate employee -->
                            <form action="allocate-employee" method="post">
                                <input type="hidden" name="complaintId" value="${deptAdminViewComplaints.complaintId}" />
                                <input type="hidden" name="status" value="Open" />

                                <td>
                                    <div style="width:110px;">
                                        <select class="form-select" name="employeeId" id="employeeId">
                                            <!-- Loop to display employees -->
                                            <c:forEach var="employee" items="${employees}">
                                                <option value="${employee.employeeId}"
                                                    <c:if test="${deptAdminViewComplaints.employeeId != null && employee.employeeId == deptAdminViewComplaints.employeeId.employeeId}">
                                                        selected
                                                    </c:if>>
                                                    ${employee.empFullName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>

                                <td>
                                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                                </td>
                            </form>


                            <!-- Form to delete employee-->


                            <form action="delete-employee" method="post" onsubmit="return confirm('Are you sure you want to delete this employee?');">
                                <!-- Hidden field for complaintId -->
                                <input type="hidden" name="complaintId" value="${deptAdminViewComplaints.complaintId}" />

                                <!-- Ensure employeeId exists before creating the hidden input -->
                                <c:if test="${deptAdminViewComplaints.employeeId != null}">
                                    <input type="hidden" name="employeeId" value="${deptAdminViewComplaints.employeeId.employeeId}" />
                                </c:if>

                                <td>
                                    <button type="submit" class="btn btn-danger mt-2">Delete Employee</button>
                                </td>
                            </form>


                        </tr>
                    </c:forEach>
                </tbody>

        </div>
    </div>
</div>
</body>
</html>
