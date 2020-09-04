package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public class KitDao {
	CoronaKit coronaKit = new CoronaKit();
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private static final String SELECT_ALL_PRODUCT_KIT = "select kit.kit_id,product.product_name,kit.productquantity,product_cost*productquantity as totalProductCost from kit join product on kit.productid=product.product_id where kit_id =? ;";
	private static final String SELECT_FINAL_KIT = "select sum(productquantity) SUMQ,sum(product_cost*productquantity) SUMC from kit join product  on kit.productid=product.product_id where kit_id = ?;";
	private static final String EXIST_QUERY = "select COUNT(*) COUNT from kit WHERE kit_id=? and productid=?;";
	private static final String MAX_QUERY_PRODUCT = "select MAX(product_id) MAX from product;";
	private static final String MAX_QUERY_KIT = "select MAX(kit_id) MAXKIT from KIT;";
	private static final String INSERT_TO_KIT_QUERY = "INSERT INTO KIT" + "  (kit_id, productid, productquantity,productamount) VALUES " +
	        " (?, ?, ?, ?);";
	private static final String UPDATE_PRODUCT_KIT_SQL = "update kit set productquantity = ? where kit_id = ? and productid = ?;";
	private static final String DELETE_PRODUCT_KIT_SQL = "DELETE FROM kit WHERE kit_id = ? and productid = ?;";
	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	// add DAO methods as per requirements
	public void insertToKit(KitDetail kitDetail) throws SQLException {
       // System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connect();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TO_KIT_QUERY);) {
        	System.out.println(INSERT_TO_KIT_QUERY);
            preparedStatement.setInt(1, kitDetail.getId());
            preparedStatement.setInt(2, kitDetail.getProductId());
            preparedStatement.setInt(3, kitDetail.getQuantity());
            preparedStatement.setDouble(4, kitDetail.getAmount());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
	
	 public boolean updateProductInKit(KitDetail kitDetail) throws SQLException {
	        boolean rowUpdated = false;
	        try (Connection connection = connect(); 
	        	PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_KIT_SQL);) {
	            statement.setInt(2, kitDetail.getId());
	            statement.setInt(3, kitDetail.getProductId());
	            statement.setInt(1, kitDetail.getQuantity());
	            rowUpdated = statement.executeUpdate() > 0;
	            
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
			return rowUpdated;
	        
	    }
	 public boolean deleteProductInKit(KitDetail kitDetail) throws SQLException {
	        boolean rowUpdated = false;
	        try (Connection connection = connect(); 
	        	PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_KIT_SQL);) {
	            statement.setInt(1, kitDetail.getId());
	            statement.setInt(2, kitDetail.getProductId());
	           // statement.setInt(1, kitDetail.getQuantity());
	            rowUpdated = statement.executeUpdate() > 0;
	            
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
			return rowUpdated;
	        
	    }
		public List <KitDetail> selectAllProductsInKit(int kit_Id) {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List <KitDetail> kitDetail = new ArrayList <> ();
	        // Step 1: Establishing a Connection
	        try (Connection connection = connect();
	              // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_KIT);) {
	        	//int Id = coronaKit.getId();
	        	preparedStatement.setInt(1, kit_Id);
	        	System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int kitId = rs.getInt("kit_id");
	                String product_name = rs.getString("product_name");
	                int product_quantity = rs.getInt("productquantity");
	                double product_cost_total = rs.getDouble("totalProductCost");
	                kitDetail.add(new KitDetail(kitId,product_name,product_quantity, product_cost_total));
	            }
	        } catch (SQLException e) {
	           printSQLException(e);
	        }
	        return kitDetail;
	    }
	
	public int getItemExistInKit(KitDetail kitDetail) throws SQLException {
	       // System.out.println(INSERT_USERS_SQL);
			int COUNT = 0;
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = connect();
	        	
	        	PreparedStatement preparedStatement = connection.prepareStatement(EXIST_QUERY);) {
	        	System.out.println(EXIST_QUERY);
	            preparedStatement.setInt(1, kitDetail.getId());
	            preparedStatement.setInt(2, kitDetail.getProductId());
	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
		        	COUNT = rs.getInt("COUNT");
	            }
	            System.out.println(preparedStatement);
	            //preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return COUNT;
	    }
	public int countProduct() throws SQLException {
        System.out.println(MAX_QUERY_PRODUCT);
        int PRODUCT_COUNT = 0;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connect();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(MAX_QUERY_PRODUCT);) {
        	//System.out.println(MAX_QUERY_PRODUCT);
            //preparedStatement.setString(1, productMaster.getProductName());
           // preparedStatement.setDouble(2, productMaster.getCost());
            //preparedStatement.setString(3, productMaster.getProductDescription());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
	        	PRODUCT_COUNT = rs.getInt("MAX");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return PRODUCT_COUNT;
    }
	
	public int countKit() throws SQLException {
        System.out.println(MAX_QUERY_KIT);
        int KIT_COUNT = 0;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connect();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(MAX_QUERY_KIT);) {
        	//System.out.println(MAX_QUERY_PRODUCT);
            //preparedStatement.setString(1, productMaster.getProductName());
           // preparedStatement.setDouble(2, productMaster.getCost());
            //preparedStatement.setString(3, productMaster.getProductDescription());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	KIT_COUNT = rs.getInt("MAXKIT");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return KIT_COUNT;
    }
	
	public Map<String, String> getKitConfimed(int kit_id) throws SQLException {
       // System.out.println(MAX_QUERY);
        //int PRODUCT_COUNT = 0;
        Map<String,String> kitValues = new HashMap<String,String>();
       // registerSensor(sensorName);
        
       //sensorValues.put("y","26");
        //sensorValues.put("z","27");
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connect();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FINAL_KIT);) {
        	preparedStatement.setInt(1, kit_id);
        	System.out.println(SELECT_FINAL_KIT);
            //preparedStatement.setString(1, productMaster.getProductName());
           // preparedStatement.setDouble(2, productMaster.getCost());
            //preparedStatement.setString(3, productMaster.getProductDescription());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	kitValues.put(rs.getString("SUMQ"),rs.getString("SUMC"));
	        	//PRODUCT_COUNT = rs.getInt("MAX");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return kitValues;
    }
	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
}
}