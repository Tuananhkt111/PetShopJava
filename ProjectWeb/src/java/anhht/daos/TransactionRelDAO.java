/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.TransactionRelDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bbbyl
 */
public class TransactionRelDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public TransactionRelDAO() {
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

    public boolean insert(TransactionRelDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into AccessoryTransactionRel values (?,?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getAccID());
            preStm.setString(2, dto.getAccTranID());
            preStm.setString(3, dto.getAccName());
            preStm.setString(4, dto.getAccCatID());
            preStm.setString(5, dto.getBrand());
            preStm.setString(6, dto.getDescription());
            preStm.setFloat(7, dto.getPrice());
            preStm.setDate(8, new java.sql.Date(dto.getStartSellingDate().getTime()));
            preStm.setString(9, dto.getImage());
            preStm.setFloat(10, dto.getSalePercent());
            preStm.setInt(11, dto.getQuantity());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public ArrayList<TransactionRelDTO> findByAccTranID(String accTranID) throws Exception {
        ArrayList<TransactionRelDTO> result = null;
        TransactionRelDTO dto = null;
        try {
            String sql = "select Quantity, AccID from AccessoryTransactionRel where AccTranID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accTranID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String accID = rs.getString("AccID");
                int quantity = rs.getInt("Quantity");
                dto = new TransactionRelDTO(accID, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<TransactionRelDTO> findByAccTranIDDetails(String accTranID) throws Exception {
        ArrayList<TransactionRelDTO> result = null;
        TransactionRelDTO dto = null;
        try {
            String sql = "select AccID, AccName, AccCatID, Brand, Description, Price, StartSellingDate, Image, SalePercent, Quantity from AccessoryTransactionRel where AccTranID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accTranID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String accID = rs.getString("AccID");
                String accName = rs.getString("AccName");
                String accCatID = rs.getString("AccCatID");
                String brand = rs.getString("Brand");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date startSellingDate = rs.getDate("StartSellingDate");
                String image = rs.getString("Image");
                float salePercent = rs.getFloat("SalePercent");
                int quantity = rs.getInt("Quantity");
                dto = new TransactionRelDTO(accID, accName, accCatID, brand, description, image, price, salePercent, startSellingDate, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
}
