import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HostelApplicationGUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel nameLabel, courseLabel, addressLabel, emailLabel, mobileNoLabel, aadharNoLabel, fatherNameLabel, motherNameLabel, occupationLabel, incomeLabel, stateLabel, districtLabel, collegeLabel, hostelLabel, genderLabel;
    private JTextField nameField, courseField, addressField, emailField, mobileNoField, aadharNoField, fatherNameField, motherNameField, occupationField, incomeField;
    private JComboBox<String> stateComboBox, districtComboBox, collegeComboBox, hostelComboBox, genderComboBox;
    private JButton submitButton;

    private static java.util.List<Student> acceptedStudents = new ArrayList<>();
    private static java.util.List<Student> rejectedStudents = new ArrayList<>();
    private static Map<String, java.util.List<String>> districtColleges = new HashMap<>();
    private static Map<String, java.util.List<String>> collegeMaleHostels = new HashMap<>();
    private static Map<String, java.util.List<String>> collegeFemaleHostels = new HashMap<>();
    private static Map<String, Integer> hostelCapacity = new HashMap<>();

    private final String[] states = {"Karnataka"};
    private final String[] districts = {"Bangalore", "DAKSHINA KANNADA", "Mysore", "Hassan"};
    private final String[] colleges = {"Bengaluru University", "Jain (Deemed-to-be University)", "New Horizon College of Engineering",
            "Dayananda Sagar University", "St Aloysius College (Deemed to be university)", "Mangalore University", "St Joseph's Engineering College", "St. Agnes College(Autonomous)", "Maharaja Institute Of Technology Thandavapura", "St Philomena's College", "Amity University", "University of Mysore"};
    private final String[] maleHostels = {"Hostel1", "Hostel2", "Hostel3", "Hostel4", "Hostel5", "Hostel6", "Hostel7", "Hostel8"};
    private final String[] femaleHostels = {"Minority Hostel for Women", "University Women Hostel1", "BCM Women Hostel1",
            "Minority Women Hostel1", "BCM Girls Hostel2", "Minority Women Hostel", "BCM Women Hostel3", "Minority Women Hostel2", "BCM Women Hostel, Joythi", "Minority Women Hostel, Hampanakata", "BCM Women Hostel, Asegoli", "University women Hostel, Natekala", "Minority Women Hostel, Vamanjure", "BCM Girls Hostel, Kadri", "Womens Hostel, Kanakanady", "Govt Women Hostel, Nanthur", "Girls Hostel1", "Hostel Girls BCM", "University Women Hostel3", "Minority Women Hostel Hostel", "Nayana Womens Hostel", "BCM Women Hostel5", "Girls Hostel", "Minority Women Hostel6"};

    public HostelApplicationGUI() {
        frame = new JFrame("Hostel Application");
        panel = new JPanel();
        panel.setLayout(new GridLayout(16, 2, 10, 10));

        nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateName();
            }
        });

        courseLabel = new JLabel("Course:");
        courseField = new JTextField();
        courseField.setPreferredSize(new Dimension(200, 30));

        addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(200, 30));

        emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateEmail();
            }
        });

        mobileNoLabel = new JLabel("Mobile No:");
        mobileNoField = new JTextField();
        mobileNoField.setPreferredSize(new Dimension(200, 30));
        mobileNoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateMobileNo();
            }
        });

        aadharNoLabel = new JLabel("Aadhar No:");
        aadharNoField = new JTextField();
        aadharNoField.setPreferredSize(new Dimension(200, 30));
        aadharNoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateAadharNo();
            }
        });

        fatherNameLabel = new JLabel("Father's Name:");
        fatherNameField = new JTextField();
        fatherNameField.setPreferredSize(new Dimension(200, 30));

        motherNameLabel = new JLabel("Mother's Name:");
        motherNameField = new JTextField();
        motherNameField.setPreferredSize(new Dimension(200, 30));

        occupationLabel = new JLabel("Occupation:");
        occupationField = new JTextField();
        occupationField.setPreferredSize(new Dimension(200, 30));

        incomeLabel = new JLabel("Income:");
        incomeField = new JTextField();
        incomeField.setPreferredSize(new Dimension(200, 30));

        stateLabel = new JLabel("State:");
        stateComboBox = new JComboBox<>(states);
        stateComboBox.setPreferredSize(new Dimension(200, 30));

        districtLabel = new JLabel("District:");
        districtComboBox = new JComboBox<>(districts);
        districtComboBox.setPreferredSize(new Dimension(200, 30));
        districtComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCollegeComboBox();
            }
        });

        collegeLabel = new JLabel("College:");
        collegeComboBox = new JComboBox<>();
        collegeComboBox.setPreferredSize(new Dimension(200, 30));
        collegeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHostelComboBox();
            }
        });

        hostelLabel = new JLabel("Hostel:");
        hostelComboBox = new JComboBox<>();
        hostelComboBox.setPreferredSize(new Dimension(200, 30));

        genderLabel = new JLabel("Gender:");
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setPreferredSize(new Dimension(200, 30));
        genderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHostelComboBox();
            }
        });

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setPreferredSize(new Dimension(200, 40));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(courseLabel);
        panel.add(courseField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(mobileNoLabel);
        panel.add(mobileNoField);
        panel.add(aadharNoLabel);
        panel.add(aadharNoField);
        panel.add(fatherNameLabel);
        panel.add(fatherNameField);
        panel.add(motherNameLabel);
        panel.add(motherNameField);
        panel.add(occupationLabel);
        panel.add(occupationField);
        panel.add(incomeLabel);
        panel.add(incomeField);
        panel.add(stateLabel);
        panel.add(stateComboBox);
        panel.add(districtLabel);
        panel.add(districtComboBox);
        panel.add(collegeLabel);
        panel.add(collegeComboBox);
        panel.add(hostelLabel);
        panel.add(hostelComboBox);
        panel.add(genderLabel);
        panel.add(genderComboBox);
        panel.add(submitButton);

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        initializeData();
    }

    private void initializeData() {
        for (String district : districts) {
            districtColleges.put(district, new ArrayList<>());
        }

        districtColleges.get("Bangalore").add("Bengaluru University");
        districtColleges.get("Bangalore").add("Jain (Deemed-to-be University)");
        districtColleges.get("Bangalore").add("New Horizon College of Engineering");
        districtColleges.get("Bangalore").add("Dayananda Sagar University");
        districtColleges.get("DAKSHINA KANNADA").add("St Aloysius College (Deemed to be university)");
        districtColleges.get("DAKSHINA KANNADA").add("Mangalore University");
        districtColleges.get("DAKSHINA KANNADA").add("St Joseph's Engineering College");
        districtColleges.get("DAKSHINA KANNADA").add("St. Agnes College(Autonomous)");
        districtColleges.get("Mysore").add("Maharaja Institute Of Technology Thandavapura");
        districtColleges.get("Mysore").add("St Philomena's College");
        districtColleges.get("Mysore").add("Amity University");
        districtColleges.get("Mysore").add("University of Mysore");

        for (String college : colleges) {
            collegeMaleHostels.put(college, new ArrayList<>());
            collegeFemaleHostels.put(college, new ArrayList<>());
        }

        // Male Hostels Initialization
        collegeMaleHostels.get("Bengaluru University").add("Hostel1");
        collegeMaleHostels.get("Bengaluru University").add("Hostel2");
        collegeMaleHostels.get("Jain (Deemed-to-be University)").add("Hostel3");
        collegeMaleHostels.get("New Horizon College of Engineering").add("Hostel4");
        collegeMaleHostels.get("Dayananda Sagar University").add("Hostel5");
        collegeMaleHostels.get("St Aloysius College (Deemed to be university)").add("Hostel6");
        collegeMaleHostels.get("Mangalore University").add("Hostel7");
        collegeMaleHostels.get("St Joseph's Engineering College").add("Hostel8");
        collegeMaleHostels.get("St. Agnes College(Autonomous)").add("BCM Hostel, Joythi");

        // Female Hostels Initialization
        collegeFemaleHostels.get("Bengaluru University").add("Minority Hostel for Women");
        collegeFemaleHostels.get("Bengaluru University").add("University Women Hostel1");
        collegeFemaleHostels.get("Jain (Deemed-to-be University)").add("BCM Women Hostel1");
        collegeFemaleHostels.get("New Horizon College of Engineering").add("Minority Women Hostel1");
        collegeFemaleHostels.get("Dayananda Sagar University").add("BCM Girls Hostel2");
        collegeFemaleHostels.get("St Aloysius College (Deemed to be university)").add("Minority Women Hostel");
        collegeFemaleHostels.get("Mangalore University").add("BCM Women Hostel3");
        collegeFemaleHostels.get("St Joseph's Engineering College").add("Minority Women Hostel2");
        collegeFemaleHostels.get("St. Agnes College(Autonomous)").add("BCM Women Hostel, Joythi");
        collegeFemaleHostels.get("Maharaja Institute Of Technology Thandavapura").add("Minority Women Hostel, Hampanakata");
        collegeFemaleHostels.get("St Philomena's College").add("BCM Women Hostel, Asegoli");
        collegeFemaleHostels.get("Amity University").add("University women Hostel, Natekala");
        collegeFemaleHostels.get("University of Mysore").add("Minority Women Hostel, Vamanjure");
        collegeFemaleHostels.get("St Joseph's Engineering College").add("BCM Girls Hostel, Kadri");
        collegeFemaleHostels.get("Mangalore University").add("Womens Hostel, Kanakanady");
        collegeFemaleHostels.get("St Aloysius College (Deemed to be university)").add("Govt Women Hostel, Nanthur");
        collegeFemaleHostels.get("St. Agnes College(Autonomous)").add("Girls Hostel1");
        collegeFemaleHostels.get("Maharaja Institute Of Technology Thandavapura").add("Hostel Girls BCM");
        collegeFemaleHostels.get("St Philomena's College").add("University Women Hostel3");
        collegeFemaleHostels.get("Amity University").add("Minority Women Hostel Hostel");
        collegeFemaleHostels.get("University of Mysore").add("Nayana Womens Hostel");
        collegeFemaleHostels.get("St Joseph's Engineering College").add("BCM Women Hostel5");
        collegeFemaleHostels.get("Mangalore University").add("Girls Hostel");
        collegeFemaleHostels.get("St Aloysius College (Deemed to be university)").add("Minority Women Hostel6");

        for (String hostel : maleHostels) {
            hostelCapacity.put(hostel, 5);
        }

        for (String hostel : femaleHostels) {
            hostelCapacity.put(hostel, 5);
        }
    }

    private void updateCollegeComboBox() {
        String selectedDistrict = (String) districtComboBox.getSelectedItem();
        collegeComboBox.removeAllItems();
        if (selectedDistrict != null) {
            for (String college : districtColleges.get(selectedDistrict)) {
                collegeComboBox.addItem(college);
            }
        }
        updateHostelComboBox();
    }

    private void updateHostelComboBox() {
        String selectedCollege = (String) collegeComboBox.getSelectedItem();
        String selectedGender = (String) genderComboBox.getSelectedItem();
        hostelComboBox.removeAllItems();
        if (selectedCollege != null && selectedGender != null) {
            java.util.List<String> hostels = selectedGender.equals("Male") ? collegeMaleHostels.get(selectedCollege) : collegeFemaleHostels.get(selectedCollege);
            for (String hostel : hostels) {
                hostelComboBox.addItem(hostel);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String course = courseField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String mobileNo = mobileNoField.getText();
        String aadharNo = aadharNoField.getText();
        String fatherName = fatherNameField.getText();
        String motherName = motherNameField.getText();
        String occupation = occupationField.getText();
        double income = Double.parseDouble(incomeField.getText());
        String state = (String) stateComboBox.getSelectedItem();
        String district = (String) districtComboBox.getSelectedItem();
        String college = (String) collegeComboBox.getSelectedItem();
        String hostel = (String) hostelComboBox.getSelectedItem();
        String gender = (String) genderComboBox.getSelectedItem();

        if (isInputValid(name, email, mobileNo, aadharNo)) {
            Student student = new Student(name, course, address, email, mobileNo, aadharNo, fatherName, motherName, occupation, income, state, district, college, hostel, gender);

            if (income <= 500000 && hostelCapacity.get(hostel) > 0) {
                acceptedStudents.add(student);
                hostelCapacity.put(hostel, hostelCapacity.get(hostel) - 1);
                saveToFile("accepted_students.txt", student);
                JOptionPane.showMessageDialog(frame, "Student accepted.");
                System.exit(0);
            } else {
                rejectedStudents.add(student);
                saveToFile("rejected_students.txt", student);
                JOptionPane.showMessageDialog(frame, "Student rejected.");
                System.exit(0);
            }
        }
    }

    private boolean isInputValid(String name, String email, String mobileNo, String aadharNo) {
        return validateName() && validateEmail() && validateMobileNo() && validateAadharNo();
    }

    private boolean validateName() {
        String name = nameField.getText();
        if (name.matches("[a-zA-Z ]+")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid name. Only alphabetic characters and spaces are allowed.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean validateEmail() {
        String email = emailField.getText();
        if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean validateMobileNo() {
        String mobileNo = mobileNoField.getText();
        if (mobileNo.matches("[0-9]{10}")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid mobile number. It should be 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean validateAadharNo() {
        String aadharNo = aadharNoField.getText();
        if (aadharNo.matches("\\d{12}")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Aadhar number. It should be 12 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void saveToFile(String fileName, Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("\nName:"+student.getName() + "\nCourse:" + student.getCourse() + "\nAddress:" + "\nGender" + student.getGender()+ student.getAddress() + "\nEmail:" + student.getEmail() + "\nMobile NO"
                    + student.getMobileNo() + "\nAadharNO:" + student.getAadharNo() + "\nFather name:" + student.getFatherName() + "\nMother Name:" + student.getMotherName() + "\nCollege:"
                    + student.getOccupation() + "," + student.getIncome() + "," + student.getState() + "," + student.getDistrict() + ","
                    + student.getCollege() + "\nHostel:" + student.getHostel() + "\n-------------------------------");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving to file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new HostelApplicationGUI();
    }
}
