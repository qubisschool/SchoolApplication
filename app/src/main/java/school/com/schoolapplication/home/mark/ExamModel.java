package school.com.schoolapplication.home.mark;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/14/2018.
 */
public class ExamModel {
    @SerializedName("ExamName")
    private String examName;

    @SerializedName("ID_Exam")
    private String examId;

    public String getExamName() {
        return examName;
    }

    public String getExamId() {
        return examId;
    }
}
