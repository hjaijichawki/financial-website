package com.octest.dao;

import java.util.List;

import com.octest.beans.Staff;

public interface StaffDao {
    void addStaff(Staff staff);
    void updateStaff(Staff staff);
    void deleteStaff(Staff staff);
    List<Staff> getAllStaff();
}
