/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.AccessoryDTO;
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
public class AccessoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccessoryDAO() {
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

    public ArrayList<AccessoryDTO> findAllAccByLikeName(String search) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent, isDelete "
                    + "from Accessory where AccName like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
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
                boolean isDelete = rs.getBoolean("isDelete");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, isDelete);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<AccessoryDTO> findAllAccByAccCatID(String accCatID) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent, isDelete "
                    + "from Accessory where AccCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accCatID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accID = rs.getString("AccID");
                String accName = rs.getString("AccName");
                float price = rs.getFloat("Price");
                String description = rs.getString("Description");
                String brand = rs.getString("Brand");
                String image = rs.getString("Image");
                Date startSellingDate = rs.getDate("StartSellingDate");
                int availableQuantity = rs.getInt("AvailableQuantity");
                float salePercent = rs.getFloat("SalePercent");
                boolean isDelete = rs.getBoolean("isDelete");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, isDelete);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<AccessoryDTO> findAllAccByPetCatID(String petCatID) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent, isDelete "
                    + "from Accessory where AccCatID in (select AccCatID from AccessoryCategory where PetCatID = ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, petCatID);
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
                boolean isDelete = rs.getBoolean("isDelete");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, isDelete);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<AccessoryDTO> findAllAcc() throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent, isDelete "
                    + "from Accessory";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
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
                boolean isDelete = rs.getBoolean("isDelete");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, isDelete);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public AccessoryDTO findByPrimaryKey(String accID) throws Exception {
        AccessoryDTO dto = null;
        try {
            String sql = "select AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent, isDelete "
                    + "from Accessory where AccID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String accName = rs.getString("AccName");
                String accCatID = rs.getString("AccCatID");
                float price = rs.getFloat("Price");
                String description = rs.getString("Description");
                String brand = rs.getString("Brand");
                String image = rs.getString("Image");
                Date startSellingDate = rs.getDate("StartSellingDate");
                int availableQuantity = rs.getInt("AvailableQuantity");
                float salePercent = rs.getFloat("SalePercent");
                boolean isDelete = rs.getBoolean("isDelete");
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, isDelete);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public int findAvailableQuantityAcc(String id) throws Exception {
        int result = -1;
        try {
            String sql = "select AvailableQuantity from Accessory where AccID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = rs.getInt("AvailableQuantity");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    
    public ArrayList<AccessoryDTO> findAccByLikeName(String search, boolean isDelete) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent "
                    + "from Accessory where AccName like ? and isDelete = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setBoolean(2, isDelete);
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
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, isDelete);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<AccessoryDTO> findLatestAccessories() throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select top 12 AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent "
                    + "from Accessory where AvailableQuantity > ? and isDelete = ? order by StartSellingDate DESC";
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
                dto = new AccessoryDTO(accID, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, false);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<AccessoryDTO> findRelatedAccessories(String accCatID, String accID) throws Exception {
        ArrayList<AccessoryDTO> result = null;
        AccessoryDTO dto = null;
        try {
            String sql = "select top 4 AccID, AccName, AccCatID, Price, Description, Brand,"
                    + " Image, StartSellingDate, AvailableQuantity, SalePercent "
                    + "from Accessory where AvailableQuantity > ? and isDelete = ? and AccCatID = ? and accID <> ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 0);
            preStm.setBoolean(2, false);
            preStm.setString(3, accCatID);
            preStm.setString(4, accID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String accId = rs.getString("AccID");
                String accName = rs.getString("AccName");
                float price = rs.getFloat("Price");
                String description = rs.getString("Description");
                String brand = rs.getString("Brand");
                String image = rs.getString("Image");
                Date startSellingDate = rs.getDate("StartSellingDate");
                int availableQuantity = rs.getInt("AvailableQuantity");
                float salePercent = rs.getFloat("SalePercent");
                dto = new AccessoryDTO(accId, accName, accCatID, description, brand, image, price, salePercent, startSellingDate, availableQuantity, false);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkExisted(String accID) throws Exception {
        boolean result = false;
        try {
            String sql = "select AccName from Accessory where AccID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(AccessoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Accessory values (?,?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getAccID());
            preStm.setString(2, dto.getAccName());
            preStm.setString(3, dto.getAccCatID());
            preStm.setString(4, dto.getBrand());
            preStm.setString(5, dto.getDescription());
            preStm.setFloat(6, dto.getPrice());
            preStm.setDate(7, new java.sql.Date(dto.getStartSellingDate().getTime()));
            preStm.setString(8, dto.getImage());
            preStm.setInt(9, dto.getAvailableQuantity());
            preStm.setFloat(10, dto.getSalePercent());
            preStm.setBoolean(11, dto.isIsDelete());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(AccessoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update Accessory set AccName = ?, AccCatID = ?, "
                    + "Brand = ?, Description = ?, Price = ?, StartSellingDate = ?,"
                    + " Image = ?, AvailableQuantity = ?, SalePercent = ?, isDelete = ? where "
                    + "AccID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(11, dto.getAccID());
            preStm.setString(1, dto.getAccName());
            preStm.setString(2, dto.getAccCatID());
            preStm.setString(3, dto.getBrand());
            preStm.setString(4, dto.getDescription());
            preStm.setFloat(5, dto.getPrice());
            preStm.setDate(6, new java.sql.Date(dto.getStartSellingDate().getTime()));
            preStm.setString(7, dto.getImage());
            preStm.setInt(8, dto.getAvailableQuantity());
            preStm.setFloat(9, dto.getSalePercent());
            preStm.setBoolean(10, dto.isIsDelete());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean restore(ArrayList<AccessoryDTO> list) throws Exception {
        boolean check = false;
        try {
            String sql = "update Accessory set AvailableQuantity = ? where AccID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            for (AccessoryDTO accessoryDTO : list) {
                preStm.setString(2, accessoryDTO.getAccID());
            preStm.setInt(1, accessoryDTO.getQuantity());
            check = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
}
