package edu.ncsu.csc216.pack_scheduler.user;

/**
 * User superclass which is the parent of Student and Registrar class. Used for registration
 * 
 * @author Hubert Ngo, Dustin Hollar, Brian Wu
 *
 */
public abstract class User {

    /** Student's firstName */
    private String firstName;
    /** Student's lastName */
    private String lastName;
    /** Student's id */
    private String id;
    /** Student's email */
    private String email;
    /** Student's password */
    private String password;

    /**
     * Creates a new User object
     * 
     * @param firstName User's firstname
     * @param lastName User's lastName
     * @param id User's id
     * @param email User's email
     * @param password User's password
     */
    public User(String firstName, String lastName, String id, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
        setEmail(email);
        setPassword(password);
    }

    /**
     * Returns the student's first name.
     * @return the first name
     */
    public String getFirstName() {
    	return firstName;
    }

    /**
     * Sets the student's first name.
     * @param firstName the firstName to set
     * @throws IllegalArgumentException if the string is empty or null
     */
    public void setFirstName(String firstName) {
    	if (firstName == null || firstName.equals("") ) {
    		throw new IllegalArgumentException("Invalid first name");
    	}
    	this.firstName = firstName;
    }

    /**
     * Returns the student's last name.
     * @return the last name
     */
    public String getLastName() {
    	return lastName;
    }

    /**
     * Sets the student's last name.
     * @param lastName the lastName to set
     * @throws IllegalArgumentException if string is empty or null
     */
    public void setLastName(String lastName) {
    	if (lastName == null || lastName.equals("")) {
    		throw new IllegalArgumentException("Invalid last name");
    	}
    	this.lastName = lastName;
    }

    /**
     * Returns the student's id.
     * @return student id
     */
    public String getId() {
    	return id;
    }

    /**
     * Sets the student id.
     * @param id the id to set
     * @throws IllegalArgumentException if the string is empty or null
     */
    protected void setId(String id) {
    	if (id == null || id.equals("")) {
    		throw new IllegalArgumentException("Invalid id");
    	}
    	this.id = id;
    }

    /**
     * Returns the student's email.
     * @return the email
     */
    public String getEmail() {
    	return email;
    }

    /**
     * Sets the student's email.
     * @param email the email to set
     * @throws IllegalArgumentException if the string is empty or null, if email doesn't contain an '@' character
     *  or email doesn't contain a ',' character or if the index of the last '.' character in the email string is 
     *  earlier than the index of the first '.' character  
     */
    public void setEmail(String email) {
    	if (email == null || email.equals("") || !email.contains("@") || !email.contains(".") || 
    			email.lastIndexOf(".") < email.indexOf("@")) {
    		throw new IllegalArgumentException("Invalid email");
    	}
    	this.email = email;
    }

    /**
     * Returns the student's password.
     * @return the password
     */
    public String getPassword() {
    	return password;
    }

    /**
     * Returns the password.
     * @param password the password to set
     * @throws IllegalArgumentException if the password is null or an empty string
     */
    public void setPassword(String password) {
    	if (password == null || password.equals("")) {
    		throw new IllegalArgumentException("Invalid password");
    	}
    	this.password = password;
    }
    
    /**
     * Generates a hashCode for User
     * 
     * @return the hashCode as an int
     */
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

    /**
     * Compares 2 Users for equality
     * 
     * @param obj object to compare
     * @return boolean determining if the 2 objects are equal
     */
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
}