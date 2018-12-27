package school.com.schoolapplication.home.attendance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import school.com.schoolapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherAttendanceHomeFragment extends Fragment implements View.OnClickListener{

    private Button mBtnNewAttndance;
    public TeacherAttendanceHomeFragment() {
        // Required empty public constructor
    }

    public static TeacherAttendanceHomeFragment newInstance(){
        TeacherAttendanceHomeFragment teacherAttendanceHomeFragment = new TeacherAttendanceHomeFragment();

        return teacherAttendanceHomeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance_home_teacher, container, false);
        mBtnNewAttndance = view.findViewById( R.id.btn_new );
        mBtnNewAttndance.setOnClickListener( this );
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if ( id == R.id.btn_new ){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add( R.id.frame_home_container , SelectClassFragment.getInstance() )
                    .addToBackStack("select class").commit();
        }
    }
}
