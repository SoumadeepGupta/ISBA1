package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	//CoronaKit coronaKit = new CoronaKit();
	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
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

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		HttpSession session = request.getSession();
		CoronaKit coronaKit = (CoronaKit) session.getAttribute("coronaKit");
		System.out.println(coronaKit.getEmail());
		int kitId = coronaKit.getId();
		request.setAttribute("FirstName", "Name: "+coronaKit.getPersonName());
		request.setAttribute("Email", "EMailID: "+coronaKit.getEmail());
		request.setAttribute("MobileNo", "MobileNo: "+coronaKit.getContactNumber());
		
		//System.out.println(Address);
		Map<String,String> confirmedKit = kitDAO.getKitConfimed(kitId);
		System.out.println(confirmedKit);
		request.setAttribute("confirmedKit", confirmedKit);
		//getKitConfimed
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		HttpSession session = request.getSession();
		CoronaKit coronaKit = (CoronaKit) session.getAttribute("coronaKit");
		int kitId = coronaKit.getId();
		System.out.println(coronaKit.getEmail());
		String Address= request.getParameter("Address");
		System.out.println("==========="+Address);
		//System.out.println(fullName);
		coronaKit.setDeliveryAddress(Address);
		System.out.println(coronaKit.getDeliveryAddress()+"|");
		request.setAttribute("FirstName", "Name: "+coronaKit.getPersonName());
		request.setAttribute("Email", "EMailID: "+coronaKit.getEmail());
		request.setAttribute("MobileNo", "MobileNo: "+coronaKit.getContactNumber());
		
		request.setAttribute("Address", "Address: "+coronaKit.getDeliveryAddress());
		
		Map<String,String> confirmedKitDetail = kitDAO.getKitConfimed(kitId);
		System.out.println(confirmedKitDetail);
		request.setAttribute("confirmedKitDetail", confirmedKitDetail);
		request.setAttribute("kitId", Integer.toString(kitId));
		
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hellos");
		HttpSession session = request.getSession();
		CoronaKit coronaKit = (CoronaKit) session.getAttribute("coronaKit");
		int productId = 0;
		//HashMap<Integer,Integer> cartItems =new HashMap<Integer,Integer>();
		//String productQuantity = "";
		//String view = "";
		//int id = Integer.parseInt(request.getParameter("id"));
		int iterator = kitDAO.countProduct();
		//int kitId = kitDAO.countKit();
		
		int kitId = coronaKit.getId();
		int itemCount = 0;
		System.out.println(iterator);
		for(int i =1 ;i <=iterator ; i++)
		{
			productId = i;
			KitDetail getKitItem = new KitDetail(kitId, productId);
			
		itemCount = kitDAO.getItemExistInKit(getKitItem);
		String productQuantityC = request.getParameter("quantity"+i);
	//	System.out.println("|"+ productQuantityC + "|");
		if(productQuantityC!= null && productQuantityC.isEmpty() == false && (Integer.parseInt(productQuantityC) != 0))
		{
		System.out.println("|"+ productQuantityC + "|");
		
		
		if(itemCount==0)
		{
		//ProductMaster showEditProductForm = productMasterDao.getProduct(productId);
		//String productQuantity = request.getParameter("quantity"+i);
		//int pq = Integer.parseInt(productQuantityC);
		//int productAmount = 10;
		KitDetail newKit = new KitDetail(kitId, productId, Integer.parseInt(productQuantityC));
		kitDAO.insertToKit(newKit);
		}
		else if(itemCount==1)
		{
			//if((Integer.parseInt(productQuantityC) > 0))
			//{
			System.out.println("insie ifesle" +productQuantityC);
			KitDetail updateKit = new KitDetail(kitId, productId, Integer.parseInt(productQuantityC));
			kitDAO.updateProductInKit(updateKit);
			//}
		//	else if ((Integer.parseInt(productQuantityC) == 0))
		//	{
		//		KitDetail updateKit = new KitDetail(kitId, productId);
		///		kitDAO.deleteProductInKit(updateKit);	
		//	}
		}
		
		//productQuantity = request.
		//request.
		//Double cost = Double.parseDouble(request.getParameter("cost"));
		//String productDescription = request.getParameter("productDescription");
		//ProductMaster updProduct = new ProductMaster(id,productName, cost, productDescription);
		//productMasterDao.updateProduct(updProduct);
		//System.out.println(productQuantity1);
		//view = listAllP	roducts(request,response);
		//return view;select kit.kit_id,kit.productid,kit.productquantity,product.product_
		//cost*productquantity as totalProductCost from kit join product on kit.productid=product.product_id;
		}
		else if (itemCount==1)
		{
			KitDetail updateKit = new KitDetail(kitId, productId);
			kitDAO.deleteProductInKit(updateKit);
		}
		// TODO Auto-generated method stub
				
	}
		String view = "";
		List<KitDetail> listAllProductsInKit = kitDAO.selectAllProductsInKit(kitId);
		request.getSession().setAttribute("listAllProductsInKit", listAllProductsInKit);
		Map<String,String> confirmedKitDetail = kitDAO.getKitConfimed(kitId);
		System.out.println(confirmedKitDetail);
		request.setAttribute("confirmedKitDetail", confirmedKitDetail);
		view =  "/showkit.jsp";
		return view;
		//return "";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "";
		HttpSession session = request.getSession();
		CoronaKit coronaKit = new CoronaKit();
		coronaKit.setId(kitDAO.countKit()+1);
		String fullName = request.getParameter("fullName");
		String emailID = request.getParameter("email");
		String MobileNo= request.getParameter("MobileNumber");
		System.out.println("==========="+MobileNo);
		System.out.println(fullName);
		coronaKit.setPersonName(fullName);
		coronaKit.setEmail(emailID);
		coronaKit.setContactNumber(MobileNo);
		System.out.println("|"+coronaKit.getContactNumber());
		List<ProductMaster> listAllProducts = productMasterDao.selectAllProducts();
		request.setAttribute("listAllProducts", listAllProducts);
		
		view = "/showproductstoadd.jsp";
		// TODO Auto-generated method stub
		session.setAttribute("coronaKit", coronaKit);
		return view;
		// TODO Auto-generated method stub
		//return "";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		return "";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String view = "/newuser.jsp";
		//view = insertNewUser(request,response);
		
		
		//
		// TODO Auto-generated method stub
		return view;
		//return "";
	}
}