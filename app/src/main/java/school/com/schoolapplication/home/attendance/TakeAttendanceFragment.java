package school.com.schoolapplication.home.attendance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.NetworkConnectionCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseCustom;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.AttendInfoParent;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.StudentAttendanceModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeAttendanceFragment extends Fragment implements View.OnClickListener {
    private AttendanceTakerRecyclerAdapter attendanceTakerRecyclerAdapter;
    private RecyclerView mRecyListSudents;
    private static final String CLASS_ID = "studNo";
    private static final String DIVISION = "DIV";
    private TextView mTxtPresentCount, mTxtAbsentCount;
    public TakeAttendanceFragment() {
        // Required empty public constructor
    }
    public static TakeAttendanceFragment getInstance(String classId, String division){
        TakeAttendanceFragment takeAttendanceFragment = new TakeAttendanceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CLASS_ID, classId );
        bundle.putString( DIVISION, division );
        takeAttendanceFragment.setArguments( bundle );
        return takeAttendanceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_take_attendance, container, false);
        Bundle bundle = getArguments();
        String classId = bundle.getString(CLASS_ID);
        String div       = bundle.getString( DIVISION );
        try{
            if ( !classId.isEmpty() )
                getStudentList( classId, div );
        }catch ( Exception e ){
            //Do nothing
        }
        mTxtPresentCount = view.findViewById( R.id.txt_total_present );
        mTxtAbsentCount = view.findViewById( R.id.txt_total_absent );
        mRecyListSudents = view.findViewById( R.id.recycler_attendance_marker );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyListSudents.setLayoutManager( layoutManager );
        mRecyListSudents.setItemAnimator( new DefaultItemAnimator() );

        view.findViewById( R.id.btn_present_all ).setOnClickListener( this );
        view.findViewById( R.id.btn_absent_all ).setOnClickListener( this );

        return view;
    }
    @Override
    public void onClick( View view ){
        int id = view.getId();
        if ( id == R.id.btn_present_all ){
            if ( attendanceTakerRecyclerAdapter != null ){
                attendanceTakerRecyclerAdapter.markAll( true );
            }
        }else if ( id == R.id.btn_absent_all ){
            if ( attendanceTakerRecyclerAdapter != null ){
                attendanceTakerRecyclerAdapter.markAll( false );
            }
        }
    }
    private void getStudentList(final String classId, final String div ){
        String url = "http://test.qubisapps.com/api/Student/listStudents";
        Map<String,String > param = new HashMap<>();
        param.put("ID_Class", classId );
        param.put("Division", div );
        NetworkConnectionCustom.getInstance().volleyPosting(url, param, getContext(), new NetworkResponseCustom() {
            @Override
            public void onSuccess(String response) {
                    try{
                        AttendInfoParent attendInfoParent =
                                new Gson().fromJson( response, AttendInfoParent.class );
                        List<StudentAttendanceModel> studentAttendanceModelList = attendInfoParent.getAttendanceInfo().
                                getStudentAttendanceModelList();
                        attendanceTakerRecyclerAdapter = new AttendanceTakerRecyclerAdapter(
                                studentAttendanceModelList, getContext(), new AttendanceTakerRecyclerAdapter.Marker() {
                            @Override
                            public void mark(int present, int absent) {
                                mTxtPresentCount.setText( Integer.toString(present) );
                                mTxtAbsentCount.setText( Integer.toString( absent ) );
                            }
                        }
                        );
                        mRecyListSudents.setAdapter( attendanceTakerRecyclerAdapter );

                        if ( studentAttendanceModelList.size() < 1 )
                            goBack("d","");
                    }catch ( Exception e ){
//do nothing
                    }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText( getContext(), "Internal server error occured", Toast.LENGTH_SHORT ).show();
                goBack(classId, div);
            }
        });
    }
    private void goBack(String classId, String div){
        Toast.makeText(getContext(), "No students found on this class", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove( TakeAttendanceFragment.getInstance( classId, div) );
        fragmentTransaction.commit();
        fragmentManager.popBackStack();

    }
    /*
        {"attndinfo":{
            "atndlist":[{
                "StudentIDNumber":"STM120180013","Name":"fdh","ID_Registration":13},{"StudentIDNumber":"STM120180015","Name":"ELDHOSE BABU","ID_Registration":15},{"StudentIDNumber":"STM120180019","Name":"fgh","ID_Registration":19},{"StudentIDNumber":"STM120180020","Name":"tysw4y","ID_Registration":20},{"StudentIDNumber":"STM120180021","Name":"fghtrsth","ID_Registration":21},{"StudentIDNumber":"STM120180022","Name":"EDRHG","ID_Registration":22},{"StudentIDNumber":"STM120180023","Name":"eldhose babu","ID_Registration":23},{"StudentIDNumber":"STM120180025","Name":"aergq3eg","ID_Registration":25},{"StudentIDNumber":"STM120180031","Name":"MUHAMMED SANJEED P","ID_Registration":31},{"StudentIDNumber":"STM120180033","Name":"eldhose","ID_Registration":33},{"StudentIDNumber":"STM120180036","Name":"yyyyy","ID_Registration":36},{"StudentIDNumber":"STM120180024","Name":"ht","ID_Registration":24},{"StudentIDNumber":"STM120180027","Name":"ghts","ID_Registration":27},{"StudentIDNumber":"STM120180028","Name":"ELDHOSE BABU","ID_Registration":28},{"StudentIDNumber":"STM120180030","Name":"ELDHOSE BABU","ID_Registration":30},{"StudentIDNumber":"STM120180032","Name":"AMMU","ID_Registration":32},{"StudentIDNumber":"STM120180035","Name":"JOVNA ","ID_Registration":35},{"StudentIDNumber":"STM120180026","Name":"trhjus","ID_Registration":26},{"StudentIDNumber":"STM120180029","Name":"ELDHOSE BABU","ID_Registration":29},{"StudentIDNumber":"STM120180040","Name":"aleena","ID_Registration":40},{"StudentIDNumber":"STM120180037","Name":"eldhose","ID_Registration":37},{"StudentIDNumber":"STM120180038","Name":"dghwir","ID_Registration":38},{"StudentIDNumber":"STM120180013","Name":"fdh","ID_Registration":13},{"StudentIDNumber":"STM120180015","Name":"ELDHOSE BABU","ID_Registration":15},{"StudentIDNumber":"STM120180019","Name":"fgh","ID_Registration":19},{"StudentIDNumber":"STM120180020","Name":"tysw4y","ID_Registration":20},{"StudentIDNumber":"STM120180021","Name":"fghtrsth","ID_Registration":21},{"StudentIDNumber":"STM120180022","Name":"EDRHG","ID_Registration":22},{"StudentIDNumber":"STM120180023","Name":"eldhose babu","ID_Registration":23},{"StudentIDNumber":"STM120180025","Name":"aergq3eg","ID_Registration":25},{"StudentIDNumber":"STM120180031","Name":"MUHAMMED SANJEED P","ID_Registration":31},{"StudentIDNumber":"STM120180033","Name":"eldhose","ID_Registration":33},{"StudentIDNumber":"STM120180036","Name":"yyyyy","ID_Registration":36},{"StudentIDNumber":"STM120180024","Name":"ht","ID_Registration":24},{"StudentIDNumber":"STM120180027","Name":"ghts","ID_Registration":27},{"StudentIDNumber":"STM120180028","Name":"ELDHOSE BABU","ID_Registration":28},{"StudentIDNumber":"STM120180030","Name":"ELDHOSE BABU","ID_Registration":30},{"StudentIDNumber":"STM120180032","Name":"AMMU","ID_Registration":32},{"StudentIDNumber":"STM120180035","Name":"JOVNA ","ID_Registration":35},{"StudentIDNumber":"STM120180026","Name":"trhjus","ID_Registration":26},{"StudentIDNumber":"STM120180029","Name":"ELDHOSE BABU","ID_Registration":29},{"StudentIDNumber":"STM120180040","Name":"aleena","ID_Registration":40},{"StudentIDNumber":"STM120180037","Name":"eldhose","ID_Registration":37},{"StudentIDNumber":"STM120180038","Name":"dghwir","ID_Registration":38}],"ResponseCode":1,"ResponseMessage":null},"statuscode":1,"statusmessage":null}*/
}
