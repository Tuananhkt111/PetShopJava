/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.actions;

import anhht.daos.AccessoryDAO;
import anhht.daos.TransactionDAO;
import anhht.daos.TransactionRelDAO;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.TransactionDTO;
import anhht.dtos.TransactionRelDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Action;

/**
 *
 * @author bbbyl
 */
@ParentPackage("json-default")
@ResultPath("/")
@Result(name = "jsonResult", type = "json")
public class TransactionAction extends ActionSupport {

    private String username, status, msg, accTranID;
    private ArrayList<TransactionDTO> list;
    private ArrayList<TransactionRelDTO> translist;

    public TransactionAction() {
    }

    @Action("findTranAction")
    public String findAll() throws Exception {
        TransactionDAO dao = new TransactionDAO();
        if (username.equals("")) {
            if (status.equals("All")) {
                list = dao.findAll();
            } else {
                list = dao.findAllByStatus(status);
            }
        } else {
            if (status.equals("All")) {
                list = dao.findByUsername(username);
            } else {
                list = dao.findByStatusAndUsername(status, username);
            }
        }
        return "jsonResult";
    }

    @Action("findTranByUsernameAction")
    public String findTranByUsername() throws Exception {
        TransactionDAO dao = new TransactionDAO();
        list = dao.findByUsername(username);
        return "jsonResult";
    }

    @Action("viewDetailAction")
    public String viewDetail() throws Exception {
        TransactionRelDAO dao = new TransactionRelDAO();
        translist = dao.findByAccTranIDDetails(accTranID);
        return "jsonResult";
    }

    @Action("updateTranAction")
    public String updateTran() throws Exception {
        TransactionDAO dao = new TransactionDAO();
        if (dao.updateStatus(status, accTranID)) {
            msg = "Update transaction success";
        } else {
            msg = "Update transaction failed";
        }
        return "jsonResult";
    }

    @Action("restoreAction")
    public String restore() throws Exception {
        AccessoryDAO accDAO = new AccessoryDAO();
        TransactionRelDAO trDAO = new TransactionRelDAO();
        ArrayList<TransactionRelDTO> tranList = trDAO.findByAccTranID(accTranID);
        ArrayList<AccessoryDTO> accList = new ArrayList<>();
        for (TransactionRelDTO transactionRelDTO : tranList) {
            AccessoryDTO accDTO = accDAO.findByPrimaryKey(transactionRelDTO.getAccID());
            accDTO.setQuantity(transactionRelDTO.getQuantity() + accDTO.getAvailableQuantity());
            accList.add(accDTO);
        }
        if (accDAO.restore(accList)) {
            msg = "Restore all canceled accessories success";
        } else {
            msg = "Restore all canceled accessories failed";
        }
        return "jsonResult";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<TransactionDTO> getList() {
        return list;
    }

    public void setList(ArrayList<TransactionDTO> list) {
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAccTranID() {
        return accTranID;
    }

    public void setAccTranID(String accTranID) {
        this.accTranID = accTranID;
    }

    public ArrayList<TransactionRelDTO> getTranslist() {
        return translist;
    }

    public void setTranslist(ArrayList<TransactionRelDTO> translist) {
        this.translist = translist;
    }

}
