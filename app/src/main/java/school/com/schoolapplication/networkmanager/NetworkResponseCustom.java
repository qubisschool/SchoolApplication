package school.com.schoolapplication.networkmanager;

import com.android.volley.VolleyError;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public interface NetworkResponseCustom {
    public void onSuccess(String response);
    public void onError(VolleyError error);
}
