package com.example.shrukul.farmour;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.SEARCH_SERVICE;

/**
 * Created by Admin on 04-06-2015.
 */
public class FragmentProd extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragmentProd";
    private final String serverUrl = "http://bucksbuddy.pe.hu/index.php";
    CardView pulses, cereals, vegetables, fruits, oil, food_grain, dairy, processed_food;

    TextView amt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_prod, container, false);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pulses = (CardView) getView().findViewById(R.id.pulses);
        cereals= (CardView) getView().findViewById(R.id.cereals);
        vegetables = (CardView) getView().findViewById(R.id.vegetables);
        fruits = (CardView) getView().findViewById(R.id.fruits);
        oil = (CardView) getView().findViewById(R.id.oil);
        food_grain = (CardView) getView().findViewById(R.id.food_grains);
        dairy = (CardView) getView().findViewById(R.id.dairy);
        processed_food = (CardView) getView().findViewById(R.id.processed_food);

        pulses.setOnClickListener(this);
        cereals.setOnClickListener(this);
        vegetables.setOnClickListener(this);
        fruits.setOnClickListener(this);
        oil.setOnClickListener(this);
        food_grain.setOnClickListener(this);
        dairy.setOnClickListener(this);
        processed_food.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(getActivity(), SubCategory.class);
        switch (v.getId()) {
            case R.id.pulses:
                it.putExtra("type", "Pulses");
                break;
            case R.id.cereals:
                it.putExtra("type", "Cereals");
                break;
            case R.id.vegetables:
                it.putExtra("type", "Vegetables");
                break;
            case R.id.fruits:
                it.putExtra("type", "Fruits");
                break;
            case R.id.oil:
                it.putExtra("type", "Edible Oil");
                break;
            case R.id.food_grains:
                it.putExtra("type", "Food Grains");
                break;
            case R.id.dairy:
                it.putExtra("type", "Dairy");
                break;
            case R.id.processed_food:
                it.putExtra("type", "Processed Food");
                break;
        }
        startActivity(it);
        getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"item"+item);
        switch (item.getItemId()) {
            case android.R.id.home:
                // Called when the up caret in actionbar is pressed
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
