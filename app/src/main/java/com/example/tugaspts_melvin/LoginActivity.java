package com.example.tugaspts_melvin;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btn_back;
    Button btn_twitter;
    Button btn_3dot;
    Button btn_login;
    Button btn_register2;
    TextInputEditText textInputEditTextemail;
    TextInputEditText textInputEditTextpassword;
    private static String URL_LOGIN = "http://192.168.1.2/android_register_login/login.php";
    private ProgressBar pg_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginform_activity);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_3dot = (Button) findViewById(R.id.btn_3dot);
        btn_twitter = (Button) findViewById(R.id.btn_twitter);
        btn_login = (Button) findViewById(R.id.btn_login);
        pg_loading = (ProgressBar) findViewById(R.id.pg_loading);
        textInputEditTextemail = (TextInputEditText) findViewById(R.id.tb_loginemail);
        textInputEditTextpassword = (TextInputEditText) findViewById(R.id.tb_loginpassword);



        // on click event
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backtomainmenu();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = textInputEditTextemail.getText().toString().trim();
                String mPassword = textInputEditTextpassword.getText().toString().trim();
                LogikaSimpleLogIn(mEmail,mPassword);
            }
        });

        btn_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpawnToast("Versi 1.0 by Steward Melvin Tang");
            }
        });

        btn_3dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpawnToast("Belum ada apa apa disini.");
            }
        });


                // end phase
            }
            //login dengan menggunakan mySql
            public void Login(final String email, final String password) {

                pg_loading.setVisibility(View.VISIBLE);
                btn_login.setVisibility(View.GONE);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");
                                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                                    if (success.equals("1")) {

                                        for (int i = 0; i < jsonArray.length(); i++) {

                                            JSONObject object = jsonArray.getJSONObject(i);
                                            String name = object.getString("name").trim();
                                            String email = object.getString("email").trim();

                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("name", name);
                                            intent.putExtra("email", email);
                                            startActivity(intent);
                                            finish();

                                            pg_loading.setVisibility(View.GONE);


                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    pg_loading.setVisibility(View.GONE);
                                    btn_login.setVisibility(View.VISIBLE);
                                    Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pg_loading.setVisibility(View.GONE);
                                btn_login.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }

            private void Backtomainmenu() {
                Intent intentback = new Intent(LoginActivity.this, MainTwitter.class);
                startActivity(intentback);
            }

            private void SpawnToast(String text) {
                Toast.makeText(LoginActivity.this, "" + text, Toast.LENGTH_SHORT).show();

            }

            private void LogikaSimpleLogIn(String email, String pass) {
                if (email.equals("Steward") && pass.equals("Steward")) {
                    Intent ketwitter = new Intent(LoginActivity.this, MainActivity.class);
                    ketwitter.putExtra("emailnya", ""+email);
                    startActivity(ketwitter);
                }else{
                SpawnToast("Email atau Password salah");
                }

            }
            // end phase2
        }