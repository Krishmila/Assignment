package com.caremarque.doctor.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.jasper.compiler.Node.DoBodyAction;
import org.json.JSONException;
import org.json.JSONObject;

import com.caremarque.doctor.model.Doctor;
import com.caremarque.doctor.util.CommonUtils;
import com.caremarque.doctor.util.Constants;
import com.caremarque.doctor.util.DBConnection;

public class DoctorServiceImpl implements IDoctorService {

	//This object is for logging
	public static final Logger log = Logger.getLogger(IDoctorService.class.getName());

	
	//implementation of createDoctor method
	
		@Override
		public String createDoctor(Doctor doctor) {
		
			String output = "";
			Connection con = null;
			PreparedStatement preparedStatement = null;
			
			System.out.println("Doctor Charges: " +  doctor.getDoctorCharges());
			
				try {
					con = DBConnection.getDBConnection();
					
					String query = "INSERT INTO doctor("
							+"doctorId,"
							+"firstName,"
							+"lastName,"
							+"regNo,"
							+"gender,"
							+"specialization,"
							+"phone,"
							+"email,"
							+"doctorCharges,"
							+"password,"
							+"confirmPassword)"
							+"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
					
					preparedStatement = con.prepareStatement(query);
					
					preparedStatement.setString(Constants.COLUMN_INDEX_ONE, doctor.getDoctorId());
					preparedStatement.setString(Constants.COLUMN_INDEX_TWO, doctor.getFirstName());
					preparedStatement.setString(Constants.COLUMN_INDEX_THREE, doctor.getLastName());
					preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, doctor.getRegNo());
					preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, doctor.getGender());
					preparedStatement.setString(Constants.COLUMN_INDEX_SIX, doctor.getSpecialization());
					preparedStatement.setString(Constants.COLUMN_INDEX_SEVEN, doctor.getPhone());
					preparedStatement.setString(Constants.COLUMN_INDEX_EIGHT, doctor.getEmail());
					preparedStatement.setDouble(Constants.COLUMN_INDEX_NINE, doctor.getDoctorCharges());
					preparedStatement.setString(Constants.COLUMN_INDEX_TEN, doctor.getPassword());
					preparedStatement.setString(Constants.COLUMN_INDEX_ELEVEN, doctor.getConfirmPassword());
					
					preparedStatement.executeUpdate();
					
