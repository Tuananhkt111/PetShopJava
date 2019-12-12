/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.PetCategoryDAO;
import anhht.dtos.PetCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
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
public class PetCategoryAction extends ActionSupport {

    private ArrayList<PetCategoryDTO> list;
    private String petCatSearch, petCatIDIns, petTypeIns, petCatIDUpdt, petTypeUpdt, petCatIDDelete, msg;
    private PetCategoryDTO dto;
    private boolean isExisted;

    public PetCategoryAction() {
    }

    @Action("loadAllPetCategoryAction")
    public String loadAllPetCategoryAvailable() throws Exception {
        PetCategoryDAO dao = new PetCategoryDAO();
        list = dao.findAllPetCategoryAvailable();
        return "jsonResult";
    }

    @Action("findPetCatByLikeTypeAction")
    public String findPetCatByLikeType() throws Exception {
        PetCategoryDAO dao = new PetCategoryDAO();
        list = dao.findPetCategoryByLikeType(petCatSearch);
        return "jsonResult";
    }

    @Action("checkPetCatIDExistedAction")
    public String checkExisted() throws Exception {
        PetCategoryDAO dao = new PetCategoryDAO();
        isExisted = dao.checkExisted(petCatIDIns);
        return "jsonResult";
    }

    @Action("insertPetCatAction")
    public String insert() throws Exception {
        dto = new PetCategoryDTO(petCatIDIns, petTypeIns);
        PetCategoryDAO dao = new PetCategoryDAO();
        if (dao.insert(dto)) {
            msg = "Insert pet category success";
        } else {
            msg = "Insert pet category failed";
        }
        return "jsonResult";
    }

    @Action("updatePetCatAction")
    public String update() throws Exception {
        dto = new PetCategoryDTO(petCatIDUpdt, petTypeUpdt);
        PetCategoryDAO dao = new PetCategoryDAO();
        if (dao.update(dto)) {
            msg = "Update pet category success";
        } else {
            msg = "Update pet category failed";
        }
        return "jsonResult";
    }

    @Action("deletePetCatAction")
    public String delete() throws Exception {
        PetCategoryDAO dao = new PetCategoryDAO();
        if (dao.delete(petCatIDDelete)) {
            msg = "Delete pet category success";
        } else {
            msg = "Delete pet category failed";
        }
        return "jsonResult";
    }

    public ArrayList<PetCategoryDTO> getList() {
        return list;
    }

    public void setList(ArrayList<PetCategoryDTO> list) {
        this.list = list;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getPetCatSearch() {
        return petCatSearch;
    }

    public void setPetCatSearch(String petCatSearch) {
        this.petCatSearch = petCatSearch;
    }

    public String getPetCatIDIns() {
        return petCatIDIns;
    }

    public void setPetCatIDIns(String petCatIDIns) {
        this.petCatIDIns = petCatIDIns;
    }

    public boolean isIsExisted() {
        return isExisted;
    }

    public void setIsExisted(boolean isExisted) {
        this.isExisted = isExisted;
    }

    public String getPetTypeIns() {
        return petTypeIns;
    }

    public void setPetTypeIns(String petTypeIns) {
        this.petTypeIns = petTypeIns;
    }

    public String getPetCatIDUpdt() {
        return petCatIDUpdt;
    }

    public void setPetCatIDUpdt(String petCatIDUpdt) {
        this.petCatIDUpdt = petCatIDUpdt;
    }

    public String getPetTypeUpdt() {
        return petTypeUpdt;
    }

    public void setPetTypeUpdt(String petTypeUpdt) {
        this.petTypeUpdt = petTypeUpdt;
    }

    public PetCategoryDTO getDto() {
        return dto;
    }

    public void setDto(PetCategoryDTO dto) {
        this.dto = dto;
    }

    public String getPetCatIDDelete() {
        return petCatIDDelete;
    }

    public void setPetCatIDDelete(String petCatIDDelete) {
        this.petCatIDDelete = petCatIDDelete;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
