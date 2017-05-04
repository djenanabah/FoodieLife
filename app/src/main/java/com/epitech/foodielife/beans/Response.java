package com.epitech.foodielife.beans;

import java.util.List;

/**
 *
 * @author Vincent RAGOT
 * @param <T> Template for any class
 */
public class Response<T> {

    private String message;
    private List<T> list;

    /**
     * Constructor
     */
    public Response() {
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

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }

}
