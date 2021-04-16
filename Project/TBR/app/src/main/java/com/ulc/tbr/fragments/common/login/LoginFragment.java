package com.ulc.tbr.fragments.common.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ulc.tbr.R;
//import com.ulc.tbr.fragments.common.login.util.LoggedInUserView;
//import com.ulc.tbr.fragments.common.login.util.LoginFormState;
//import com.ulc.tbr.fragments.common.login.util.LoginResult;
//import com.ulc.tbr.fragments.common.login.util.LoginViewModel;
//import com.ulc.tbr.fragments.common.login.util.LoginViewModelFactory;
import com.ulc.tbr.models.users.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {


    public User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText netIDEditText = view.findViewById(R.id.net_ID);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: This is original implement
                String netId = netIDEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                loginUser(netId, password);
            }
        });
    }

    public void loginUser(String netId, String password) {
        Boolean validLogin = true;
        String url = "https://pistachio.khello.co/get_login.php";



        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.i("Some sort of unique string identifier here","HIT THE DATABASE");
                    Log.i("Some sort of unique string identifier here",response.toString());
                    JSONObject userObject = new JSONObject(response.toString());
                    Log.i("Sdsaaaaaaaa","HIT THE DATABASE");

                    user = new User(
                            (String) userObject.get("student_id"),
                            password,
                            (String)userObject.get("net_id"),
                            (String) userObject.get("email"),
                             Boolean.valueOf(userObject.get("tutor").toString()),
                             Boolean.valueOf(userObject.get("tutee").toString()));
                    if (user == null){
                        Toast.makeText(getContext().getApplicationContext(), "NetID or Password is incorrect", Toast.LENGTH_LONG).show();
                    } else {
                        Bundle userData = new Bundle();
                        userData.putSerializable("user", user);

                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.action_login, userData);

                    }

                } catch (JSONException e) {
                    Log.i("Some sort of unique string identifier here","PARSING WENT WRONG");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Some sort of unique string identifier here",error.toString());
                user = null;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("net_id", netId);
                Params.put("password", password);
                return Params;
            }
        };
        Mysingleton.getInstance(getContext()).addTorequestque(stringRequest);
    }


}