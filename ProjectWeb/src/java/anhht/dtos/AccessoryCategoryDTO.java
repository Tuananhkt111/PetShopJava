/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.dtos;

import anhht.daos.PetCategoryDAO;
import java.io.Serializable;

/**
 *
 * @author tuana
 */
public class AccessoryCategoryDTO implements Serializable {
    private String accCatID, accCatName, petCatID, petType;

    public AccessoryCategoryDTO(String accCatID, String accCatName, String petCatID) throws Exception {
        this.accCatID = accCatID;
        this.accCatName = accCatName;
        this.petCatID = petCatID;
        setPetType();
    }
    

    public String getAccCatID() {
        return accCatID;
    }

    public void setAccCatID(String accCatID) {
        this.accCatID = accCatID;
    }

    public String getAccCatName() {
        return accCatName;
    }

    public void setAccCatName(String accCatName) {
        this.accCatName = accCatName;
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

    public void setPetType() throws Exception {
        PetCategoryDAO dao = new PetCategoryDAO();
        this.petType = dao.findByPrimaryKey(petCatID);
    }
    
}
