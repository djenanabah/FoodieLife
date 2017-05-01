package com.epitech.foodielife;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tsy-jon on 30/04/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout Prof_section;
    private Button SignOut;
    private SignInButton SignIn;
    private TextView Name, Email;
    private ImageView Prof_Pic;
    private GoogleApiClient googleApiClient;
    private UserClientInfo userInfo;
    private RestClientUsage rclientUsage;

    private static final int REQ_CODE = 9002;
    private static final String TAG = "LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInfo = new UserClientInfo();
        rclientUsage = new RestClientUsage(this);
        //Views
        Prof_section = (LinearLayout)findViewById(R.id.prof_section);
        SignOut = (Button)findViewById(R.id.bn_logout);
        SignIn = (SignInButton)findViewById(R.id.bn_login);
        Name = (TextView)findViewById(R.id.name);
        Email = (TextView)findViewById(R.id.email);
        Prof_Pic = (ImageView)findViewById(R.id.prof_pic);

        // Button click listeners
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);

        validateServerClientID();

        Prof_section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bn_login:
                signIn();
                break;
            case R.id.bn_logout:
                signOut();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void validateServerClientID() {
        String serverClientId = getString(R.string.server_client_id);
        String suffix = ".apps.googleusercontent.com";
        if (!serverClientId.trim().endsWith(suffix)) {
            String message = "Invalid server client ID in strings.xml, must end with " + suffix;

            Log.w(TAG, message);
            //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);

    }
    private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Log.d(TAG, "signOut:onResult: " + status);
                updateUI(false);
            }
        });

    }
    private void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String idToken = account.getIdToken();
            Log.i(TAG, "ID Token: " + idToken);
            // TODO(user): send token to server and validate server-side
            try {
                rclientUsage.login(idToken);

            } catch (JSONException e){
                Log.d(TAG, "JSONException: " + e.getMessage());
            }

            // Jackson json = new JacksonFactory();
           //String s = serializer.;

            /*String name = account.getDisplayName();
            String email = account.getEmail();
            String img_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
            Glide.with(this).load(img_url).into(Prof_Pic);*/
            //updateUI(true);
        }
        else {
            updateUI(false);
        }

    }
    public void updateUI(boolean isLogin){
        if (isLogin){
            //ToDo: go to the mapsActivity
            Prof_section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
        }
        else {
            Prof_section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
