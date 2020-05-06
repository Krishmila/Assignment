package com.caremarque.doctor.service;

import java.util.ArrayList;
import java.util.Map;

import com.caremarque.doctor.model.Doctor;

public interface IDoctorService {

	
	public String createDoctor(Doctor doctor);

	public ArrayList<String> getDoctorIDs();
	
	public String getDoctors();

	public String cancelDoctor(String doctorId);

	public String updateDoctor(String doctorId,String firstName,String lastName,String regNo,String gender,String specialization,String phone,String email,String doctorCharges,String password,String confirmPassword);
	

}
