package com.dreamyphobic.luckydraw;

/*
  Created by Harsh Gupta on 24/04/2021.
 */

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dreamyphobic.luckydraw.models.EventModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.dreamyphobic.luckydraw.AppController.BASE_URL;

@SuppressLint("ValidFragment")
public class EventsFragment extends Fragment implements EventClickListener {
    int wizard_page_position;
    ArrayList<EventModel> rowListItem = new ArrayList<>();
    String token;
    String _id;
    EventAdapter rcAdapter;

    public EventsFragment(int position) {
        this.wizard_page_position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout_id = R.layout.events_fragment;
        View view = inflater.inflate(layout_id, container, false);

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        token = sh.getString("token", "");
        _id = sh.getString("_id","");


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recyclerView);
        rView.setHasFixedSize(false);
        rView.setLayoutManager(layoutManager);
        rView.setNestedScrollingEnabled(false);

        rcAdapter = new EventAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        rcAdapter.setClickListener(this);
        getAllItemList(wizard_page_position);
        return view;
    }

    private void getAllItemList(int status){
        rowListItem.clear();
        String url = BASE_URL +"/events/";
        if(status==0){
            url = url+"nextEvents";
        }
        else{
            url = url+"previousEvents";
        }


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", response.toString());
//                        pDialog.dismiss();
                        try {
                            for(int i=0;i<response.length();i++)
                            {
                                JSONObject jsonObject = response.getJSONObject(i);
                                EventModel event = new Gson().fromJson(jsonObject.toString(),EventModel.class);
                                Log.d("Event status",event.getReward()+" "+ event.getStatus());
                                rowListItem.add(event);
                            }
                            rcAdapter.notifyDataSetChanged();

                        }
                        catch (Exception w)
                        {
                            w.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("TAG", String.valueOf(error.networkResponse));

                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","Bearer "+token);
                return headers;
            }
        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void itemClicked(View view, EventModel eventModel) {

        ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Joining...");
        pDialog.show();
        String url = BASE_URL+"/events/addParticipant/"+eventModel.get_id();
        Map<String, String> params = new HashMap<String, String>();
        params.put("participant_id", _id);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH,
                url,parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        pDialog.dismiss();
                        Toast.makeText(getActivity(), "Joined Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.networkResponse.statusCode==409){
                            pDialog.dismiss();
                            Toast.makeText(getActivity(), "Already Joined", Toast.LENGTH_SHORT).show();
                        }
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","Bearer "+token);
                return headers;
            }
        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

//        Toast.makeText(getActivity(), "Position "  + " clicked!", Toast.LENGTH_SHORT).show();
    }
}
