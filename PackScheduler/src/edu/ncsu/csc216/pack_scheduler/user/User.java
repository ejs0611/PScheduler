package edu.ncsu.csc216.pack_scheduler.user;


/**
 * User abstract class
 * 
 * @author Eric Smith
 */
public abstract class User {

	/** Students firstName */
	private String firstName;
	/** Students lastName */
	private String lastName;
	/** Students id */
	private String id;
	/** Students email */
	private String email;
	/** Students password */
	private String password;

	

	/**
	 * User constructor
	 * 
	 * @param firstName user first name
	 * @param lastName user last name
	 * @param id user id
	 * @param email user email
	 * @param password user password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * Sets the Student's password.
	 * 
	 * @param hashPW the password to set
	 * @throws IllegalArgumentException if the hashPW parameter is invalid
	 */
	public void setPassword(String hashPW) {
		if (hashPW == null || "".equals(hashPW)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = hashPW;
	}

	/**
	 * Sets the Student's email.
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if the email parameter is invalid
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				index1 = i;
			} else if (email.charAt(i) == '.') {
				index2 = i;
			}
		}
		if (index1 > index2) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Sets the Student's id.
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if the id parameter is invalid
	 */
	public void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Sets the Student's last name.
	 * 
	 * @param lastName the last name to set
	 * @throws IllegalArgumentException if the lastName parameter is invalid
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	
	}

	/**
	 * Sets the Student's first name.
	 * 
	 * @param firstName the first name to set
	 * @throws IllegalArgumentException if the firstName parameter is invalid
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	
	}

	/**
	 * Returns the Student's first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the Student's last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the Student's id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the Student's email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the Student's password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
//	private static class Registrar extends User {
//	    /**
//	     * Create a registrar user.
//	     * 
//	     * @param firstName registrar first name
//	     * @param lastName registrar last name
//	     * @param id registrar id
//	     * @param email registrar email
//	     * @param hashPW registrar hashed password
//	     */
//	    public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
//	        super(firstName, lastName, id, email, hashPW);
//	    }
//	}

}

