package school.com.schoolapplication.networkmanager;

import com.google.gson.annotations.SerializedName;

import school.com.schoolapplication.home.mark.ExamDetailsInfo;
import school.com.schoolapplication.home.mark.Exminfo;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/14/2018.
 */
public class NetworkResponseParent {
    @SerializedName("Exminfo")
    private Exminfo exminfo;
    @SerializedName("statuscode")
    private int statusCode;
    @SerializedName("statusmessage")
    private String statusMessage;
    @SerializedName("Exmdtlsinfo")
    private ExamDetailsInfo examDetailsInfo;

    public Exminfo getExminfo() {
        return exminfo;
    }
    public ExamDetailsInfo getExamDetailsInfo(){
        return examDetailsInfo;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
