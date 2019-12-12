/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bbbyl
 */
public class TransactionDTO implements Serializable {
    private String accTranID, username, deliveryPhone, deliverAddress, status;
    private Date timeOrder, timeFinish;
    private float totalPrice;

    public TransactionDTO(String accTranID, String username, String deliveryPhone, String deliverAddress, float totalPrice) {
        this.accTranID = accTranID;
        this.username = username;
        this.deliveryPhone = deliveryPhone;
        this.deliverAddress = deliverAddress;
        this.status = "Waiting for response";
        this.timeOrder = new Date();
        this.totalPrice = totalPrice;
    }

    public TransactionDTO(String accTranID, String username, String deliveryPhone, String deliverAddress, String status, Date timeOrder, Date timeFinish, float totalPrice) {
        this.accTranID = accTranID;
        this.username = username;
        this.deliveryPhone = deliveryPhone;
        this.deliverAddress = deliverAddress;
        this.status = status;
        this.timeOrder = timeOrder;
        this.timeFinish = timeFinish;
        this.totalPrice = totalPrice;
    }

    
    public String getAccTranID() {
        return accTranID;
    }

    public void setAccTranID(String accTranID) {
        this.accTranID = accTranID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Date timeOrder) {
        this.timeOrder = timeOrder;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    
}
