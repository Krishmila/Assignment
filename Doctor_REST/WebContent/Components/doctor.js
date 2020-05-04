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
		
	// If valid------------------------
		//$("#formDoctor").submit();
		
		//If valid
		var type = ($("hideDoctorIdSave").val() == "") ? "POST" : "PUT";
		
		$.ajax(
		{
			url : "DoctorAPI",
			type : type,
			data : $("formDoctor").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onDoctorSaveComplete(response.responseText, status);
			}
		});
});

function onDoctorSaveComplete(response, status)
{
	if (status == "success"){
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divDoctorsGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error"){
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error"){
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else{
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidDoctorIDSave").val("");
	$("#formDoctor")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
		$("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
		$("#doctorId").val($(this).closest("tr").find('td:eq(0)').text());
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



// CLIENTMODEL=========================================================================
function validateDoctorForm()
{
// CODE
if ($("#firstName").val().trim() == "")
{
return "Insert First Name.";
}
// NAME
if ($("#lastName").val().trim() == "")
{
return "Insert Last Name.";
}
//PRICE-------------------------------
if ($("#regNo").val().trim() == "")
{
return "Insert Registration Number.";
}
//NAME
if ($("#gender").val().trim() == "")
{
return "Insert Gender.";
}
//NAME
if ($("#specialization").val().trim() == "")
{
return "Insert Specialization.";
}
//NAME
if ($("#phone").val().trim() == "")
{
return "Insert Phone Number.";
}
//NAME
if ($("#email").val().trim() == "")
{
return "Insert Email Address.";
}
//NAME
if ($("#doctorCharges").val().trim() == "")
{
return "Insert Doctor Charges.";
}
// is numerical value
var tmpCharges = $("#doctorCharges").val().trim();
if (!$.isNumeric(tmpCharges))
{
return "Insert a numerical value for Item Price.";
}
// convert to decimal price
$("#doctorCharges").val(parseFloat(tmpCharges).toFixed(2));
// DESCRIPTION------------------------
if ($("#password").val().trim() == "")
{
return "Insert Password.";
}
if ($("#confirmPassword").val().trim() == "")
{
return "Insert to Confirm Password.";
}
return true;
}