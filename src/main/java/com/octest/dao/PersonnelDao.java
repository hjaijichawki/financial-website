package com.octest.dao;

import java.util.List;
import com.octest.beans.Personnel;

public interface PersonnelDao {
    void addPersonnel(Personnel personnel);
    List<Personnel> getAllPersonnel();
    Personnel getPersonnelById(String id);
    boolean updatePersonnel(Personnel personnel);
    boolean deletePersonnel(String id);
    public List<Personnel> getPersonnelByYear(int year) ;

}