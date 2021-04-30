package com.ulc.tbr.fragments.student.getatutor;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.fragments.common.login.Mysingleton;
import com.ulc.tbr.models.util.*;
import com.ulc.tbr.models.users.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.ulc.tbr.fragments.student.getatutor.Get_A_Tutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Get_A_Tutor extends Fragment implements AdapterView.OnItemSelectedListener {
    String YEAR = "2021";
    static int sessionID = 0;
    MainActivity ma;
    DatabaseHelper database;
    String currentDate;

    ArrayList<String> remoteTutorsIDsSelectedCourse = new ArrayList<String>();;
    ArrayList<TutorAvailablity> remoteTutorAvailabilityOnDate = new ArrayList<TutorAvailablity>();
    ArrayList<TutorAvailablity> remoteResult = new ArrayList<TutorAvailablity>();
    ArrayList<TutorAvailablity> remote_available_session = new ArrayList<TutorAvailablity>();



    ArrayAdapter<TutorAvailablity> remote_adapter_session;

    ViewGroup view_viewGroup;

    User user;
    boolean isTutor;

    // UPPER MENU BEGIN
    Spinner spinner_homeMenu;
    ArrayList<String> homeMenu;
    ArrayAdapter<String> adapter_homeMenu;
    // UPPER MENU BEGIN
//    Button button_home;
//    Button button_get_a_tutor;
//    Button button_my_sessions;
//    Button button_logout;
    // UPPER MENU END

    TextView textView_get_a_tutor;
    TextView textView_date;
    TextView textView_subject;
    TextView textView_course;

    Spinner spinner_week;
    ArrayList<String> available_week;
    ArrayAdapter<String> adapter_week;

    Boolean first = true;
    Boolean test = true;

    Button button_prevWeek;
    Button button_nextWeek;


    Spinner spinner_subject;
    ArrayList<String> available_subject;
    ArrayAdapter<String> adapter_subject;

    Spinner spinner_course;
    ArrayList<String> available_course_Default;
    ArrayAdapter<String> adapter_course_Default;
    ArrayList<String> available_course_ECE;
    ArrayAdapter<String> adapter_course_ECE;
    ArrayList<String> available_course_CS;
    ArrayAdapter<String> adapter_course_CS;
    ArrayList<String> available_course_MATH;
    ArrayAdapter<String> adapter_course_MATH;


    ArrayList<Course> available_courses;
    ArrayList<Course> available_courses_CS;
    ArrayList<Course> available_courses_ECE;
    ArrayList<Course> available_courses_MATH;

    // tutoravailability for week and subject|course selected
    ListView listView_session;
    ArrayList<TutorAvailablity> tutorAvailablity_session;
    ArrayList<String> available_session;
    ArrayAdapter<String> adapter_session;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "userID";
    private static final String ARG_PARAM2 = "userID";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Get_A_Tutor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Get_A_Tutor.
     */
    // TODO: Rename and change types and number of parameters
    public static Get_A_Tutor newInstance(String param1, String param2) {
        Get_A_Tutor fragment = new Get_A_Tutor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view_viewGroup = container;
        View view = inflater.inflate(R.layout.fragment_get__a__tutor, view_viewGroup, false);

        // add popup window items
        //view = inflater.inflate(R.layout.get_a_tutor_confirm_session_popup, view_viewGroup, false);

        // Inflate the layout for this fragment
        return view;//inflater.inflate(R.layout.fragment_get__a__tutor, view_viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        remote_adapter_session = new ArrayAdapter<TutorAvailablity>(getContext(), android.R.layout.simple_list_item_1, remote_available_session );

//        remoteTutorsIDsSelectedCourse = new ArrayList<String>();
        user = (User) this.getArguments().getSerializable("user");
        boolean isTutor = user.isTutor(); // use this to erase entries of self

        // UPPER MENU BEGIN
        // user = (User) this.getArguments().getSerializable("user");

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

            currFragmentIndex = 1;
        }
        else if (user.isTutor()) { // Tutor
            homeMenu.add("Home");
            homeMenu.add("My Sessions");
            homeMenu.add("Change Availability");
            homeMenu.add("Change Courses");
            homeMenu.add("Logout");

            currFragmentIndex = 0; // tutor should not be able to navigate to this page
        }
        else if (user.isTutee()) { // Student
            homeMenu.add("Home");
            homeMenu.add("Get A Tutor");
            homeMenu.add("My Sessions");
            homeMenu.add("Logout");

            currFragmentIndex = 1;

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


        // user = (User) getActivity().getIntent().getSerializableExtra("user");

        Bundle userData = new Bundle();
        userData.putSerializable("user", user);


        ma = (MainActivity) getActivity();
        database = ma.getDatabase();

        textView_get_a_tutor = (TextView) view.findViewById(R.id.textView_get_a_tutor);
        textView_date        = (TextView) view.findViewById(R.id.textView_date);
        textView_subject     = (TextView) view.findViewById(R.id.textView_subject);
        textView_course      = (TextView) view.findViewById(R.id.textView_course);

        button_prevWeek       = (Button) view.findViewById(R.id.button_prevWeek);
        button_nextWeek       = (Button) view.findViewById(R.id.button_nextWeek);

        //SPINNER INITIALIZATION
        spinner_week          = (Spinner) view.findViewById(R.id.spinner_week);
        spinner_subject       = (Spinner) view.findViewById(R.id.spinner_subject);
        spinner_course        = (Spinner) view.findViewById(R.id.spinner_course);

        //LISTVIEW INITIAZLIZATION
        listView_session = (ListView) view.findViewById(R.id.listView_timeblock);



        // BEGIN WEEK BUTTONS
        button_prevWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                remote_adapter_session.clear();
                if (spinner_week.getSelectedItem() != null) {
                    int curr_week_pos = spinner_week.getSelectedItemPosition();
                    if (curr_week_pos > 0) {
                        curr_week_pos--;
                        spinner_week.setSelection(curr_week_pos, true);
//                        populateSessionListView();
                    }
                }
                else {
                    spinner_week.setSelection(0, true);
                }
            }
        });

        button_nextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                remote_adapter_session.clear();
                if (spinner_week.getSelectedItem() != null) {
                    int curr_week_pos = spinner_week.getSelectedItemPosition();
                    if (curr_week_pos < available_week.size() - 1) {
                        curr_week_pos++;
                        spinner_week.setSelection(curr_week_pos, true);
//                        populateSessionListView();
                    }
                }
                else {
                    spinner_week.setSelection(0, true);
                }
            }
        });
        // END WEEK BUTTONS
        // END WEEK

        weekHelper();
        subjectHelper();
        courseHelper();
        listViewHelper();


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner_homeMenu) {
            String menuSelection = (String) spinner_homeMenu.getSelectedItem();

            Bundle userData = new Bundle();
            userData.putSerializable("user", user);

            switch(menuSelection) {
                case "Home":
                    NavHostFragment.findNavController(Get_A_Tutor.this)
                            .navigate(R.id.action_get_a_tutor_to_home, userData);
//                     do nothing
//                     refresh home?
                    break;
//                case "Get A Tutor":
//                    NavHostFragment.findNavController(Get_A_Tutor.this)
//                            .navigate(R.id.action_home_to_get_a_tutor, userData);
//
//                    break;
                case "My Sessions":
                    NavHostFragment.findNavController(Get_A_Tutor.this)
                            .navigate(R.id.action_get_a_tutor_to_my_sessions, userData);

                    break;
                case "Change Availability":
                    NavHostFragment.findNavController(Get_A_Tutor.this)
                            .navigate(R.id.action_get_a_tutor_to_set_tutor_availability, userData);

                    break;
                case "Change Courses":
                    NavHostFragment.findNavController(Get_A_Tutor.this)
                            .navigate(R.id.action_get_a_tutor_to_set_tutor_courses, userData);

                    break;
                case "Logout" :
                    NavHostFragment.findNavController(Get_A_Tutor.this)
                            .navigate(R.id.action_get_a_tutor_logout, userData);

                    break;
                default :
//                    NavHostFragment.findNavController(com.ulc.tbr.fragments.common.home.Home.this)
//                            .navigate(R.id.action_home_to_home, userData);
                    // do nothing
                    // refresh home?
                    break;
            }


        }

        if (parent.getId() == R.id.spinner_week) {
            if (first) {
                //button_prevWeek.setEnabled(false);
                //button_prevWeek.setClickable(false);
                //button_prevWeek.setBackgroundColor(Color.GRAY);
                //button_nextWeek.setEnabled(false);
                //button_nextWeek.setClickable(false);
                //button_nextWeek.setBackgroundColor(Color.GRAY);
                first = false;

            } else {

                spinner_subject.setEnabled(true);
                spinner_subject.setClickable(true);
                button_prevWeek.setEnabled(true);
                button_prevWeek.setClickable(true);
                button_prevWeek.setBackgroundColor(Color.RED);
                button_nextWeek.setEnabled(true);
                button_nextWeek.setClickable(true);
                button_nextWeek.setBackgroundColor(Color.RED);




                if (spinner_subject.getSelectedItemPosition() != 0 && spinner_course.getSelectedItemPosition() != 0) {
                    String week;
                    String subject;
                    String course;
                    Log.i("FML","KILL ME");

//                    week = (String) spinner_week.getSelectedItem();
//                    subject = (String) spinner_subject.getSelectedItem();
//                    course = (String) spinner_course.getSelectedItem();
//                    course = course.substring(course.lastIndexOf(" ")+1);

//                    tutorAvailablity_session = populateAvailableTutorSessions(week, subject, course);
//                    available_session = loadTutorAvailabilityToString(tutorAvailablity_session);
                  //  remote_available_session = new ArrayList<TutorAvailablity>();
                    remote_adapter_session.clear();
                    //remote_available_session = loadTutorAvailability(week, subject, course);
                    populateSessionListView();



//                    listViewHelper();

                    // updateCalendar();
                }


            }
        }

        if (parent.getId() == R.id.spinner_subject) {

            switch ( (String) spinner_subject.getSelectedItem() ) {
                case "Select a Subject":
                    spinner_course.setAdapter(adapter_course_Default);
                    System.out.println("adapter_course_Default");
                    spinner_course.setSelection(0);
                    break;
                case "Electrical and Computer Engineering":
                    System.out.println("adapter_course_ECE");
                    available_courses_ECE = database.getAllCoursesBySubject("Electrical and Computer Engineering");
                    available_course_ECE = populateCourses(available_courses_ECE);
                    adapter_course_ECE = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_ECE);
                    spinner_course.setAdapter(adapter_course_ECE);

                    break;
                case "Computer Science":
                    System.out.println("adapter_course_CS");
                    available_courses_CS = database.getAllCoursesBySubject("Computer Science");
                    available_course_CS = populateCourses(available_courses_CS);
                    adapter_course_CS = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_CS);
                    spinner_course.setAdapter(adapter_course_CS);
                    break;
                case "Mathematics":
                    System.out.println("adapter_course_MATH");
                    available_courses_MATH = database.getAllCoursesBySubject("Mathematics");
                    available_course_MATH = populateCourses(available_courses_MATH);
                    adapter_course_MATH = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_MATH);
                    spinner_course.setAdapter(adapter_course_MATH);
                    break;
                default:
                    spinner_course.setAdapter(adapter_course_Default);
                    break;
            }


            if (spinner_subject.getSelectedItemPosition() != 0) { // If a subject is selected
                spinner_course.setEnabled(true);
                spinner_course.setClickable(true);
            } else {
                spinner_course.setEnabled(false);
                spinner_course.setClickable(false);
            }

        }
        if (parent.getId() == R.id.spinner_course) {
            if (spinner_course.getSelectedItemPosition() != 0) { // If a subject is selected
                populateSessionListView();
            }

        }

    }


    private void populateSessionListView() {
        String week;
        String subject;
        String course;

        week = (String) spinner_week.getSelectedItem();
        subject = (String) spinner_subject.getSelectedItem();
        course = (String) spinner_course.getSelectedItem();
        course = course.substring(course.lastIndexOf(" ")+1);


        populateAvailableTutorSessions(week, subject, course);
//        available_session = loadTutorAvailabilityToString(tutorAvailablity_session);
//        adapter_session = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, available_session );
        remote_adapter_session = new ArrayAdapter<TutorAvailablity>(getContext(), android.R.layout.simple_list_item_1, remote_available_session );
//        listView_session.setAdapter(adapter_session);
        listView_session.setAdapter(remote_adapter_session);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_week) {
            button_prevWeek.setEnabled(false);
            button_prevWeek.setBackgroundColor(Color.GRAY);
            button_nextWeek.setEnabled(false);
            button_nextWeek.setBackgroundColor(Color.GRAY);

            spinner_subject.setEnabled(false);
            spinner_subject.setClickable(false);
            spinner_course.setEnabled(false);
            spinner_course.setClickable(false);
        }
        if (parent.getId() == R.id.spinner_subject) {
            spinner_course.setEnabled(false);
        }
        if (parent.getId() == R.id.spinner_course) {
            // DISABLE CALENDAR

        }

    }

    private void listViewHelper() {
        listView_session.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set up my session
                String studentID;
                String tutorID;
                String date;
                String time;
                String subject;
                int courseNo = 0;
                String location;
                String description;

                String week;
                String course;

                String courseNum;
                String listviewsession;


                // class variable that stores all tutor avaialbiltity for selected week subject|coursenum
                //tutorAvailablity_session = new ArrayList<TutorAvailablity>();

                // toString version of tutorAvailablity_session for displaying in listview
                //available_session = new ArrayList<String>();




                week = (String) spinner_week.getSelectedItem();
                course = (String) spinner_course.getSelectedItem();
                course = course.substring(course.lastIndexOf(" ")+1);


                subject = (String) spinner_subject.getSelectedItem();
                courseNum = (String) spinner_course.getSelectedItem();
                courseNum = courseNum.substring(courseNum.lastIndexOf(" ")+1);


                try {
                    Log.d("___________________________","D");
                    courseNo = Integer.parseInt(courseNum);

                    // we now have to match
                    Log.i("GFFFFdaasdFFSAD", listView_session.getAdapter().getItem(position).toString());
                    TutorAvailablity selectedTutorAvailability = (TutorAvailablity) listView_session.getAdapter().getItem(position);
                    //String [] availability;

                    //availability = listviewsession.split(" ");


                    Session newSession = new Session(user.getStudentID(),selectedTutorAvailability.getTutorId(),selectedTutorAvailability.getDate(),selectedTutorAvailability.getTime(), subject, courseNo, "Virtual", "", sessionID++);

                    showSessionConfirmationPopup(view, newSession, selectedTutorAvailability);


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERROR CLICKING ON TIMEBLOCK. COURSE NUMBER DIDNT WORK OUT");
                }



            }
        });
    }

    private void weekHelper() {
        // BEGIN WEEK
        available_week = new ArrayList<String>();
        // TODO: NEED A GOOD WAY TO LOAD THESE FOR ONLY VALID WEEKS EACH SEMESTER
        // THIS WILL DO FOR NOW THO
        // ADMIN ACTIVITY TO SET WEEKS?

    ////////////////////////////////////////////////////////////////////////////////////////////////

        Calendar cal = Calendar.getInstance();
        int currWeek = cal.get(Calendar.WEEK_OF_YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        currentDate = sdf.format(cal.getTime());


        available_week.add("Select a week");

        for(int i= 0; i< 15; i++){
            cal.set(Calendar.WEEK_OF_YEAR, currWeek + i);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String monday = sdf.format(cal.getTime());
            cal.set(Calendar.WEEK_OF_YEAR, currWeek + i + 1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String sunday = sdf.format(cal.getTime());
            available_week.add(monday + " - " + sunday);
        }


    ////////////////////////////////////////////////////////////////////////////////////////////////

        adapter_week = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, available_week);
        adapter_week.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_week.setAdapter(adapter_week);
        spinner_week.setEnabled(true);
        spinner_week.setOnItemSelectedListener(this);
        spinner_week.setSelection(0, true);

        button_prevWeek.setEnabled(true);
        button_prevWeek.setClickable(true);
        button_prevWeek.setBackgroundColor(Color.RED);
        button_nextWeek.setEnabled(true);
        button_nextWeek.setClickable(true);
        button_nextWeek.setBackgroundColor(Color.RED);

    }

    private void subjectHelper(){
        // BEGIN SUBJECT
        available_subject = database.getAllSubjects();
        available_subject.add(0, "Select a Subject");
        adapter_subject = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_subject);
        spinner_subject.setAdapter(adapter_subject);
        spinner_subject.setOnItemSelectedListener(this);
        spinner_subject.setSelection(0, true);
        spinner_subject.setEnabled(false);
        spinner_subject.setClickable(false);
        // END SUBJECT
    }

    private void courseHelper() {

        // BEGIN COURSE
        available_course_Default = new ArrayList<String>();
        adapter_course_Default = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, available_course_Default);
        available_course_Default.add("Select a course");
        available_course_Default.add("No courses available");

        spinner_course.setAdapter(adapter_course_Default);

        spinner_course.setOnItemSelectedListener(this);
        spinner_course.setSelection(0, true);
        spinner_course.setEnabled(false);
        spinner_course.setClickable(false);
        // END COURSE
    }

    private ArrayList<String> populateCourses(ArrayList<Course> courses) {
        ArrayList<String> result = new ArrayList<String>();

        result.add("Select a Course");
        for (int i = 0; i < courses.size(); i++) {
            result.add(courses.get(i).toString());
        }

        return result;
    }


    private void remoteGetTutorAvailability(String date, String subject, String courseNo){
//        Log.i("Turtle", "HELL02");
        remote_adapter_session.clear();
        Log.i("Shit","Butt");

        String url = "https://pistachio.khello.co/get_available_tutors.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("Shitouttaluck", "bub");
                    JSONObject jsonObject = new JSONObject(response);
//                    Log.i("Shit","Butt2");
                    JSONArray array = (JSONArray) jsonObject.get("Availability: ");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonArray = (JSONObject) array.get(i);

                        remote_adapter_session.add(new TutorAvailablity(
                                (String) jsonArray.get("name"),
                                (String) jsonArray.get("tutor_id"),
                                (String) jsonArray.get("date"),
                                (String) jsonArray.get("time"),
                                Boolean.parseBoolean(String.valueOf(jsonArray.get("booked")))
                                )
                        );
                        Log.i("Student",(String) jsonArray.get("student_id"));

                    }
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Shit","Fuck");
                Log.i("Some sort of unique string identifier here",error.toString());
                user = null;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.i("Shit","Fuck2");
                Log.i("date",date);
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("date", date);
                Params.put("subject", subject);
                Params.put("course_num", courseNo);
                return Params;
            }
        };
        Mysingleton.getInstance(getContext()).addTorequestque(stringRequest);
        //requestQueue.add(stringRequest);
