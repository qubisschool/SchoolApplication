package school.com.schoolapplication.home.mark;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import school.com.schoolapplication.R;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/5/2018.
 */
public class ShowExamListAdapter extends RecyclerView.Adapter<ShowExamListAdapter.ExamViewHolder>{
    private List<ExamModel> mExamList;
    private Context mContext;
    private SelectExam selectExam;
    public ShowExamListAdapter(List<ExamModel>  list, Context context, SelectExam selectExam){
        mExamList = list;
        this.selectExam = selectExam;
        mContext = context;
    }
    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_exam_list, parent, false);
        return new ExamViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final ExamViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        final ExamModel examModel = this.mExamList.get(pos);

        holder.txtExamName.setText( examModel.getExamName() );
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectExam.select( examModel.getExamId() );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExamList.size();
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder{
        private TextView txtExamName;
        private LinearLayout parent;
        public ExamViewHolder(View view){
            super(view);
            txtExamName = view.findViewById( R.id.txt_exam_name );
            parent  = view.findViewById( R.id.parent );
        }
    }
    public interface SelectExam{
        void select(String examId);
    }
}
