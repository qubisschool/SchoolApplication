package school.com.schoolapplication.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import school.com.schoolapplication.R;
import school.com.schoolapplication.home.attendance.TeacherAttendanceHomeFragment;
import school.com.schoolapplication.home.mark.ExamListMarkAddingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomeFragment extends Fragment implements View.OnClickListener{


    public TeacherHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_teacher_home, container, false);
        view.findViewById( R.id.btn_attendance ).setOnClickListener( this );
        view.findViewById( R.id.btn_exam ).setOnClickListener( this );
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch ( id ){
            case R.id.btn_attendance:{
                getFragmentManager().beginTransaction().add( R.id.frame_home_container,  TeacherAttendanceHomeFragment.newInstance())
                .addToBackStack("attendace").commit();
            }
            break;
            case R.id.btn_exam:{
                getFragmentManager().beginTransaction().add( R.id.frame_home_container, new ExamListMarkAddingFragment() )
                .addToBackStack("same").commit();
            }
            break;
        }
    }
}
