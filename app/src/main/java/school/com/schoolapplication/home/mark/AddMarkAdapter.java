package school.com.schoolapplication.home.mark;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.responsemodel.attendance.StudentAttendanceModel;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/21/2018.
 */
public class AddMarkAdapter extends RecyclerView.Adapter<AddMarkAdapter.AddMarkAdapterHolder> {
    private List<StudentAttendanceModel> mStudentAttendanceModelList;
    public AddMarkAdapter( List<StudentAttendanceModel>studentAttendanceModelList ){
        this.mStudentAttendanceModelList = studentAttendanceModelList;

    }
    @Override
    public AddMarkAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_add_mark, parent, false);
        return new AddMarkAdapterHolder( itemView );
    }

    @Override
    public void onBindViewHolder(AddMarkAdapterHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        String mark = holder.mark.getText().toString();
        if ( mark.isEmpty() )
            mark = "0";
        mStudentAttendanceModelList.get( pos ).setMark( Double.parseDouble( mark ));
        holder.studName.setText(  mStudentAttendanceModelList.get( pos ).getName() );
        holder.mark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudentAttendanceModelList.size();
    }
    public List<StudentAttendanceModel> getMarkAndStudent(){
        return mStudentAttendanceModelList;
    }

    public class AddMarkAdapterHolder extends RecyclerView.ViewHolder{
        TextView studName;
        EditText mark;
        public AddMarkAdapterHolder(View view ){
            super(view);
            studName = view.findViewById( R.id.student );
            mark = view.findViewById( R.id.edt_mark );
        }

    }

}
