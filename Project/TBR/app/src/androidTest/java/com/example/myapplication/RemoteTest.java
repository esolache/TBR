package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ulc.tbr.fragments.common.login.Mysingleton;
import com.ulc.tbr.models.util.Course;
import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.models.util.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 * Test
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class RemoteTest {
    private Context appContext;

    @Before
    public void createContext() {
        // Make sure DB is cleared out
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public  void login() {
        //    tutorSessions = new ArrayList<>();
        Log.i("RIGHT HERE", "HELL02");
        String url = "https://pistachio.khello.co/get_sessions_tutor.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String test = (String) jsonObject.get("name");
                    assertTrue(test.equals("Super Smart"));
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Some sort of unique string identifier here",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("net_id", "tutor1");
                Params.put("password", "tutor1");
                return Params;
            }
        };
        Mysingleton.getInstance(appContext).addTorequestque(stringRequest);
        //requestQueue.add(stringRequest);
    }

    @Test
    public  void getTutorSubject() {
        //    tutorSessions = new ArrayList<>();
        String tutor_id = "1111111111";
        Log.i("RIGHT HERE", "HELL02");
        String url = "https://pistachio.khello.co/get_sessions_tutor.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TUTOR","HIT THE DATABASE HURRAy");
                    Log.i("TUTOR", response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = (JSONArray) jsonObject.get("Sessions: ");
                    JSONObject jsonArray = (JSONObject) array.get(0);
                    String test = (String) jsonArray.get("subject");
                    Log.d("a",test);
                    assertTrue(test.equals("Computer Science"));
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Some sort of unique string identifier here",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("tutor_id", tutor_id);
                return Params;
            }
        };
        Mysingleton.getInstance(appContext).addTorequestque(stringRequest);
        //requestQueue.add(stringRequest);
    }

    @Test
    public  void getAvailTutor() {
        //    tutorSessions = new ArrayList<>();
        String tutor_id = "1111111111";
        String date = "03\\/28\\/2021";
        Log.i("RIGHT HERE", "HELL02");
        String url = "https://pistachio.khello.co/get_availability.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TUTOR","HIT THE DATABASE HURRAy");
                    Log.i("TUTOR", response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = (JSONArray) jsonObject.get("Availability: ");
                    JSONObject jsonArray = (JSONObject) array.get(0);
                    String test = (String) jsonArray.get("time");
                    assertTrue(test.equals("09:00"));
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Some sort of unique string identifier here",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.i("tutor_id",tutor_id);
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("tutor_id", tutor_id);
                return Params;
            }
        };
        Mysingleton.getInstance(appContext).addTorequestque(stringRequest);
        //requestQueue.add(stringRequest);
    }

    @Test
    public  void getStudentSubject() {
        //    tutorSessions = new ArrayList<>();
        String student_id = "1111111111";
        Log.i("RIGHT HERE", "HELL02");
        String url = "https://pistachio.khello.co/get_sessions_student.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TUTOR","HIT THE DATABASE HURRAy");
                    Log.i("TUTOR", response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = (JSONArray) jsonObject.get("Sessions: ");
                    JSONObject jsonArray = (JSONObject) array.get(0);
                    String test = (String) jsonArray.get("subject");
                    assertTrue(test.equals("Computer Science"));
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Some sort of unique string identifier here",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("student_id", student_id);
                return Params;
            }
        };
        Mysingleton.getInstance(appContext).addTorequestque(stringRequest);
        //requestQueue.add(stringRequest);
    }

    @Test
    public  void getSubjectCourses() {
        //    tutorSessions = new ArrayList<>();
        String tutor_id = "Mathematics";
        Log.i("RIGHT HERE", "HELL02");
        String url = "https://pistachio.khello.co/get_all_courses_by_subject.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TUTOR","HIT THE DATABASE HURRAy");
                    Log.i("TUTOR", response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = (JSONArray) jsonObject.get("Courses: ");
                    JSONObject jsonArray = (JSONObject) array.get(0);
                    String test = (String) jsonArray.get("course");
                    assertTrue(test.equals("Linear Algebra"));
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Some sort of unique string identifier here",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.i("tutor_id",tutor_id);
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("tutor_id", tutor_id);
                return Params;
            }
        };
        Mysingleton.getInstance(appContext).addTorequestque(stringRequest);
        //requestQueue.add(stringRequest);
    }

}
