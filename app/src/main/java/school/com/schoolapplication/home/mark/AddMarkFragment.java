package school.com.schoolapplication.home.mark;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import school.com.schoolapplication.R;
import school.com.schoolapplication.home.TeacherHomeFragment;
import school.com.schoolapplication.networkmanager.NetworkConnectionCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseCustom;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.AttendInfoParent;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.StudentAttendanceModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMarkFragment extends Fragment {
    private RecyclerView recyclerView;
    private static final String EXAM_INFO = "examInfo";
    private static final String SUBJECT_DET = "subject-det";
    private static final String DIV = "subject-det";
    ExamDetailsInfo examDetailsInfo;
    String fkSUbject;
    AddMarkAdapter addMarkAdapter;
    String div ;
    public AddMarkFragment() {
        // Required empty public constructor
    }

    public static AddMarkFragment getInstance(ExamDetailsInfo examDetailsInfo, String fkSubject, String div ){
        AddMarkFragment addMarkFragment = new AddMarkFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable( EXAM_INFO, examDetailsInfo );
        bundle.putString( SUBJECT_DET, fkSubject );
        bundle.putString( DIV, div);
        addMarkFragment.setArguments( bundle );
        return addMarkFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_mark, container, false);
        recyclerView = view.findViewById(R.id.recy_add_mark);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        Bundle bundle = getArguments();
        examDetailsInfo = bundle.getParcelable( EXAM_INFO );
        fkSUbject = bundle.getString( SUBJECT_DET );
        div = bundle.getString( DIV );
        getStudentList();
        view.findViewById( R.id.submit ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMark();
            }
        });
        return view;
    }
    private void getStudentList(){
        String url = "http://test.qubisapps.com/api/Student/listStudents";
        Map<String,String > param = new HashMap<>();
        param.put("ID_Class", examDetailsInfo.getFkClass()  );
        param.put("Division", div );
        NetworkConnectionCustom.getInstance().volleyPosting(url, param,getContext() ,new NetworkResponseCustom() {
            @Override
            public void onSuccess(String response) {
                AttendInfoParent attendInfoParent =
                        new Gson().fromJson( response, AttendInfoParent.class );
                List<StudentAttendanceModel> studentAttendanceModelList = attendInfoParent.getAttendanceInfo().
                        getStudentAttendanceModelList();

                addMarkAdapter = new AddMarkAdapter( studentAttendanceModelList );
                recyclerView.setAdapter( addMarkAdapter );
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
    private void submitMark(){
        if ( addMarkAdapter != null ){
            List<StudentAttendanceModel> studentAttendanceModelList =
                    addMarkAdapter.getMarkAndStudent();
            try{
                String url = "http://test.qubisapps.com/api/Exam/UpdateMark";

                JSONArray jsonArray = new JSONArray();
                for (StudentAttendanceModel studentAttModel : studentAttendanceModelList ) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ID_Registration", studentAttModel.getRegisrationId() );
                    jsonObject.put("Mark", studentAttModel.getMark() );
                    jsonArray.put( jsonObject );
                }
                String jsonString = jsonArray.toString();
                Map<String, String > param = new HashMap<>();
                param.put("mrklist", jsonString );
                param.put("ID_User", "1");
                param.put("FK_Class", examDetailsInfo.getFkClass() );
                param.put("division", div );
                param.put("FK_Exam", examDetailsInfo.getIdExam() );
                param.put("FK_Subject", fkSUbject );
                NetworkConnectionCustom.getInstance().volleyPosting(url, param, getContext(), new NetworkResponseCustom() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText( getContext(), "Mark added successfully", Toast.LENGTH_LONG ).show();
                        getFragmentManager().beginTransaction().replace(R.id.frame_home_container, new TeacherHomeFragment() )
                                .commit();
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });

            }catch ( JSONException e){

            }
        }
    }

}
