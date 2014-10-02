package com.ebook.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 */
@Named(value = "genreBean")
@SessionScoped
public class GenreBean implements Serializable {

    private int genreID;
    private String name;

    /**
     * Creates a new instance of GenreBean
     */
    public GenreBean() {
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.genreID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenreBean other = (GenreBean) obj;
        if (this.genreID != other.genreID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GenreBean{" + "genreID=" + genreID + '}';
    }

}
