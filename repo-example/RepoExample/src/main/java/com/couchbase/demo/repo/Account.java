
package com.couchbase.demo.repo;

/**
 * A Pojo which describes an account
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Account {

    private String aId;
    private String firstName;
    private String lastName;
    private String emailAddr;
    private String type;

    public Account(String aId) {

        this.aId = aId;
        this.type = "account";
    
    }
    
    /**
     * @return the aid
     */
    public String getAid() {
        return aId;
    }

    /**
     * @param aid the aid to set
     */
    public void setAid(String aid) {
        this.aId = aid;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailAddr
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * @param emailAddr the emailAddr to set
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    @Override
    public String toString() {
    
        return "firstName = " + firstName + ", lastName = " + lastName;
        
    }

    public String getType() {
        return type;
    }
    
    
    
    
    
}
