/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.AccessoryDTO;
import anhht.dtos.FavoriteDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tuana
 */
public class FavoriteDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public FavoriteDAO() {
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

    public ArrayList<AccessoryDTO> findEightMostFavoriteAccessories() throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select top 8 * from Accessory inner join (select AccID, COUNT(AccID) as FavoriteCount from Favorite "
                    + "group by AccID ) as FavoriteCountTable on FavoriteCountTable.AccID = Accessory.AccID where AvailableQuantity > ? and isDelete = ? order by FavoriteCount DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 0);
            preStm.setBoolean(2, false);
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
                float salePercent = rs.getFloat("SalePercent");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<AccessoryDTO> findFavoriteAccessoriesByUsername(String username) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select * from Accessory inner join (select AccID from Favorite "
                    + "where Username = ?) as FavoriteCountTable on FavoriteCountTable.AccID = Accessory.AccID where isDelete = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setBoolean(2, false);
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
                float salePercent = rs.getFloat("SalePercent");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int countFavoriteByAccId (String accID) throws Exception {
        int result = -1;
        try {
            String sql = "select count(AccID) as FavoriteCount from Favorite where AccID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accID);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = rs.getInt("FavoriteCount");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(FavoriteDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "delete from Favorite where AccID = ? and Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getAccID());
            preStm.setString(2, dto.getUsername());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insert(FavoriteDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Favorite values(?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getAccID());
            preStm.setString(2, dto.getUsername());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
