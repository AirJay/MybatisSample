package com.springriver.example.mybatis.util;

import org.apache.ibatis.session.SqlSession;

import com.springriver.example.mybatis.bean.Product;
import com.springriver.example.mybatis.mapper.ProductMapper;

public class ProductManager {
	public static Product selectProductById(int id) {
		SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory()
				.openSession();
		try {
			ProductMapper ProductMapper = sqlSession
					.getMapper(ProductMapper.class);
			return ProductMapper.selectProductById(id);
		} finally {
			sqlSession.close();
		}
	}

	public static void insertProduct(Product Product) {
		SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory()
				.openSession();
		try {
			ProductMapper ProductMapper = sqlSession
					.getMapper(ProductMapper.class);
			ProductMapper.insertProduct(Product);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
