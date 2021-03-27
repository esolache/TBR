package com.ulc.tbr.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    private final String studentID = "0000000000";
    private final String stutorID = "0000011111";
    private final String tutor1ID = "1111111111";
    private final String tutor2ID = "1111111112";
    private final String tutor3ID = "1111111113";
    private final String tutor4ID = "1111111114";

    //    private CoursesDBHelper coursesDB;
//    private TutorCoursesDBHelper tutorCourseDB;
//    private TutorAvailabilityDBHelper tutorAvailabilityDB;
//    private UsersDBHelper usersDB;
//    private MySessionsDBHelper mySessionsDB;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        coursesDB = new CoursesDBHelper(this);
//        tutorCourseDB = new TutorCoursesDBHelper(this);
//        tutorAvailabilityDB = new TutorAvailabilityDBHelper(this);
//        usersDB = new UsersDBHelper(this);
//        mySessionsDB = new MySessionsDBHelper(this);
        database = new DatabaseHelper(this);

        populateSessions();
        populateCourses();
        populateAvailability();
        populateUsers();
        populateTutorCoursesAvailability();

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public CoursesDBHelper getCoursesDB() { return coursesDB; }
//
//    public TutorCoursesDBHelper getTutorCourseDB() { return tutorCourseDB; }
//
//    public TutorAvailabilityDBHelper getTutorAvailabilityDB() { return tutorAvailabilityDB; }
//
//    public UsersDBHelper getUsersDB() { return usersDB; }
//
//    public MySessionsDBHelper getMySessionsDB() { return mySessionsDB; }

    public DatabaseHelper getDatabase() { return database; }


