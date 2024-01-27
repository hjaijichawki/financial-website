package com.octest.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Outils;

public class OutilsDaoImpl implements OutilsDao {

    private DaoFactory daoFactory;

    public OutilsDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Outils> getAllOutils() {
        List<Outils> outilsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Outils;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Outils outils = new Outils(
                    resultSet.getString("O_ID"),
                    resultSet.getFloat("Prix"),
                    resultSet.getString("Appartenance"),
                    resultSet.getInt("Year")
                );
                outilsList.add(outils);
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

        return outilsList;
    }

    @Override
    public Outils getOutilsById(String id) {
        Outils outils = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Outils WHERE O_ID = ?;");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                outils = new Outils(
                    resultSet.getString("O_ID"),
                    resultSet.getFloat("Prix"),
                    resultSet.getString("Appartenance"),
                    resultSet.getInt("Year")
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

        return outils;
    }
    @Override
    public boolean addOutils(Outils outils) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                "INSERT INTO Outils (O_ID, Prix, Appartenance, Year) VALUES (?, ?, ?, ?);"
            );
            preparedStatement.setString(1, outils.getId());
            preparedStatement.setFloat(2, outils.getPrix());
            preparedStatement.setString(3, outils.getAppartenance());
            preparedStatement.setInt(4, outils.getYear());

            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection, statement and result set
            try { preparedStatement.close(); } catch (Exception e) { }
            try { connection.close(); } catch (Exception e) { }
        }

        return false;
    }



        @Override
        public List<Outils> getOutilsByYear(int year) {
            List<Outils> outilsList = new ArrayList<>();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = daoFactory.getConnection();
                preparedStatement = connection.prepareStatement("SELECT * FROM Outils WHERE Year = ?");
                preparedStatement.setInt(1, year);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Outils outils = new Outils(
                        resultSet.getString("O_ID"),
                        resultSet.getFloat("Prix"),
                        resultSet.getString("Appartenance"),
                        resultSet.getInt("Year")
                    );
                    outilsList.add(outils);
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

            return outilsList;
        }
        
        @Override
        public boolean deleteOutils(String id) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            boolean rowDeleted = false;
            
            try {
                connection = daoFactory.getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM Outils WHERE O_ID = ?;");
                preparedStatement.setString(1, id);
                
                rowDeleted = preparedStatement.executeUpdate() > 0;
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
            
            return rowDeleted;
        }
        @Override
        public boolean updateOutils(Outils outils) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            boolean updated = false;

            try {
                connection = daoFactory.getConnection();
                preparedStatement = connection.prepareStatement("UPDATE Outils SET Prix = ?, Appartenance = ?, Year = ? WHERE O_ID = ?");
                preparedStatement.setFloat(1, outils.getPrix());
                preparedStatement.setString(2, outils.getAppartenance());
                preparedStatement.setInt(3, outils.getYear());
                preparedStatement.setString(4, outils.getId());
                updated = preparedStatement.executeUpdate() > 0;
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
                }
            }
         

            return updated;
        }
    }


