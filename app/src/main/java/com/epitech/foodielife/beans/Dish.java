package com.epitech.foodielife.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vincent RAGOT
 */
@Entity
@Table(name = "Dish")
public class Dish implements Serializable {

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "description", nullable = true)
    private String description;

    @Id
    @Column(name = "idRestaurant", unique = true)
    private int idRestaurant;

    /**
     * Constructor
     */
    public Dish() {
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the idRestaurant
     */
    public int getIdRestaurant() {
        return idRestaurant;
    }

    /**
     * @param idRestaurant the idRestaurant to set
     */
    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

}
