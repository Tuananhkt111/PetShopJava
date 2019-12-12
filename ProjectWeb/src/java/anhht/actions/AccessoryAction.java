/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.AccessoryCategoryDAO;
import anhht.daos.AccessoryDAO;
import anhht.daos.FavoriteDAO;
import anhht.dtos.AccessoryDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author tuana
 */
@ParentPackage("json-default")
@Results({
    @Result(name = "jsonResult", type = "json")
    ,
    @Result(name = "loadaccsuccess", location = "/guest/product.jsp")
    ,
    @Result(name = "loadaccfailed", location = "/guest/error.jsp")
    ,
    @Result(name = "loadaccessories", location = "/guest/accessories.jsp"),})
public class AccessoryAction extends ActionSupport {

    private String txtSearchPC, txtSearchAC, txtSearchName, accIdSearch;
    private String accIDIns, accNameIns, accCatIDIns, brandIns, desIns, priceIns, salePercentIns,
            startSellingDateIns, availableQuantityIns, imageIns, isDeleteIns;
    private String accIDUpdt, accNameUpdt, accCatIDUpdt, brandUpdt, desUpdt, priceUpdt, salePercentUpdt,
            startSellingDateUpdt, availableQuantityUpdt, imageUpdt, isDeleteUpdt;
    private String accSearch, isDeleteSearch, accCatIDSearch, petCatSearch;
    private String accIDDelete, accCategory;
    private String msg;
    private boolean isExisted;
    private int countFavorite;
    private AccessoryDTO dto;
    private ArrayList<AccessoryDTO> list;

    public AccessoryAction() {

    }

