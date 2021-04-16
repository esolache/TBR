package com.ulc.tbr.fragments.common.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;
//import com.ulc.tbr.fragments.common.login.util.LoggedInUserView;
//import com.ulc.tbr.fragments.common.login.util.LoginFormState;
//import com.ulc.tbr.fragments.common.login.util.LoginResult;
//import com.ulc.tbr.fragments.common.login.util.LoginViewModel;
//import com.ulc.tbr.fragments.common.login.util.LoginViewModelFactory;
import com.ulc.tbr.models.users.User;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
   // private LoginViewModel loginViewModel;
    DatabaseHelper users;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainActivity ma = (MainActivity) getActivity();
        users = ma.getDatabase();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        user = new User(null, null, null, null, false, false);
        super.onViewCreated(view, savedInstanceState);
//        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
//                .get(LoginViewModel.class);

        final EditText netIDEditText = view.findViewById(R.id.net_ID);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);
//        final Button studentButton = view.findViewById(R.id.student);
//        final Button tutorButton = view.findViewById(R.id.tutorButton);
//        final Button studentAndTutorButton = view.findViewById(R.id.studentAndTutorButton);

//        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), new Observer<LoginFormState>() {
//            @Override
//            public void onChanged(@Nullable LoginFormState loginFormState) {
//                if (loginFormState == null) {
//                    return;
//                }
//                loginButton.setEnabled(loginFormState.isDataValid());
//                if (loginFormState.getUsernameError() != null) {
//                    netIDEditText.setError(getString(loginFormState.getUsernameError()));
//                }
//                if (loginFormState.getPasswordError() != null) {
//                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
//                }
//            }
//        });
//
//        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
//            @Override
//            public void onChanged(@Nullable LoginResult loginResult) {
//                if (loginResult == null) {
//                    return;
//                }
//                loadingProgressBar.setVisibility(View.GONE);
//                if (loginResult.getError() != null) {
//                    showLoginFailed(loginResult.getError());
//                }
//                if (loginResult.getSuccess() != null) {
//                    //updateUiWithUser(loginResult.getSuccess());
//                }
//            }
//        });
//
//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                loginViewModel.loginDataChanged(netIDEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//            }
//        };
//        netIDEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(netIDEditText.getText().toString(),
//                            passwordEditText.getText().toString());
//                }
//                return false;
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: This is original implement
                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(netIDEditText.getText().toString(),
//                        passwordEditText.getText().toString());

                boolean login = false;
                if (users.getPassword(netIDEditText.getText().toString()).equals(passwordEditText.getText().toString())) {
                    login = true;
                }
                if (login) {
                    user = users.getUser(netIDEditText.getText().toString());

                    Bundle userData = new Bundle();
                    userData.putSerializable("user", user);

                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_login, userData);


                } else {
                    Toast.makeText(getContext().getApplicationContext(), "NetID or Password is incorrect", Toast.LENGTH_LONG).show();
                }
                // TODO: This is new implement
//                String username = netIDEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//                username = "student";
//                password = "student";
//                Boolean validLogin = validateLogin(username, password);
//                String databasePassword = getPassword(passwordEditText.getText().toString());
//                if (databasePassword.equals(passwordEditText.getText().toString())){
//                    Toast.makeText(getContext(), "Logging In", Toast.LENGTH_LONG).show();
//                }

            }
        });

    }

    private Boolean validateLogin(String username, String password) {
        Boolean validLogin = true;
        String url = "http://10.0.2.2/android_connect/get_login.php";
//        final String[] returnPassword = {""};
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                netIDEditText
//                returnPassword[0] = response.toString();
                Log.i("Anthropocentric", response);
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("netid", username);
                Params.put("password", password);
                return Params;
            }
        };
        Mysingleton.getInstance(getActivity()).addTorequestque(stringRequest);
        //TODO:
//        if (null response) incorrecet login

        return false;
    }
}