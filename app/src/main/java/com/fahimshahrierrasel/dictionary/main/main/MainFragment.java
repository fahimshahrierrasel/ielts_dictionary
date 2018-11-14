package com.fahimshahrierrasel.dictionary.main.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.fahimshahrierrasel.dictionary.R;
import com.fahimshahrierrasel.dictionary.main.adapter.NewsPaperAdapter;
import com.fahimshahrierrasel.dictionary.main.data.model.NewsPaper;
import com.fahimshahrierrasel.dictionary.main.newspaper.NewsPaperContract;
import com.fahimshahrierrasel.dictionary.main.newspaper.NewsPaperFragment;
import com.fahimshahrierrasel.dictionary.main.newspaper.NewsPaperPresenter;

import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {
    private MainContract.Presenter mainPresenter;
    private NewsPaperContract.Presenter newsPaperPresenter;
    /**
     * Android Views
     **/
    SearchView searchView;
    RecyclerView recyclerViewNewspaper;

    /**
     * Android Views
     **/

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    /**
     * Binds XML views
     * Call this function after layout is ready.
     **/
    private void bindViews(View rootView) {
        searchView = rootView.findViewById(R.id.searchView);
        recyclerViewNewspaper = rootView.findViewById(R.id.recyclerView_newspaper);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        bindViews(root);
        return root;
    }

    @Override
    public void populateNewspaperRecyclerView(List<NewsPaper> newsPapers) {
        recyclerViewNewspaper.setAdapter(new NewsPaperAdapter(newsPapers, mainPresenter::onNewsPaperItemClicked));
        recyclerViewNewspaper.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public Context getFragmentContext() {
        return getActivity();
    }

    @Override
    public void openNewsPaperWebView(NewsPaper newsPaper) {
        NewsPaperFragment newsPaperFragment = NewsPaperFragment.newInstance(newsPaper.getName(), newsPaper.getUrl());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, newsPaperFragment)
                .addToBackStack(null)
                .commit();

        newsPaperPresenter = new NewsPaperPresenter(newsPaperFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.destroy();
    }

    @Override
    public void setFragmentTitle(String title) {
        if (getActivity() != null)
            ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mainPresenter = presenter;
    }
}
