package school.com.schoolapplication.home;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import school.com.schoolapplication.R;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private boolean isToggleOpen = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mDrawerLayout = findViewById( R.id.drawer_layout );
        mDrawerLayout.openDrawer(GravityCompat.START);

        getSupportFragmentManager().beginTransaction().replace( R.id.frame_home_container,
                new TeacherHomeFragment()/*TeacherAttendanceHomeFragment.newInstance()*/ )
        .commit();
        getSupportFragmentManager().beginTransaction().replace( R.id.fragment_navigation,
                new NavigationDrawerFragment()/*TeacherAttendanceHomeFragment.newInstance()*/ )
                .commit();
        findViewById( R.id.toggle ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( mDrawerLayout.isDrawerOpen( GravityCompat.START) ){
                    mDrawerLayout.openDrawer( GravityCompat.END );
                }
                else{
                    mDrawerLayout.openDrawer( GravityCompat.START );
                };
            }
        });
    }
}

