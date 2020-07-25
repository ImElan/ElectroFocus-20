package elan.mit.electrofocus;

public class SelectionList
{
    public String student_1;
    public String student_2;
    public String student_3;
    public String college_name;
    public String serial_no;

    public SelectionList()
    {

    }

    public SelectionList(String student_1, String student_2, String student_3,String college_name,String serial_no)
    {
        this.student_1 = student_1;
        this.student_2 = student_2;
        this.student_3 = student_3;
        this.college_name = college_name;
        this.serial_no = serial_no;
    }

    public String getStudent_1() {
        return student_1;
    }

    public void setStudent_1(String student_1) {
        this.student_1 = student_1;
    }

    public String getStudent_2() {
        return student_2;
    }

    public void setStudent_2(String student_2) {
        this.student_2 = student_2;
    }

    public String getStudent_3() {
        return student_3;
    }

    public void setStudent_3(String student_3) {
        this.student_3 = student_3;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }
}

