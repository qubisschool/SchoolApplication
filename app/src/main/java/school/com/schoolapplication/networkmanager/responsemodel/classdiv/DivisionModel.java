package school.com.schoolapplication.networkmanager.responsemodel.classdiv;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class DivisionModel {
    @SerializedName("ClassName")
    private String className;

    @SerializedName("Division")
    private String division;

    @SerializedName("ID_ClassDetails")
    private String classDetailsId;

    public String getClassName() {
        return className;
    }

    public String getDivision() {
        return division;
    }

    public String getClassDetailsId() {
        return classDetailsId;
    }
}