					String newDoctor = getDoctors();
					output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
					System.out.println("inside data base");
				
				 }catch (Exception e) { 
					output = "{\"status\":\"error\", \"data\": \"Error while inserting doctor details.\"}";
					System.err.println(e.getMessage());
					log.log(Level.SEVERE, e.getMessage());
					
					
				 } finally {
					
					try {
						if (preparedStatement != null) {
							preparedStatement.close();
						}
		
						if (con != null) {
							con.close();
						}	
						
					} catch (Exception e) {
					log.log(Level.SEVERE, e.getMessage());
					
					}
				 }
				
				return output;
				
		}
		
		
	//implementation of getDoctors method
		
		@Override
		public String getDoctors() {
	
			String output = "";
			Statement st = null;
			ResultSet rs = null;
			Connection con = null;
		
			try {
				
				con = DBConnection.getDBConnection();
				
				String query = "SELECT * FROM doctor";
				
				st = con.createStatement();
				rs = st.executeQuery(query);
				
				output = "<table border=\"1\">"						
						+"<tr>"+"<th>doctorId</th>"
						+"<th>firstName</th>"
						+"<th>lastName</th>"
						+"<th>regNo</th>"
						+"<th>gender</th>"
						+"<th>specialization</th>"
						+"<th>phone</th>"
						+"<th>email</th>"
						+"<th>doctorCharges</th>"
						+"<th>password</th>"
						+"<th>confirmPassword</th>"
						+"<th>Update</th>"+"<th>Remove</th></tr>";
				
				while(rs.next()) {
					
					String doctorId = rs.getString("doctorId");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String regNo =  rs.getString("regNo");
					String gender = rs.getString("gender");
					String specialization = rs.getString("specialization");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					double doctorCharges = rs.getDouble("doctorCharges");
					String password = rs.getString("password");
					String confirmPassword = rs.getString("confirmPassword");
					
					output += "<tr><td><input id='hidDoctorIDUpdate' name='hidDoctorIDUpdate'  value='"+ doctorId 
								+"'>"+ "</td>";
					output += "<td>" + firstName + "</td>";
					output += "<td>" + lastName + "</td>";
					output += "<td>" + regNo + "</td>";
					output += "<td>" + gender + "</td>";
					output += "<td>" + specialization + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + doctorCharges + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + confirmPassword + "</td>";
					
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'data-doctorid'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'data-doctorid='"
							+ doctorId + "'>" + "</td></tr>";
				}
				
				output += "</table>";
				
				} catch(Exception e) {
				
					output = "Error while reading doctor details...!";
					System.err.println(e.getMessage());
				
				}finally {
				
					try {
					
						if (st != null) {
							st.close();
						}
						
						if (con != null) {
							con.close();
						}
						
						if(rs != null) {
							rs.close();
						}
						
					} catch (Exception e) {
					e.printStackTrace();
					}
				
				}
			
			return output;
	
		}
		
	
		//implementation of updateDoctor method..
		
		@Override	
		public String updateDoctor(String doctorId,String firstName,String lastName,String regNo,String gender,String specialization,String phone,String email,String doctorCharges,String password,String confirmPassword) {
		
		
			String output = "";
			Connection con = null;
			PreparedStatement preparedStatement = null;
			
			try {
				Double carge=Double.parseDouble(doctorCharges);
				
				con = DBConnection.getDBConnection();
				String query = "UPDATE doctor SET doctorId=?,firstName=?, lastName=?, regNo=?, gender=?, specialization=?, phone=?, email=?, doctorCharges=?, password=?, confirmPassword=? WHERE doctorId=?";
				
				preparedStatement = con.prepareStatement(query);
				
				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, doctorId );
				preparedStatement.setString(Constants.COLUMN_INDEX_TWO, firstName);
				preparedStatement.setString(Constants.COLUMN_INDEX_THREE, lastName);
				preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, regNo);
				preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, gender);
				preparedStatement.setString(Constants.COLUMN_INDEX_SIX, specialization);
				preparedStatement.setString(Constants.COLUMN_INDEX_SEVEN, phone);
				preparedStatement.setString(Constants.COLUMN_INDEX_EIGHT, email);
				preparedStatement.setDouble(Constants.COLUMN_INDEX_NINE, carge);
				preparedStatement.setString(Constants.COLUMN_INDEX_TEN, password);
				preparedStatement.setString(Constants.COLUMN_INDEX_ELEVEN, confirmPassword);
				preparedStatement.setString(Constants.COLUMN_INDEX_TWELVE, doctorId);
				preparedStatement.execute();
				
				String newDoctor = getDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
				
				
			}catch(Exception e) {
							output = "{\"status\":\"error\", \"data\": \"Error while updating doctor details.\"}";
							System.out.println(e.getMessage());
							log.log(Level.SEVERE, e.getMessage());
			}finally {
				
					try {
							if(preparedStatement != null) {
								preparedStatement.close();
							}
					
							if(con != null) {
								con.close();
							}
							
					}catch(Exception e) {
						
					e.printStackTrace();
					log.log(Level.SEVERE, e.getMessage());
						}
					}
			
			return output;
		}

		//implementation of cancelDoctor method

		@Override
		public String cancelDoctor(String doctorId) {
			
			String output = "";
			PreparedStatement preparedStatmnt = null;
			Connection con = null;
			
			try {
				
				con = DBConnection.getDBConnection();
				
				String query = "DELETE FROM doctor WHERE doctorId = ?";
				
				preparedStatmnt = con.prepareStatement(query);
				
				preparedStatmnt.setString(Constants.COLUMN_INDEX_ONE, doctorId);
				System.out.println("inside delete"+doctorId);
				preparedStatmnt.execute();
				
				String newDoctor = getDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";

			}catch(Exception e) {
				
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
				System.out.println(e.getMessage());
			
			}finally {
				
						try {
							
						if(preparedStatmnt != null) {
							preparedStatmnt.close();
						}
						
						if(con != null) {
							con.close();
						}
						
						}catch(Exception e) {
							e.printStackTrace();
							}
						
						}
			
			return output;
		}
	
		
		
			//This method get all the existing doctorids and put them into a arraylist
		
			@Override
			public ArrayList<String> getDoctorIDs() {
		
				
					PreparedStatement preparedStatement = null;
					ResultSet rs = null;
					Connection con = null;
		
						ArrayList<String> arrayList = new ArrayList<String>();
		
							try {
								
									con = DBConnection.getDBConnection();
					
									String query = "SELECT doctor.doctorId FROM doctor";
					
									preparedStatement = con.prepareStatement(query);
									rs = preparedStatement.executeQuery();
					
									while(rs.next()) {
						
										arrayList.add(rs.getString(1));
						
									}
								
							} catch (Exception e) {
								
									log.log(Level.SEVERE, e.getMessage());
				
							} finally {
								
								try {
									
									if (preparedStatement != null) {
										preparedStatement.close();
										}
									
									if (con != null) {
										con.close();
										}
									
								 } catch (SQLException e) {
									
									log.log(Level.SEVERE, e.getMessage());
									}
	
							}
							
							System.out.println(arrayList.size());
							return arrayList;
			}
}
