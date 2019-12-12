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
 * @author bbbyl
 */
public class TransactionRelDTO implements Serializable {
    private String accID, accTranID, accName, accCatID, brand, description, image, accCatName;
    private float price, salePercent;
    private Date startSellingDate;
    private int quantity;

    public TransactionRelDTO(String accID, String accTranID, String accName, String accCatID, String brand, String description, String image, float price, float salePercent, Date startSellingDate, int quantity) {
        this.accID = accID;
        this.accTranID = accTranID;
        this.accName = accName;
        this.accCatID = accCatID;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.price = price;
        this.salePercent = salePercent;
        this.startSellingDate = startSellingDate;
        this.quantity = quantity;
    }

    public TransactionRelDTO(String accID, String accName, String accCatID, String brand, String description, String image, float price, float salePercent, Date startSellingDate, int quantity) throws Exception {
        this.accID = accID;
        this.accName = accName;
        this.accCatID = accCatID;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.price = price;
        this.salePercent = salePercent;
        this.startSellingDate = startSellingDate;
        this.quantity = quantity;
        setAccCatName();
    }

    public TransactionRelDTO(String accID, int quantity) {
        this.accID = accID;
        this.quantity = quantity;
    }

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public String getAccTranID() {
        return accTranID;
    }

    public void setAccTranID(String accTranID) {
        this.accTranID = accTranID;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(float salePercent) {
        this.salePercent = salePercent;
    }

    public Date getStartSellingDate() {
        return startSellingDate;
    }

    public void setStartSellingDate(Date startSellingDate) {
        this.startSellingDate = startSellingDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getAccCatName() {
        return accCatName;
    }

    public void setAccCatName() throws Exception {
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        this.accCatName = dao.findByPrimaryKey(accCatID);
    }
}
