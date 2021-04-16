package com.ulc.tbr.fragments.common.mysessions;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.fragments.common.login.LoginFragment;
import com.ulc.tbr.fragments.common.login.Mysingleton;
import com.ulc.tbr.models.util.Session;
import com.ulc.tbr.models.users.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.ulc.tbr.fragments.common.mysessions.My_Sessions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class My_Sessions extends Fragment implements AdapterView.OnItemSelectedListener {

    // UPPER MENU BEGIN
    Spinner spinner_homeMenu;
    ArrayList<String> homeMenu;
    ArrayAdapter<String> adapter_homeMenu;

    ViewGroup view_viewGroup;


    User user;

    ArrayList<Session> tutorSessions;
    ArrayList<Session> studentSessions;

    ArrayAdapter<Session> adapter_tutorSessions;
    ArrayAdapter<Session> adapter_studentSessions;

    ListView listView_sessions;

    TextView textView_sessionType;

    Button button_studentSessions;
    Button button_tutorSessions;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public My_Sessions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment My_Sessions.
     */
    // TODO: Rename and change types and number of parameters
    public static My_Sessions newInstance(String param1, String param2) {
        My_Sessions fragment = new My_Sessions();
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

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_viewGroup = container;
        return inflater.inflate(R.layout.fragment_my__sessions, view_viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner_homeMenu = (Spinner) view.findViewById(R.id.spinner_homeMenu);

        homeMenu = new ArrayList<String>();

        int currFragmentIndex;

        Log.i("RIGHT HERE", "HELL0");
        getTutorSessions(user.getNetID());

//        if (user.isTutor() && user.isTutee()){
//            Log.i("RIGHT HERE", "HELL0");
//            tutorSessions = getTutorSessions(user.getNetID());
//            studentSessions = getStudentSessions(user.getNetID());
//        } else if (user.isTutor()) {
//            tutorSessions = getTutorSessions(user.getNetID());
//        } else {
//            studentSessions = getStudentSessions(user.getNetID());
//        }

            if (user.isTutor() && user.isTutee()) { // StudentTutor
            homeMenu.add("Home");
            homeMenu.add("Get A Tutor");
            homeMenu.add("My Sessions");
            homeMenu.add("Change Availability");
            homeMenu.add("Change Courses");
            homeMenu.add("Logout");

            currFragmentIndex = 2;
        }
        else if (user.isTutor()) { // Tutor
            homeMenu.add("Home");
            homeMenu.add("My Sessions");
            homeMenu.add("Change Availability");
            homeMenu.add("Change Courses");
            homeMenu.add("Logout");

            currFragmentIndex = 1;
        }
        else if (user.isTutee()) { // Student
            homeMenu.add("Home");
            homeMenu.add("Get A Tutor");
            homeMenu.add("My Sessions");
            homeMenu.add("Logout");

            currFragmentIndex = 2;
        }
        else {
            // ERROR UNREACHABLE CASE
            homeMenu = null;
            currFragmentIndex = 0;
        }

        adapter_homeMenu = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, homeMenu);
        spinner_homeMenu.setAdapter(adapter_homeMenu);

        spinner_homeMenu.setOnItemSelectedListener(this);
        spinner_homeMenu.setSelection(currFragmentIndex, true);
        spinner_homeMenu.setEnabled(true);
        spinner_homeMenu.setClickable(true);



        listView_sessions = view.findViewById(R.id.listView_sessions);

        textView_sessionType = view.findViewById(R.id.textView_sessionType);

        button_studentSessions = view.findViewById(R.id.button_studentSession);
        button_tutorSessions = view.findViewById(R.id.button_tutorSessions);

        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");

        if (user.isTutor() && user.isTutee() ){ // STUTOR CASE

            //textView.setText("Getting sessions for stutor with student id: " + user.getStudentID());
            button_studentSessions.setEnabled(true);
            button_tutorSessions.setEnabled(true);
            button_tutorSessions.setBackgroundColor(Color.RED);
            button_studentSessions.setBackgroundColor(Color.RED);
            button_tutorSessions.setVisibility(View.VISIBLE);
            button_studentSessions.setVisibility(View.VISIBLE);


            adapter_tutorSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, tutorSessions);

            adapter_studentSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, studentSessions);

        } else if (user.isTutor()){
            //textView.setText("Getting sessions for tutor with tutor id: " + user.getStudentID());
            button_studentSessions.setEnabled(false);
            button_tutorSessions.setEnabled(false);
            button_tutorSessions.setVisibility(View.GONE);
            button_studentSessions.setVisibility(View.GONE);


            adapter_tutorSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, tutorSessions);

            listView_sessions.setAdapter(adapter_tutorSessions);

        } else if (user.isTutee()) {
            //textView.setText("Getting sessions for student with tutor id: " + user.getStudentID());
            button_studentSessions.setEnabled(false);
            button_tutorSessions.setEnabled(false);
            button_tutorSessions.setVisibility(View.GONE);
            button_studentSessions.setVisibility(View.GONE);


            adapter_studentSessions =
                    new ArrayAdapter<Session>(getContext(), android.R.layout.simple_selectable_list_item, studentSessions);

            listView_sessions.setAdapter(adapter_studentSessions);
        } else {
            System.out.println("SOMETHING WENT WRONG: MY SESSIONS PAGE. LOGGED IN USER OBJECT IS BUNK");
        }


        listView_sessions.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        button_studentSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView_sessions.setAdapter(adapter_studentSessions);
            }
        });

        button_tutorSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView_sessions.setAdapter(adapter_tutorSessions);
            }
        });

                // listView.setAdapter(studentSessions);

        listView_sessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {


                Session session =(Session) (listView_sessions.getItemAtPosition(myItemInt));
                Bundle sessionDetails = new Bundle();
                sessionDetails.putSerializable("session", session);


                showSessionDetailsPopup(myView, session);

//                NavHostFragment.findNavController(com.example.myapplication.common.My_Sessions.this)
//                        .navigate(R.id.action_my_Sessions_to_sessionDetails, sessionDetails);


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_homeMenu) {
            String menuSelection = (String) spinner_homeMenu.getSelectedItem();

            Bundle userData = new Bundle();
            userData.putSerializable("user", user);

            switch(menuSelection) {
                case "Home":
                    NavHostFragment.findNavController(My_Sessions.this)
                            .navigate(R.id.action_my_sessions_to_home, userData);
                    break;
                case "Get A Tutor":
                    NavHostFragment.findNavController(My_Sessions.this)
                            .navigate(R.id.action_my_sessions_to_get_a_tutor, userData);

                    break;
//                case "My Sessions":
//                    NavHostFragment.findNavController(My_Sessions.this)
//                            .navigate(R.id.action_my_sessions_to_my_sessions, userData);
//
//                    break;
                case "Change Availability":
                    NavHostFragment.findNavController(My_Sessions.this)
                            .navigate(R.id.action_my_sessions_to_set_tutor_availability, userData);

                    break;
                case "Change Courses":
                    NavHostFragment.findNavController(My_Sessions.this)
                            .navigate(R.id.action_my_sessions_to_set_tutor_courses, userData);

                    break;
                case "Logout" :
                    NavHostFragment.findNavController(My_Sessions.this)
                            .navigate(R.id.action_my_sessions_logout, userData);

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


    public  void getTutorSessions(String tutor_id){
        Log.i("RIGHT HERE", "HELL02");
        String url = "https://pistachio.khello.co/get_sessions_tutor.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TUTOR","HIT THE DATABASE");
                Log.i("TUTOR",response.toString());
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
                Params.put("tutor_id", tutor_id);

                return Params;
            }
        };
        Mysingleton.getInstance(getContext()).addTorequestque(stringRequest);
    }

    public ArrayList<Session> getStudentSessions(String student_id){
        return  null;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_homeMenu) {

        }

    }
    // UPPER MENU END




    // CONFIRMATION AND ADD DESCRIPTION FOR CREATE SESSION
    public void showSessionDetailsPopup(View view, Session session) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.my_sessions_session_details_popup, view_viewGroup, false);

        PopupWindow popup = new PopupWindow(popupView, 1000, 1000, true);
        popup.setOutsideTouchable(true);
        //popup.setContentView(view);
        popup.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView textView = popupView.findViewById(R.id.sessionDetails);

        textView.setText(session.sessionDetails());

        Button button_dismiss = (Button) popupView.findViewById(R.id.button_dismiss);



        button_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });




    }

    public class StartAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("startAsyncTask", "start");
            return null;
        }
    }


}