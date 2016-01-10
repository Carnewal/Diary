package com.carnewal.diary.fragment;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.carnewal.diary.R;
import com.carnewal.diary.persistence.Const;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class DiaryAddFragment extends Fragment {

    @Bind(R.id.etTitle)
    public EditText  etTitle;

    @Bind(R.id.etContent)
    public EditText etContent;


    public DiaryAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diaryadd, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.btnDone)
    public void finishEntry() {
        ContentValues values = new ContentValues();
        values.put(Const.ENTRY_COLUMN_TITLE, etTitle.getText().toString() );
        values.put(Const.ENTRY_COLUMN_RECORDDATE, new Date().toString());
        values.put(Const.ENTRY_COLUMN_CONTENT, etContent.getText().toString() );

        Uri diaryUri = getActivity().getApplicationContext().getContentResolver().insert(Const.CONTENT_PROVIDER_URL_URI, values);
    }
}
