package school.com.schoolapplication.home.mark;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/14/2018.
 */
public class Exminfo {
    @SerializedName("Exmlist")
    private List<ExamModel> examModelList;

    @SerializedName("ResponseCode")
    private String responseCode;

    @SerializedName("ResponseMessage")
    private String responseMessage;

    public List<ExamModel> getExamModelList() {
        return examModelList;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
