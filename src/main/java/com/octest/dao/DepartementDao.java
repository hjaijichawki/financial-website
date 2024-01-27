package com.octest.dao;

import java.util.List;

import com.octest.beans.Departement;

public interface DepartementDao {
    void add(Departement departement);
    void update(Departement departement);
    void delete(int D_id);
    Departement getDepartementById(int D_id);
    List<Departement> getAllDepartements();
}