package com.ulc.tbr.fragments.tutor.setTutorAvailability;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.models.util.*;
import com.ulc.tbr.models.users.*;
import com.ulc.tbr.fragments.common.Adapters.CalendarAdapter;
import static com.ulc.tbr.fragments.common.Adapters.CalendarAdapter.gridSlots;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.ulc.tbr.fragments.tutor.setTutorAvailability.SetTutorAvailability#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetTutorAvailability extends Fragment implements AdapterView.OnItemSelectedListener {

    // UPPER MENU BEGIN
    Spinner spinner_homeMenu;
    ArrayList<String> homeMenu;
    ArrayAdapter<String> adapter_homeMenu;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper dbHelper;
    // Required empty public constructor
    private User user;
    private Spinner spinner_week;
    private ArrayList<String> available_week;
    private ArrayAdapter<String> adapter_week;
    private GridView calendar;
    String[] headerText = {"Time","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    String[] slotText = {
            "07:00am","","","","","","","",
            "07:30am","","","","","","","",
            "08:00am","","","","","","","",
            "08:30am","","","","","","","",
            "09:00am","","","","","","","",
            "09:30am","","","","","","","",
            "10:00am","","","","","","","",
            "10:30am","","","","","","","",
            "11:00am","","","","","","","",
            "11:30am","","","","","","","",
            "12:00pm","","","","","","","",
            "12:30pm","","","","","","","",
            "01:00pm","","","","","","","",
            "01:30pm","","","","","","","",
            "02:00pm","","","","","","","",
            "02:30pm","","","","","","","",
            "03:00pm","","","","","","","",
            "03:30pm","","","","","","","",
            "04:00pm","","","","","","","",
            "04:30pm","","","","","","","",
            "05:00pm","","","","","","","",
            "05:30pm","","","","","","","",
            "06:00pm","","","","","","","",
            "06:30pm","","","","","","","",
            "07:00pm","","","","","","","",
            "07:30pm","","","","","","",""
    } ;


    public SetTutorAvailability() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tutorSetDateAndTime.
     */
    // TODO: Rename and change types and number of parameters
    public static SetTutorAvailability newInstance(String param1, String param2) {
        SetTutorAvailability fragment = new SetTutorAvailability();
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
        // Inflate the layout for this fragment
        MainActivity ma = (MainActivity) getActivity();
        dbHelper = ma.getDatabase();
        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");
//        user = (User) getActivity().getIntent().getSerializableExtra("user");
        return inflater.inflate(R.layout.fragment_tutor_set_date_and_time, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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

            currFragmentIndex = 3;
        }
        else if (user.isTutor()) { // Tutor
            homeMenu.add("Home");
            homeMenu.add("My Sessions");
            homeMenu.add("Change Availability");
            homeMenu.add("Change Courses");
            homeMenu.add("Logout");

            currFragmentIndex = 2;
        }
        else if (user.isTutee()) { // Student
            homeMenu.add("Home");
            homeMenu.add("Get A Tutor");
            homeMenu.add("My Sessions");
            homeMenu.add("Logout");

            currFragmentIndex = 0; // student should not be able to navigate here
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


        CalendarAdapter adapter = new CalendarAdapter(getContext(),slotText);
        CalendarAdapter headerAdapter = new CalendarAdapter(getContext(),headerText);

        Button confirm = (Button) view.findViewById(R.id.confirm_availability);

        GridView header = (GridView) view.findViewById(R.id.calendar_header);
        header.setAdapter(headerAdapter);


        calendar=(GridView) view.findViewById(R.id.calendar);
        calendar.setAdapter(adapter);
        calendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "You Clicked at " +slotText[+ position], Toast.LENGTH_SHORT).show();
                if(!spinner_week.getSelectedItem().toString().equals("Select a week")) {
                    int row = (position / 8);
                    int col = position % 8;
                    if (gridSlots[row][col] == 1) {
                        gridSlots[row][col] = 0;
                    } else if(gridSlots[row][col] == 0) {
                        gridSlots[row][col] = 1;
                    }else if(gridSlots[row][col] == 2){
                        gridSlots[row][col] = 3;
                    }else if(gridSlots[row][col] == 3) {
                        gridSlots[row][col] = 2;
                    }
                    adapter.notifyDataSetChanged();

                }
            }
        });


        spinner_week          = (Spinner) view.findViewById(R.id.selectWeek);
        //////////////////////////////////////////////////////////////////////////////////

        // BEGIN WEEK
        available_week = new ArrayList<String>();
        // TODO: NEED A GOOD WAY TO LOAD THESE FOR ONLY VALID WEEKS EACH SEMESTER
        // THIS WILL DO FOR NOW THO
        // ADMIN ACTIVITY TO SET WEEKS?
        available_week.add("Select a week");
        available_week.add("01/24 - 01/30");
        available_week.add("01/31 - 02/06");
        available_week.add("02/07 - 02/13");
        available_week.add("02/14 - 02/20");
        available_week.add("02/21 - 02/27");
        available_week.add("02/28 - 03/06");
        available_week.add("03/07 - 03/13");
        available_week.add("03/14 - 03/20");
        available_week.add("03/21 - 03/27");
        available_week.add("03/28 - 04/03");
        available_week.add("04/04 - 04/10");
        available_week.add("04/11 - 04/17");
        available_week.add("04/18 - 04/24");
        available_week.add("04/25 - 05/01");
        available_week.add("05/02 - 05/08");

        adapter_week = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, available_week);
