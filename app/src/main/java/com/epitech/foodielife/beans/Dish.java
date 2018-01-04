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

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Id
    @Column(name = "idDish", unique = true)
    private int idDish;
    
    @Column(name = "idRestaurant", nullable = false)
    private int idRestaurant;

    /**
     * Constructor
     */
    public Dish() {
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
     * @return the idDish
     */
    public int getIdDish() {
        return idDish;
    }

    /**
     * @param idDish the idDish to set
     */
    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

}
