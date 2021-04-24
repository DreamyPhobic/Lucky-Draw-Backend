package com.dreamyphobic.luckydraw.activities;

/*
  Created by Harsh Gupta on 24/04/2021.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dreamyphobic.luckydraw.AppController;
import com.dreamyphobic.luckydraw.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dreamyphobic.luckydraw.AppController.BASE_URL;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    View bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateAccount:

                UserSignUp();
                //UserLogin();
//                UserPost();
                break;
            case R.id.txtSignin:
                Toast.makeText(this, "Signin clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    public void UserSignUp() {
        String url = BASE_URL +"/users/signup";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", ((EditText) findViewById(R.id.input_name)).getText().toString());
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
                        Toast.makeText(SignUpActivity.this, "Account Created Successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("TAG", error.getMessage());
                        pDialog.hide();
                    }
                });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void UserLogin(){
        String url = "https://selective-oval-celsius.glitch.me/users";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("TAG", String.valueOf(error.networkResponse));
                        pDialog.hide();
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", ((EditText) findViewById(R.id.input_name)).getText().toString());
                params.put("email", ((EditText) findViewById(R.id.input_email)).getText().toString());
                params.put("password", ((EditText) findViewById(R.id.input_password)).getText().toString());

                return params;
            }


        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void UserPost(){
        String url = "https://selective-oval-celsius.glitch.me/users/123";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", ((EditText) findViewById(R.id.input_name)).getText().toString());
        params.put("email", ((EditText) findViewById(R.id.input_email)).getText().toString());
        params.put("password", ((EditText) findViewById(R.id.input_password)).getText().toString());
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        pDialog.hide();
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