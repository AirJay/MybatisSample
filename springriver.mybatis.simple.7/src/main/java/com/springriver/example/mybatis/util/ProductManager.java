package com.springriver.example.mybatis.util;


import com.springriver.example.mybatis.bean.Product;
import com.springriver.example.mybatis.mapper.ProductMapper;

public class ProductManager {
	public static Product selectProductById(final int id) {
		
		return (Product)new DatabaseOperation() {
			
			@Override
			public Object performAction() {
				ProductMapper ProductMapper = getSession().getMapper(ProductMapper.class);
				return ProductMapper.selectProductById(id);
			}
		}.invoke();
	}

	public static void insertProduct(final Product Product) {
		
		new DatabaseOperation(true) {
			
			@Override
			public Object performAction() {
				ProductMapper ProductMapper = getSession()
						.getMapper(ProductMapper.class);
				ProductMapper.insertProduct(Product);
				return null;
			}
		}.invoke();
	}
}
