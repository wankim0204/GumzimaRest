package com.gumzima.shopping.rest.controller.product;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gumzima.shopping.exception.ImageRegistException;
import com.gumzima.shopping.exception.ProductDeleteException;
import com.gumzima.shopping.exception.ProductRegistException;
import com.gumzima.shopping.exception.ProductUpdateException;
import com.gumzima.shopping.model.common.FileManager;
import com.gumzima.shopping.model.domain.Product;
import com.gumzima.shopping.model.domain.SearchText;
import com.gumzima.shopping.model.product.service.P_subcategoryService;
import com.gumzima.shopping.model.product.service.P_topcategoryService;
import com.gumzima.shopping.model.product.service.ProductService;
import com.gumzima.shopping.model.recipe.service.RecipeService;

@RestController
public class RestProductController {
	private static final Logger logger = LoggerFactory.getLogger(RestProductController.class);
	@Autowired
	private ProductService productService;

	@Autowired
	private P_topcategoryService p_topcategoryService;

	@Autowired
	private P_subcategoryService p_subcategoryService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private FileManager fileManager;

	private ServletContext servletContext;

	/*---------------------------------------------------------------------
	 * 이용자 페이지 상품
	 * --------------------------------------------------------------------*/
	@GetMapping(value = "/rest/topcategory")
	public List getTopcategory() {
		List topList = p_topcategoryService.selectAll();
		return topList;
	}

	@GetMapping("/rest/subcategory/{topcategory_id}")
	public List getSubcategory(@PathVariable int topcategory_id) {
		List subList = p_subcategoryService.selectAllById(topcategory_id);
		return subList;
	}
	
	@GetMapping(value = "/rest/getProduct/{topcategory_id}")
	public List getProduct(@PathVariable int topcategory_id) {
		List<Product> productList = productService.selectByTopcategory(topcategory_id);
		return productList;
	}
	
	@GetMapping(value = "/rest/searchProduct/{text}")
	public List searchProduct(@PathVariable String text) {
		List<Product> productList = productService.searchProduct(text);
		return productList;
	}

	/*---------------------------------------------------------------------
	 * 예외 처리
	 * --------------------------------------------------------------------*/
	@ExceptionHandler(ProductRegistException.class)
	@ResponseBody
	public String handleException(ProductRegistException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":\"0\",");
		sb.append("\"msg\":\"" + e.getMessage() + "\"");
		sb.append("}");
		return sb.toString();
	}

	@ExceptionHandler(ProductUpdateException.class)
	@ResponseBody
	public String handleException(ProductUpdateException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":\"0\",");
		sb.append("\"msg\":\"" + e.getMessage() + "\"");
		sb.append("}");
		return sb.toString();
	}

	@ExceptionHandler(ProductDeleteException.class)
	@ResponseBody
	public String handleException(ProductDeleteException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":\"0\",");
		sb.append("\"msg\":\"" + e.getMessage() + "\"");
		sb.append("}");
		return sb.toString();
	}

	@ExceptionHandler(ImageRegistException.class)
	@ResponseBody
	public String handleException(ImageRegistException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":\"0\",");
		sb.append("\"msg\":\"" + e.getMessage() + "\"");
		sb.append("}");
		return sb.toString();
	}
}
