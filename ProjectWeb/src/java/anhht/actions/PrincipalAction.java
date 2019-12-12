/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.PrincipalDAO;
import anhht.daos.ShoppingCartDAO;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.PrincipalDTO;
import anhht.dtos.ShoppingCartDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
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
    ,
    @Result(name = "logout", type = "redirect", location = "/guest/home.jsp")
    ,
    @Result(name = "loginsuccess", location = "/guest/home.jsp")
    ,
    @Result(name = "error", location = "/guest/error.jsp")
    ,
    @Result(name = "registersuccess", type = "chain", location = "loginActionSyn"),})
public class PrincipalAction extends ActionSupport {

    private String txtUsernameLg, txtPasswordLg, role;
    private String txtUsernameRg, txtPasswordRg, txtFullnameRg, txtPhoneRg, txtAddressRg;
    private String txtFullnameUpd, txtPhoneUpdt, txtAddressUpdt, msg;
    private PrincipalDTO dto;
    private boolean isExisted;

    public PrincipalAction() {
    }

    @Action("checkUsernameAction")
    public String checkUsername() throws Exception {
        PrincipalDAO dao = new PrincipalDAO();
        isExisted = dao.checkExisted(txtUsernameRg);
        return "jsonResult";
    }

    @Action("logOutAction")
    public String logOut() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("USER");
        if (username != null) {
            ShoppingCartDTO scDTO = (ShoppingCartDTO) session.getAttribute("cart");
            if (scDTO != null) {
                ShoppingCartDAO dao = new ShoppingCartDAO();
                if (dao.checkEmptyByUsername(username)) {
                    if (!dao.deleteAllByUsername(username)) {
                        request.setAttribute("ERROR", "Delete all accessories in cart failed.");
                        return "error";
                    }
                }
                if (!scDTO.getCart().isEmpty()) {
                    if (!dao.insertAll(scDTO)) {
                        request.setAttribute("ERROR", "Insert all accessories to cart failed.");
                        return "error";
                    }
                }
            }
            session.invalidate();
        }
        return "logout";
    }

    @Action("loginAction")
    public String logIn() throws Exception {
        PrincipalDAO dao = new PrincipalDAO();
        role = dao.checkLogin(txtUsernameLg, txtPasswordLg);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!role.equals("failed")) {
            if (role.equals("admin") || role.equals("user")) {
                HttpSession session = request.getSession();
                session.setAttribute("USER", txtUsernameLg);
                session.setAttribute("ROLE", role);
                if (role.equals("user")) {
                    ShoppingCartDAO scDAO = new ShoppingCartDAO();
                    ArrayList<AccessoryDTO> listAccCart = scDAO.findAllAccCartByUsername(txtUsernameLg);
                    ShoppingCartDTO scDTO = new ShoppingCartDTO(txtUsernameLg);
                    for (AccessoryDTO accessoryDTO : listAccCart) {
                        scDTO.addCart(accessoryDTO);
                    }
                    session.setAttribute("cart", scDTO);
                }
            }
        }
        return "jsonResult";
    }
    
    @Action("loadProfileAction")
    public String loadProfile() throws Exception {
        PrincipalDAO dao = new PrincipalDAO();
        dto = dao.findByUsername(txtUsernameLg);
        return "jsonResult";
    }
    
    @Action("updateProfileAction")
    public String updateProfile() throws Exception {
        PrincipalDAO dao = new PrincipalDAO();
        dto = new PrincipalDTO(txtUsernameLg, "", txtFullnameUpd, txtPhoneUpdt, txtAddressUpdt);
        if(dao.update(dto)) {
            msg = "Update profile success";
        } else {
            msg = "Update profile failed";
        }
        return "jsonResult";
    }

    @Action("loginActionSyn")
    public String logInSyn() throws Exception {
        PrincipalDAO dao = new PrincipalDAO();
        role = dao.checkLogin(txtUsernameRg, txtPasswordRg);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!role.equals("failed")) {
            if (role.equals("admin") || role.equals("user")) {
                HttpSession session = request.getSession();
                session.setAttribute("USER", txtUsernameRg);
                session.setAttribute("ROLE", role);
                if (role.equals("user")) {
                    ShoppingCartDAO scDAO = new ShoppingCartDAO();
                    ArrayList<AccessoryDTO> listAccCart = scDAO.findAllAccCartByUsername(txtUsernameRg);
                    ShoppingCartDTO scDTO = new ShoppingCartDTO(txtUsernameRg);
                    for (AccessoryDTO accessoryDTO : listAccCart) {
                        scDTO.addCart(accessoryDTO);
                    }
                    session.setAttribute("cart", scDTO);
                }
                return "loginsuccess";
            } else {
                request.setAttribute("ERROR", "Your role is not supported.");
                return "error";
            }
        } else {
            request.setAttribute("ERROR", "Log in or register error.");
            return "error";
        }
    }

    @Action("registerAction")
    public String registerAccount() throws Exception {
        dto = new PrincipalDTO(txtUsernameRg, txtPasswordRg, txtFullnameRg, txtPhoneRg, txtAddressRg);
        PrincipalDAO dao = new PrincipalDAO();
        if (dao.register(dto)) {
            return "registersuccess";
        } else {
            HttpServletRequest req = ServletActionContext.getRequest();
            req.setAttribute("ERROR", "Register user failed.");
            return "error";
        }
    }

    public String getTxtUsernameLg() {
        return txtUsernameLg;
    }

    public void setTxtUsernameLg(String txtUsernameLg) {
        this.txtUsernameLg = txtUsernameLg;
    }

    public String getTxtPasswordLg() {
        return txtPasswordLg;
    }

    public void setTxtPasswordLg(String txtPasswordLg) {
        this.txtPasswordLg = txtPasswordLg;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTxtUsernameRg() {
        return txtUsernameRg;
    }

    public void setTxtUsernameRg(String txtUsernameRg) {
        this.txtUsernameRg = txtUsernameRg;
    }

    public String getTxtPasswordRg() {
        return txtPasswordRg;
    }

    public void setTxtPasswordRg(String txtPasswordRg) {
        this.txtPasswordRg = txtPasswordRg;
    }

    public String getTxtFullnameRg() {
        return txtFullnameRg;
    }

    public void setTxtFullnameRg(String txtFullnameRg) {
        this.txtFullnameRg = txtFullnameRg;
    }

    public String getTxtPhoneRg() {
        return txtPhoneRg;
    }

    public void setTxtPhoneRg(String txtPhoneRg) {
        this.txtPhoneRg = txtPhoneRg;
    }

    public String getTxtAddressRg() {
        return txtAddressRg;
    }

    public void setTxtAddressRg(String txtAddressRg) {
        this.txtAddressRg = txtAddressRg;
    }

    public PrincipalDTO getDto() {
        return dto;
    }

    public void setDto(PrincipalDTO dto) {
        this.dto = dto;
    }

    public boolean isIsExisted() {
        return isExisted;
    }

    public void setIsExisted(boolean isExisted) {
        this.isExisted = isExisted;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getTxtPhoneUpdt() {
        return txtPhoneUpdt;
    }

    public void setTxtPhoneUpdt(String txtPhoneUpdt) {
        this.txtPhoneUpdt = txtPhoneUpdt;
    }

    public String getTxtAddressUpdt() {
        return txtAddressUpdt;
    }

    public void setTxtAddressUpdt(String txtAddressUpdt) {
        this.txtAddressUpdt = txtAddressUpdt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTxtFullnameUpd() {
        return txtFullnameUpd;
    }

    public void setTxtFullnameUpd(String txtFullnameUpd) {
        this.txtFullnameUpd = txtFullnameUpd;
    }


    
}
