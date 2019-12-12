/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.FavoriteDAO;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.FavoriteDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 *
 * @author tuana
 */
@ParentPackage("json-default")
@ResultPath("/")
@Result(name = "jsonResult", type = "json")
public class FavoriteAction extends ActionSupport {

    private String accIDIns, usernameIns, usernameSearch;
    private String accIDDel, usernameDel, msg;
    private ArrayList<AccessoryDTO> list;
    private FavoriteDTO dto;

    public FavoriteAction() {
    }

    @Action("load8MostFavoriteAccessoriesAction")
    public String findMostFavoriteAccessories() throws Exception {
        FavoriteDAO dao = new FavoriteDAO();
        list = dao.findEightMostFavoriteAccessories();
        return "jsonResult";
    }

    @Action("loadFavoriteAccessoriesByNameAction")
    public String loadFavoriteAccessoriesByName() throws Exception {
        FavoriteDAO dao = new FavoriteDAO();
        list = dao.findFavoriteAccessoriesByUsername(usernameSearch);
        return "jsonResult";
    }

    @Action("insertFavoriteAction")
    public String insert() throws Exception {
        FavoriteDAO dao = new FavoriteDAO();
        dto = new FavoriteDTO(usernameIns, accIDIns);
        if (dao.insert(dto)) {
            msg = "Add accessory to favorite list success";
        } else {
            msg = "Add accessory to favorite list failed";
        }
        return "jsonResult";

    }

    @Action("deleteFavoriteAction")
    public String delete() throws Exception {
        FavoriteDAO dao = new FavoriteDAO();
        dto = new FavoriteDTO(usernameDel, accIDDel);
        if (dao.delete(dto)) {
            msg = "Delete accessory from favorite list success";
        } else {
            msg = "Delete accessory from favorite list failed";
        }
        return "jsonResult";
    }

    public ArrayList<AccessoryDTO> getList() {
        return list;
    }

    public void setList(ArrayList<AccessoryDTO> list) {
        this.list = list;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getAccIDIns() {
        return accIDIns;
    }

    public void setAccIDIns(String accIDIns) {
        this.accIDIns = accIDIns;
    }

    public String getUsernameIns() {
        return usernameIns;
    }

    public void setUsernameIns(String usernameIns) {
        this.usernameIns = usernameIns;
    }

    public String getAccIDDel() {
        return accIDDel;
    }

    public void setAccIDDel(String accIDDel) {
        this.accIDDel = accIDDel;
    }

    public String getUsernameDel() {
        return usernameDel;
    }

    public void setUsernameDel(String usernameDel) {
        this.usernameDel = usernameDel;
    }

    public FavoriteDTO getDto() {
        return dto;
    }

    public void setDto(FavoriteDTO dto) {
        this.dto = dto;
    }

    public String getUsernameSearch() {
        return usernameSearch;
    }

    public void setUsernameSearch(String usernameSearch) {
        this.usernameSearch = usernameSearch;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
}
