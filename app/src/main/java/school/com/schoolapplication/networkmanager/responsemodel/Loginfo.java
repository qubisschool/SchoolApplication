package school.com.schoolapplication.networkmanager.responsemodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class Loginfo {
    @SerializedName("ID_User")
    private int userId;
    @SerializedName("ResponseCode")
    private int responseCode;
    @SerializedName("ResponseMessage")
    private String responseMessage;

    public int getUserId() {
        return userId;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
