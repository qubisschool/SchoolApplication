package school.com.schoolapplication.home.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.StudentAttendanceModel;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/21/2018.
 */
public class TakeAttendanceListAdapter extends BaseAdapter {
    private List<StudentAttendanceModel> mStudentAttendanceModelList;
    private Context mContext;
    public TakeAttendanceListAdapter(List<StudentAttendanceModel> studentAttendanceModelList, Context context ){
        mStudentAttendanceModelList = studentAttendanceModelList;
        mContext = context;
    }
    public int getCount(){
        return mStudentAttendanceModelList.size();
    }
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView==null)
            vi = LayoutInflater.from( mContext).inflate( R.layout.list_take_attendance, null);
        TextView txtName = vi.findViewById( R.id.txt_stud_name );
        CheckBox checkBox = vi.findViewById( R.id.chk_attendance );
        StudentAttendanceModel studentAttendanceModel = mStudentAttendanceModelList.get( position );
        txtName.setText( studentAttendanceModel.getName() );
        return vi;
    }
}
