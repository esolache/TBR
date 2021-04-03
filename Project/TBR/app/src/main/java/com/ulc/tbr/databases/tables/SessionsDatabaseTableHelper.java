//package com.ulc.tbr.databases.tables;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.ulc.tbr.databases.DatabaseHelper;
//import com.ulc.tbr.models.util.Session;
//
//public class SessionsDatabaseTableHelper {
//
//    private DatabaseHelper db;
//
//    // SESSION TABLE
//    public static final String TABLE_NAME_SESSION = "sessions_table";
//    public static final String COL_1_SESSION = "student_id";
//    public static final String COL_2_SESSION= "tutor_id";
//    public static final String COL_3_SESSION= "date";
//    public static final String COL_4_SESSION = "time";
//    public static final String COL_5_SESSION = "subject";
//    public static final String COL_6_SESSION = "course_num";
//    public static final String COL_7_SESSION = "location";
//    public static final String COL_8_SESSION = "description";
//    public static final String COL_9_SESSION = "session_id";
//    public static final String CREATE_TABLE_SESSION = "CREATE TABLE "+TABLE_NAME_SESSION+
//            "("+
//            COL_1_SESSION +" VARCHAR(30), "+
//            COL_2_SESSION +" VARCHAR(30), "+
//            COL_3_SESSION +" VARCHAR(30), "+
//            COL_4_SESSION +" CHAR(30), "+
//            COL_5_SESSION +" VARCHAR(30), "+
//            COL_6_SESSION +" INTEGER(3), "+
//            COL_7_SESSION +" VARCHAR(30), "+
//            COL_8_SESSION +" TEXT, "+
//            COL_9_SESSION +" INTEGER(8), "+
//            "PRIMARY KEY(student_id, tutor_id, date, time)"+
//            ")";
//    // SESSIONS TABLE
//
//    public SessionsDatabaseTableHelper(DatabaseHelper db) {
//        this.db = db;
//    }
//
//    /* SECTION FOR SESSIONS START */
//
//    public boolean addDataSession(Session session) throws Exception {
//        long result;
//
//        try {
//            SQLiteDatabase db = this.db.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(COL_1_SESSION, session.getStudentID());
//            contentValues.put(COL_2_SESSION, session.getTutorID());
//            contentValues.put(COL_3_SESSION, session.getDate());
//            contentValues.put(COL_4_SESSION, session.getTime());
//            contentValues.put(COL_5_SESSION, session.getSubject());
//            contentValues.put(COL_6_SESSION, session.getCourseNo());
//            contentValues.put(COL_7_SESSION, session.getLocation());
//            contentValues.put(COL_8_SESSION, session.getDescription());
//            contentValues.put(COL_9_SESSION, session.getSessionID());
//            result = db.insert(TABLE_NAME_SESSION,null,contentValues);
//
//        }
//        catch (Exception e) {
//            // System.out.println("You can't add a session like that"));
//            result = -1;
//        }
//
//
//        if (result == -1){
//            Exception e = new Exception("You can't add a session like that");
//            throw e;
//        }
//        return true;
//    }
//
//
//
//}
