package guru.qa.filetest.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public String name;
    public String id;
    public ArrayList<String> role;
    public int age;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public String getDoj() {
        return doj;
    }

    public boolean isMarried() {
        return married;
    }

    public Address getAddress() {
        return address;
    }

    public String doj;
    public boolean married;
    public Address address;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", role=" + role +
                ", age=" + age +
                ", doj='" + doj + '\'' +
                ", married=" + married +
                ", address=" + address +
                '}';
    }

    public static class Address{
        public String street;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public String city;
        public String country;

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

}
