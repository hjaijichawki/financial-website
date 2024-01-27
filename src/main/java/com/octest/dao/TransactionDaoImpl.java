package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Transaction;

public class TransactionDaoImpl implements TransactionDao {
    private DaoFactory daoFactory;

    public TransactionDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                "INSERT INTO Transaction (Date, ID_Transaction, Beneficiaire, Montant, Etat, YEAR) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setDate(1, transaction.getDate());
            preparedStatement.setString(2, transaction.getIdTransaction());
            preparedStatement.setString(3, transaction.getBeneficiaire());
            preparedStatement.setDouble(4, transaction.getMontant());
            preparedStatement.setString(5, transaction.getEtat());
            preparedStatement.setInt(6, transaction.getYear());

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

    @Override
    public List<Transaction> getAllTransactions(int year) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT Date, ID_Transaction, Beneficiaire, Montant, Etat, YEAR FROM Transaction WHERE YEAR = ?;");
            preparedStatement.setInt(1, year);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                    resultSet.getDate("Date"),
                    resultSet.getString("ID_Transaction"),
                    resultSet.getString("Beneficiaire"),
                    resultSet.getDouble("Montant"),
                    resultSet.getString("Etat"),
                    resultSet.getInt("YEAR")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return transactions;
    }

    @Override
    public boolean deleteTransaction(Transaction transaction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Transaction WHERE ID_Transaction = ?;");
            preparedStatement.setString(1, transaction.getIdTransaction());

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                deleted = true;
            }
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

        return deleted;
    }
    @Override
    
    public List<String> getAllYears() {
        List<String> years = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT DISTINCT YEAR FROM Transaction;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int year = resultSet.getInt("YEAR");
                years.add(String.valueOf(year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return years;
    }
    public boolean updateTransactionStateToPaid(String transactionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean updated = false;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Transaction SET Etat = 'Payé' WHERE ID_Transaction = ?;");
            preparedStatement.setString(1, transactionId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                updated = true;
            }
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

        return updated;
    }
    public Transaction getTransactionById(String transactionId) {
        Transaction transaction = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT Date, Beneficiaire, Montant, Etat, YEAR FROM Transaction WHERE ID_Transaction = ?;");
            preparedStatement.setString(1, transactionId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                transaction = new Transaction(
                    resultSet.getDate("Date"),
                    transactionId,
                    resultSet.getString("Beneficiaire"),
                    resultSet.getDouble("Montant"),
                    resultSet.getString("Etat"),
                    resultSet.getInt("YEAR")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return transaction;
    }




}
