package com.dattt.miniassignment.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dattt.miniassignment.dto.Product;
import com.dattt.miniassignment.service.CategoryService;
import com.dattt.miniassignment.service.ProductService;

import antlr.StringUtils;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;
	private final String UPLOAD_DIR = "D:\\Spring\\";
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getAllProducts() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("products", productService.getAllProduct());
		return mav;
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView getProductDetail(@RequestParam("id")Long id) {
		ModelAndView mav = new ModelAndView("detail");
		mav.addObject("productD", productService.getProduct(id));
		return mav;
		
	}
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView getCategory() {
		ModelAndView mav = new ModelAndView("addProduct");
		mav.addObject("listC", categoryService.getCate());
		return mav;
	}
	@RequestMapping(value ="/addNewProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute(name = "productForm")Product product, @RequestParam("file")MultipartFile file, ModelMap mm) {
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            product.setPics(fileName);
            productService.addProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return "redirect:/addProduct";
	}
}
