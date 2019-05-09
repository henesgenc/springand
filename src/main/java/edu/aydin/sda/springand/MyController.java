package edu.aydin.sda.springand;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.aydin.sda.Entity.Products;
import edu.aydin.sda.Entity.Stocks;
import edu.aydin.sda.Entity.Users;
import edu.aydin.sda.Util.Utill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MyController {
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
		session.close();
		for(Products p: products)
			System.out.println(p.getProduct_Name());
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
		session.close();
		/*for(Products p: products)
			System.out.println(p.getProduct_Name());*/
		return "/products.jsp";
	}
	@RequestMapping("/product{id}")
	public String ProductDetail(@PathVariable("id")String id,Model model) {
		Session session = Utill.getSessionFactory().openSession();
		List <Products> product = session.createQuery("from Products where id = "+Integer.parseInt(id),Products.class).list();
		for(Products p: product)
			System.out.println(p.getProduct_Name());
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
	@RequestMapping("/deneme")
	public String ShowStock() {
		Session session = Utill.getSessionFactory().openSession();
		Products product = session.createQuery("from Products where id = "+66,Products.class).getSingleResult();
		System.out.println(product.getStock().getStock());
		return "";
	}
}
