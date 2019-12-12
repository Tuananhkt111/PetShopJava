/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.dtos;

import anhht.daos.AccessoryCategoryDAO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author tuana
 */
public class AccessoryDTO implements Serializable {
    private String accID, accName, accCatID, description, brand, image, accCatName;
    private float price, salePercent;
    private Date startSellingDate;
    private int availableQuantity;
    private int quantity;
    private boolean isDelete;

    public AccessoryDTO(String accID, String accName, String accCatID, String description, String brand, String image, String accCatName, float price, float salePercent, Date startSellingDate, int availableQuantity, int quantity, boolean isDelete) {
        this.accID = accID;
        this.accName = accName;
        this.accCatID = accCatID;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.accCatName = accCatName;
        this.price = price;
        this.salePercent = salePercent;
        this.startSellingDate = startSellingDate;
        this.availableQuantity = availableQuantity;
        this.quantity = quantity;
        this.isDelete = isDelete;
    }

    public AccessoryDTO(String accID, String accName, String accCatID, String description, String brand, String image, float price, float salePercent, Date startSellingDate, int availableQuantity) throws Exception {
        this.accID = accID;
        this.accName = accName;
        this.accCatID = accCatID;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.price = price;
        this.salePercent = salePercent;
        this.startSellingDate = startSellingDate;
        this.availableQuantity = availableQuantity;
        this.quantity = 0;
        this.isDelete = false;
        setAccCatName();
    }

    public AccessoryDTO(String accID, String accName, String accCatID, String description, String brand, String image, float price, float salePercent, Date startSellingDate, int availableQuantity, boolean isDelete) throws Exception {
        this.accID = accID;
        this.accName = accName;
        this.accCatID = accCatID;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.price = price;
        this.salePercent = salePercent;
        this.startSellingDate = startSellingDate;
        this.availableQuantity = availableQuantity;
        this.quantity = 0;
        this.isDelete = isDelete;
        setAccCatName();
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccCatID() {
        return accCatID;
    }

    public void setAccCatID(String accCatID) {
        this.accCatID = accCatID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartSellingDate() {
        return startSellingDate;
    }

    public void setStartSellingDate(Date startSellingDate) {
        this.startSellingDate = startSellingDate;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getAccCatName() {
        return accCatName;
    }

    public void setAccCatName() throws Exception {
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        this.accCatName = dao.findByPrimaryKey(accCatID);
    }

    public float getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(float salePercent) {
        this.salePercent = salePercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
       
    
}
