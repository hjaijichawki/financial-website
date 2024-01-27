package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Staff;

public class StaffDaoImpl implements StaffDao {

    private DaoFactory daoFactory;

    public StaffDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addStaff(Staff staff) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Staff (D_id, S_id, S_full_name, S_priority, Salary) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, staff.getD_id());
            preparedStatement.setInt(2, staff.getS_id());
            preparedStatement.setString(3, staff.getS_full_name());
            preparedStatement.setInt(4, staff.getS_priority());
            preparedStatement.setDouble(5, staff.getSalary());
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
    public void updateStaff(Staff staff) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Staff SET S_full_name=?, S_priority=?, Salary=? WHERE D_id=? AND S_id=?");
            preparedStatement.setString(1, staff.getS_full_name());
            preparedStatement.setInt(2, staff.getS_priority());
            preparedStatement.setDouble(3, staff.getSalary());
            preparedStatement.setInt(4, staff.getD_id());
            preparedStatement.setInt(5, staff.getS_id());
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
    public void deleteStaff(Staff staff) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Staff WHERE D_id=? AND S_id=?");
            preparedStatement.setInt(1, staff.getD_id());
            preparedStatement.setInt(2, staff.getS_id());
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
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<Staff>();
        Connection connection = null;
        java.sql.Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Staff;");

            while (resultSet.next()) {
                int d_id = resultSet.getInt("D_id");
                int s_id = resultSet.getInt("S_id");
                String s_full_name = resultSet.getString("S_full_name");
                int s_priority = resultSet.getInt("S_priority");
                double salary = resultSet.getDouble("Salary");

                Staff staff = new Staff(d_id, s_id, s_full_name, s_priority, salary);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return staffList;
    }
   }