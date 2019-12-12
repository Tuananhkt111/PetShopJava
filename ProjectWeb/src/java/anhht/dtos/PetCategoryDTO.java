/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.dtos;

import java.io.Serializable;

/**
 *
 * @author tuana
 */
public class PetCategoryDTO implements Serializable {
    private String petCatID, petType;

    public PetCategoryDTO(String petCatID, String petType) {
        this.petCatID = petCatID;
        this.petType = petType;
    }

    public String getPetCatID() {
        return petCatID;
    }

    public void setPetCatID(String petCatID) {
        this.petCatID = petCatID;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

}
