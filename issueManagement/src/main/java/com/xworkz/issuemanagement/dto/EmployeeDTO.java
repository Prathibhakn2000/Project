package com.xworkz.issuemanagement.dto;

import javax.persistence.*;

@Entity
@Table(name="employee_details")
public class EmployeeDTO {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "employee_id" )
private  int employeeId;

@Column(name = "employee_name" )
private String empFullName;

@Column(name = "employee_designation" )
private String designation;

@Column(name = "employee_dept_type" )
private String departmentType;

@Column(name = "employee_email" )
private String email;

@Column(name = "employee_contactnumber" )
private Long contactNumber;

@Column(name = "employee_address" )
private String address;

@ManyToOne
@JoinColumn(name = "department_id" )
private DepartmentDTO deptId;

@Column(name = "employee_status" )
private String status;

@Column(name = "employee_otp" )
private Long otp;

   public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employee) {
        this.employeeId = employee;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DepartmentDTO getDeptId() {
        return deptId;
    }

    public void setDeptId(DepartmentDTO deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOtp() {
        return otp;
    }

    public void setOtp(Long otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", empFullName='" + empFullName + '\'' +
                ", designation='" + designation + '\'' +
                ", departmentType='" + departmentType + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber=" + contactNumber +
                ", address='" + address + '\'' +
                ", deptId=" + deptId +
                ", status='" + status + '\'' +
                ", otp=" + otp +
                '}';
    }
}
