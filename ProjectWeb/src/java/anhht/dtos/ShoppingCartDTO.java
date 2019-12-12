/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.dtos;

import anhht.daos.AccessoryDAO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Hoang Dung
 */
public class ShoppingCartDTO implements Serializable {

    private String username;
    private HashMap<String, AccessoryDTO> cart;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String, AccessoryDTO> getCart() {
        return cart;
    }

    public ShoppingCartDTO() {
        this.username = "Guest";
        this.cart = new HashMap<>();
    }

    public ShoppingCartDTO(String username) {
        this.username = username;
        this.cart = new HashMap<>();
    }

    public boolean addCart(AccessoryDTO dto) throws Exception {
        boolean check = false;
        if (this.cart.containsKey(dto.getAccID())) {
            int quantityAdded = this.cart.get(dto.getAccID()).getQuantity();
            AccessoryDAO dao = new AccessoryDAO();
            int available = dao.findAvailableQuantityAcc(dto.getAccID());
            if (available != -1) {
                if (available > quantityAdded) {
                    int quantity = quantityAdded + dto.getQuantity();
                    dto.setQuantity(quantity);
                    this.cart.put(dto.getAccID(), dto);
                    check = true;
                }
            }
        } else {
            this.cart.put(dto.getAccID(), dto);
            check = true;
        }
        return check;
    }

    public void removeCart(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public float getTotal() throws Exception {
        float result = 0;
        for (AccessoryDTO dto : this.cart.values()) {
            result += dto.getPrice() * dto.getQuantity();
        }
        return result;
    }

    public boolean updateCart(String id, int quantity) throws Exception {
        boolean check = false;
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
        }
        return check;
    }
}
