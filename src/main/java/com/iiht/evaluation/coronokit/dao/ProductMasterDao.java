package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	private static final String INSERT_USERS_SQL = "INSERT INTO product" + "  (product_name, product_cost, product_desc) VALUES " +
		        " (?, ?, ?);";
						//String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_PRODUCT_BY_ID = "select product_id, product_name, product_cost, product_desc from product where product_id = ?;";
	private static final String SELECT_ALL_PRODUCT = "select * from product";
	//private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_PRODUCT_SQL = "update product set product_name = ?,product_cost= ?, product_desc =? where product_id = ?;";
	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
//	public  getProducts() throws SQLException
//	{
//		ArrayList<ProductMaster> productList = new ArrayList<>();
//		connect();
//		Statement createStatement = jdbcConnection.createStatement();
//		ResultSet executeQuery = createStatement.executeQuery("select * from products");
//		while(executeQuery.next())
//		{
//			'interface
//		}
//		
//		
//		return null;
//		
//	}

	// add DAO methods as per requirements
	public List <ProductMaster> selectAllProducts() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <ProductMaster> productMaster = new ArrayList <> ();
        // Step 1: Establishing a Connection
        try (Connection connection = connect();
              // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                Double product_cost = rs.getDouble("product_cost");
                String product_desc = rs.getString("product_desc");
                productMaster.add(new ProductMaster(product_id, product_name, product_cost, product_desc));
            }
        } catch (SQLException e) {
           printSQLException(e);
        }
        return productMaster;
    }
	  public boolean updateProduct(ProductMaster productMaster) throws SQLException {
	        boolean rowUpdated = false;
	        try (Connection connection = connect(); 
	        	PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
	            statement.setString(1, productMaster.getProductName());
	            statement.setDouble(2, productMaster.getCost());
	            statement.setString(3, productMaster.getProductDescription());
	            statement.setInt(4, productMaster.getId());

	            rowUpdated = statement.executeUpdate() > 0;
	            
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
			return rowUpdated;
	        
	    }
	
	  public ProductMaster getProduct(int id) throws SQLException {
		  ProductMaster productMaster = null;
	       
	        try (Connection connection = connect(); 
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);){
	        System.out.println(id);
	        preparedStatement.setInt(1, id);
	        System.out.println(preparedStatement); 
	        ResultSet rs = preparedStatement.executeQuery();
	         
	        if (rs.next()) {
	        	String product_name = rs.getString("product_name");
                Double product_cost = rs.getDouble("product_cost");
                String product_desc = rs.getString("product_desc");
	             
	            productMaster = new ProductMaster(id, product_name, product_cost, product_desc);
	        }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	         
	        
	         
	        return productMaster;
	    }
	  
	 public void insertProduct(ProductMaster productMaster) throws SQLException {
	        System.out.println(INSERT_USERS_SQL);
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = connect();
	        	
	        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);) {
	        	System.out.println(INSERT_USERS_SQL);
	            preparedStatement.setString(1, productMaster.getProductName());
	            preparedStatement.setDouble(2, productMaster.getCost());
	            preparedStatement.setString(3, productMaster.getProductDescription());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
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