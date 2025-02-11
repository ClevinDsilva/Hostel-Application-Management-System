public class Student {
    private String name;
    private String course;
    private String address;
    private String email;
    private String mobileNo;
    private String aadharNo;
    private String fatherName;
    private String motherName;
    private String occupation;
    private double income;
    private String state;
    private String district;
    private String college;
    private String hostel;
    private String gender;

    public Student(String name, String course, String address, String email, String mobileNo, String aadharNo, String fatherName, String motherName, String occupation, double income, String state, String district, String college, String hostel, String gender) {
        this.name = name;
        this.course = course;
        this.address = address;
        this.email = email;
        this.mobileNo = mobileNo;
        this.aadharNo = aadharNo;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.occupation = occupation;
        this.income = income;
        this.state = state;
        this.district = district;
        this.college = college;
        this.hostel = hostel;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getOccupation() {
        return occupation;
    }

    public double getIncome() {
        return income;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getCollege() {
        return college;
    }

    public String getHostel() {
        return hostel;
    }

    public String getGender() {
        return gender;
    }
}
