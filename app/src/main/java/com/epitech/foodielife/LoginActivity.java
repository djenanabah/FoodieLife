package com.epitech.foodielife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.epitech.foodielife.beans.UserClientInfo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tsy-jon on 30/04/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SignInButton SignIn;
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
        SignIn = (SignInButton)findViewById(R.id.bn_login);

        // Button click listeners
        SignIn.setOnClickListener(this);

        validateServerClientID();

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
            }
        });
    }
    private void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String idToken = account.getIdToken();
            if (idToken == null){
                Toast.makeText(this, "Erreur: Id Token", Toast.LENGTH_SHORT);
                return;
            }
            Log.i(TAG, "ID Token: " + idToken);
            // TODO(user): send token to server and validate server-side
            try {
                rclientUsage.login(idToken);

            } catch (JSONException e){
                Log.d(TAG, "JSONException: " + e.getMessage());
            }
        }
        else {
            Toast.makeText(this, "Google connexion failed", Toast.LENGTH_SHORT).show();
        }

    }


    public void updateUserInfo(JSONObject response) {
        try {
            userInfo.setName(response.getString("name"));
            userInfo.setPictureUrl(response.getString("pictureUrl"));
            userInfo.seteMail(response.getString("eMail"));
            userInfo.setToken(response.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
        intent.putExtra("UserClientInfo", userInfo);
        startActivity(intent);
        signOut();
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