/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.TransactionDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bbbyl
 */
public class TransactionDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public TransactionDAO() {
    }

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean insert(TransactionDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into AccessoryTransaction values (?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getAccTranID());
            preStm.setString(2, dto.getUsername());
            Timestamp timeOrder = new Timestamp(dto.getTimeOrder().getTime());
            preStm.setTimestamp(3, timeOrder);
            preStm.setString(4, null);
            preStm.setString(5, dto.getDeliveryPhone());
            preStm.setString(6, dto.getDeliverAddress());
            preStm.setFloat(7, dto.getTotalPrice());
            preStm.setString(8, dto.getStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkExisted(String accTranID) throws Exception {
        boolean check = false;
        try {
            String sql = "select AccTranID from AccessoryTransaction where AccTranID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accTranID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateTotalPrice(float total, String accTranID) throws Exception {
        boolean check = false;
        try {
            String sql = "update AccessoryTransaction set TotalPrice = ? where accTranID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setFloat(1, total);
            preStm.setString(2, accTranID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public ArrayList<TransactionDTO> findByUsername(String username) throws Exception {
        ArrayList<TransactionDTO> result = null;
        TransactionDTO dto = null;
        try {
            String sql = "select AccTranID, TimeOrder, TimeFinish, DeliveryPhone,"
                    + " DeliveryAddress, TotalPrice, Status from AccessoryTransaction where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accTranID = rs.getString("AccTranID");
                Date timeOrder = rs.getTimestamp("TimeOrder");
                Date timeFinish = rs.getTimestamp("TimeFinish");
                String deliveryPhone = rs.getString("DeliveryPhone");
                String deliveryAddress = rs.getString("DeliveryAddress");
                float totalPrice = rs.getFloat("TotalPrice");
                String status = rs.getString("Status");
                dto = new TransactionDTO(accTranID, username, deliveryPhone, deliveryAddress, status, timeOrder, timeFinish, totalPrice);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<TransactionDTO> findAll() throws Exception {
        ArrayList<TransactionDTO> result = null;
        TransactionDTO dto = null;
        try {
            String sql = "select AccTranID, Username, TimeOrder, TimeFinish, DeliveryPhone,"
                    + " DeliveryAddress, TotalPrice, Status from AccessoryTransaction";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accTranID = rs.getString("AccTranID");
                String username = rs.getString("Username");
                Date timeOrder = rs.getTimestamp("TimeOrder");
                Date timeFinish = rs.getTimestamp("TimeFinish");
                String deliveryPhone = rs.getString("DeliveryPhone");
                String deliveryAddress = rs.getString("DeliveryAddress");
                float totalPrice = rs.getFloat("TotalPrice");
                String status = rs.getString("Status");
                dto = new TransactionDTO(accTranID, username, deliveryPhone, deliveryAddress, status, timeOrder, timeFinish, totalPrice);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<TransactionDTO> findAllByStatus(String status) throws Exception {
        ArrayList<TransactionDTO> result = null;
        TransactionDTO dto = null;
        try {
            String sql = "select AccTranID, Username, TimeOrder, TimeFinish, DeliveryPhone,"
                    + " DeliveryAddress, TotalPrice, Status from AccessoryTransaction where Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, status);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accTranID = rs.getString("AccTranID");
                String username = rs.getString("Username");
                Date timeOrder = rs.getTimestamp("TimeOrder");
                Date timeFinish = rs.getTimestamp("TimeFinish");
                String deliveryPhone = rs.getString("DeliveryPhone");
                String deliveryAddress = rs.getString("DeliveryAddress");
                float totalPrice = rs.getFloat("TotalPrice");
                dto = new TransactionDTO(accTranID, username, deliveryPhone, deliveryAddress, status, timeOrder, timeFinish, totalPrice);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<TransactionDTO> findByStatusAndUsername(String status, String username) throws Exception {
        ArrayList<TransactionDTO> result = null;
        TransactionDTO dto = null;
        try {
            String sql = "select AccTranID, Username, TimeOrder, TimeFinish, DeliveryPhone,"
                    + " DeliveryAddress, TotalPrice, Status from AccessoryTransaction where Status = ? and Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, status);
            preStm.setString(2, username);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accTranID = rs.getString("AccTranID");
                Date timeOrder = rs.getTimestamp("TimeOrder");
                Date timeFinish = rs.getTimestamp("TimeFinish");
                String deliveryPhone = rs.getString("DeliveryPhone");
                String deliveryAddress = rs.getString("DeliveryAddress");
                float totalPrice = rs.getFloat("TotalPrice");
                dto = new TransactionDTO(accTranID, username, deliveryPhone, deliveryAddress, status, timeOrder, timeFinish, totalPrice);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateStatus(String status, String accTranID) throws Exception {
        boolean check = false;
        try {
            String sql = "update AccessoryTransaction set Status = ?, TimeFinish = ? where AccTranID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, status);
            if (status.equals("Finished")) {
                Date date = new Date();
                preStm.setTimestamp(2, new Timestamp(date.getTime()));
            } else {
                preStm.setString(2, null);
            }
            preStm.setString(3, accTranID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