    @Action("searchAccessoryAction")
    public String searchLatestAccessories() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        list = dao.findLatestAccessories();
        return "jsonResult";
    }

    @Action("findByLikeNameAction")
    public String findByLikeName() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        list = dao.findAllAccByLikeName(accSearch);
        return "loadaccessories";
    }
    
    @Action("findAllAccAction")
    public String findAllAcc() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        list = dao.findAllAcc();
        return "loadaccessories";
    }
    
    @Action("findByAccCatIDAction")
    public String findByAccCatID() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        list = dao.findAllAccByAccCatID(accCatIDSearch);
        return "loadaccessories";
    }
    
    @Action("findByPetCatIDAction")
    public String findByPetCatID() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        list = dao.findAllAccByPetCatID(petCatSearch);
        return "loadaccessories";
    }

    @Action("getRelatedAccAction")
    public String getRelatedAccessories() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        list = dao.findRelatedAccessories(accCatIDSearch, accSearch);
        return "jsonResult";
    }

    @Action("checkAccIDExistedAction")
    public String checkExisted() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        isExisted = dao.checkExisted(accIDIns);
        return "jsonResult";
    }

    @Action("insertAccAction")
    public String insert() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startSellingDate = dateFormat.parse(startSellingDateIns);
        float price = Float.parseFloat(priceIns);
        price = (float) Math.round(price * 100) / 100;
        float salePercent = Float.parseFloat(salePercentIns);
        salePercent = (float) Math.round(salePercent * 100) / 100;
        dto = new AccessoryDTO(accIDIns, accNameIns, accCatIDIns, desIns, brandIns, imageIns, price, salePercent, startSellingDate, Integer.parseInt(availableQuantityIns), Boolean.parseBoolean(isDeleteIns));
        AccessoryDAO dao = new AccessoryDAO();
        if (dao.insert(dto)) {
            return "jsonResult";
        } else {
            HttpServletRequest req = ServletActionContext.getRequest();
            req.setAttribute("ERROR", "Insert accessory failed.");
            return "error";
        }
    }

    @Action("updateAccAction")
    public String update() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startSellingDate = dateFormat.parse(startSellingDateUpdt);
        float price = Float.parseFloat(priceUpdt);
        price = (float) Math.round(price * 100) / 100;
        float salePercent = Float.parseFloat(salePercentUpdt);
        salePercent = (float) Math.round(salePercent * 100) / 100;
        dto = new AccessoryDTO(accIDUpdt, accNameUpdt, accCatIDUpdt, desUpdt, brandUpdt, imageUpdt, price, salePercent, startSellingDate, Integer.parseInt(availableQuantityUpdt), Boolean.parseBoolean(isDeleteUpdt));
        AccessoryDAO dao = new AccessoryDAO();
        if (dao.update(dto)) {
            return "jsonResult";
        } else {
            HttpServletRequest req = ServletActionContext.getRequest();
            req.setAttribute("ERROR", "Update accessory failed.");
            return "error";
        }
    }

    @Action("findAccByLikeNameAction")
    public String findAccByLikeName() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        if (isDeleteSearch.equals("All")) {
            list = dao.findAllAccByLikeName(accSearch);
        } else if (isDeleteSearch.equals("True")) {
            list = dao.findAccByLikeName(accSearch, true);
        } else {
            list = dao.findAccByLikeName(accSearch, false);
        }
        return "jsonResult";
    }

    @Action("findAccByIdAction")
    public String findAccById() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        dto = dao.findByPrimaryKey(accIdSearch);
        if (dto != null) {
            FavoriteDAO favDAO = new FavoriteDAO();
            countFavorite = favDAO.countFavoriteByAccId(accIdSearch);
            if (countFavorite != -1) {
                AccessoryCategoryDAO acDAO = new AccessoryCategoryDAO();
                accCategory = acDAO.findAccCatNameByAccID(dto.getAccCatID());
                if (accCategory.equals("")) {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("ERROR", "Load category of accessory failed");
                    return "loadaccfailed";
                }
                return "loadaccsuccess";
            } else {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("ERROR", "Count favorite failed");
                return "loadaccfailed";
            }
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Accessory load failed");
            return "loadaccfailed";
        }
    }

    public String getTxtSearchPC() {
        return txtSearchPC;
    }

    public void setTxtSearchPC(String txtSearchPC) {
        this.txtSearchPC = txtSearchPC;
    }

    public String getTxtSearchAC() {
        return txtSearchAC;
    }

    public void setTxtSearchAC(String txtSearchAC) {
        this.txtSearchAC = txtSearchAC;
    }

    public String getTxtSearchName() {
        return txtSearchName;
    }

    public void setTxtSearchName(String txtSearchName) {
        this.txtSearchName = txtSearchName;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public ArrayList<AccessoryDTO> getList() {
        return list;
    }

    public void setList(ArrayList<AccessoryDTO> list) {
        this.list = list;
    }

    public String getAccIDIns() {
        return accIDIns;
    }

    public void setAccIDIns(String accIDIns) {
        this.accIDIns = accIDIns;
    }

    public boolean isIsExisted() {
        return isExisted;
    }

    public void setIsExisted(boolean isExisted) {
        this.isExisted = isExisted;
    }

    public String getAccNameIns() {
        return accNameIns;
    }

    public void setAccNameIns(String accNameIns) {
        this.accNameIns = accNameIns;
    }

    public String getAccCatIDIns() {
        return accCatIDIns;
    }

    public void setAccCatIDIns(String accCatIDIns) {
        this.accCatIDIns = accCatIDIns;
    }

    public String getBrandIns() {
        return brandIns;
    }

    public void setBrandIns(String brandIns) {
        this.brandIns = brandIns;
    }

    public String getDesIns() {
        return desIns;
    }

    public void setDesIns(String desIns) {
        this.desIns = desIns;
    }

    public String getPriceIns() {
        return priceIns;
    }

    public void setPriceIns(String priceIns) {
        this.priceIns = priceIns;
    }

    public String getStartSellingDateIns() {
        return startSellingDateIns;
    }

    public void setStartSellingDateIns(String startSellingDateIns) {
        this.startSellingDateIns = startSellingDateIns;
    }

    public String getAvailableQuantityIns() {
        return availableQuantityIns;
    }

    public void setAvailableQuantityIns(String availableQuantityIns) {
        this.availableQuantityIns = availableQuantityIns;
    }

    public AccessoryDTO getDto() {
        return dto;
    }

    public void setDto(AccessoryDTO dto) {
        this.dto = dto;
    }

    public String getImageIns() {
        return imageIns;
    }

    public void setImageIns(String imageIns) {
        this.imageIns = imageIns;
    }

    public String getAccSearch() {
        return accSearch;
    }

    public void setAccSearch(String accSearch) {
        this.accSearch = accSearch;
    }

    public String getIsDeleteSearch() {
        return isDeleteSearch;
    }

    public void setIsDeleteSearch(String isDeleteSearch) {
        this.isDeleteSearch = isDeleteSearch;
    }

    public String getIsDeleteIns() {
        return isDeleteIns;
    }

    public void setIsDeleteIns(String isDeleteIns) {
        this.isDeleteIns = isDeleteIns;
    }

    public String getAccIDDelete() {
        return accIDDelete;
    }

    public void setAccIDDelete(String accIDDelete) {
        this.accIDDelete = accIDDelete;
    }

    public String getAccIDUpdt() {
        return accIDUpdt;
    }

    public void setAccIDUpdt(String accIDUpdt) {
        this.accIDUpdt = accIDUpdt;
    }

    public String getAccNameUpdt() {
        return accNameUpdt;
    }

    public void setAccNameUpdt(String accNameUpdt) {
        this.accNameUpdt = accNameUpdt;
    }

    public String getAccCatIDUpdt() {
        return accCatIDUpdt;
    }

    public void setAccCatIDUpdt(String accCatIDUpdt) {
        this.accCatIDUpdt = accCatIDUpdt;
    }

    public String getBrandUpdt() {
        return brandUpdt;
    }

    public void setBrandUpdt(String brandUpdt) {
        this.brandUpdt = brandUpdt;
    }

    public String getDesUpdt() {
        return desUpdt;
    }

    public void setDesUpdt(String desUpdt) {
        this.desUpdt = desUpdt;
    }

    public String getPriceUpdt() {
        return priceUpdt;
    }

    public void setPriceUpdt(String priceUpdt) {
        this.priceUpdt = priceUpdt;
    }

    public String getStartSellingDateUpdt() {
        return startSellingDateUpdt;
    }

    public void setStartSellingDateUpdt(String startSellingDateUpdt) {
        this.startSellingDateUpdt = startSellingDateUpdt;
    }

    public String getAvailableQuantityUpdt() {
        return availableQuantityUpdt;
    }

    public void setAvailableQuantityUpdt(String availableQuantityUpdt) {
        this.availableQuantityUpdt = availableQuantityUpdt;
    }

    public String getImageUpdt() {
        return imageUpdt;
    }

    public void setImageUpdt(String imageUpdt) {
        this.imageUpdt = imageUpdt;
    }

    public String getIsDeleteUpdt() {
        return isDeleteUpdt;
    }

    public void setIsDeleteUpdt(String isDeleteUpdt) {
        this.isDeleteUpdt = isDeleteUpdt;
    }

    public String getSalePercentIns() {
        return salePercentIns;
    }

    public void setSalePercentIns(String salePercentIns) {
        this.salePercentIns = salePercentIns;
    }

    public String getSalePercentUpdt() {
        return salePercentUpdt;
    }

    public void setSalePercentUpdt(String salePercentUpdt) {
        this.salePercentUpdt = salePercentUpdt;
    }

    public String getAccIdSearch() {
        return accIdSearch;
    }

    public void setAccIdSearch(String accIdSearch) {
        this.accIdSearch = accIdSearch;
    }

    public int getCountFavorite() {
        return countFavorite;
    }

    public void setCountFavorite(int countFavorite) {
        this.countFavorite = countFavorite;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAccCatIDSearch() {
        return accCatIDSearch;
    }

    public void setAccCatIDSearch(String accCatIDSearch) {
        this.accCatIDSearch = accCatIDSearch;
    }

    public String getAccCategory() {
        return accCategory;
    }

    public void setAccCategory(String accCategory) {
        this.accCategory = accCategory;
    }

    public String getPetCatSearch() {
        return petCatSearch;
    }

    public void setPetCatSearch(String petCatSearch) {
        this.petCatSearch = petCatSearch;
    }

}
