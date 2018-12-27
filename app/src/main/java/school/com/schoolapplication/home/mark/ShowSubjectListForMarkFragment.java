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

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.NetworkConnectionCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseParent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowSubjectListForMarkFragment extends Fragment {
    private RecyclerView recyclerView;
    private static final String EXAM_ID = "examId";
    public static ShowSubjectListForMarkFragment getInstance(String exampId ){
        ShowSubjectListForMarkFragment showSubjectListForMarkFragment = new ShowSubjectListForMarkFragment();
        Bundle bundle = new Bundle();
        bundle.putString( EXAM_ID, exampId );
        showSubjectListForMarkFragment.setArguments( bundle );
        return showSubjectListForMarkFragment;
    }
    public ShowSubjectListForMarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_subject_list_for_mark, container, false);

        recyclerView = view.findViewById( R.id.recycler_show_subject );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );


        Bundle bundle = getArguments();
        String examId = bundle.getString( EXAM_ID );
        if( examId.isEmpty() ){
            //Do nothing
        }else {
            showExamList( examId );
        }
        return view;
    }
    private void showExamList(String examId ){
        String url = "http://test.qubisapps.com/api/Exam/listExamDetails?ID_Exam=" + examId;
        NetworkConnectionCustom.getInstance().volleygetting(url, getContext(), new NetworkResponseCustom() {
            @Override
            public void onSuccess(String response) {
                try{
                    NetworkResponseParent networkResponseParent  =
                            new Gson().fromJson( response, NetworkResponseParent.class );

                    final ExamDetailsInfo examDetailsInfo = networkResponseParent.getExamDetailsInfo();
                    ShowSubjectListAdapter showSubjectListAdapter =
                            new ShowSubjectListAdapter(examDetailsInfo.getSubjectDetails(), new ShowSubjectListAdapter.SelectSubject() {
                                @Override
                                public void select(SubjectDetails subjectDetails) {
                                    getFragmentManager().beginTransaction().replace( R.id.frame_home_container,
                                            SelectClassForMarkAddingFragment.getInstance( examDetailsInfo, subjectDetails )  )
                                            .addToBackStack("mark").commit();
                                }
                            });
                    recyclerView.setAdapter( showSubjectListAdapter );
                }
                catch ( Exception e ){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG ).show();
            }
        });
    }
}
