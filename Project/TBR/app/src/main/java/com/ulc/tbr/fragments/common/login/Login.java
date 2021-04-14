package com.ulc.tbr.fragments.common.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.databases.DatabaseHelper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.ulc.tbr.R;

//import com.ulc.tbr.fragments.common.login.util.LoggedInUserView;
//import com.ulc.tbr.fragments.common.login.util.LoginFormState;
//import com.ulc.tbr.fragments.common.login.util.LoginResult;
//import com.ulc.tbr.fragments.common.login.util.LoginViewModel;
//import com.ulc.tbr.fragments.common.login.util.LoginViewModelFactory;
import com.ulc.tbr.models.users.*;

import java.net.URI;
import java.net.URL;

public class Login extends Fragment {
  //  private LoginViewModel loginViewModel;
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

        final EditText netIDEditText = view.findViewById(R.id.net_ID);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean login = false;
                if (users.getPassword(netIDEditText.getText().toString()).equals(passwordEditText.getText().toString())) {
                    login = true;
                }
                if (login) {
                    user = users.getUser(netIDEditText.getText().toString());

                    Bundle userData = new Bundle();
                    userData.putSerializable("user", user);

                    NavHostFragment.findNavController(com.ulc.tbr.fragments.common.login.Login.this)
                            .navigate(R.id.action_login, userData);

                } else {
                    Toast.makeText(getContext().getApplicationContext(), "NetID or Password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    private void showLoginFailed(@StringRes Integer errorString) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(
                    getContext().getApplicationContext(),
                    errorString,
                    Toast.LENGTH_LONG).show();
        }
    }
}