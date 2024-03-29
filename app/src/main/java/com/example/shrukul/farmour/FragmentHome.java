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
public class FragmentHome extends Fragment implements View.OnClickListener {

    private final String serverUrl = "http://bucksbuddy.pe.hu/index.php";
    CardView produce, invest, meat, insurance, farmtech, support;

    TextView amt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
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

        produce = (CardView) getView().findViewById(R.id.produce);
        invest = (CardView) getView().findViewById(R.id.invest);
        meat = (CardView) getView().findViewById(R.id.meat);
        insurance = (CardView) getView().findViewById(R.id.insurance);
        farmtech = (CardView) getView().findViewById(R.id.farmtech);
        support = (CardView) getView().findViewById(R.id.support);

        produce.setOnClickListener(this);
        invest.setOnClickListener(this);
        meat.setOnClickListener(this);
        insurance.setOnClickListener(this);
        farmtech.setOnClickListener(this);
        support.setOnClickListener(this);
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
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        switch (v.getId()) {
            case R.id.produce:
                FragmentProd fragment_prod = new FragmentProd();
                fragmentTransaction.replace(R.id.frame, fragment_prod);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.invest:
                FragmentInvest fragmentInvest = new FragmentInvest();
                fragmentTransaction.replace(R.id.frame, fragmentInvest);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.meat:
                FragmentMeat fragment_meat = new FragmentMeat();
                fragmentTransaction.replace(R.id.frame, fragment_meat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.insurance:
                FragmentInsurance fragment_insurance = new FragmentInsurance();
                fragmentTransaction.replace(R.id.frame, fragment_insurance);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.farmtech:
                FragmentFarmTech fragmentFarmTech = new FragmentFarmTech();
                fragmentTransaction.replace(R.id.frame, fragmentFarmTech);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.support:
                FragmentSupport fragmentSupport = new FragmentSupport();
                fragmentTransaction.replace(R.id.frame, fragmentSupport);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }
}
