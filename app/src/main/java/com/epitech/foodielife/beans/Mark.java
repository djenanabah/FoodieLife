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
@Table(name = "Mark")
public class Mark implements Serializable {

    @Id
    @Column(name = "idMark", unique = true)
    private int idMark;

    @Column(name = "stars", nullable = false)
    private int stars;

    @Column(name = "commentaire", nullable = true)
    private String commentaire;

    @Column(name = "idDish", nullable = false)
    private int idDish;

    @Column(name = "user", nullable = false)
    private String user;

    /**
     * Constructor
     */
    public Mark() {
    }

    /**
     * @return the idMark
     */
    public int getIdMark() {
        return idMark;
    }

    /**
     * @param idMark the idMark to set
     */
    public void setIdMark(int idMark) {
        this.idMark = idMark;
    }

    /**
     * @return the stars
     */
    public int getStars() {
        return stars;
    }

    /**
     * @param stars the stars to set
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

}
