package com.dattt.miniassignment.controller;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dattt.miniassignment.dto.CartItem;
import com.dattt.miniassignment.dto.Product;
import com.dattt.miniassignment.service.ProductService;

@Controller

public class CartController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String viewAdd(ModelMap mm, HttpSession session, @RequestParam("id") Long id) {
		HashMap<Long, CartItem> cartItems = (HashMap<Long, CartItem>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Product product = productService.getProduct(id);
		if (product != null) {
			if (cartItems.containsKey(id)) {
				CartItem item = cartItems.get(id);
				item.setProduct(product);
				item.setQuantity(item.getQuantity() + 1);
				cartItems.put(id, item);
			} else {
				CartItem item = new CartItem();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(id, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/home";

	}

	public double totalPrice(HashMap<Long, CartItem> cartItems) {
		double count = 0;
		for (Entry<Long, CartItem> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
		}
		return count;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String viewRemove(ModelMap mm, HttpSession session, @RequestParam("id") Long id) {
		HashMap<Long, CartItem> cartItems = (HashMap<Long, CartItem>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (cartItems.containsKey(id)) {
			cartItems.remove(id);
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "viewCart";
	}

	@RequestMapping(value = "/removeAllItemsInCart", method = RequestMethod.GET)
	public String viewRemoveAll(ModelMap mm, HttpSession session) {
		HashMap<Long, CartItem> cartItems = (HashMap<Long, CartItem>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		cartItems.clear();
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "viewCart";
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String getViewCartPage() {
		return "viewCart";
	}
}
