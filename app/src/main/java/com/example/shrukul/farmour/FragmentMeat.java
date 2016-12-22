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
public class FragmentMeat extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragmentMeat";
    private final String serverUrl = "http://bucksbuddy.pe.hu/index.php";
    CardView chicken, mutton, pork, seafood;

    TextView amt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meat, container, false);
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

        chicken = (CardView) getView().findViewById(R.id.chicken);
        mutton= (CardView) getView().findViewById(R.id.mutton);
        pork = (CardView) getView().findViewById(R.id.pork);
        seafood= (CardView) getView().findViewById(R.id.seafood);

        chicken.setOnClickListener(this);
        mutton.setOnClickListener(this);
        pork.setOnClickListener(this);
        seafood.setOnClickListener(this);
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
        Intent it = new Intent(getActivity(), SellerList.class);
        switch (v.getId()) {
            case R.id.chicken:
                it.putExtra("type", 1);
                it.putExtra("item", "Chicken");
                break;
            case R.id.mutton:
                it.putExtra("type", 2);
                it.putExtra("item", "Mutton");
                break;
            case R.id.pork:
                it.putExtra("type", 3);
                it.putExtra("item", "Pork");
                break;
            case R.id.seafood:
                it.putExtra("type", 4);
                it.putExtra("item", "SeaFood");
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