package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Product;

public class ProductDaoImpl implements ProductDao {

    private DaoFactory daoFactory;

    public ProductDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO product (P_id, P_name, D_id, Number, P_priority, Price) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, product.getP_id());
            preparedStatement.setString(2, product.getP_name());
            preparedStatement.setInt(3, product.getD_id());
            preparedStatement.setInt(4, product.getNumber());
            preparedStatement.setInt(5, product.getP_priority());
            preparedStatement.setDouble(6, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE product SET D_id=?, Number=?, P_priority=?, Price=? WHERE P_id=? AND P_name=?");
            preparedStatement.setInt(1, product.getD_id());
            preparedStatement.setInt(2, product.getNumber());
            preparedStatement.setInt(3, product.getP_priority());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getP_id());
            preparedStatement.setString(6, product.getP_name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM product WHERE P_id=? AND P_name=?");
            preparedStatement.setInt(1, product.getP_id());
            preparedStatement.setString(2, product.getP_name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Product get(int p_id, String p_name) {
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE P_id = ? AND P_name = ?");
            preparedStatement.setInt(1, p_id);
            preparedStatement.setString(2, p_name);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int d_id = resultSet.getInt("D_id");
                int number = resultSet.getInt("Number");
                int p_priority = resultSet.getInt("P_priority");
                double price = resultSet.getDouble("Price");
                
                product = new Product(p_id, p_name, d_id, number, p_priority, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return product;
    }
    @Override
    public List<Product> list() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM product");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setP_id(resultSet.getInt("P_id"));
                product.setP_name(resultSet.getString("P_name"));
                product.setD_id(resultSet.getInt("D_id"));
                product.setNumber(resultSet.getInt("Number"));
                product.setP_priority(resultSet.getInt("P_priority"));
                product.setPrice(resultSet.getDouble("Price"));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productList;
    }
}