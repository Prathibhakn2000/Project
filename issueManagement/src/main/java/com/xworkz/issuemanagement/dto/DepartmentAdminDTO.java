package com.xworkz.issuemanagement.dto;

import javax.persistence.*;

@Entity
@Table(name="department_admin")
public class DepartmentAdminDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_admin_id" )
    private int departmentAdminId;

    @Column(name = "department_admin_name" )
    private String fullName;

//    @Column(name = "department_name" )
//    private String departmentName;

    @Column(name = "admin_email" )
    private String email;

    @Column(name = "admin_contact_number" )
    private Long contactNumber;

    @Column(name = "admin_alternative_contact_number" )
    private Long alternativeContactNumber;

    @Column(name = "admin_address" )
    private String address;

    @Column(name = "password" )
    private String password;

    @Column(name = "failed_attempts")
    private int failedAttempt=0;

    public static final int Max_LOGIN_ATTEMPTS=3;
    @Column(name = "account_locked")
    private boolean accountLocked=false;

   //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "fk_department_id",referencedColumnName = "department_id") //parent...left one child
    @ManyToOne
    @JoinColumn(name = "fk_department_id" )
    private  DepartmentDTO deptId;

    @Column(name = "department_type" )
    private String departmentType;


    public int getDepartmentAdminId() {
        return departmentAdminId;
    }

    public void setDepartmentAdminId(int departmentAdminId) {
        this.departmentAdminId = departmentAdminId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Long getAlternativeContactNumber() {
        return alternativeContactNumber;
    }

    public void setAlternativeContactNumber(Long alternativeContactNumber) {
        this.alternativeContactNumber = alternativeContactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public DepartmentDTO getDeptId() {
        return deptId;
    }

    public void setDeptId(DepartmentDTO deptId) {
        this.deptId = deptId;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    @Override
    public String toString() {
        return "DepartmentAdminDTO{" +
                "departmentAdminId=" + departmentAdminId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber=" + contactNumber +
                ", alternativeContactNumber=" + alternativeContactNumber +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", failedAttempt=" + failedAttempt +
                ", accountLocked=" + accountLocked +
                ", deptId=" + deptId +
                ", departmentType='" + departmentType + '\'' +
                '}';
    }
}
