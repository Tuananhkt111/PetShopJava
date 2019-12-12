/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhht.daos;

import anhht.db.MyConnection;
import anhht.dtos.PrincipalDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tuana
 */
public class PrincipalDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public PrincipalDAO() {
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

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "select Role from Principal where Username = ? and Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public PrincipalDTO getDeliveryInfo(String username) throws Exception {
        PrincipalDTO dto = null;
        try {
            String sql = "select Phone, Address from Principal where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                dto = new PrincipalDTO(username, "", "", phone, address);      
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean checkExisted(String username) throws Exception {
        boolean result = false;
        try {
            String sql = "select Role from Principal where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public PrincipalDTO findByUsername(String username) throws Exception {
        PrincipalDTO result = null;
        try {
            String sql = "select Fullname, Phone, Address from Principal where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                result = new PrincipalDTO(username, "", fullname, phone, address);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean register(PrincipalDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Principal values(?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getRole());
            preStm.setString(4, dto.getFullname());
            preStm.setString(5, dto.getPhone());
            preStm.setString(6, dto.getAddress());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(PrincipalDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update Principal set Fullname = ?, Phone = ?, Address = ? where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(4, dto.getUsername());
            preStm.setString(1, dto.getFullname());
            preStm.setString(2, dto.getPhone());
            preStm.setString(3, dto.getAddress());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
