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
import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.fragments.common.login.util.LoggedInUserView;
import com.ulc.tbr.fragments.common.login.util.LoginFormState;
import com.ulc.tbr.fragments.common.login.util.LoginResult;
import com.ulc.tbr.fragments.common.login.util.LoginViewModel;
import com.ulc.tbr.fragments.common.login.util.LoginViewModelFactory;
import com.ulc.tbr.models.users.User;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
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
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText netIDEditText = view.findViewById(R.id.net_ID);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);
//        final Button studentButton = view.findViewById(R.id.student);
//        final Button tutorButton = view.findViewById(R.id.tutorButton);
//        final Button studentAndTutorButton = view.findViewById(R.id.studentAndTutorButton);

        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    netIDEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    //updateUiWithUser(loginResult.getSuccess());
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(netIDEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        netIDEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(netIDEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(netIDEditText.getText().toString(),
                        passwordEditText.getText().toString());

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

//                    if (user.isTutor() && user.isTutee()) {
//                        NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                                .navigate(R.id.action_loginFragment_to_studentTutorHome,userData);
//                    } else if (user.isTutee()) {
//                        NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                                .navigate(R.id.action_loginFragment_to_studentHome,userData);
//                    }else if (user.isTutor()) {
//                        NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                                .navigate(R.id.action_loginFragment_to_tutorHome,userData);
//                    }
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "NetID or Password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    if (tutor.isChecked() && student.isChecked()) {
//                        users.addData(1000, emailEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), emailEditText.getText().toString(),
//                                true, true);
//                    } else if (student.isChecked()) {
//                        users.addData(1000, emailEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), emailEditText.getText().toString(),
//                                false, true);
//                    } else if (tutor.isChecked()) {
//                        users.addData(1000, emailEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), emailEditText.getText().toString(),
//                                true, false);
//                    } else {
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                user.setNetID(emailEditText.getText().toString());
//                user.setPassword(passwordEditText.getText().toString());
//                if (tutor.isChecked()) {user.setTutor(true);}
//                if (student.isChecked()) {user.setTutee(true);}
//                Bundle userData = new Bundle();
//                userData.putSerializable("user", user);
//
//                if (user.isTutor() && user.isTutee()) {
//                    Log.d("my", String.valueOf(user.isTutor()));
//                    Log.d("12", String.valueOf(user.isTutee()));
//                    NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                            .navigate(R.id.action_loginFragment_to_studentTutorHome,userData);
//                } else if (user.isTutee()) {
//                    NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                            .navigate(R.id.action_loginFragment_to_studentHome,userData);
//                }else if (user.isTutor()) {
//                    NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                            .navigate(R.id.action_loginFragment_to_tutorHome,userData);
//                }
//                //loadingProgressBar.setVisibility(View.VISIBLE);
//                // loginViewModel.login(usernameEditText.getText().toString(),passwordEditText.getText().toString());
//            }
//        });
        // TODO: Remove all these buttons
//        studentButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_studentHome);
//            }
//        });
//
//        tutorButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_tutorHome);
//            }
//        });
//
//        studentAndTutorButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_studentTutorHome);
//            }
//        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
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