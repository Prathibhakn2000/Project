package com.xworkz.issuemanagement.dto;

import javax.persistence.*;

@Entity
@Table(name="complaint_details")
public class RaiseComplaintDTO {

    public RaiseComplaintDTO()
    {
        System.out.println("Creating RaiseComplaintDTO");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private int complaintId;

    @Column(name = "complaint_type")
    private String complaintType;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private  String city;

    @Column(name = "area")
    private  String area;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // Example mapping assuming many images to one signup
    @JoinColumn(name = "s_id", referencedColumnName = "id") // Adjust as per your schema
    private SignUpDTO signUpDTO;

    public SignUpDTO getSignUpDTO() {
        return signUpDTO;
    }

    public void setSignUpDTO(SignUpDTO signUpDTO) {
        this.signUpDTO = signUpDTO;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne
    @JoinColumn(name = "dept_id")
    private DepartmentDTO departmentDTO;

    @Column(name = "status")
    private String status;

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeDTO employeeId;

    public EmployeeDTO getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(EmployeeDTO employeeId) {
        this.employeeId = employeeId;
    }




    @Override
    public String toString() {
        return "RaiseComplaintDTO{" +
                "complaintId=" + complaintId +
                ", complaintType='" + complaintType + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", signUpDTO=" + signUpDTO +
                ", departmentDTO=" + departmentDTO +
                ", status='" + status + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
