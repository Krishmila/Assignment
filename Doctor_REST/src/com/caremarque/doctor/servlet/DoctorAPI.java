
package com.caremarque.doctor.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caremarque.doctor.model.Doctor;
import com.caremarque.doctor.service.DoctorServiceImpl;
import com.caremarque.doctor.util.CommonUtils;

@WebServlet("/DoctorAPI")
public class DoctorAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl();
	
    public DoctorAPI() {
        super();
      
    }

		private static Map getParasMap(HttpServletRequest request) {
			
			Map<String, String> map = new HashMap<String, String>();
			
			try {
				
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
									 scanner.useDelimiter("\\A").next() : "";
				
				scanner.close();
				
				String[] params = queryString.split("&");
				for(String param : params) {
					
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
									 
			}
			catch (Exception e) {
				
			}
			return map;
		}
	    
    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Doctor doctor = new Doctor();
			DoctorServiceImpl ob=new DoctorServiceImpl();
			String doctorId = CommonUtils.generateDoctorIDs(ob.getDoctorIDs());
			System.out.println("check");
			
			doctor.setDoctorId(doctorId);
			doctor.setFirstName(request.getParameter("firstName"));
			System.out.println("Check Name"+request.getParameter("firstName"));
			doctor.setLastName(request.getParameter("lastName"));
			doctor.setRegNo(request.getParameter("regNo"));
			doctor.setGender(request.getParameter("gender"));
			doctor.setSpecialization(request.getParameter("specialization"));
			doctor.setPhone(request.getParameter("phone"));
			doctor.setEmail(request.getParameter("email"));
			System.out.println("Check charges"+request.getParameter("doctorCharges"));
			try {
			doctor.setDoctorCharges(Double.parseDouble(request.getParameter("doctorCharges")));
			}catch(NumberFormatException e) {System.out.println(e);}
			doctor.setPassword(request.getParameter("password"));
			doctor.setConfirmPassword(request.getParameter("confirmPassword"));
	
			String output = doctorServiceImpl.createDoctor(doctor);
			System.out.println(output);
			response.getWriter().write(output);
			
		}

	
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Map paras = getParasMap(request);
		
			try {
			String output = doctorServiceImpl.updateDoctor(
			paras.get("doctorId").toString(),
			paras.get("firstName").toString(),
			paras.get("lastName").toString(),
			paras.get("regNo").toString(),
			paras.get("gender").toString(),
			paras.get("specialization").toString(),
			paras.get("phone").toString(),
			paras.get("email").toString(),
			paras.get("doctorCharges").toString(),
			paras.get("password").toString(),
			paras.get("confirmPassword").toString());
			
			response.getWriter().write(output);
			}catch(Exception e) {
				System.out.println(e);
			}
		}

		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Map paras = getParasMap(request);
			
			String output = doctorServiceImpl.cancelDoctor(paras.get("doctorID").toString());
			
			response.getWriter().write(output);
		}
	

}
