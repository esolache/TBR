package com.ulc.tbr.fragments.common.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulc.tbr.R;
import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.models.users.User;
import com.ulc.tbr.models.util.TutorAvailablity;

import java.util.ArrayList;
import java.util.Arrays;

public class CalendarAdapter extends BaseAdapter {
    private Context mContext;
    private static String[] slotText;
    private static DatabaseHelper dbHelper;
    public static int[][] gridSlots = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };


    public CalendarAdapter(Context c, String[] slotText, DatabaseHelper dbHelper) {
        mContext = c;
        this.slotText = slotText;
        this.dbHelper = dbHelper;
    }

    @Override
    public int getCount() {
        return slotText.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    private static int timeToRow(String time){
        int row = (Integer.parseInt(time.substring(0,2)) - 7)*2;
        if(time.substring(3,5).equals("30")){
            row += 1;
        }

        return row;
    }
    private static int dayToColumn(String day, String week){
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
    public static String dateConverter(String week, int col){
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

    public static String timeConverter(String time){
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

    public void clearCalendar(){
        for (int[] row : gridSlots) {
            Arrays.fill(row, 0);
        }
        notifyDataSetChanged();
    }
    public void update(String week, User user){
        for(int i = 0; i < 26; i++){
            for(int j= 0; j < 8; j++){
                if(gridSlots[i][j]==1){
                    String time = timeConverter(slotText[i*8]);
                    String date = dateConverter(week, j);
                    String tutorID = user.getStudentID();
                    dbHelper.addAvailability(tutorID,date,time);
                    gridSlots[i][j] = 2;
                }else if(gridSlots[i][j]==3){
                    String time = timeConverter(slotText[i*8]);
                    String date = dateConverter(week, j);
                    String tutorID = user.getStudentID();
                    dbHelper.deleteAvailability(tutorID,date,time);
                    gridSlots[i][j] = 0;
                }
            }
        }
        notifyDataSetChanged();
    }
    public void populateCalendar(ArrayList<TutorAvailablity> availList, String week){
        //Weeks array in format [03/28/2021][03/29/2021]
        for (TutorAvailablity avail : availList) {
            String tempDate = avail.getDate();
            String tempTime = avail.getTime();
            Boolean tempBooked = avail.isBooked();
            if (!tempBooked) {
                int col = dayToColumn(tempDate, week);
                int row = timeToRow(tempTime);
                gridSlots[row][col] = 2;
            } else {
                int col = dayToColumn(tempDate, week);
                int row = timeToRow(tempTime);
                gridSlots[row][col] = 4;
            }

        }
        notifyDataSetChanged();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.calendar_slot, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            textView.setText(slotText[position]);
        } else {
            grid = (View) convertView;
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            if (gridSlots[position / 8][position % 8] == 1) {
                textView.setBackgroundResource(R.drawable.select);
            } else if (gridSlots[position / 8][position % 8] == 2) {
                textView.setBackgroundResource(R.drawable.selected);
            } else if (gridSlots[position / 8][position % 8] == 3) {
                textView.setBackgroundResource(R.drawable.cancellable);
            } else if (gridSlots[position / 8][position % 8] == 4) {
                textView.setBackgroundResource(R.drawable.booked);
            } else {
                textView.setBackgroundResource(R.drawable.border);
            }
        }
        return grid;
    }
}