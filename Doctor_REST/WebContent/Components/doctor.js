/**
 * 
 */

$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	
	
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
	// Form validation-------------------
		var status = validateDoctorForm();
		if (status != true)
		{
			$("#alertError").text(status);
			$("#alertError").show();
			return;
		}
		
	
	//If valid
		var check=$("#Save").val() == "";
		var type = (check) ? "POST" : "PUT";
		
		$.ajax(
		{
			url : "DoctorAPI",
			type : type,
			data : $("#formDoctor").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onDoctorSaveComplete(response.responseText, status);
				
			}
		});
		$("#hidDoctorIDSave").val("");
		$("#formDoctor")[0].reset();
		
});

function onDoctorSaveComplete(response, status)
{
	
	if (status == "success"){
		
		$("#alertSuccess").text("Successfully saved.");
		
		$("#alertSuccess").show();
		
		setTimeout(window.location.reload(true), 95000000);
		
	} else if (status == "error"){
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else{
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
		$("#Save").val("PUT");
		$("#hidDoctorIdSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
		$("#doctorId").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
		$("#firstName").val($(this).closest("tr").find('td:eq(1)').text());
		$("#lastName").val($(this).closest("tr").find('td:eq(2)').text());
		$("#regNo").val($(this).closest("tr").find('td:eq(3)').text());
		$("#gender").val($(this).closest("tr").find('td:eq(4)').text());
		$("#specialization").val($(this).closest("tr").find('td:eq(5)').text());
		$("#phone").val($(this).closest("tr").find('td:eq(6)').text());
		$("#email").val($(this).closest("tr").find('td:eq(7)').text());
		$("#doctorCharges").val($(this).closest("tr").find('td:eq(8)').text());
		$("#password").val($(this).closest("tr").find('td:eq(9)').text());
		$("#confirmPassword").val($(this).closest("tr").find('td:eq(10)').text());
});



$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "DoctorAPI",
		type : "DELETE",
		data : "doctorID=" + $(this).data("doctorid"),
		dataType : "text",
		complete : function(response, status)
		{
			onDoctorDeleteComplete(response.responseText, status);
		}
		});
	});

function onDoctorDeleteComplete(response, status){
	
	if (status == "success")
	{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		setTimeout(window.location.reload(true), 95000000);
		
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// CLIENTMODEL=========================================================================
function validateDoctorForm()
{
	// FIRSTNAME
	if ($("#firstName").val().trim() == "")
	{
		return "Insert First Name.";
	}
	var letterReg1 = /^[A-Za-z]+$/;
	var tmpfName =  $("#firstName").val().trim();
	if(!tmpfName.match(letterReg1)){
		return "First Name must have alphabet charaters only...!";
	}
	
	// LASTNAME
	if ($("#lastName").val().trim() == "")
	{
		return "Insert Last Name.";
	}
	var letterReg2 = /^[A-Za-z]+$/;
	var tmplName =  $("#lastName").val().trim();
	if(!tmplName.match(letterReg2)){
		return "Last Name must have alphabet charaters only...!";
	}
	//REGISTRATION NUMBER..
	if ($("#regNo").val().trim() == "")
	{
		return "Insert Registration Number.";
	}
	//GENDER...
	if ($("#gender").val().trim() == "0")
	{
		return "Select Gender.";
	}
	//SPECIALIZATION..
	if ($("#specialization").val().trim() == "")
	{
		return "Insert Specialization.";
	}
	//PHONE..
	if ($("#phone").val().trim() == "")
	{
		return "Insert Phone Number.";
	}
	var contactReg = /^\d{10}$/;
	var tmpPhone =  $("#phone").val().trim();
	if(!tmpPhone.match(contactReg)){
		return "Insert a valid Phone Number...!";
	}
	//EMAIL
	if ($("#email").val().trim() == "")
	{
		return "Insert Email Address.";
	}
	
	var emailReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var tmpEmail =  $("#email").val().trim();
	if(!tmpEmail.match(emailReg)){
		return "Insert a valid Email...!";
	}
	
	//DOCTOR CHARGES
	if ($("#doctorCharges").val().trim() == "")
	{
		return "Insert Doctor Charges.";
	}
	// is numerical value
	var tmpCharges = $("#doctorCharges").val().trim();
	if (!$.isNumeric(tmpCharges))
	{
		return "Insert a numerical value for Doctor Charges.";
	}
	// convert to decimal price
	$("#doctorCharges").val(parseFloat(tmpCharges).toFixed(2));
	
	// PASSWORD
	if ($("#password").val().trim() == "")
	{
		return "Insert Password.";
	}
	
	var pwdReg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,10}$/;
	var tmpPwd =  $("#password").val().trim();
	if(!tmpPwd.match(pwdReg)){
		return "Insert a Password 4 to 10 characters which contain at least one numeric digit, one uppercase and one lowercase letter...!";
	}
	//CONFIRM PASSWORD
	if ($("#confirmPassword").val().trim() == "")
	{
		return "Insert to Confirm Password.";
	}
	
	var tmpCpwd = $("#confirmPassword").val().trim();
	if(tmpCpwd != tmpPwd){
		return "Passwords are mismatching...!";
	}
	
return true;
}