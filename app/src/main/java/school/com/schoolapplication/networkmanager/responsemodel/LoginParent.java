package school.com.schoolapplication.networkmanager.responsemodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class LoginParent {

    @SerializedName("loginfo")
    private Loginfo loginfo;

    @SerializedName("statuscode")
    private int statusCode;

    @SerializedName("statusmessage")
    private String statusMessage;

    public Loginfo getLoginfo() {
        return loginfo;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
