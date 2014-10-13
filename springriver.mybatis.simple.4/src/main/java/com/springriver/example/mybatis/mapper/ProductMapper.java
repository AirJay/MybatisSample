package com.springriver.example.mybatis.mapper;

import com.springriver.example.mybatis.bean.Product;

public interface ProductMapper {
	public Product selectProductById(int id);
	public void insertProduct(Product product);
}
