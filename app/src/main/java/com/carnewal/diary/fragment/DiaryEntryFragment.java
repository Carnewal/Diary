package com.carnewal.diary.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carnewal.diary.R;
import com.carnewal.diary.model.DiaryEntry;
import com.carnewal.diary.persistence.Const;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class DiaryEntryFragment extends Fragment {

    @Bind(R.id.btnDone)
    public Button btnDone;

    @Bind(R.id.tvIntro)
    public TextView tvIntro;


    @Bind(R.id.etTitle)
    public EditText etTitle;

    @Bind(R.id.etContent)
    public EditText etContent;

    private DiaryEntry diaryEntry;
    private String mode;

    public DiaryEntryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diaryentry, container, false);
        ButterKnife.bind(this, view);

        diaryEntry = (DiaryEntry) getActivity().getIntent()
                .getExtras().getSerializable(Const.EXTRA_ENTRY);
        mode = getActivity().getIntent().getExtras().getString(Const.EXTRA_MODE);

        setup(diaryEntry, mode);


        return view;
    }

    private void setup(DiaryEntry de, String mode) {

        etTitle.setText(de.getTitle());
        etContent.setText(de.getContent());

        if(mode == Const.MODE_EDIT) {
            tvIntro.setText("Bladzijde bewerken");
            etTitle.setEnabled(true);
            etContent.setEnabled(true);
            btnDone.setText(R.string.action_save);

        } else {

            tvIntro.setText("Bladzijde bekijken");
            etTitle.setEnabled(false);
            etContent.setEnabled(false);
            btnDone.setText(R.string.action_edit);
        }


    }

    @OnClick(R.id.btnDone)
    public void actionButton() {
        if(mode == Const.MODE_EDIT) {

            Log.i("Save", "Now");
            getActivity().onBackPressed();
        } else {
            mode = Const.MODE_EDIT;
            setup(diaryEntry, mode);
        }
    }

}
