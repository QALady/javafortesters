package com.javafortesters.tests;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactsData implements Comparable<ContactsData> {
    private static Logger LOG = LoggerFactory.getLogger(ContactsData.class.getName());

    private String firstname;
    private String lastname;
    private String address;
    private String homeNumber;
    private String phoneNumber;
    private String email;
    private String groupID;
    private String day;
    private String month;
    private String year;

    public ContactsData(String firstname, String lastname, String address, String homeNumber, String phoneNumber, String email, String groupID, String day, String month, String year) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.groupID = groupID;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public ContactsData() {
        this.firstname = "";
        this.lastname = "";
        this.homeNumber = "";
        this.email = "";
    }

    public ContactsData(String name) {
        this();//contructor call from constuctor
        this.firstname = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactsData{");
        sb.append("firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", homeNumber='").append(homeNumber).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", groupID='").append(groupID).append('\'');
        sb.append(", day='").append(day).append('\'');
        sb.append(", month='").append(month).append('\'');
        sb.append(", year='").append(year).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsData that = (ContactsData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (homeNumber != null ? !homeNumber.equals(that.homeNumber) : that.homeNumber != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (homeNumber != null ? homeNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(ContactsData other) {
        int compareTo1 = StringUtils.defaultString(this.lastname).toLowerCase()
                .compareTo(StringUtils.defaultString(other.lastname).toLowerCase());
        return compareTo1 != 0 ? compareTo1 : StringUtils.defaultString(this.firstname).toLowerCase()
                .compareTo(StringUtils.defaultString(other.firstname).toLowerCase());
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = String.valueOf(homeNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = String.valueOf(phoneNumber);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public void setDay(int day) {
        this.day = String.valueOf(day);
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = String.valueOf(year);
    }

    public enum Months {January, February, March, April, May, June, July, August, September, October, November, December}
}
