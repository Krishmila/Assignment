<%@page import="com.caremarque.doctor.util.CommonUtils"%>
<%@page import= "com.caremarque.doctor.service.DoctorServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%--   
    <%
  //Save---------------------------------
    if (request.getParameter("doctorId") != null)
    {
    	Doctor doctorObj = new Doctor();
    String stsMsg = "";
    //Insert--------------------------
    if (request.getParameter("hidItemIDSave") == "")
    {
    stsMsg = doctorObj.createDoctor(request.getParameter("doctorId"),
    request.getParameter("firstName"),
    request.getParameter("lastName"),
    request.getParameter("regNo"),
    request.getParameter("gender"),
    request.getParameter("specialization"),
    request.getParameter("phone"),
    request.getParameter("email"),
    request.getParameter("doctorCharges"),
    request.getParameter("password"),
    request.getParameter("confirmPassword"));
    }
    else//Update----------------------
    {
    stsMsg = doctorObj.updateDoctor(request.getParameter("hidDoctorIDSave"),
    			request.getParameter("doctorId"),
    			request.getParameter("firstName"),
    		    request.getParameter("lastName"),
    		    request.getParameter("regNo"),
    		    request.getParameter("gender"),
    		    request.getParameter("specialization"),
    		    request.getParameter("phone"),
    		    request.getParameter("email"),
    		    request.getParameter("doctorCharges"),
    		    request.getParameter("password"),
    		    request.getParameter("confirmPassword"));
    }
    session.setAttribute("statusMsg", stsMsg);
    }
    //Delete-----------------------------
    if (request.getParameter("hidDoctorIDDelete") != null)
    {
    	Doctor doctorObj = new Doctor();
    String stsMsg =
    		doctorObj.deleteDoctor(request.getParameter("hidDcotorIDDelete"));
    session.setAttribute("statusMsg", stsMsg);
    }
    
    %> 
    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/doctor.js"></script>

</head>
<body>

<div class="container">
<div class="row">
<div class="col-6">
	<h1>Doctor Management..</h1>
	
	<!-- <form id="formDoctor" name="formDoctor">   -->
		<form id="formDoctor" name="formDoctor" method="POST" action="doctor.jsp">
		
		<!--Doctor ID:  -->
		<input id="doctorId" name="doctorId" type="hidden" class="form-control form-control-sm" value="10">
		
		<br>First Name:
		<input id="firstName" name="firstName" type="text" class="form-control form-control-sm">
		
		<br> Last Name:
		<input id="lastName" name="lastName" type="text" class="form-control form-control-sm">
		
		<br> Registration Number:
		<input id="regNo" name="regNo" type="text" class="form-control form-control-sm">
		
		<br> Gender:
		<input id="gender" name="gender" type="text" class="form-control form-control-sm">
		
		<br> Specialization:
		<input id="specialization" name="specialization" type="text" class="form-control form-control-sm">
		
		<br> Phone:
		<input id="phone" name="phone" type="text" class="form-control form-control-sm">
		
		<br> Email:
		<input id="email" name="email" type="text" class="form-control form-control-sm">
		
		<br> Doctor Charges:
		<input id="doctorCharges" name="doctorCharges" type="number" step="1" pattern="\d+"  class="form-control form-control-sm">
		
		<br> Password:
		<input id="password" name="password" type="text" class="form-control form-control-sm">
		
		<br> Confirm Password:
		<input id="confirmPassword" name="confirmPassword" type="text" class="form-control form-control-sm">
		
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
		<input type="hidden" id="Save" name="hidDoctorIDSave" value="">
		
	</form>
	
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		
		<br>
		<div id="divDoctorsGrid">
			<%
					DoctorServiceImpl doctorObj = new DoctorServiceImpl();
					out.print(doctorObj.getDoctors());
			%>
		</div>
		
		

</div></div></div>

</body>
</html>