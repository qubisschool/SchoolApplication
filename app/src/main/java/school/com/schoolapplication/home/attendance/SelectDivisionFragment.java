package school.com.schoolapplication.home.attendance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import school.com.schoolapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectDivisionFragment extends Fragment {


    public SelectDivisionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_division, container, false);
    }

}
