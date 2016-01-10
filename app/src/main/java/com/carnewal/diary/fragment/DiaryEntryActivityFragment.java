package com.carnewal.diary.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carnewal.diary.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DiaryEntryActivityFragment extends Fragment {

    public DiaryEntryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diaryentry, container, false);
    }
}
