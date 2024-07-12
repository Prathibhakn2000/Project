<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Spring Project</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--for dropdown--!>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

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

     <div class=" dropdown-toggle"  id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">


         <img src="https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg" alt="Profile" width="50" height="50" class="rounded-circle"> <!-- Add your profile icon path -->

                </div>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="edit-profile"><strong>Edit</strong></a></li><!--href="Edit-Profile" edit controller getmapping action--!>
        <li><a class="dropdown-item" href="ResetPassword.jsp"><strong>Reset Password</strong></a></li>
        <li><a class="dropdown-item" href="view-profile"><strong>View</strong></a></li>
      </ul>
    </div>


</div>
</div>
  </div>
</nav>
</body>
</html>

