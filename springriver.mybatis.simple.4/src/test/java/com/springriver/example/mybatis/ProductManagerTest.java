package com.springriver.example.mybatis;

import org.junit.Test;

import com.springriver.example.mybatis.bean.Product;
import com.springriver.example.mybatis.util.ProductManager;

public class ProductManagerTest {

	@Test
	public void testProuct() {
		Product product = new Product();
		product.setProductName("Sunrise Juniors Boardshort");
		ProductManager.insertProduct(product);
		Product insertedProduct = ProductManager.selectProductById(product.getProductId());
		assert(insertedProduct != null);
		assert(product.getProductName().equals(insertedProduct.getProductName()));
	}
}
