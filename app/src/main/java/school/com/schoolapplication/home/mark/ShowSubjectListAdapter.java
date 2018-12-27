package school.com.schoolapplication.home.mark;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import school.com.schoolapplication.R;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/21/2018.
 */
public class ShowSubjectListAdapter extends RecyclerView.Adapter<ShowSubjectListAdapter.SubjectViewHolder> {
    private List<SubjectDetails> subjectDetails;
    private SelectSubject selectSubject;

    public ShowSubjectListAdapter(List<SubjectDetails> subjectDetails, SelectSubject selectSubject ){
        this.subjectDetails = subjectDetails;
        this.selectSubject = selectSubject;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_exam_list, parent, false);
        return new SubjectViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        holder.txtSubjectName.setText( subjectDetails.get( pos ).getSubjectName() );
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectSubject.select( subjectDetails.get( pos ) );
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectDetails.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSubjectName;
        private LinearLayout parent;
        public SubjectViewHolder(View view ){
            super( view );
            txtSubjectName= view.findViewById( R.id.txt_exam_name );
            parent  = view.findViewById( R.id.parent );
        }
    }
    public interface SelectSubject{
        void select( SubjectDetails subjectDetails );
    }
}
