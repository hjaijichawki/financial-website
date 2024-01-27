package com.octest.dao;


import java.util.List;

import com.octest.beans.Years;

public interface YearDao {
    void addYear(Years year);
    public Years getYear(int year);
    public void updateYear(Years year) ;
    public int getMaxYear();
    public void updateYearState(int year, String newState) ;
    public List<Integer> getAllYears() ;



}
