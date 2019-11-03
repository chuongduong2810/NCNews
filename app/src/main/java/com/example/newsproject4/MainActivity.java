package com.example.newsproject4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsproject4.Retrofit.APIUtils;
import com.example.newsproject4.Retrofit.DataClient;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 6969;
    List<AuthUI.IdpConfig> providers;
    //Button btn_sign_out;

    String uid;
    String userEmail;
    String userName;
    //String key = "1234";
    private DataClient dataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);


        //Init providers
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
                //new AuthUI.IdpConfig.FacebookBuilder().build(),
                //new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        showSignInOptions();
    }

    private void InsertDB(FirebaseUser user) {
        uid = user.getUid();
        userEmail = user.getEmail();
        userName = user.getDisplayName();

        dataClient = APIUtils.getData();

        Call<String> callback = dataClient.InsertData(uid, userEmail, userName);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                if (result.equals("Success")) {
                    Toast.makeText(MainActivity.this, "Inserted to database", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.drawable.logo_news)
                        .build(), MY_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            final IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK && data != null) {
                //Get user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Show name on Toast
                Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                //Set button signout
                //btn_sign_out.setEnabled(true);
                InsertDB(user);
                //finish();
                Intent intent = new Intent(MainActivity.this,navigation.class);
                startActivity(intent);
                finish();
            } else {
              //Press back
                    //Toast.makeText(this, "Error: " + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
