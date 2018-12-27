package school.com.schoolapplication.home.mark;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/21/2018.
 */
public class ExamDetailsInfo implements Parcelable {
    @SerializedName("ClassName")
    private String className;

    @SerializedName("FK_Class")
    private String fkClass;

    @SerializedName("Division")
    private String division;

    @SerializedName("ID_Exam")
    private String idExam;

    @SerializedName("subdetailsList")
    private List<SubjectDetails> subjectDetails;

    @SerializedName("ResponseCode")
    private String responseCode;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFkClass() {
        return fkClass;
    }

    public void setFkClass(String fkClass) {
        this.fkClass = fkClass;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }


    public List<SubjectDetails> getSubjectDetails() {
        return subjectDetails;
    }

    public void setSubjectDetails(List<SubjectDetails> subjectDetails) {
        this.subjectDetails = subjectDetails;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @SerializedName("ResponseMessage")
    private String responseMessage;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.className);
        dest.writeString(this.fkClass);
        dest.writeString(this.division);
        dest.writeString(this.idExam);
        dest.writeTypedList(this.subjectDetails);
        dest.writeString(this.responseCode);
        dest.writeString(this.responseMessage);
    }

    public ExamDetailsInfo() {
    }

    protected ExamDetailsInfo(Parcel in) {
        this.className = in.readString();
        this.fkClass = in.readString();
        this.division = in.readString();
        this.idExam = in.readString();
        this.subjectDetails = in.createTypedArrayList(SubjectDetails.CREATOR);
        this.responseCode = in.readString();
        this.responseMessage = in.readString();
    }

    public static final Parcelable.Creator<ExamDetailsInfo> CREATOR = new Parcelable.Creator<ExamDetailsInfo>() {
        @Override
        public ExamDetailsInfo createFromParcel(Parcel source) {
            return new ExamDetailsInfo(source);
        }

        @Override
        public ExamDetailsInfo[] newArray(int size) {
            return new ExamDetailsInfo[size];
        }
    };
}
