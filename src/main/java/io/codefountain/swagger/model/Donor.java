package io.codefountain.swagger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long donorId;
    private String firstName;
    private String lastName;
    private String bloodGroup;
    private String contactNo;
    private boolean isFirstTimeDonor;
    private LocalDate dateOfBirth;
    private String emergencyContactNo;


    public long getDonorId() {
        return donorId;
    }

    public void setDonorId(long donorId) {
        this.donorId = donorId;
    }

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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public boolean isFirstTimeDonor() {
        return isFirstTimeDonor;
    }

    public void setFirstTimeDonor(boolean firstTimeDonor) {
        isFirstTimeDonor = firstTimeDonor;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donorId=" + donorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", isFirstTimeDonor=" + isFirstTimeDonor +
                ", dateOfBirth=" + dateOfBirth +
                ", emergencyContactNo='" + emergencyContactNo + '\'' +
                '}';
    }
}
