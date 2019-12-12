/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.AccessoryCategoryDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tuana
 */
public class AccessoryCategoryDAO implements Serializable {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccessoryCategoryDAO() {
    }
    
    public void closeConnection() throws Exception {
        if(rs != null) {
            rs.close();
        }
        if(preStm != null) {
            preStm.close();
        }
        if(conn != null) {
            conn.close();
        }
    }
    
    public ArrayList<AccessoryCategoryDTO> findAllAccessoryCategoryAvailable() throws Exception {
        ArrayList<AccessoryCategoryDTO> result = null;
        AccessoryCategoryDTO dto = null;
        try {
            String sql = "select AccCatID, AccCatName, PetCatID from AccessoryCategory";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String accCatID = rs.getString("AccCatID");
                String accCatName = rs.getString("AccCatName");
                String petCatID = rs.getString("PetCatID");
                dto = new AccessoryCategoryDTO(accCatID, accCatName, petCatID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<AccessoryCategoryDTO> findAccCategoryByLikeName(String search) throws Exception {
        ArrayList<AccessoryCategoryDTO> result = null;
        AccessoryCategoryDTO dto = null;
        try {
            String sql = "select AccCatID, AccCatName, PetCatID from AccessoryCategory where AccCatName like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String accCatID = rs.getString("AccCatID");
                String accCatName = rs.getString("AccCatName");
                String petCatID = rs.getString("PetCatID");
                dto = new AccessoryCategoryDTO(accCatID, accCatName, petCatID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public boolean checkExisted(String accCatID) throws Exception {
        boolean result = false;
        try {
            String sql = "select AccCatName from AccessoryCategory where AccCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accCatID);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public String findByPrimaryKey(String accCatID) throws Exception {
        String result = null;
        try {
            String sql = "select AccCatName from AccessoryCategory where AccCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, accCatID);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = rs.getString("AccCatName");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(AccessoryCategoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into AccessoryCategory values(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getAccCatID());
            preStm.setString(2, dto.getAccCatName());
            preStm.setString(3, dto.getPetCatID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(AccessoryCategoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update AccessoryCategory set AccCatName = ?, PetCatID = ? where AccCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(3, dto.getAccCatID());
            preStm.setString(1, dto.getAccCatName());
            preStm.setString(2, dto.getPetCatID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean delete(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "delete from AccessoryCategory where AccCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
        public String findAccCatNameByAccID(String id) throws Exception {
        String result = "";
        try {
            String sql = "select AccCatName from AccessoryCategory where AccCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = rs.getString("AccCatName");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
