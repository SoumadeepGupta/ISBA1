package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster; 

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
				
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		System.out.println(action);
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}
	
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String view = "index.jsp";
		return view;
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String view = "";
		List<ProductMaster> listAllProducts = productMasterDao.selectAllProducts();
		request.getSession().setAttribute("listAllProducts", listAllProducts);
		view =  "/listproducts.jsp";
		return view;
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println("hello");
		
		
		String view = "";
		int id = Integer.parseInt(request.getParameter("id"));
		
		String productName = request.getParameter("productName");
		Double cost = Double.parseDouble(request.getParameter("cost"));
		String productDescription = request.getParameter("productDescription");
		ProductMaster updProduct = new ProductMaster(id,productName, cost, productDescription);
		productMasterDao.updateProduct(updProduct);
		System.out.println("test");
		view = listAllProducts(request,response);
		return view;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String view = "";
		String ProductId = request.getParameter("id");
		System.out.println(ProductId);
		int id = Integer.parseInt(ProductId);
		//int id = 1;
		ProductMaster showEditProductForm = productMasterDao.getProduct(id);
		request.setAttribute("showEditProductForm", showEditProductForm);
	    System.out.println(showEditProductForm); 
		view = "editproduct.jsp";
		//view = "/editproduct.jsp";
		// TODO Auto-generated method stub
		return view;
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		// TODO Auto-generated method stub
		String view = "";
		String productName = request.getParameter("productName");
		Double cost = Double.parseDouble(request.getParameter("cost"));
		String productDescription = request.getParameter("productDescription");
		ProductMaster newProduct = new ProductMaster(productName, cost, productDescription);
		productMasterDao.insertProduct(newProduct);
		System.out.println("test");
		view = listAllProducts(request,response);
		return view;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		view = "/newproduct.jsp";
		// TODO Auto-generated method stub
		return view;
	}
	
	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");
		String view = "";
		
		if (loginid.equalsIgnoreCase("admin") && password.contentEquals("admin"))
			view = listAllProducts(request,response);
		else
			view = "/errorPage.jsp";
		//session.
		//response.setContentType("text/html");
		return view;
	}

	
}