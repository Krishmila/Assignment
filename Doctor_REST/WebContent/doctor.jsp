<%@page import="com.caremarque.doctor.util.CommonUtils"%>
<%@page import= "com.caremarque.doctor.service.DoctorServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/doctor.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/doctor.js"></script>

</head>
<body>

<div class="container">

	
	<!-- <form id="formDoctor" name="formDoctor">   -->
		<form id="formDoctor" name="formDoctor" method="POST" action="doctor.jsp">
		
		<div class="form-title"> Doctor Registration</div>
		<br/><br/>
		<h4>Add Doctor Details</h4>
		
		
					<!--Doctor ID:  -->
					<!--  <label>Doctor ID <label_1>*</label_1></label><br /> -->
					<input id="doctorId" name="doctorId" type="hidden" class="form-control form-control-sm" value="10">
			
			
		<div class="row">		
			<div class="col">
					<label>First Name<label_1>*</label_1></label><br/>
					<input id="firstName" name="firstName" type="text" class="form-control form-control-sm"><br/>
			</div>
		</div>	
		
			<div class="row">
				<div class="col">	
					<label>Last Name<label_1>*</label_1></label><br/>
					<input id="lastName" name="lastName" type="text" class="form-control form-control-sm"><br/>
				</div>
			</div>	
		
		
		<div class="row">
				<div class="col">
					<label>Registration Number<label_1>*</label_1></label><br/>
					<input id="regNo" name="regNo" type="text" class="form-control form-control-sm"><br/>
				</div>
			</div>	
		
		<div class="row">
				<div class="col">
					<label>Gender<label_1>*</label_1></label><br/>
					<input id="gender" name="gender" type="text" class="form-control form-control-sm"><br/>
				</div>
		</div>		
		
		<div class="row">
				<div class="col">
					<label>Specialization<label_1>*</label_1></label><br/>
					<input id="specialization" name="specialization" type="text" class="form-control form-control-sm"><br/>
				</div>
		</div>
		
		<div class="row">
				<div class="col">		
					<label> Phone<label_1>*</label_1></label><br/>
					<input id="phone" name="phone" type="text" class="form-control form-control-sm"><br/>
				</div>
		</div>
		
			
		<div class="row">
				<div class="col">			
					<label> Email<label_1>*</label_1></label><br/>
					<input id="email" name="email" type="text" class="form-control form-control-sm"><br/>
				</div>
		</div>		
		
		<div class="row">
				<div class="col">	
					<label>Doctor Charges<label_1>*</label_1></label><br/>
					<input id="doctorCharges" name="doctorCharges" type="number" step="1" pattern="\d+"  class="form-control form-control-sm"><br/>
				</div>
		</div>		
		
		<div class="row">
				<div class="col">	
					<label>Password<label_1>*</label_1></label><br/>
					<input id="password" name="password" type="text" class="form-control form-control-sm"><br/>
				</div>
		</div>		
		
		<div class="row">
				<div class="col">	
					<label>Confirm Password<label_1>*</label_1></label><br/>
					<input id="confirmPassword" name="confirmPassword" type="text" class="form-control form-control-sm"><br/>
				</div>
		</div>		
		
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
		<input type="hidden" id="Save" name="hidDoctorIDSave" value=""> <br><br>
		
	
	
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		</form>
		
		<br>
		<div id="divDoctorsGrid">
			<%
					DoctorServiceImpl doctorObj = new DoctorServiceImpl();
					out.print(doctorObj.getDoctors());
			%>
		</div>
		
		

</div>



</body>
</html>