package com.carnewal.diary.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.carnewal.diary.R;
import com.carnewal.diary.activity.DiaryListActivity;
import com.carnewal.diary.model.DiaryEntry;
import com.carnewal.diary.persistence.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brecht on 10/01/2016.
 */
public class DiaryAdapter extends ArrayAdapter<DiaryEntry> {

    private List<DiaryEntry> diary;

    public DiaryAdapter(Context context, int resource, List<DiaryEntry> list) {
        super(context, resource, list);
        if(diary == null) {
            diary = new ArrayList<>();
        } else {
            diary = list;
        }

    }

    public DiaryAdapter(Context context, int resource) {
        super(context, resource);
        diary = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return diary.size();
    }

    @Override
    public DiaryEntry getItem(int position) {
        return diary.get(position);
    }

    @Override
    public long getItemId(int position) {
        return diary.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DiaryEntry entry = getItem(position);

        // Could really use a viewholder :)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_diary, parent, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.diarylist_title);
        TextView tvDate = (TextView) view.findViewById(R.id.diarylist_date);

        tvTitle.setText(diary.get(position).getTitle());
        tvDate.setText(diary.get(position).getDateCreated());

        return view;
    }



    public void setCursor(Cursor data) {

        diary.clear();

        if (data != null) {
            data.moveToFirst();
            while (!data.isAfterLast()) {
                String title, content, date;
                Long id;

                title = data.getString(data.getColumnIndex(Const.ENTRY_COLUMN_TITLE));
                content = data.getString(data.getColumnIndex(Const.ENTRY_COLUMN_CONTENT));
                id = data.getLong(data.getColumnIndex(Const.ENTRY_COLUMN_ID));
                date = data.getString(data.getColumnIndex(Const.ENTRY_COLUMN_RECORDDATE));

                DiaryEntry de = new DiaryEntry(id, title, content, date);
                diary.add(de);

                data.moveToNext();
            }
        }


        notifyDataSetChanged();

    }

}