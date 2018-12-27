package school.com.schoolapplication.home.attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.StudentAttendanceModel;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/5/2018.
 */
public class AttendanceTakerRecyclerAdapter extends RecyclerView.Adapter<AttendanceTakerRecyclerAdapter.MarkerViewHolder>{
    private List<StudentAttendanceModel> mStudentAttendanceModelList;
    private Context mContext;
    private Marker marker;
    public AttendanceTakerRecyclerAdapter( List<StudentAttendanceModel>  list, Context context, Marker marker){
        mStudentAttendanceModelList = list;
        this.marker = marker;
        mContext = context;
    }
    @Override
    public MarkerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_take_attendance, parent, false);
        return new MarkerViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final MarkerViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        StudentAttendanceModel studentAttendanceModel = mStudentAttendanceModelList.get(pos);
        holder.txtSlNo.setText( studentAttendanceModel.getStudentIdNumber() );
        holder.txtName.setText( studentAttendanceModel.getName() );
        if ( studentAttendanceModel.isPresent() )
            holder.chckMarker.setChecked( true );
        else holder.chckMarker.setChecked( false );
        holder.chckMarker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mStudentAttendanceModelList.get(pos).setPresent( b );
                int absent = 0;
                int present =0;
                for (StudentAttendanceModel studentceModel : mStudentAttendanceModelList ) {
                    if ( studentceModel.isPresent() )
                        present +=1;
                    else absent += 1;
                }
                marker.mark(present, absent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudentAttendanceModelList.size();
    }
    public void markAll( boolean b ){
        for ( int i =0 ; i < mStudentAttendanceModelList.size(); i++ ){
            mStudentAttendanceModelList.get( i ).setPresent( b );
            notifyDataSetChanged();
        }

    }
    public class MarkerViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtSlNo;
        private CheckBox chckMarker;
        public MarkerViewHolder(View view){
            super(view);
            txtSlNo  = view.findViewById( R.id.txt_sl_no );
            txtName = view.findViewById( R.id.txt_stud_name );
            chckMarker = view.findViewById( R.id.chk_attendance );
        }
    }
    public interface Marker{
        void mark( int present, int absent);
    }
}
