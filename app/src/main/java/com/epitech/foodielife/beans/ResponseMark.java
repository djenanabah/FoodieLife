package com.epitech.foodielife.beans;

import java.util.List;

/**
 * Created by Vincent RAGOT on 04/05/2017.
 */

public class ResponseMark {
    private String message;
    private List<Mark> list;

    /**
     * Constructor
     */
    public ResponseMark() {
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
    public List<Mark> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Mark> list) {
        this.list = list;
    }
}
