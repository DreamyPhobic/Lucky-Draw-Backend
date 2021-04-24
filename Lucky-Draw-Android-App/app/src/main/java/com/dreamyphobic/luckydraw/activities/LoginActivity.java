package com.dreamyphobic.luckydraw.activities;

/*
  Created by Harsh Gupta on 24/04/2021.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dreamyphobic.luckydraw.AppController;
import com.dreamyphobic.luckydraw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dreamyphobic.luckydraw.AppController.BASE_URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtForgotPassword:
                Toast.makeText(this, "Forgot Password clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSignIn:
                UserLogin();
                break;
            case R.id.txtSignUp:
                Toast.makeText(this, "Sign Up clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    public void UserLogin(){
        String url = BASE_URL +"/users/login";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", ((EditText) findViewById(R.id.input_email)).getText().toString());
        params.put("password", ((EditText) findViewById(R.id.input_password)).getText().toString());
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        pDialog.dismiss();
                        try {
                            String token = response.getString("token");
                            JSONObject user= response.getJSONObject("user");
                            String name = user.getString("name");
                            String email = user.getString("email");
                            String _id = user.getString("_id");
                            int tickets = user.getInt("tickets");
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("token", token);
                            myEdit.putString("name",name);
                            myEdit.putString("email",email);
                            myEdit.putString("_id",_id);
                            myEdit.putInt("tickets",tickets);
                            myEdit.apply();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(LoginActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("TAG", String.valueOf(error.networkResponse));
                        pDialog.hide();
                    }
                });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}