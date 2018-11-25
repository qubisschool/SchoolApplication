package school.com.schoolapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import school.com.schoolapplication.home.HomeActivity;
import school.com.schoolapplication.networkmanager.NetworkConnectionCustom;
import school.com.schoolapplication.networkmanager.NetworkResponseCustom;
import school.com.schoolapplication.networkmanager.responsemodel.LoginParent;
import school.com.schoolapplication.utils.LoadingFragment;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mLnrLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLnrLoader = findViewById( R.id.lnr_alert );
        findViewById( R.id.btn_login ).setOnClickListener( this );
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if ( id == R.id.btn_login ){
            login();
        }else if ( id == R.id.lnr_alert ){
            //Do nothing
        }
    }
    private void login(){
        EditText edtUserName = findViewById( R.id.edt_user_name );
        EditText edtPassword = findViewById( R.id.edt_password );

        String userName = edtUserName.getText().toString();
        if ( !userName.isEmpty() ){
            String password = edtPassword.getText().toString();
            if ( !password.isEmpty() ){
                String url = "http://test.qubisapps.com/api/Authentication/userlogin";
                Map<String,String> params = new HashMap<>();
                params.put("Username", userName );
                params.put("Password", password );
                mLnrLoader.setVisibility(View.VISIBLE);
                NetworkConnectionCustom.getInstance().volleyPosting(url, params, this, new NetworkResponseCustom() {
                    @Override
                    public void onSuccess(String response) {
                        try{
                            LoginParent loginParent = new Gson().fromJson( response, LoginParent.class );
                            if ( loginParent.getStatusCode() == 1 ){
                                Intent intent = new Intent( LoginActivity.this, HomeActivity.class );
                                startActivity( intent );
                                finish();
                            }else {
                                toast("Invalid user name or password");
                            }
                        }catch ( Exception e ){
                            //DO nothing
                        }

                        mLnrLoader.setVisibility( View.GONE );

                    }

                    @Override
                    public void onError(VolleyError error) {
                        mLnrLoader.setVisibility( View.GONE );
                        toast( error.toString() );
                    }
                });
            }else toast("Please enter password");
        }else
            toast("Please enter user name");
    }
    private void toast( String message ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }


}
