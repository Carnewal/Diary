package com.carnewal.diary.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.carnewal.diary.R;
import com.carnewal.diary.adapter.DiaryAdapter;
import com.carnewal.diary.model.DiaryEntry;
import com.carnewal.diary.persistence.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

// ListActivity gaf problemen met actionbar
public class DiaryListActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private DiaryAdapter mAdapter;

    @Bind(R.id.entryList)
    public ListView entryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarylist);
        ButterKnife.bind(this);
        getLoaderManager().initLoader(0, null, this);
        mAdapter = new DiaryAdapter(this, R.layout.listitem_diary);
        setListAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diarylist, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add) {
            Intent addEntryIntent = new Intent(this,DiaryAddActivity.class);
            startActivity(addEntryIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(DiaryListActivity.this, Const.CONTENT_PROVIDER_URL_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.setCursor(data);
        Log.i("Done","done");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.setCursor(null);
    }

    @OnItemClick(R.id.entryList)
    public void onListItemClick(int pos) {

        Log.i("Click", pos + "");
        DiaryEntry de = mAdapter.getItem(pos);
        Intent intent = new Intent(this, DiaryEntryActivity.class);
        intent.putExtra(Const.EXTRA_ENTRY, de);
        intent.putExtra(Const.EXTRA_MODE, Const.MODE_VIEW);


        startActivity(intent);
    }


    private void setListAdapter(DiaryAdapter adapter) {
        entryListView.setAdapter(adapter);
    }



}
