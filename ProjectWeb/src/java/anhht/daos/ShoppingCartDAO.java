/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.ShoppingCartDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tuana
 */
public class ShoppingCartDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ShoppingCartDAO() {
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

    public ArrayList<AccessoryDTO> findAllAccCartByUsername(String username) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select * from Accessory inner join (select AccID, "
                    + "Quantity from ShoppingCart where Username = ? "
                    + ") as CartQuantityTable on CartQuantityTable.AccID = Accessory.AccID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accID = rs.getString("AccID");
                String accName = rs.getString("AccName");
                String accCatID = rs.getString("AccCatID");
                float price = rs.getFloat("Price");
                String description = rs.getString("Description");
                String brand = rs.getString("Brand");
                String image = rs.getString("Image");
                Date startSellingDate = rs.getDate("StartSellingDate");
                int availableQuantity = rs.getInt("AvailableQuantity");
                int quantity = rs.getInt("Quantity");
                float salePercent = rs.getFloat("SalePercent");
                boolean isDelete = rs.getBoolean("isDelete");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, accName, price, salePercent, startSellingDate, availableQuantity, quantity, isDelete);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean checkEmptyByUsername(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "select Username from ShoppingCart where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if(rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean deleteAllByUsername(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "delete from ShoppingCart where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertAll(ShoppingCartDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into ShoppingCart values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            HashMap<String, AccessoryDTO> cart = dto.getCart();
            for (Map.Entry me : cart.entrySet()) {
                AccessoryDTO accDTO = (AccessoryDTO) me.getValue();
                preStm.setString(1, accDTO.getAccID());
                preStm.setString(2, dto.getUsername());
                preStm.setInt(3, accDTO.getQuantity());
                check = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
