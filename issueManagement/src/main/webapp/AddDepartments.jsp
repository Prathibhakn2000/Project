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
      <h3><b><center>Add Departments</center></b></h3>
      <form action="add-department" method="post">

             <span style="color:green"><strong>${message}</strong></span>

                        <div class="row mb-3">
                            <span id="deptError" style="color:red;"></span><br>
                            <label for="deptType" class="form-label"><b>Department Type:</b></label>
                                <input type="text" class="form-control" id="deptType" name="departmentType" placeholder="Enter Department Type" value="">
                            </div>


                        <div class="mb-3">
                            <span id="deptAddressError" style="color:red;"></span><br>
                            <label for="deptAddress" class="form-label"><b>Department Address:</b></label>
                                <input type="text" class="form-control" id="deptAddress" name="departmentAddress" placeholder="Enter Department Address" value="" >
                            </div>


                        <div class="mb-3">
                              <span id="deptCityError" style="color:red;"></span><br>
                              <label for="deptCity" class="form-label"><b>department City:</b></label>
                                  <input type="text" class="form-control" id="deptCity" name="departmentCity" placeholder="Enter Department City" value="" >
                              </div>


                         <input type="submit" value="Add Department" class="btn btn-primary oval-btn bold-text" id="add_dept">
                         </form>
                             </div>
                           </div>
                         </div>
                         </body>
                         </html>
