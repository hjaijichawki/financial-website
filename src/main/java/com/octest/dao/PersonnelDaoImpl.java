package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.octest.beans.Personnel;

public class PersonnelDaoImpl implements PersonnelDao {
    private DaoFactory daoFactory;

    public PersonnelDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addPersonnel(Personnel personnel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                "INSERT INTO Personnel (P_ID, Salaire, Appartenance, Year) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, personnel.getP_ID());
            preparedStatement.setFloat(2, personnel.getSalaire());
            preparedStatement.setString(3, personnel.getAppartenance());
            preparedStatement.setInt(4, personnel.getYear());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and Connection objects
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
    public boolean deletePersonnel(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Personnel WHERE P_ID = ?;");
            preparedStatement.setString(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in the opposite order of their creation
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result > 0;
    }
    public boolean updatePersonnel(Personnel personnel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean rowUpdated = false;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE Personnel SET Salaire=?, Appartenance=?, Year=? WHERE P_ID=?;");
            preparedStatement.setFloat(1, personnel.getSalaire());
            preparedStatement.setString(2, personnel.getAppartenance());
            preparedStatement.setInt(3, personnel.getYear());
            preparedStatement.setString(4, personnel.getP_ID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	  // Close the ResultSet, PreparedStatement and Connection objects
            try {
            
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }        }
        return rowUpdated;
    }
    public Personnel getPersonnelById(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Personnel personnel = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Personnel WHERE P_ID = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                personnel = new Personnel(
                    resultSet.getString("P_ID"),
                    resultSet.getFloat("Salaire"),
                    resultSet.getString("Appartenance"),
                    resultSet.getInt("Year")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection, statement, and result set
        	  // Close the ResultSet, PreparedStatement and Connection objects
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

        return personnel;
    }
    public List<Personnel> getAllPersonnel() {
        List<Personnel> personnelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Personnel;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Personnel personnel = new Personnel(
                    resultSet.getString("P_ID"),
                    resultSet.getFloat("Salaire"),
                    resultSet.getString("Appartenance"),
                    resultSet.getInt("Year")
                );
                personnelList.add(personnel);
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

        return personnelList;
    }
    public List<Personnel> getPersonnelByYear(int year) {
        List<Personnel> personnelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Personnel WHERE Year = ?");
            preparedStatement.setInt(1, year);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Personnel personnel = new Personnel(
                    resultSet.getString("P_ID"),
                    resultSet.getFloat("Salaire"),
                    resultSet.getString("Appartenance"),
                    resultSet.getInt("Year")
                );
                personnelList.add(personnel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resultSet, preparedStatement, and connection
            try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return personnelList;
    }

}

  