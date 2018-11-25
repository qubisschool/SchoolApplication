package school.com.schoolapplication.networkmanager.responsemodel.classdiv;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class DivInfoParent {
    @SerializedName("divinfo")
    private DivInfo mDivInfo;

    @SerializedName("statuscode")
    private String statusCode;

    @SerializedName("statusmessage")
    private String statusMessage;

    public DivInfo getmDivInfo() {
        return mDivInfo;
    }

    public void setmDivInfo(DivInfo mDivInfo) {
        this.mDivInfo = mDivInfo;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
