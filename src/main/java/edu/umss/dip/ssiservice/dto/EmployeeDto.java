package edu.umss.dip.ssiservice.dto;

import edu.umss.dip.ssiservice.model.Contract;
import edu.umss.dip.ssiservice.model.Employee;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;

public class EmployeeDto extends DtoBase<Employee> {
    private String firstName;
    private String lastName;
    private String image;
    private String jobPosition;
    private String jobCode;
    private Boolean featured;
    private String jobDescription;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    public EmployeeDto toDto(Employee employee, ModelMapper mapper) {
        super.toDto(employee, mapper);
        for (Contract contract : employee.getContracts()) {
            jobPosition = contract.getPosition().getName();
            jobCode = contract.getPosition().getName();
        }
        featured = true;
        setImageBase64(employee);
        //jobDescription = "Descripcion de job";
        return this;
    }

    private void setImageBase64(Employee employee) {
        if (employee.getImage() != null) {
            byte[] bytes = new byte[employee.getImage().length];
            for (int i = 0; i < employee.getImage().length; i++) {
                bytes[i] = employee.getImage()[i];
            }
            String imageStr = Base64.encodeBase64String(bytes);
            this.setImage(imageStr);

        }
    }
}
