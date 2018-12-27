package school.com.schoolapplication.home.mark;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/21/2018.
 */
public class SubjectDetails implements Parcelable {
    @SerializedName("Fk_Subject")
    private String fkSubject;

    @SerializedName("ExamDate")
    private String examDate;

    @SerializedName("SubjectName")
    private String subjectName;

    public String getFkSubject() {
        return fkSubject;
    }

    public void setFkSubject(String fkSubject) {
        this.fkSubject = fkSubject;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fkSubject);
        dest.writeString(this.examDate);
        dest.writeString(this.subjectName);
    }

    public SubjectDetails() {
    }

    protected SubjectDetails(Parcel in) {
        this.fkSubject = in.readString();
        this.examDate = in.readString();
        this.subjectName = in.readString();
    }

    public static final Parcelable.Creator<SubjectDetails> CREATOR = new Parcelable.Creator<SubjectDetails>() {
        @Override
        public SubjectDetails createFromParcel(Parcel source) {
            return new SubjectDetails(source);
        }

        @Override
        public SubjectDetails[] newArray(int size) {
            return new SubjectDetails[size];
        }
    };
}
