package school.com.schoolapplication.networkmanager.responsemodel.attendance;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class AttendanceInfo {
    @SerializedName("atndlist")
    private List<StudentAttendanceModel> studentAttendanceModelList;
    @SerializedName("statuscode")
    private String statusCode;
    @SerializedName("statusmessage")
    private String statusMessage;

    public List<StudentAttendanceModel> getStudentAttendanceModelList() {
        return studentAttendanceModelList;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
