package com.ulc.tbr.fragments.common.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulc.tbr.R;

public class CalendarAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] slotText;
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


    public CalendarAdapter(Context c, String[] slotText) {
        mContext = c;
        this.slotText = slotText;
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