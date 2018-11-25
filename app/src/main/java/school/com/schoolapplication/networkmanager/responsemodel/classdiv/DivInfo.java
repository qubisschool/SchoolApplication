package school.com.schoolapplication.networkmanager.responsemodel.classdiv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class DivInfo {
    @SerializedName("divlist")
    private List<DivisionModel> divisionModelList;

    @SerializedName("ResponseCode")
    private String responseCode;

    @SerializedName("ResponseMessage")
    private String responseMessage;

    public List<DivisionModel> getDivisionModelList() {
        return divisionModelList;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
