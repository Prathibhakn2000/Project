package com.xworkz.issuemanagement.dto;

import javax.persistence.*;

@Entity
@Table(name="department")
public class DepartmentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id" )
    private  int deptId;

    @Column(name = "department_type" )
    private String departmentType;

    @Column(name = "department_address" )
    private  String departmentAddress;

    @Column(name = "department_city" )
    private String departmentCity;

    @Column(name = "no_of_employee" )
    private int noOfEmployee;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCity() {
        return departmentCity;
    }

    public void setDepartmentCity(String departmentCity) {
        this.departmentCity = departmentCity;
    }

    public int getNoOfEmployee() {
        return noOfEmployee;
    }

    public void setNoOfEmployee(int noOfEmployee) {
        this.noOfEmployee = noOfEmployee;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "deptId=" + deptId +
                ", departmentType='" + departmentType + '\'' +
                ", departmentAddress='" + departmentAddress + '\'' +
                ", departmentCity='" + departmentCity + '\'' +
                ", noOfEmployee=" + noOfEmployee +
                '}';
    }
}
