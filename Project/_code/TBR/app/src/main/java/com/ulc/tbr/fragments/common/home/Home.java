package com.ulc.tbr.fragments.common.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ulc.tbr.R;
import com.ulc.tbr.models.users.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.ulc.tbr.fragments.common.home.Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment implements AdapterView.OnItemSelectedListener {

    // UPPER MENU BEGIN
    Spinner spinner_homeMenu;
    ArrayList<String> homeMenu;
    ArrayAdapter<String> adapter_homeMenu;

//    Button button_home;
//    Button button_get_a_tutor;
//    Button button_my_sessions;
//    Button button_logout;



    User user;
    // UPPER MENU END

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");
        Log.d("Test", user.getNetID());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this    fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // UPPER MENU BEGIN
//        button_home = (Button) view.findViewById(R.id.button_home);
//        button_get_a_tutor = (Button) view.findViewById(R.id.button_get_a_tutor);
//        button_my_sessions = (Button) view.findViewById(R.id.button_my_sessions);
//        button_logout = (Button) view.findViewById(R.id.button_logout);
        // UPPER MENU END


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // UPPER MENU BEGIN
        user = (User) this.getArguments().getSerializable("user");

        spinner_homeMenu = (Spinner) view.findViewById(R.id.spinner_homeMenu);

        homeMenu = new ArrayList<String>();

        int currFragmentIndex;

        if (user.isTutor() && user.isTutee()) { // StudentTutor
            homeMenu.add("Home");
            homeMenu.add("Get A Tutor");
            homeMenu.add("My Sessions");
            homeMenu.add("Change Availability");
            homeMenu.add("Change Courses");
            homeMenu.add("Logout");
        }
        else if (user.isTutor()) { // Tutor
            homeMenu.add("Home");
            homeMenu.add("My Sessions");
            homeMenu.add("Change Availability");
            homeMenu.add("Change Courses");
            homeMenu.add("Logout");
        }
        else if (user.isTutee()) { // Student
            homeMenu.add("Home");
            homeMenu.add("Get A Tutor");
            homeMenu.add("My Sessions");
            homeMenu.add("Logout");

        }
        else {
            // ERROR UNREACHABLE CASE
            homeMenu = null;
        }

        adapter_homeMenu = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, homeMenu);
        spinner_homeMenu.setAdapter(adapter_homeMenu);

        spinner_homeMenu.setOnItemSelectedListener(this);

        currFragmentIndex = 0; // home always 0
        spinner_homeMenu.setSelection(currFragmentIndex, true);
        spinner_homeMenu.setEnabled(true);
        spinner_homeMenu.setClickable(true);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_homeMenu) {
            String menuSelection = (String) spinner_homeMenu.getSelectedItem();

            Bundle userData = new Bundle();
            userData.putSerializable("user", user);

            switch(menuSelection) {
                case "Home":
//                    NavHostFragment.findNavController(com.ulc.tbr.fragments.common.home.Home.this)
//                            .navigate(R.id.action_home_to_home, userData);
                    // do nothing
                    // refresh home?
                    break;
                case "Get A Tutor":
                    NavHostFragment.findNavController(Home.this)
                            .navigate(R.id.action_home_to_get_a_tutor, userData);

                    break;
                case "My Sessions":
                    NavHostFragment.findNavController(Home.this)
                            .navigate(R.id.action_home_to_my_sessions, userData);

                    break;
                case "Change Availability":
                    NavHostFragment.findNavController(Home.this)
                            .navigate(R.id.action_home_to_set_tutor_availability, userData);

                    break;
                case "Change Courses":
                    NavHostFragment.findNavController(Home.this)
                            .navigate(R.id.action_home_to_set_tutor_courses, userData);

                    break;
                case "Logout" :
                    NavHostFragment.findNavController(Home.this)
                            .navigate(R.id.action_home_logout, userData);

                    break;
                default :
//                    NavHostFragment.findNavController(com.ulc.tbr.fragments.common.home.Home.this)
//                            .navigate(R.id.action_home_to_home, userData);
                    // do nothing
                    // refresh home?
                    break;
            }


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_homeMenu) {

        }

    }
    // UPPER MENU END
}