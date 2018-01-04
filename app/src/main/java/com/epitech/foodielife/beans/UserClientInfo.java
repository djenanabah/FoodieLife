package com.epitech.foodielife.beans;

import java.io.Serializable;

/**
 *
 * @author Vincent RAGOT
 */
public class UserClientInfo implements Serializable {

    private String token;
    private String eMail;
    private String pictureUrl;
    private String name;
    private String message;

    /**
     * Constructor
     */
    public UserClientInfo() {
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the eMail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail the eMail to set
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return the pictureUrl
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * @param pictureUrl the pictureUrl to set
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
