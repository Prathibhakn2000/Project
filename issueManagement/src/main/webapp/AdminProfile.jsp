<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Spring Project</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<style>
  .dropdown-menu {
    min-width: 300px; /* Set the minimum width of the dropdown */
  }

  .dropdown-menu > li {
    font-size: 16px; /* Increase font size */
  }


</style>
</head>
<body>
<nav class="p-2 mb-1 navbar navbar-light bg-info">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
      </a>
      <a class="navbar-brand" href="index.jsp"><b>Home</b></a>
    </div>

    <div class="dropdown">
      <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
      Admin

      </div>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

        <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>
        <li><a class="dropdown-item" href="view-raise-complaint"><strong>ViewRaiseComplaintDetails</strong></a></li>
         <li><a class="dropdown-item" href="AddDepartments.jsp"><strong>AddDepartments</strong></a></li>


      </ul>
    </div>
  </div>
</nav>

 <form action="/edit-profile" method="post">

      <center>
          <strong style="color:blue">
              <h3 class="mt-5   extra-margin-top">${AdminProfilePageMessage}</h3>
          </strong>
      </center>

  </form>


</body>
</html>