//        adapter_week.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_week.setAdapter(adapter_week);
        spinner_week.setSelection(0);
        spinner_week.setEnabled(true);
        spinner_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int[] row : gridSlots) {
                    Arrays.fill(row, 0);
                }
                adapter.notifyDataSetChanged();
                if(!spinner_week.getSelectedItem().toString().equals("Select a week")) {
//                    03/28 - 04/03
                    String[] week = weekConverter(spinner_week.getSelectedItem().toString());
                    for (String date : week) {
                        ArrayList<TutorAvailablity> availList = dbHelper.getTutorAvailabilityOnDate(user.getStudentID(), date);
                        try {
                            for (TutorAvailablity avail : availList) {
                                String tempDate = avail.getDate();
                                String tempTime = avail.getTime();
                                Boolean tempBooked = avail.isBooked();
                                if (!tempBooked) {
                                    int col = dayToColumn(tempDate, spinner_week.getSelectedItem().toString());
                                    int row = timeToRow(tempTime);
                                    gridSlots[row][col] = 2;
                                } else {
                                    int col = dayToColumn(tempDate, spinner_week.getSelectedItem().toString());
                                    int row = timeToRow(tempTime);
                                    gridSlots[row][col] = 4;
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //////////////////////////////////////////////////////////////////////////////////

        if(!spinner_week.getSelectedItem().toString().equals("Select a week")){
            Toast.makeText(getContext(), "You selected a week", Toast.LENGTH_SHORT).show();
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 26; i++){
                    for(int j= 0; j < 8; j++){
                        if(gridSlots[i][j]==1){
                            String time = timeConverter(slotText[i*8]);
                            Log.d("DAT","Date");
                            String date = dateConverter(spinner_week.getSelectedItem().toString(), j);
                            String tutorID = user.getStudentID();
                            dbHelper.addAvailability(tutorID,date,time);
                            gridSlots[i][j] = 2;
                        }else if(gridSlots[i][j]==3){
                            String time = timeConverter(slotText[i*8]);
                            String date = dateConverter(spinner_week.getSelectedItem().toString(), j);
                            String tutorID = user.getStudentID();
                            dbHelper.deleteAvailability(tutorID,date,time);
                            gridSlots[i][j] = 0;
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
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
                    NavHostFragment.findNavController(SetTutorAvailability.this)
                            .navigate(R.id.action_set_tutor_availability_to_home, userData);

                    break;
                case "Get A Tutor":
                    NavHostFragment.findNavController(SetTutorAvailability.this)
                            .navigate(R.id.action_set_tutor_availability_to_get_a_tutor, userData);

                    break;
                case "My Sessions":
                    NavHostFragment.findNavController(SetTutorAvailability.this)
                            .navigate(R.id.action_set_tutor_availability_to_my_sessions, userData);

                    break;
                case "Change Courses":
                    NavHostFragment.findNavController(SetTutorAvailability.this)
                            .navigate(R.id.action_set_tutor_availability_to_set_tutor_courses, userData);

                    break;
                case "Logout" :
                    NavHostFragment.findNavController(SetTutorAvailability.this)
                            .navigate(R.id.action_set_tutor_availability_logout, userData);

                    break;
                default :
                    break;
            }


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_homeMenu) {

        }

    }



    public String dateConverter(String week, int col){
        String toReturn = "";
        int daysLeft = 7 - Integer.parseInt(week.substring(11));
        if(!week.substring(0,2).equals(week.substring(8,10)) && daysLeft < col) {
            int date = col - daysLeft ;
            if(date >= 10) {
                toReturn = week.substring(8, 11) + String.valueOf(date) + "/2021";
            }else{
                toReturn = week.substring(8, 11) + "0" + String.valueOf(date) + "/2021";
            }
        }else{
            int date = Integer.parseInt(week.substring(3,5)) + col - 1;
            if(date>=10){
                toReturn = week.substring(0,3) + String.valueOf(date) + "/2021";
            }else{
                toReturn = week.substring(0,3) + "0" + String.valueOf(date) + "/2021";
            }
        }
        return toReturn;
    }

    public String timeConverter(String time){
        int hour = Integer.parseInt(time.substring(0,2));
        if(time.substring(5).equals("pm") && hour != 12){
            hour += 12;
        }
        String toReturn;
        if(hour >= 10) {
            toReturn = String.valueOf(hour) + ":" + time.substring(3, 5);
        }else{
            toReturn = "0" + String.valueOf(hour) + ":" + time.substring(3, 5);
        }
        return toReturn;
    }
    public int timeToRow(String time){
        int row = (Integer.parseInt(time.substring(0,2)) - 7)*2;
        if(time.substring(3,5).equals("30")){
            row += 1;
        }

        return row;
    }
    public int dayToColumn(String day, String week){
        int col = 0;
        if(week.substring(0,2).equals(day.substring(0,2))){
            int start = Integer.parseInt(week.substring(3,5));
            col = Integer.parseInt(day.substring(3,5)) - start + 1;
        }else{
            int diff = Integer.parseInt(week.substring(11)) - Integer.parseInt(day.substring(3,5));
            col = 7 - diff;
        }
        return col;
    }
    public String[] weekConverter(String week){
        String[] toReturn = new String[7];
        int day = Integer.parseInt(week.substring(11));
        int i = 0;
        while(day!=0 && i!=7){
            String sDay;
            if(day >= 10){
                sDay = String.valueOf(day);
            }else{
                sDay = "0" + String.valueOf(day);
            }
            toReturn[6-i] = week.substring(8,11)+ sDay + "/2021";
            i++;
            day--;
        }

        if(day==0 && i!=7){
            int day2 = Integer.parseInt(week.substring(3,5));

            for(int j=0; j< 7-i; j++) {
                String sDay = sDay = String.valueOf(day2);
                toReturn[j] = week.substring(0, 3) + sDay + "/2021";
                day2++;
            }
        }

        return toReturn;
    }

}