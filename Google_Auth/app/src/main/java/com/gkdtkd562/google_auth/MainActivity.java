package com.gkdtkd562.google_auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    FirebaseAuth auth ;
    GoogleApiClient mGoogleAPI;

    Button btn_login;
    Button btn_logout;

    // MainActivity가 시작될때 실행되는 method
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser current = auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_login = findViewById(R.id.btn_login);
        btn_logout = findViewById(R.id.btn_logout);

        // 구글 auth를 이용해서 로그인을 구현
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("gkdtkd562").requestEmail().build();

        mGoogleAPI = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        // 구글아 로그인 정보를 다오
        auth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gLogin = Auth.GoogleSignInApi.getSignInIntent(mGoogleAPI);
                startActivityForResult(gLogin,1);
            }
        });

    }

    // startActivityForResult로 Activity를 열었을때, Activity가 닫히면서 결과를 주면
    // 그 결과를 수신하는 Method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Log.d("Request", " : "+requestCode);

            Log.d("Result"," : "+result.isSuccess());

            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();

                AuthCredential ac = GoogleAuthProvider.getCredential(account.getIdToken(), null );

                // 구글이 정상적으로 로그인 되었다는 신호를 주면 실행될 method
                auth.signInWithCredential(ac).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(MainActivity.this, user.getDisplayName() + "\n" + user.getEmail() + "\n" + user.getPhoneNumber(),Toast.LENGTH_LONG).show();
                    }
                });

            }

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