//_______Populate sessions database__________________________________________________________________________
    public void populateSessions(){
        try {
            database.addDataSession("0000000000", "1111111111", "03/28/2021", "09:00", "Mathematics", 341, "Liberry", "Do.  The.  Math.", -1);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }



        try {
            database.addDataSession("0000000000", "1111111112", "03/29/2021", "09:30", "Computer Science", 400, "Memorial Union", "Red black trees", -2);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }



        try {
            database.addDataSession("0000000000", "1111111114", "04/20/2021", "10:30", "Data Science and Engineering", 204, "Coffee Shop", "Wo yao cafe.", -3);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        // BEGIN STUTOR SESSIONS
            // tutor sessions
        try {
            database.addDataSession(studentID, stutorID, "04/21/2021", "10:30", "Data Science and Engineering", 204, "Coffee Shop", "Wo yao cafe.", -4);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addDataSession(studentID, stutorID, "04/22/2021", "07:00", "Computer Science", 400, "Memorial Union", "Red black trees", -5);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


            // student sessions
        try {
            database.addDataSession(stutorID, tutor1ID, "04/23/2021", "07:00", "Data Science and Engineering", 204, "Coffee Shop", "Wo yao cafe.", -6);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        // END STUTOR SESSIONS

    }
//_______Populate users database__________________________________________________________________________________
    public void populateUsers(){
        try {
            database.addData("0000000000", "student", "student", "Stu Dent", "student@wisc.edu", false, true);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addData("1111111111", "tutor1", "tutor1", "Super Smart", "tutor1@wisc.edu", true, false);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addData("1111111112", "tutor2", "tutor2", "Also Smart", "tutor2@wisc.edu", true, false);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addData("1111111113", "tutor3", "tutor3", "Kindof Smart", "tutor3@wisc.edu", true, false);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addData("1111111114", "tutor4", "tutor4", "Notso Smart", "tutor4@wisc.edu", true, false);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addData("0000011111", "stutor", "stutor", "Student Tutor", "stutor@wisc.edu", true, true);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }
    }

//_______Populate courses database__________________________________________________________________________________
    public void populateCourses(){
        try {
            database.addDataCourses("Computer Science", "Programming 1", 200);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }
        try {
            database.addDataCourses("Computer Science", "Programming 2", 300);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }
        try {
            database.addDataCourses("Computer Science", "Programming 3", 400);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }
        try {
            database.addDataCourses("Mathematics", "Linear Algebra", 341);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addDataCourses("Mathematics", "Calculus and Analytic Geometry I", 221);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addDataCourses("Mathematics", "Calculus and Analytic Geometry II", 222);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addDataCourses("Electrical and Computer Engineering", "Signals, Information, and Computation", 203);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addDataCourses("Electrical and Computer Engineering", "Data Science and Engineering", 204);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addDataCourses("Electrical and Computer Engineering", "Intoductory Experience in Electrical Engineering", 210);
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
    }

    public void populateTutorCoursesAvailability() {
        try {
            database.addTutorCourse("1111111111",  "Computer Science", 200);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addTutorCourse("1111111111",  "Computer Science", 300);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addTutorCourse("1111111111",  "Computer Science", 400);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addTutorCourse("1111111111",  "Mathematics", 221);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }


        try {
            database.addTutorCourse("1111111112", "Electrical and Computer Engineering", 203);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addTutorCourse("1111111112",  "Electrical and Computer Engineering", 204);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }
        try {
            database.addTutorCourse("1111111112",  "Electrical and Computer Engineering", 210);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }

        try {
            database.addTutorCourse("1111111112",  "Mathematics", 222);
        } catch (Exception e) {
            // La di da I have nothing to say.
        }


    }




//_______Populate tutor availability database__________________________________________________________________________________

    public void populateAvailability(){
        try {
            database.addAvailability("1111111112", "01/26/2021", "04:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "01/26/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "01/26/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "01/26/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/03/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/03/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addAvailability("1111111111", "02/03/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/03/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/08/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/08/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/08/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "02/08/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }



        try {
            database.addAvailability("1111111111", "02/16/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "02/16/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "02/16/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "02/16/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }




        try {
            database.addAvailability("1111111111", "02/25/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "02/25/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "02/25/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "02/25/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }





        try {
            database.addAvailability("1111111111", "03/10/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/10/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/10/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/10/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }




        try {
            database.addAvailability("1111111111", "03/17/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/17/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/17/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/17/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }




        try {
            database.addAvailability("1111111111", "03/25/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/25/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/25/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/25/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }





        try {
            database.addAvailability("1111111111", "03/28/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/28/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/28/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "03/28/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }




        try {
            database.addAvailability("1111111111", "04/05/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/05/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/05/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.
        }


        try {
            database.addAvailability("1111111111", "04/05/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }




        try {
            database.addAvailability("1111111111", "04/12/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/12/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/12/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/12/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }





        try {
            database.addAvailability("1111111111", "04/20/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/20/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/20/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.
        }


        try {
            database.addAvailability("1111111111", "04/20/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }



        try {
            database.addAvailability("1111111111", "04/27/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/27/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/27/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "04/27/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }





        try {
            database.addAvailability("1111111111", "05/03/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "05/03/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111111", "05/03/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111111", "05/03/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }



//____________Tutor 2______________________________________________________________________

        try {
            database.addAvailability("1111111112", "01/26/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.
        }



        try {
            database.addAvailability("1111111112", "01/26/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "01/26/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "01/26/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/03/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/03/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/03/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addAvailability("1111111112", "02/03/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/08/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/08/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/08/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/08/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/16/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/16/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111112", "02/16/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/16/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/25/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/25/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/25/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "02/25/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/10/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/10/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/10/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/10/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/17/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/17/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/17/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/17/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/25/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }
        try {
            database.addAvailability("1111111112", "03/25/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/25/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/25/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/28/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/28/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/28/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "03/28/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/05/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/05/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/05/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/05/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/12/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/12/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/12/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/12/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/20/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/20/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/20/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/20/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/27/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/27/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/27/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "04/27/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "05/03/2021", "09:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "05/03/2021", "09:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

        try {
            database.addAvailability("1111111112", "05/03/2021", "10:00");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }


        try {
            database.addAvailability("1111111112", "05/03/2021", "10:30");
        } catch (Exception e) {
            // La di da I have nothing to say.

        }

    }
}