package school.com.schoolapplication.home.mark;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import school.com.schoolapplication.R;
import school.com.schoolapplication.home.attendance.SelectClassDivAdapter;
import school.com.schoolapplication.networkmanager.NetworkConnectionCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseCustom;
import school.com.schoolapplication.networkmanager.responsemodel.classdiv.DivInfoParent;
import school.com.schoolapplication.networkmanager.responsemodel.classdiv.DivisionModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClassForMarkAddingFragment extends Fragment {
    private Spinner mSpinnerClass;
    private static final String EXAM_INFO = "examInfo";
    private static final String SUBJECT_DET = "subject-det";
    private ExamDetailsInfo examDetailsInfo;
    private SubjectDetails subjectDetails;
    private List<DivisionModel> mDivisionModelList;
    private DivisionModel mDivisionModelSelected;
    public static SelectClassForMarkAddingFragment getInstance( ExamDetailsInfo examDetailsInfo, SubjectDetails subjectDetails ){
        SelectClassForMarkAddingFragment selectClassForMarkAddingFragment = new SelectClassForMarkAddingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXAM_INFO, examDetailsInfo);
        bundle.putParcelable( SUBJECT_DET, subjectDetails );
        selectClassForMarkAddingFragment.setArguments( bundle );
        return selectClassForMarkAddingFragment;
    }
    public SelectClassForMarkAddingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_select_class_for_mark_adding, container, false);
        Bundle bundle = getArguments();
        examDetailsInfo = bundle.getParcelable( EXAM_INFO );
        subjectDetails = bundle.getParcelable( SUBJECT_DET );

        mSpinnerClass = view.findViewById( R.id.spinner_class_div );
        mSpinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mDivisionModelSelected = mDivisionModelList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getData();
        view.findViewById( R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 submit();
            }
        });
        return view;
    }

    private void getData(){
        String url = "http://test.qubisapps.com/api/Student/listDivisions";
        Map<String,String> param = new HashMap<>();
        param.put("ID_Class", examDetailsInfo.getFkClass() );
        NetworkConnectionCustom.getInstance().volleyPosting(url, param, getContext(), new NetworkResponseCustom() {
            @Override
            public void onSuccess(String response) {
                try{
                    DivInfoParent divInfoParent = new Gson().fromJson( response, DivInfoParent.class );
                    mDivisionModelList = divInfoParent.getmDivInfo().getDivisionModelList() ;
                    if ( mDivisionModelList.size() > 0 ){
                        SelectClassDivAdapter selectClassDivAdapter = new SelectClassDivAdapter(getContext(), mDivisionModelList, 1);
                        mSpinnerClass.setAdapter( selectClassDivAdapter );
                    }  else {
                        //goBack();
                    }
                }catch ( Exception e ){
                    //Do nothing
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
    private void submit(){
        if ( mDivisionModelList != null ){
            if ( mDivisionModelList.size()> 0 ){
                if ( mDivisionModelSelected != null ){
                    getFragmentManager().beginTransaction().replace( R.id.frame_home_container,
                            AddMarkFragment.getInstance( examDetailsInfo, subjectDetails.getFkSubject() , mDivisionModelSelected.getDivision() ))
                            .commit();
                }
            }
        }
    }

}
