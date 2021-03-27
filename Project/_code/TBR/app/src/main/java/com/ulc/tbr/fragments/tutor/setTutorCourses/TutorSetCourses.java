package com.ulc.tbr.fragments.tutor.setTutorCourses;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.ulc.tbr.R;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.databases.DatabaseHelper;

import com.ulc.tbr.models.users.*;
import com.ulc.tbr.models.util.*;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.ulc.tbr.fragments.tutor.setTutorCourses.TutorSetCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorSetCourses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "ListViewExample";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView selectListView;
    private ListView myClasses;
    private MainActivity ma;
    private DatabaseHelper dbHelper;
    private User user;

    Button button_addClasses;
    Button button_removeClasses;

    ArrayList<Course> courses;
    ArrayList<Course> myCourses;

    ArrayList<String> courseString;
    ArrayList<String> myCourseString;

    ArrayAdapter<String> courseArrayAdapter;
    ArrayAdapter<String> myClassesAdapter;


    public TutorSetCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutorSetClasses.
     */
    // TODO: Rename and change types and number of parameters
    public static com.ulc.tbr.fragments.tutor.setTutorCourses.TutorSetCourses newInstance(String param1, String param2) {
        com.ulc.tbr.fragments.tutor.setTutorCourses.TutorSetCourses fragment = new com.ulc.tbr.fragments.tutor.setTutorCourses.TutorSetCourses();
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
        ma = (MainActivity) getActivity();
        dbHelper = ma.getDatabase();
        Bundle bundle = this.getArguments();
        user = (User) bundle.getSerializable("user");
        return inflater.inflate(R.layout.fragment_tutor_set_classes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectListView = (ListView) view.findViewById(R.id.listView_availableClasses);
        myClasses = (ListView) view.findViewById(R.id.listView_myClasses);

        button_addClasses = view.findViewById(R.id.button_addTutorClasses);
        button_removeClasses = view.findViewById(R.id.button_removeTutorClasses);

        button_addClasses.setEnabled(true);
        button_addClasses.setClickable(true);
        button_addClasses.setBackgroundColor(Color.RED);
        button_removeClasses.setEnabled(true);
        button_removeClasses.setClickable(true);
        button_removeClasses.setBackgroundColor(Color.RED);


        ma = (MainActivity) getActivity();

//        TutorCoursesDBHelper tutorCoursesDBHelper = ma.getTutorCourseDB();
        courses = dbHelper.getDataCourses();
        myCourses = dbHelper.getTutorCourses(user.getStudentID());

        courseString = new ArrayList<String>();
        myCourseString = new ArrayList<String>();

        for(Course c : courses) {
            courseString.add(c.toStringSubjectCourseNo());
        }
        for(Course c : myCourses){
            myCourseString.add(c.toStringSubjectCourseNo());
            courseString.remove(c.toStringSubjectCourseNo());
        }
        this.selectListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.myClasses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        courseArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,courseString);
        myClassesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,myCourseString);
        this.myClasses.setAdapter(myClassesAdapter);
        this.selectListView.setAdapter(courseArrayAdapter);

        button_addClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = selectListView.getCheckedItemPositions();
                StringBuilder sb = new StringBuilder();

                try {

                    for (int i = 0; i < sp.size(); i++) {
                        int currIndex;
                        if (sp.valueAt(i)) {
                             currIndex = sp.keyAt(i);
                            //String currSelection = sp.toString();
                            //int currIndex = Integer.parseInt(currSelection.split("=")[0]);

                            String course = (String) selectListView.getItemAtPosition(currIndex);

                            String course_num = course.substring(course.lastIndexOf(" ") + 1);
                            String subject = course.substring(0, course.length() - course_num.length() - 1);

                            int courseNo = Integer.parseInt(course_num);

                            dbHelper.addTutorCourse(user.getStudentID(), subject, courseNo);

                            Toast.makeText(ma, "Course selection saved", Toast.LENGTH_LONG).show();
                        }



                    }
                } catch(Exception e){
                    Toast.makeText(ma, "Course selection not saved! Try again", Toast.LENGTH_LONG).show();
                }

                myCourses = dbHelper.getTutorCourses(user.getStudentID());


                courseString = new ArrayList<String>();
                myCourseString = new ArrayList<String>();
                for (Course c : courses) {
                    courseString.add(c.toStringSubjectCourseNo());
                }
                for (Course c : myCourses) {
                    myCourseString.add(c.toStringSubjectCourseNo());
                    courseString.remove(c.toStringSubjectCourseNo());
                }
                courseArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, courseString);
                myClassesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, myCourseString);
                myClasses.setAdapter(myClassesAdapter);
                selectListView.setAdapter(courseArrayAdapter);




            }





        });

        button_removeClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = myClasses.getCheckedItemPositions();
                StringBuilder sb = new StringBuilder();

                try {

                    for (int i = 0; i < sp.size(); i++) {
                        int currIndex;
                        if (sp.valueAt(i)) {
                            currIndex = sp.keyAt(i);
                            //String currSelection = sp.toString();
                            //int currIndex = Integer.parseInt(currSelection.split("=")[0]);

                            String course = (String) myClasses.getItemAtPosition(currIndex);

                            String course_num = course.substring(course.lastIndexOf(" ") + 1);
                            String subject = course.substring(0, course.length() - course_num.length() - 1);

                            int courseNo = Integer.parseInt(course_num);

                            dbHelper.deleteTutorCourse(user.getStudentID(), subject, courseNo);

                            Toast.makeText(ma, "Course selection saved", Toast.LENGTH_LONG).show();
                        }



                    }
                } catch(Exception e){
                    Toast.makeText(ma, "Course selection not saved! Try again", Toast.LENGTH_LONG).show();
                }

                myCourses = dbHelper.getTutorCourses(user.getStudentID());


                courseString = new ArrayList<String>();
                myCourseString = new ArrayList<String>();
                for (Course c : courses) {
                    courseString.add(c.toStringSubjectCourseNo());
                }
                for (Course c : myCourses) {
                    myCourseString.add(c.toStringSubjectCourseNo());
                    courseString.remove(c.toStringSubjectCourseNo());
                }
                courseArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, courseString);
                myClassesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, myCourseString);
                myClasses.setAdapter(myClassesAdapter);
                selectListView.setAdapter(courseArrayAdapter);




            }
        });


    }


}