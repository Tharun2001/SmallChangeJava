package com.smallChange.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;


import com.smallChange.security.Security;

public class SecurityDaoImpl implements SecurityDao{
	
	private DataSource datasource;
	
	public SecurityDaoImpl(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public List<Security> getSecurities() {
		String sql = """
				SELECT * from scott.SC_STOCK_SECURITIES
				""";
		try (Connection conn=datasource.getConnection();
				PreparedStatement stmt=conn.prepareStatement(sql)){
			ResultSet rs=stmt.executeQuery();
			List<Security> security=new ArrayList<>();
			while (rs.next())
			{
				security.add(getResult(rs));
			}
			return security;
			
		}catch(SQLException e)
		{
			throw new DatabaseException("Cannot connect to db");
		}
	}
	
	
	public Security getResult (ResultSet rs) throws SQLException {
		
		int s_id=rs.getInt("S_ID");
		String s_name=rs.getString("S_NAME");
		String s_code =rs.getString("S_CODE");
		double s_ltp = rs.getDouble("S_LTP");
		String s_ac =rs.getString("S_ASSET_CLASS");

        Security security = new Security(s_id, s_name, s_code,s_ltp,s_ac);
        return security;		
	}
	
	
	

	

	@Override
	public void insertSecurity(Security security) {
		
		if(security==null)
		{
			throw new NullPointerException("Security Cannot be null");
		}
		
		 try (Connection conn = datasource.getConnection()){
			 
			 if (security.getStockId() <= 0) {
	                throw new IllegalArgumentException("invalid Stock ID ");
	            }
            
			 insertSecurityData(conn,security);
	 
	        }
		 
		 catch(SQLException e) {
	            throw new DatabaseException("Cant connect to db");
	       }
		
	}
	
	  public void insertSecurityData (Connection conn,Security security) throws SQLException {
	        String sql = """
	   INSERT INTO scott.sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) 
	   VALUES (?, ?, ?,?,?)
	            """;
	        Objects.requireNonNull(security);
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, security.getStockId());
	            stmt.setString(2, security.getStockName());
	            stmt.setString(3, security.getStockCode());
	            stmt.setDouble(4, security.getStockLtp());
	            stmt.setString(5, security.getStockAssetClass());

	            stmt.executeUpdate();
	        } 
	        catch(SQLException e)
	        {
	        	throw new DatabaseException("cannot connect to db");
	        }
	    }
	  
	  

	@Override
	public void deleteSecurity(int sId) {
        String sqlDeleteSecurity = """
                DELETE FROM sc_stock_securities
                WHERE
                    s_id = ?
            """;
 
            if (sId <= 0) {
                throw new IllegalArgumentException("invalid stock id " + sId);
            }
            try (Connection conn = datasource.getConnection();
            		PreparedStatement stockStmt = conn.prepareStatement(sqlDeleteSecurity)) {
                    stockStmt.setInt(1, sId);
                    stockStmt.executeUpdate();
                } 
            
            catch (Exception e) {
                String msg = "Cannot delete stock for " + sId;
                throw new DatabaseException(msg, e);
            }		
	}

	  @Override
		public void updateSecurity(Security security) {			
			Objects.requireNonNull(security);
			try (Connection conn = datasource.getConnection()) {
				updateSecurity(conn, security);
			} 
			catch (SQLException e) {
				String msg = "Cannot execute updateSecurity for " + security;
				throw new DatabaseException(msg, e);
			}
			
		}
		
		public void updateSecurity(Connection conn, Security security) throws SQLException {
			String sql = """
				UPDATE scott.sc_stock_securities
				SET
				    s_name = ?,
				    s_ltp = ?,
				    s_asset_class = ?,
				    s_code = ?
				WHERE
				    s_id = ?
	    	""";

			
			Objects.requireNonNull(security);
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, security.getStockName());
	            stmt.setString(2, security.getStockCode());
	            stmt.setDouble(3, security.getStockLtp());
	            stmt.setString(4, security.getStockAssetClass());
	            stmt.setInt(5, security.getStockId());


				int rowsUpdated = stmt.executeUpdate();
				if (rowsUpdated == 0) {
					throw new DatabaseException("there is no securty with id " + security.getStockId());
				}
			} 
		}

}
