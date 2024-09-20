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


        <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">

      </div>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

      <!-- Check if profileImage is available in the session
                               <c:choose>
                                 <c:when test="${not empty sessionScope.profileImage}">
                                   <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="40" height="40" class="rounded-circle">
                                 </c:when>
                                 <c:otherwise>
                                   <img src="${pageContext.request.contextPath}/path/to/default/profile.jpg" alt="Default Profile" width="40" height="40" class="rounded-circle">
                                 </c:otherwise>
                               </c:choose>-->

       <!-- Displaying the user's name or email
           <span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b>${sessionScope.email}</b></span> <!-- or ${sessionScope.email} -->
        <br>
        <br>-->

      </ul>
    </div>
  </div>
</nav>
<body>
<h3> Employee Profile Page  </h3>
</body>

</html>
