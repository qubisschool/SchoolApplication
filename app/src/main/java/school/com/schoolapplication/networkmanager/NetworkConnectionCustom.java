package school.com.schoolapplication.networkmanager;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import school.com.schoolapplication.utils.LoadingFragment;


/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 11/20/2018.
 */
public class NetworkConnectionCustom {
    private static NetworkConnectionCustom instance = null;
    public static synchronized NetworkConnectionCustom getInstance(){
        if ( instance == null ){
            instance = new NetworkConnectionCustom();
        }
        return instance;
    }

    public void volleyPosting(String url, final Map<String, String> params,
                              Context context, final NetworkResponseCustom networkResponseCustom ){


        RequestQueue requestQueue = Volley.newRequestQueue( context );
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        networkResponseCustom.onSuccess( response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                networkResponseCustom.onError( error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        requestQueue.add( stringRequest );
    }
}
