package com.octest.dao;


import java.util.List;

import com.octest.beans.Outils;

public interface OutilsDao {
    List<Outils> getAllOutils();
    Outils getOutilsById(String id);
    boolean addOutils(Outils outils);
    boolean updateOutils(Outils outils); 
    boolean deleteOutils(String id);
    List<Outils> getOutilsByYear(int year);
}
