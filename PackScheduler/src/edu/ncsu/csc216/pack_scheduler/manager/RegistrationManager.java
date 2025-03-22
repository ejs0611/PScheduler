package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Registration Manager class
 * 
 * @author eric smith
 */
public class RegistrationManager {
	
	/** instance */
	private static RegistrationManager instance;
	/** course catalog */
	private CourseCatalog courseCatalog;
	/** student directory */
	private StudentDirectory studentDirectory;
	/** registrar user */
	private User registrar;
	/** current user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** properties files */
	private static final String PROP_FILE = "registrar.properties";

	private RegistrationManager() { //Private b/c used to construct single instance  
	    createRegistrar();
	    courseCatalog = new CourseCatalog();
	    studentDirectory = new StudentDirectory();
	    
	}

	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	private String hashPW(String pw) {
	    try {
	        MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest1.update(pw.getBytes());
	        return Base64.getEncoder().encodeToString(digest1.digest());
	    } catch (NoSuchAlgorithmException e) {
	        throw new IllegalArgumentException("Cannot hash password");
	    }
	}
	
	/**
	 * get current instance
	 * 
	 * @return instance 
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * get course catalog
	 * 
	 * @return course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * set student directory
	 * 
	 * @return student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	
	/**
	 * login
	 * 
	 * @param id unityID of user
	 * @param password password
	 * @return boolean if successfully logged in
	 */
	public boolean login(String id, String password) {
	    Student s = studentDirectory.getStudentById(id);
	    
		

	    if(this.getCurrentUser() != null && !this.getCurrentUser().getId().equals(id)) {
    		return false;
    	}
	    
	    if (registrar.getId().equals(id)) {
	    	if(registrar.getPassword().equals(hashPW(password))) {
	    		currentUser = registrar;
	        	return true;
	    	}
	    	return false;
	    } 
	    
	    if (s != null) {
	    	
	        String localHashPW = hashPW(password);
	        if (s.getPassword() != null && s.getPassword().equals(localHashPW) && s.getId() != null && s.getId().equals(id)) {
	        	
	            currentUser = s;
	            return true;
	        }
	    }
	    if(s == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
	    
	    return false;
	}



	/**
	 * logout
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * returns current user
	 * 
	 * @return currentUser the current User
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * clear data
	 */
	public void clearData() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
	}
	
	private static class Registrar extends User {
	    /**
	     * Create a registrar user.
	     * 
	     * @param firstName registrar first name
	     * @param lastName registrar last name
	     * @param id registrar unityID
	     * @param email registrar email
	     * @param hashPW registrar hashed password
	     */
	    public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
	        super(firstName, lastName, id, email, hashPW);
	    }
	}

	/**
	 * get registrar
	 * 
	 * @return registrar
	 */
	public User getRegistrar() {
		return registrar;
	}
	
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

	
}