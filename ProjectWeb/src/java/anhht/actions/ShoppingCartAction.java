/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.AccessoryDAO;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.ShoppingCartDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author tuana
 */
@ParentPackage("json-default")
@ResultPath("/")
@Results({
    @Result(name = "jsonResult", type = "json")
})
public class ShoppingCartAction extends ActionSupport {

    private String accID, username, quantity;
    private AccessoryDTO dto;
    private String msg;
    private int count , availableQuantity, quantityValue;
    private HashMap<String, AccessoryDTO> cart;

    public ShoppingCartAction() {
    }

    @Action("addCartAction")
    public String addCart() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCartDTO(username);
        }
        AccessoryDAO dao = new AccessoryDAO();
        dto = dao.findByPrimaryKey(accID);
        dto.setQuantity(1);
        if (shoppingCart.addCart(dto)) {
            msg = "Added to cart";
        } else {
            msg = "Available quantity is not enough to buy.";
        }
        session.setAttribute("cart", shoppingCart);
        count = shoppingCart.getCart().size();
        return "jsonResult";
    }
    
    @Action("addCartDetailAction")
    public String addCartDetail() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCartDTO(username);
        }
        AccessoryDAO dao = new AccessoryDAO();
        dto = dao.findByPrimaryKey(accID);
        dto.setQuantity(quantityValue);
        if (shoppingCart.addCart(dto)) {
            msg = "Added to cart";
        } else {
            msg = "Available quantity is not enough to buy.";
        }
        session.setAttribute("cart", shoppingCart);
        count = shoppingCart.getCart().size();
        return "jsonResult";
    }

    @Action("updateAccCartAction")
    public String updateAccCart() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        shoppingCart.updateCart(accID, Integer.parseInt(quantity));
        session.setAttribute("cart", shoppingCart);
        return "jsonResult";
    }

    @Action("countCartAction")
    public String countCart() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        count = shoppingCart.getCart().size();
        return "jsonResult";
    }

    @Action("loadAccCartAction")
    public String loadCart() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        HashMap<String, AccessoryDTO> cartList = shoppingCart.getCart();
        cart = new HashMap<>();
        for (Map.Entry me : cartList.entrySet()) {
            String id = (String) me.getKey();
            AccessoryDTO dtoSession = (AccessoryDTO) me.getValue();
            AccessoryDAO dao = new AccessoryDAO();
            AccessoryDTO accDTO = dao.findByPrimaryKey(id);
            accDTO.setQuantity(dtoSession.getQuantity());
            cart.put(id, accDTO);
        }
        return "jsonResult";
    }

    @Action("removeCartAction")
    public String removeCart() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");      
        shoppingCart.removeCart(accID);
        session.setAttribute("cart", shoppingCart);
        return "jsonResult";
    }
    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccessoryDTO getDto() {
        return dto;
    }

    public void setDto(AccessoryDTO dto) {
        this.dto = dto;
    }

    public HashMap<String, AccessoryDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, AccessoryDTO> cart) {
        this.cart = cart;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(int quantityValue) {
        this.quantityValue = quantityValue;
    }

    
}
