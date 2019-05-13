package edu.aydin.sda.springand;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.aydin.sda.Entity.Colors;
import edu.aydin.sda.Entity.Products;
import edu.aydin.sda.Entity.Stocks;
import edu.aydin.sda.Entity.Users;
import edu.aydin.sda.Util.Utill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class MyController {
	ArrayList<Products> cart = new ArrayList<Products>();
	@RequestMapping("/")
    public String Home(){
	    return "/index.jsp";
    }
	@RequestMapping("/login")
	public String Login(Model model){
		return "/login.jsp";
	}
	@RequestMapping(path = "/LoginControl",method = RequestMethod.POST)
	public String LoginControl(HttpServletRequest request,
			@RequestParam("username")String username,
			@RequestParam("user_password")String password,
			Model model){
		Session session = Utill.getSessionFactory().openSession();
		List <Users> users = 
        		session.createQuery(
        			"from Users", Users.class).list();
		session.close();
		for(Users u:users) {
			if(username.equals(u.getUsername())) {
				if(password.equals(u.getUser_password())) {
					HttpSession httpSession = request.getSession(); 
					httpSession.setAttribute("username", u.getUsername());
					return "redirect:/";
				}
				model.addAttribute("passwordw", 1);
				return "/login";
			}
		}
		model.addAttribute("passwordw", 2);
		return "/login";
	}
	@RequestMapping("/products")
	public String products(Model model) {
		Session session = Utill.getSessionFactory().openSession();
		List <Products> products = 
        		session.createQuery(
        			"from Products", Products.class).list();
		model.addAttribute("products",products);
		model.addAttribute("type","Products");
		session.close();
		for(Products p: products)
			System.out.println(p.getDetail());
		return "/products.jsp";
	}
	@RequestMapping("/products-{type}")
	public String productsType(Model model, @PathVariable("type")String type) {
		Session session = Utill.getSessionFactory().openSession();
		/*Transaction t;
		t = session.beginTransaction();
		Products p1 = new Products();
		p1.setDetail("");
		p1.setImage("");
		p1.setPrice(1);
		p1.setProduct_Name("");
		p1.setProduct_Type("");
		session.save(p1);
		t.commit();*/
		List <Products> products = 
        		session.createQuery(
        			"from Products where Product_Type = '"+type+"'", Products.class).list();
		model.addAttribute("products",products);
		model.addAttribute("type",type);
		session.close();
		/*for(Products p: products)
			System.out.println(p.getProduct_Name());*/
		return "/products.jsp";
	}
	@RequestMapping("/product{id}")
	public String ProductDetail(@PathVariable("id")String id,Model model) {
		Session session = Utill.getSessionFactory().openSession();
		Products product = session.createQuery("from Products where id = "+Integer.parseInt(id),Products.class).getSingleResult();
		model.addAttribute("product",product);
		return "/detail.jsp";
	}
	@RequestMapping("/logout")
	public String LogOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	@RequestMapping("/register")
	public String Register() {
		return "/register.jsp";
	}
	@RequestMapping(path = "/registerControl", method = RequestMethod.POST)
	public String RegisterControl(Model model,
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value="user_password",required=true)String user_password,
			@RequestParam(value="user_name",required=true)String user_name,
			@RequestParam(value="user_surname",required=true)String user_surname,
			@RequestParam(value="user_birthdate",required=true)String date
			) {
		Session session = Utill.getSessionFactory().openSession();
		List <Users> users = 
        		session.createQuery(
        			"from Users", Users.class).list();
		boolean error = false;
		for(Users u:users) {
			if(u.getUsername().equals(username)) {
				error = true;
			}
		}
		if(!error) {
			Transaction transaction = null;
			transaction = session.beginTransaction();
			Users user = new Users();
			user.setUsername(username);
			user.setUser_name(user_name);
			user.setUser_surname(user_surname);
			user.setUser_password(user_password);
			user.setUser_birthdate(date);
			session.save(user);
			transaction.commit();
			session.close();
			model.addAttribute("error1", 0);
			return "redirect:/";
		}
		/*List <Users> users = 
        		session.createQuery(
        			"from Users", Users.class).list();*/
		session.close();
		/*for(Users u: users)
			System.out.println(u.getUser_name());*/
		
		model.addAttribute("error1", 1);
		return "/register";
	}
	@RequestMapping("/setStok{id}-{stock}")
	public String AddStock(
			@PathVariable("id")int id,
			@PathVariable("stock")int stock) {
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		Stocks s = new Stocks();
		Products product = session.createQuery("from Products where id = "+id,Products.class).getSingleResult();
		s.setId(id);
		s.setStock(stock);
		product.setStock(s);
		session.save(product);
		transaction.commit();
		session.close();
		return "redirect:/products";
	}
	@RequestMapping("/setColor{id}-{color}")
	public String AddColor(
			@PathVariable("id")int id,
			@PathVariable("color")String color) {
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		Colors colors = new Colors();
		Products product = session.createQuery("from Products where id = "+id,Products.class).getSingleResult();
		colors.setColor(color);
		session.save(colors);
		product.setColors(colors);
		session.save(product);
		Products p = session.createQuery("from Products where id = "+id,Products.class).getSingleResult();
		List<Colors> colorss = p.getColors();
		for(Colors c : colorss) {
			System.out.println(c.getColor());
		}
		transaction.commit();
		session.close();
		return "redirect:/products";
	}
	@RequestMapping("/deneme")
	public String ShowStock() {
		Session session = Utill.getSessionFactory().openSession();
		Products product = session.createQuery("from Products where id = "+66,Products.class).getSingleResult();
		session.close();
		System.out.println(product.getStock().getStock());
		return "";
	}
	@RequestMapping("/AddCart-{id}")
	public String AddCart(
			@PathVariable("id")int id,
			HttpSession session) {
		Session session1 = Utill.getSessionFactory().openSession();
		Products product = session1.createQuery("from Products where id = "+id,Products.class).getSingleResult();
		cart.add(product);
		for(Products x:cart) {
			System.out.println(x.getID() + "  " + x.getDetail());
		}
		session.setAttribute("cart", cart);
		return "redirect:javascript:history.back()";
	}
	@RequestMapping("/Cart")
	public String ShowCart(
			HttpSession session){
		return "/cart.jsp";
	}
	@RequestMapping("DeleteFromCart-{id}")
	public String DeleteFromCart(
			@PathVariable("id")int id,
			HttpSession session) {
		for(Products c:cart) {
			if(cart.size()==1) {
				cart.remove(c);
				session.setAttribute("cart",null);
				break;
			}
			else if(c.getID() == id) {
				cart.remove(c);
				session.setAttribute("cart",cart);
				break;
			}
		}
		return "redirect:/Cart";
	}
	@RequestMapping("/adminPanel")
	public String adminPanel(){
		return "/AdminPanel.jsp";
	}
	@RequestMapping("/adminPanelUsers")
	public String adminPanelUsers(Model model){
		Session session = Utill.getSessionFactory().openSession();
		List <Users> users = 
        		session.createQuery(
        			"from Users", Users.class).list();
		session.close();
		for(Users u:users) {
			System.out.println(u.getID()+"-"+u.getUser_birthdate()+"-"+u.getUser_name()+"-"+u.getUser_password()+"-"+u.getUser_surname()+"-"+u.getUsername());;
		}
		model.addAttribute("users",users);
		return "/adminPanelUsers.jsp";
	}
	@RequestMapping("/adminPanelProducts")
	public String adminPanelProducts(Model model) {
		Session session = Utill.getSessionFactory().openSession();
		List <Products> products = 
        		session.createQuery(
        			"from Products", Products.class).list();
		model.addAttribute("products",products);
		session.close();
		model.addAttribute("products",products);
		return "/adminPanelProducts.jsp";
	}
	@RequestMapping("/AddProduct")
	public String addProduct() {
		return "/adminPanelAddProduct.jsp";
	}
	@PostMapping("/adminPanelProducts")
	public String addProductPost(
			@RequestParam(value="image",required=true)String img,
			@RequestParam(value="name",required=true)String name,
			@RequestParam(value="type",required=true)String type,
			@RequestParam(value="price",required=true)float price,
			@RequestParam(value="details",required=false)String details,
			@RequestParam(value="colors",required=false)List<Colors> colors){
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		Products p = new Products(img,name,type,price,details);
		session.save(p);
		transaction.commit();
		session.close();
		return "redirect:/adminPanelProducts";
	}
	@RequestMapping("/editUser{id}")
	public String editUser(
			@PathVariable("id")int id,
			Model model) {
		Session session = Utill.getSessionFactory().openSession();
		Users user = session.createQuery("from Users where id="+id,Users.class).getSingleResult();
		model.addAttribute("user",user);
		return "/editUser.jsp";
	}
	@RequestMapping("/editProduct{id}")
	public String editProduct(
			@PathVariable("id")int id,
			Model model) {
		Session session = Utill.getSessionFactory().openSession();
		Products product = session.createQuery("from Products where id="+id,Products.class).getSingleResult();
		model.addAttribute("product",product);
		return "/adminPanelEditProduct.jsp";
	}
	@RequestMapping("/deleteUser{id}")
	public String deleteUser(@PathVariable("id")int id) {
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction;
		transaction = session.getTransaction();
        transaction.begin();
		Users user = session.createQuery("from Users where id="+id,Users.class).getSingleResult();
		session.remove(user);
		transaction.commit();
		session.close();
		return "redirect:/adminPanelUsers";
	}
	@PostMapping("/editUser")
	public String editUser(
			@RequestParam(value="id",required=true)int id,
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value="password",required=true)String password,
			@RequestParam(value="name",required=true)String name,
			@RequestParam(value="surname",required=true)String surname,
			@RequestParam(value="birthdate",required=true)String birthdate) {
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction;
		transaction = session.getTransaction();
		transaction.begin();
		Users user = session.createQuery("from Users where id="+id,Users.class).getSingleResult();
		user.setUsername(username);
		user.setUser_name(name);
		user.setUser_surname(surname);
		user.setUser_birthdate(birthdate);
		user.setUser_password(password);
		transaction.commit();
		session.close();
		return "/editUser"+id;
	}
	@PostMapping("/editProduct")
	public String editProduct(
			@RequestParam(value="id",required=true)int id,
			@RequestParam(value="image",required=true)String image,
			@RequestParam(value="changeimage")String image2,
			@RequestParam(value="name",required=true)String name,
			@RequestParam(value="type",required=true)String type,
			@RequestParam(value="price",required=true)float price,
			@RequestParam(value="stock",required=true)int stock,
			@RequestParam(value="detail",required=true)String detail) {
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction;
		transaction = session.getTransaction();
		transaction.begin();
		Products product = session.createQuery("from Products where id="+id,Products.class).getSingleResult();
		System.out.println(image2);
		if(image2.equals("")) {
			product.setImage(image);
		}else {
			product.setImage(image2);
		}
		product.setProduct_Name(name);
		product.setProduct_Type(type);
		product.setPrice(price);
		product.getStock().setStock(stock);;
		product.setDetail(detail);
		session.save(product);
		transaction.commit();
		session.close();
		return "redirect:/editProduct"+id;
	}
	@RequestMapping("/deleteProduct{id}")
	public String deleteProduct(@PathVariable("id")int id) {
		Session session = Utill.getSessionFactory().openSession();
		Transaction transaction;
		transaction = session.getTransaction();
        transaction.begin();
		Products product = session.createQuery("from Products where id="+id,Products.class).getSingleResult();
		System.out.println(product.getProduct_Name());
		session.remove(product);
		transaction.commit();
		session.close();
		return "redirect:/adminPanelUsers";
	}
	
}
