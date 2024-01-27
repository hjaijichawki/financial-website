package com.octest.dao;

import java.util.List;

import com.octest.beans.Product;

public interface ProductDao {
    void add(Product product);
    void update(Product product);
    void delete(Product product);
    Product get(int p_id, String p_name);
    List<Product> list();
}
