package school.com.schoolapplication.home.attendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import school.com.schoolapplication.R;
import school.com.schoolapplication.networkmanager.responsemodel.classdiv.DivisionModel;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class SelectClassDivAdapter extends BaseAdapter {
    private int mType;
    private List<DivisionModel> mDivisionModelList;
    private Context mContext;
    public SelectClassDivAdapter(@NonNull Context context , @NonNull List<DivisionModel> divisionModelList,int type) {
        mDivisionModelList = divisionModelList;
        mType = type;
        mContext = context;
    }

    @Override
    public int getCount(){
        return  mDivisionModelList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from( mContext ).inflate( R.layout.spinner_div_class, null  );
        TextView textView = view.findViewById( R.id.txt_div_class );
        final DivisionModel divisionModel = mDivisionModelList.get(i);
        if ( mType == 1 ){
            String text = divisionModel.getClassName()+" - "+ divisionModel.getDivision();
            textView.setText( text  );
        }else if ( mType == 2) {
            textView.setText( mDivisionModelList.get( i ).getDivision()  );
        }

        return view;
    }
}
