package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Years;

public class YearDaoImpl implements YearDao {

    private DaoFactory daoFactory;

    public YearDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addYear(Years year) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO years (year, d_administration, ad_t, d_restaurant, res_t, d_foyer, fo_t, total_depense, total_tolere, departement_d, departement_t, etat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, year.getYear());
            preparedStatement.setFloat(2, year.getdAdministration());
            preparedStatement.setFloat(3, year.getAdT());
            preparedStatement.setFloat(4, year.getdRestaurant());
            preparedStatement.setFloat(5, year.getResT());
            preparedStatement.setFloat(6, year.getdFoyer());
            preparedStatement.setFloat(7, year.getFoT());
            preparedStatement.setFloat(8, year.getTotalDepense());
            preparedStatement.setFloat(9, year.getTotalTolere());
            preparedStatement.setFloat(10, year.getDepartement_d());
            preparedStatement.setFloat(11, year.getDepartement_t());
            preparedStatement.setString(12, year.getEtat());
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
    public Years getYear(int year) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Years foundYear = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM years WHERE year = ?");
            preparedStatement.setInt(1, year);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	  foundYear = new Years();
                  foundYear.setYear(resultSet.getInt("year"));
                  foundYear.setdAdministration(resultSet.getFloat("d_administration"));
                  foundYear.setAdT(resultSet.getFloat("ad_t"));
                  foundYear.setdRestaurant(resultSet.getFloat("d_restaurant"));
                  foundYear.setResT(resultSet.getFloat("res_t"));
                  foundYear.setdFoyer(resultSet.getFloat("d_foyer"));
                  foundYear.setFoT(resultSet.getFloat("fo_t"));
                  foundYear.setTotalDepense(resultSet.getFloat("total_depense"));
                  foundYear.setTotalTolere(resultSet.getFloat("total_tolere"));
                  foundYear.setDepartement_d(resultSet.getFloat("departement_d"));
                  foundYear.setDepartement_t(resultSet.getFloat("departement_t"));
                  foundYear.setEtat(resultSet.getString("etat"));
            }
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
        return foundYear;
    }
    public void updateYear(Years year) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE years SET d_administration=?, ad_t=?, d_restaurant=?, res_t=?, d_foyer=?, fo_t=?, total_depense=?, total_tolere=?, departement_d=?, departement_t=?, etat=? WHERE year=?");
            preparedStatement.setFloat(1, year.getdAdministration());
            preparedStatement.setFloat(2, year.getAdT());
            preparedStatement.setFloat(3, year.getdRestaurant());
            preparedStatement.setFloat(4, year.getResT());
            preparedStatement.setFloat(5, year.getdFoyer());
            preparedStatement.setFloat(6, year.getFoT());
            preparedStatement.setFloat(7, year.getTotalDepense());
            preparedStatement.setFloat(8, year.getTotalTolere());
            preparedStatement.setFloat(9, year.getDepartement_d());
            preparedStatement.setFloat(10, year.getDepartement_t());
            preparedStatement.setString(11, year.getEtat());
            preparedStatement.setInt(12, year.getYear());
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
    public int getMaxYear() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int maxYear = -1;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT MAX(year) AS max_year FROM years");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                maxYear = resultSet.getInt("max_year");
            }
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
        return maxYear;
    }
    public void updateYearState(int year, String newState) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE years SET etat=? WHERE year=?");
            preparedStatement.setString(1, newState);
            preparedStatement.setInt(2, year);
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
    public List<Integer> getAllYears() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> years = new ArrayList<Integer>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT DISTINCT year FROM years");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                years.add(resultSet.getInt("year"));
            }
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
        return years;
    }
}




  