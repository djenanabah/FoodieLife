package com.epitech.foodielife.beans;

import java.io.Serializable;

/**
 *
 * @author Vincent RAGOT
 * @param <T> Template for any class
 */
public class Params<T> implements Serializable {

    private UserClientInfo user;
    private T value;

    /**
     * Constructor
     */
    public Params() {
    }

    /**
     * @return the user
     */
    public UserClientInfo getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserClientInfo user) {
        this.user = user;
    }

    /**
     * @return the list
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the list to set
     */
    public void setValue(T value) {
        this.value = value;
    }

}
