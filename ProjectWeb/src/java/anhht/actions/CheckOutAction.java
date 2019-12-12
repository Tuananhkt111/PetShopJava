/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.AccessoryDAO;
import anhht.daos.PrincipalDAO;
import anhht.daos.TransactionDAO;
import anhht.daos.TransactionRelDAO;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.PrincipalDTO;
import anhht.dtos.ShoppingCartDTO;
import anhht.dtos.TransactionDTO;
import anhht.dtos.TransactionRelDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
 * @author bbbyl
 */
@ParentPackage("json-default")
@ResultPath("/")
@Results({
    @Result(name = "jsonResult", type = "json")
    ,
    @Result(name = "loadcosuccess", location = "/user/checkout.jsp")
    ,
    @Result(name = "loadcofailed", location = "/guest/error.jsp")
    ,
    @Result(name = "checkoutsuccess", location = "/user/user.jsp")
    ,
    @Result(name = "checkoutfailed", location = "/user/cart.jsp"),})
public class CheckOutAction extends ActionSupport {

    private String deliveryAddress, deliveryPhone, username;
    private HashMap<String, AccessoryDTO> cart;

    public CheckOutAction() {
    }

    @Action("getDeliveryInfoAction")
    public String getDeliveryInfo() throws Exception {
        PrincipalDAO dao = new PrincipalDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        PrincipalDTO dto = dao.getDeliveryInfo(username);
        if (dto != null) {
            deliveryAddress = dto.getAddress();
            deliveryPhone = dto.getPhone();
        } else {
            request.setAttribute("ERROR", "Load checkout failed");
            return "loadcofailed";
        }
        HttpSession session = request.getSession();
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        HashMap<String, AccessoryDTO> cartList = shoppingCart.getCart();
        cart = new HashMap<>();
        for (Map.Entry me : cartList.entrySet()) {
            String id = (String) me.getKey();
            AccessoryDTO dtoSession = (AccessoryDTO) me.getValue();
            AccessoryDAO accDAO = new AccessoryDAO();
            AccessoryDTO accDTO = accDAO.findByPrimaryKey(id);
            accDTO.setQuantity(dtoSession.getQuantity());
            cart.put(id, accDTO);
        }
        return "loadcosuccess";
    }

    @Action("checkOutAction")
    public String checkOut() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("USER");
        ShoppingCartDTO shoppingCart = (ShoppingCartDTO) session.getAttribute("cart");
        HashMap<String, AccessoryDTO> cartList = shoppingCart.getCart();
        for (Map.Entry me : cartList.entrySet()) {
            String id = (String) me.getKey();
            AccessoryDTO dtoSession = (AccessoryDTO) me.getValue();
            AccessoryDAO accDAO = new AccessoryDAO();
            AccessoryDTO accDTO = accDAO.findByPrimaryKey(id);
            if (accDTO.isIsDelete() || accDTO.getAvailableQuantity() == 0 || accDTO.getAvailableQuantity() < dtoSession.getQuantity()) {
                return "checkoutfailed";
            }
        }
        TransactionDAO tranDAO = new TransactionDAO();
        String accTranID = generateAccTranID();
        TransactionDTO tranDTO = new TransactionDTO(accTranID, userName, deliveryPhone, deliveryAddress, 0);
        tranDAO.insert(tranDTO);
        float total = 0;
        for (Map.Entry me : cartList.entrySet()) {
            String id = (String) me.getKey();
            AccessoryDTO dtoSession = (AccessoryDTO) me.getValue();
            AccessoryDAO accDAO = new AccessoryDAO();
            AccessoryDTO accDTO = accDAO.findByPrimaryKey(id);
            TransactionRelDTO tranRelDTO = new TransactionRelDTO(id, accTranID, accDTO.getAccName(), accDTO.getAccCatID(), accDTO.getBrand(), accDTO.getDescription(), accDTO.getImage(), accDTO.getPrice(), accDTO.getSalePercent(), accDTO.getStartSellingDate(), dtoSession.getQuantity());
            TransactionRelDAO tranRelDAO = new TransactionRelDAO();
            tranRelDAO.insert(tranRelDTO);
            accDTO.setAvailableQuantity(accDTO.getAvailableQuantity() - dtoSession.getQuantity());
            accDAO.update(accDTO);
            total += dtoSession.getQuantity() * accDTO.getPrice() * (1 - accDTO.getSalePercent());
        }
        total =(float) Math.round(total * 100) / 100;
        tranDTO.setTotalPrice(total);
        tranDAO.updateTotalPrice(total, accTranID);
        shoppingCart.getCart().clear();
        session.setAttribute("cart", shoppingCart);
        return "checkoutsuccess";
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public HashMap<String, AccessoryDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, AccessoryDTO> cart) {
        this.cart = cart;
    }

    public String generateAccTranID() throws Exception {
        String id;
        Random rd = new Random();
        TransactionDAO dao = new TransactionDAO();
        do {
            id = "AT";
            for (int i = 0; i < 4; i++) {
                id += rd.nextInt(10);
            }
        } while (dao.checkExisted(id));
        return id;
    }
}
