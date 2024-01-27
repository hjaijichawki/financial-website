package com.octest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.User;

public class UserDaoImpl implements UserDao {

    private DaoFactory daoFactory;

    public UserDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user (U_id, password, Full_name, Secret_code, Email, name) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getuId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getSecretCode());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getName());
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
    public List<User> getUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("U_id"),
                        resultSet.getString("password"),
                        resultSet.getString("Full_name"),
                        resultSet.getString("Secret_code"),
                        resultSet.getString("Email"),
                        resultSet.getString("name")
                );
                users.add(user);
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
        return users;
    }

    @Override
    public boolean deleteUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE U_id = ?");
            preparedStatement.setString(1, user.getuId());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public boolean checkUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean userExists = false;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE password = ? AND email = ?;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userExists = true;
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

        return userExists;
    }
    public boolean UserAuth(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean userExists = false;
        
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE U_id = ? AND Secret_code = ?;");
            preparedStatement.setString(1, user.getuId());
            preparedStatement.setString(2, user.getSecretCode());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close the connection and prepared statement
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
        return userExists;
    }
    public boolean updateUserPassword(String userId, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE U_id = ?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    public User getUser(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE Email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("U_id"),
                        resultSet.getString("password"),
                        resultSet.getString("Full_name"),
                        resultSet.getString("Secret_code"),
                        resultSet.getString("Email"),
                        resultSet.getString("name")
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
        return user;
    }
    public boolean updateUserDetails(String userId, String newName, String newFullName, String newEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE user SET name = ?, Full_name = ?, Email = ? WHERE U_id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newFullName);
            preparedStatement.setString(3, newEmail);
            preparedStatement.setString(4, userId);
            
            int rowsUpdated = preparedStatement.executeUpdate();
            
            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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


}
