package school.com.schoolapplication.networkmanager.responsemodel.attendance;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class StudentAttendanceModel {
    @SerializedName("StudentIDNumber")
    private String studentIdNumber;
    @SerializedName("Name")
    private String name;
    @SerializedName("ID_Registration")
    private String regisrationId;

    public String getRegisrationId() {
        return regisrationId;
    }

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
