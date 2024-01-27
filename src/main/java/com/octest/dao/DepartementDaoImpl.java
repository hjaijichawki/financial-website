package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Departement;



public class DepartementDaoImpl implements DepartementDao {
    private DaoFactory daoFactory;

    DepartementDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Departement departement) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Departement(D_id, D_name, D_priority, Materials, Bank_Account_RIB) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, departement.getD_id());
            preparedStatement.setString(2, departement.getD_name());
            preparedStatement.setInt(3, departement.getD_priority());
            preparedStatement.setString(4, departement.getMaterials());
            preparedStatement.setInt(5, departement.getBankAccount_RIB());

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
    public void update(Departement departement) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Departement SET D_name = ?, D_priority = ?, Materials = ?, Bank_Account_RIB = ? WHERE D_id = ?;");
            preparedStatement.setString(1, departement.getD_name());
            preparedStatement.setInt(2, departement.getD_priority());
            preparedStatement.setString(3, departement.getMaterials());
            preparedStatement.setInt(4, departement.getBankAccount_RIB());
            preparedStatement.setInt(5, departement.getD_id());

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
    public void delete(int D_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Departement WHERE D_id = ?;");
            preparedStatement.setInt(1, D_id);

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
    public Departement getDepartementById(int D_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Departement departement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Departement WHERE D_id = ?;");
            preparedStatement.setInt(1, D_id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                departement = new Departement(
                        resultSet.getInt("D_id"),
                        resultSet.getString("D_name"),
                        resultSet.getInt("D_priority"),
                        resultSet.getString("Materials"),
                        resultSet.getInt("Bank_Account_RIB")
                );
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
        return departement;
    }

    @Override
    public List<Departement> getAllDepartements() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Departement> departements = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Departement;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Departement departement = new Departement(
                        resultSet.getInt("D_id"),
                        resultSet.getString("D_name"),
                        resultSet.getInt("D_priority"),
                        resultSet.getString("Materials"),
                        resultSet.getInt("Bank_Account_RIB")
                );
                departements.add(departement);
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
        return departements;
    }
}