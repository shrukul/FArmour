package com.example.shrukul.farmour;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;

import java.text.ParseException;
import java.util.List;

/**
 * Created by shrukul on 12/18/16.
 */

public class SellerList extends AppCompatActivity {

    private static final int MEAT_CHICKEN = 1;
    private static final int MEAT_MUTTON = 2;
    private static final int MEAT_PORK = 3;
    private static final int MEAT_SEAFOOD = 4;

    private static final int INSURANCE_FARM = 5;
    private static final int INSURANCE_VEHICLE = 6;
    private static final int INSURANCE_LIFE = 7;

    private List<Person> persons;
    TempData tempData;
    private RecyclerView rv;
    private Toolbar toolbar;
    LinearLayoutManager llm;
    ProgressDialog progressDialog;
    Transition fade;

    private static final String TAG = "SellerList";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.seller_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("item"));

        llm = new LinearLayoutManager(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        progressDialog = new ProgressDialog(SellerList.this,
                R.style.FarmourTheme_Dialog);

//        fillTempContent(getIntent().getIntExtra("type", 0));

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        Thread t = getThread();
        t.start();


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Transition makeEnterTransition() {
        Transition fade = new Fade();
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        return fade;
    }

    private void fillTempContent(int type) {

        TempData td = new TempData();
        switch (type) {
            case MEAT_CHICKEN:
                rv.setAdapter(new RVAdapter(td.getChickenList()));
                break;
            case MEAT_MUTTON:
                rv.setAdapter(new RVAdapter(td.getMuttonList()));
                break;
            case MEAT_PORK:
                rv.setAdapter(new RVAdapter(td.getPorkList()));
                break;
            case MEAT_SEAFOOD:
                rv.setAdapter(new RVAdapter(td.getSeaFoodList()));
                break;
            case INSURANCE_FARM:
                rv.setAdapter(new RVAdapter(td.getMuttonList()));
                break;
            case INSURANCE_VEHICLE:
                rv.setAdapter(new RVAdapter(td.getPorkList()));
                break;
            case INSURANCE_LIFE:
                rv.setAdapter(new RVAdapter(td.getSeaFoodList()));
                break;
        }

        progressDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public Thread getThread() {
        return new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {

                        wait(2000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fillTempContent(getIntent().getIntExtra("type", 0));
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        };
    }
}
