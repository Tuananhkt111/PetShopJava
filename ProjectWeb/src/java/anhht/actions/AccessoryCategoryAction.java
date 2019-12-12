/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.AccessoryCategoryDAO;
import anhht.dtos.AccessoryCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
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
public class AccessoryCategoryAction extends ActionSupport {

    private ArrayList<AccessoryCategoryDTO> list;
    private String accCatSearch, accCatIDIns, accCatNameIns, accCatIDUpdt, accCatNameUpdt, accCatIDDelete, petCatIDInsFr, petCatIDUpdtFr, msg;
    private AccessoryCategoryDTO dto;
    private boolean isExisted;

    public AccessoryCategoryAction() {
    }

    @Action("loadAllAccessoryCategoryAction")
    public String loadAllAccessoryCategory() throws Exception {
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        list = dao.findAllAccessoryCategoryAvailable();
        return "jsonResult";
    }

    @Action("findAccCatByLikeNameAction")
    public String findAccCatByLikeName() throws Exception {
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        list = dao.findAccCategoryByLikeName(accCatSearch);
        return "jsonResult";
    }

    @Action("checkAccCatIDExistedAction")
    public String checkExisted() throws Exception {
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        isExisted = dao.checkExisted(accCatIDIns);
        return "jsonResult";
    }

    @Action("insertAccCatAction")
    public String insert() throws Exception {
        dto = new AccessoryCategoryDTO(accCatIDIns, accCatNameIns, petCatIDInsFr);
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        if (dao.insert(dto)) {
            msg = "Insert accessory category success";
        } else {
            msg = "Insert accessory category failed";
        }
        return "jsonResult";
    }

    @Action("updateAccCatAction")
    public String update() throws Exception {
        dto = new AccessoryCategoryDTO(accCatIDUpdt, accCatNameUpdt, petCatIDUpdtFr);

        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        if (dao.update(dto)) {
            msg = "Update accessory category success";
        } else {
            msg = "Update accessory category failed";
        }
        return "jsonResult";
    }

    @Action("deleteAccCatAction")
    public String delete() throws Exception {
        AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
        if (dao.delete(accCatIDDelete)) {
            msg = "Delete accessory category success";
        } else {
            msg = "Delete accessory category failed";
        }
        return "jsonResult";
    }

    public ArrayList<AccessoryCategoryDTO> getList() {
        return list;
    }

    public void setList(ArrayList<AccessoryCategoryDTO> list) {
        this.list = list;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getAccCatSearch() {
        return accCatSearch;
    }

    public void setAccCatSearch(String accCatSearch) {
        this.accCatSearch = accCatSearch;
    }

    public String getAccCatIDIns() {
        return accCatIDIns;
    }

    public void setAccCatIDIns(String accCatIDIns) {
        this.accCatIDIns = accCatIDIns;
    }

    public String getAccCatNameIns() {
        return accCatNameIns;
    }

    public void setAccCatNameIns(String accCatNameIns) {
        this.accCatNameIns = accCatNameIns;
    }

    public String getAccCatIDUpdt() {
        return accCatIDUpdt;
    }

    public void setAccCatIDUpdt(String accCatIDUpdt) {
        this.accCatIDUpdt = accCatIDUpdt;
    }

    public String getAccCatNameUpdt() {
        return accCatNameUpdt;
    }

    public void setAccCatNameUpdt(String accCatNameUpdt) {
        this.accCatNameUpdt = accCatNameUpdt;
    }

    public String getAccCatIDDelete() {
        return accCatIDDelete;
    }

    public void setAccCatIDDelete(String accCatIDDelete) {
        this.accCatIDDelete = accCatIDDelete;
    }

    public AccessoryCategoryDTO getDto() {
        return dto;
    }

    public void setDto(AccessoryCategoryDTO dto) {
        this.dto = dto;
    }

    public boolean isIsExisted() {
        return isExisted;
    }

    public void setIsExisted(boolean isExisted) {
        this.isExisted = isExisted;
    }

    public String getPetCatIDInsFr() {
        return petCatIDInsFr;
    }

    public void setPetCatIDInsFr(String petCatIDInsFr) {
        this.petCatIDInsFr = petCatIDInsFr;
    }

    public String getPetCatIDUpdtFr() {
        return petCatIDUpdtFr;
    }

    public void setPetCatIDUpdtFr(String petCatIDUpdtFr) {
        this.petCatIDUpdtFr = petCatIDUpdtFr;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
