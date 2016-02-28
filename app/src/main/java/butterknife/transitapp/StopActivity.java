package butterknife.transitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.transitapp.StopActivityRelated.AsyncTaskRunner;
import butterknife.transitapp.StopActivityRelated.Feed;
import butterknife.transitapp.StopActivityRelated.StopRecyclerAdapter;

public class StopActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
            case R.id.action_search:
                EditText stopNumberInput = (EditText) findViewById(R.id.stopNumberInput);
                String stopNumber = stopNumberInput.getText().toString();
                AsyncTaskRunner r = new AsyncTaskRunner(stopNumber);
                r.execute();
                if (Feed.getStopData() != null) {
                    mRecyclerView = (RecyclerView) findViewById(R.id.stop_recycler_view);
                    // using and setting a linear layout manager
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    // specifying an adapter
                    mAdapter = new StopRecyclerAdapter(Feed.getStopData());
                    mRecyclerView.setAdapter(mAdapter);
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
