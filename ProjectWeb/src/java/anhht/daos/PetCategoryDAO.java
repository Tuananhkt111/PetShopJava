/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.PetCategoryDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tuana
 */
public class PetCategoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public PetCategoryDAO() {
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
    
    public ArrayList<PetCategoryDTO> findAllPetCategoryAvailable() throws Exception {
        ArrayList<PetCategoryDTO> result = null;
        PetCategoryDTO dto = null;
        try {
            String sql = "select PetCatID, PetType from PetCategory";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String petCatID = rs.getString("PetCatID");
                String petType = rs.getString("PetType");
                dto = new PetCategoryDTO(petCatID, petType);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<PetCategoryDTO> findPetCategoryByLikeType(String search) throws Exception {
        ArrayList<PetCategoryDTO> result = null;
        PetCategoryDTO dto = null;
        try {
            String sql = "select PetCatID, PetType from PetCategory where PetType like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) {
                String petCatID = rs.getString("PetCatID");
                String petType = rs.getString("PetType");
                dto = new PetCategoryDTO(petCatID, petType);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public boolean checkExisted(String petCatID) throws Exception {
        boolean result = false;
        try {
            String sql = "select PetType from PetCategory where PetCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, petCatID);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public String findByPrimaryKey(String petCatID) throws Exception {
        String result = null;
        try {
            String sql = "select PetType from PetCategory where PetCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, petCatID);
            rs = preStm.executeQuery();
            if(rs.next()) {
                result = rs.getString("PetType");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(PetCategoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into PetCategory values(?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPetCatID());
            preStm.setString(2, dto.getPetType());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(PetCategoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update PetCategory set PetType = ? where PetCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(2, dto.getPetCatID());
            preStm.setString(1, dto.getPetType());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean delete(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "delete from PetCategory where PetCatID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
