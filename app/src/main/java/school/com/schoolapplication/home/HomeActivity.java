package school.com.schoolapplication.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import school.com.schoolapplication.R;
import school.com.schoolapplication.home.attendance.AttendanceHomeFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.frame_home_container, new AttendanceHomeFragment() );*/

        getSupportFragmentManager().beginTransaction().replace( R.id.frame_home_container, AttendanceHomeFragment.newInstance() )
        .commit();

    }
}

