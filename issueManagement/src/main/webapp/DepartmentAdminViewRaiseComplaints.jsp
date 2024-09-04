

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

<!-- Display Success Message -->
<c:if test="${not empty successMessage}">
    <div class="alert alert-success" role="alert">
        ${successMessage}
    </div>
</c:if>

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
                        <th>Status</th>
                        <th>Allocate Employee</th>
                        <th>Submit</th>
                    </tr>
                </thead>
                <tbody>
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

                            <form action="allocate-employee" method="post">
                                <td>
                                    <input type="hidden" name="complaintId" value="${deptAdminViewComplaints.complaintId}" id="complaintId">

                                    <select class="form-select" name="status" id="status">
                                        <option selected>${complaint.status}</option>
                                        <option value="Completed">Completed</option>
                                        <option value="In Progress">In Progress</option>
                                        <option value="Not Completed">Not Completed</option>
                                    </select>
                                </td>

                                <td>
                                    <div style="width:110px;">
                                        <select class="form-select" name="employeeId" id="employeeId">
                                            <c:forEach var="employee" items="${employees}">
                                                <option value="${employee.employeeId}"
                                                    <c:if test="${employee.employeeId == employee.employeeId}">
                                                        selected
                                                    </c:if>>
                                                    ${employee.empFullName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>

                                <td>
                                    <button type="submit" class="btn btn-primary mt-2"
                                        <c:if test="${not empty deptAdminViewComplaints.employeeId}">
                                            disabled
                                        </c:if>>
                                        Submit
                                    </button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>




