package com.octest.dao;

import java.util.List;
import com.octest.beans.User;

public interface UserDao {
    void addUser(User user);
    List<User> getUsers();
    boolean deleteUser(User user);
    boolean checkUser(User user);
    public boolean UserAuth(User user);
    boolean updateUserPassword(String userId, String newPassword);
    public User getUser(String email, String password) ;
    public boolean updateUserDetails(String userId, String newName, String newFullName, String newEmail) ;


}