//        return remoteTutorAvailabilityOnDate;
    }


    private ArrayList<String> loadTutorAvailabilityToString(ArrayList<TutorAvailablity> availablity) {
        ArrayList<String> result;

        result = new ArrayList<String>();
        for (int i = 0; i < availablity.size(); i++) {
            String date = availablity.get(i).getDate();

            result.add(availablity.get(i).toStringTutorName());
        }

        return result;
    }


    // MAIN METHOD FOR POPULATING TUTOR AVAILABILITY FOR SESSIONS FOR WEEK AND SUBJECT|COURSE
    private void populateAvailableTutorSessions(String week, String subject, String course) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        try {
            String monday = week.split(" - ")[0];
            String sunday = week.split(" - ")[1];

            Date mondayDate = sdf.parse(monday);
            Date sundayDate = sdf.parse(sunday);
            Date currDate = mondayDate;

            cal.setTime(mondayDate);

            while (!currDate.after(sundayDate)) {

                String currDateString = sdf.format(currDate);
                Log.i("ASSAHHH", currDateString);

                remoteGetTutorAvailability(currDateString, subject, course);

                cal.add(Calendar.DATE, 1);
                currDate = cal.getTime();
            }

        } catch (Exception e) {

            return;
        }

        return;
    }

    public void remoteAddSession(Session session){
        Log.i("Input",session.getStudentID() + " " + session.getTutorID() + " " + session.getDate() + " " + session.getTime() + " " + session.getSubject() + " " + String.valueOf(session.getCourseNo()) + " " + session.getLocation() + " " + session.getDescription() + " " +  String.valueOf(session.getSessionID()));


        String url = "https://pistachio.khello.co/post_new_sessions.php";

        RequestQueue requestQueue = Mysingleton.getInstance(getContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("ADDED SESSION", response);

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
                Params.put("student_id", session.getStudentID());
                Params.put("tutor_id", session.getTutorID());
                Params.put("date", session.getDate());
                Params.put("time", session.getTime());
                Params.put("subject", session.getSubject());
                Params.put("course_number", String.valueOf(session.getCourseNo()));
                Params.put("location", session.getLocation());
                Params.put("description", session.getDescription());
                //Params.put("session_id", String.valueOf(session.getSessionID()));

                return Params;
            }
        };
        Mysingleton.getInstance(getContext()).addTorequestque(stringRequest);
    }

    public void updateAvailBookSession(Session session) {
        String url = "https://pistachio.khello.co/update_session_availability.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("E","it got here");
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
                Params.put("tutor_id", session.getTutorID());
                Params.put("date", session.getDate());
                Params.put("time", session.getTime());
                Params.put("booked", "TRUE");
                return Params;
            }
        };
        Mysingleton.getInstance(getContext()).addTorequestque(stringRequest);

    }

    // CONFIRMATION AND ADD DESCRIPTION FOR CREATE SESSION
    public void showSessionConfirmationPopup(View view, Session session, TutorAvailablity availablity) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.get_a_tutor_confirm_session_popup, view_viewGroup, false);

        PopupWindow popup = new PopupWindow(popupView, 1000 , 1000, true);
        popup.setOutsideTouchable(true);
        //popup.setContentView(view);
        popup.showAtLocation(view, Gravity.CENTER, 0, 0);


        Spinner spinner_location = (Spinner) popupView.findViewById(R.id.spinner_location);
        ArrayList<String> available_location;
        available_location = new ArrayList<String>();
        available_location.add("Virtual");
        ArrayAdapter<String> adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, available_location );
        spinner_location.setAdapter(adapter_location);
        spinner_location.setSelection(0, true);

        EditText editText_description = (EditText) popupView.findViewById(R.id.editText_description);
        Button button_confirm = (Button) popupView.findViewById(R.id.button_confirm);
        Button button_cancel = (Button) popupView.findViewById(R.id.button_cancel);

        button_confirm.setVisibility(View.VISIBLE);
        button_confirm.setEnabled(true);
        button_confirm.setBackgroundColor(Color.RED);

        button_cancel.setVisibility(View.VISIBLE);
        button_cancel.setEnabled(true);
        button_cancel.setBackgroundColor(Color.RED);

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = editText_description.getText().toString();
                String location = (String) spinner_location.getSelectedItem();

                session.setDescription(description);
                session.setLocation(location);

                remoteAddSession(session);
                updateAvailBookSession(session);

                remote_adapter_session.clear();
                populateSessionListView();

                popup.dismiss();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        // popupWindow.showAtLocation(popupView, Gravity.AXIS_Y_SHIFT, 0, 10);
    }

}


