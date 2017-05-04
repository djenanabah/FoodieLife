package com.epitech.foodielife.beans;

import java.util.List;

/**
 * Created by Vincent RAGOT on 04/05/2017.
 */

public class ResponseRestaurant {

    private String message;
    private List<Restaurant> list;

    /**
     * Constructor
     */
    public ResponseRestaurant() {
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
    public List<Restaurant> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Restaurant> list) {
        this.list = list;
    }

}
