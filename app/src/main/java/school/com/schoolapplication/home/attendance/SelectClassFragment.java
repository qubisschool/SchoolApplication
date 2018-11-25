package school.com.schoolapplication.home.attendance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.NetworkConnectionCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseCustom;
import school.com.schoolapplication.networkmanager.responsemodel.classdiv.DivInfoParent;
import school.com.schoolapplication.networkmanager.responsemodel.classdiv.DivisionModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClassFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout mRltvLoader;
    private Spinner mSpinnerClass;
    private Spinner mSpinnerDivision;
    private List<DivisionModel> mDivisionModelList;
    private DivisionModel mDivisionModelSelected;
    public static SelectClassFragment getInstance(){
        SelectClassFragment selectClassFragment = new SelectClassFragment();
        return selectClassFragment;
    }

    public SelectClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_class, container, false);
        mRltvLoader = view.findViewById( R.id.loader );
        mSpinnerClass = view.findViewById( R.id.spinner_class );
        mRltvLoader.setOnClickListener( this );
        view.findViewById( R.id.btn_submit).setOnClickListener( this );
        mSpinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*if ( i != 0 ){
                    getList( mDivisionModelList.get(i));
                }*/
                mDivisionModelSelected = mDivisionModelList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getData();
        return view;
    }
    private void getData(){
        String url = " http://test.qubisapps.com/api/Student/listDivisions";
        mRltvLoader.setVisibility( View.VISIBLE );
        NetworkConnectionCustom.getInstance().volleyPosting(url, new HashMap<String, String>(), getContext(), new NetworkResponseCustom() {
            @Override
            public void onSuccess(String response) {
                try{
                    DivInfoParent divInfoParent = new Gson().fromJson( response, DivInfoParent.class );
                    analyseResult( divInfoParent );
                }catch ( Exception e ){
                    //Do nothing
                }
                mRltvLoader.setVisibility(View.GONE);
            }

            @Override
            public void onError(VolleyError error) {
                goBack();
                mRltvLoader.setVisibility(View.GONE);
            }
        });
    }
    private void analyseResult( DivInfoParent divInfoParent ){
        if ( divInfoParent.getStatusCode().equals("1")){
            mDivisionModelList = divInfoParent.getmDivInfo().getDivisionModelList();
            if ( mDivisionModelList.size() > 1 ){
                SelectClassDivAdapter selectClassDivAdapter = new SelectClassDivAdapter(getContext(), mDivisionModelList, 1);
                mSpinnerClass.setAdapter( selectClassDivAdapter );
            }  else {
                goBack();
            }
        } else {
            goBack();
        }


    }
    private void goBack(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove( SelectClassFragment.getInstance() );
        fragmentTransaction.commit();
        fragmentManager.popBackStack();

    }
    private void toast(String message){
        Toast.makeText( getContext(), message, Toast.LENGTH_SHORT ).show();
    }
    public void onClick(View view ){
        int id = view.getId();
        if ( id == R.id.btn_submit && mDivisionModelSelected != null ){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add( R.id.frame_home_container,
                    TakeAttendanceFragment.getInstance( mDivisionModelSelected.getClassDetailsId() ) )
            .addToBackStack("attendancee");
            fragmentTransaction.commit();
            //fragmentManager.popBackStack();
        }
    }

}
