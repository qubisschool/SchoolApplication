package school.com.schoolapplication.home.mark;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class ExamListMarkAddingFragment extends Fragment {

    private RecyclerView recyclerView;

    public ExamListMarkAddingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_exam_list_mark_adding, container, false);
        recyclerView = view.findViewById( R.id.exam_list );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        getExamList();
        return view;
    }
    private void getExamList(){
        String url = "http://test.qubisapps.com/api/Exam/listExams";
        NetworkConnectionCustom.getInstance().volleygetting(url, getContext(), new NetworkResponseCustom() {
            @Override
            public void onSuccess(String response) {
                try{
                    NetworkResponseParent networkResponseParent  = new Gson().fromJson( response, NetworkResponseParent.class );
                    if ( networkResponseParent.getStatusCode() == 1 ){
                        ShowExamListAdapter showExamListAdapter = new ShowExamListAdapter(networkResponseParent.getExminfo().getExamModelList()
                                , getContext(), new ShowExamListAdapter.SelectExam() {
                            @Override
                            public void select( String examId ) {
                                //selectExamp( examId );
                                getFragmentManager().beginTransaction().replace( R.id.frame_home_container,
                                        ShowSubjectListForMarkFragment.getInstance( examId ) )
                                        .addToBackStack("mark").commit();
                            }
                        });
                        recyclerView.setAdapter( showExamListAdapter );
                    }
                }catch ( Exception e ){
                    //do nothing
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

}
