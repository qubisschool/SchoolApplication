package school.com.schoolapplication.networkmanager.responsemodel.attendance;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/21/2018.
 */
public class AttendInfoParent {
    public AttendanceInfo getAttendanceInfo() {
        return attendanceInfo;
    }

    @SerializedName("attndinfo")
    private AttendanceInfo attendanceInfo;
}
