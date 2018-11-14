package com.fahimshahrierrasel.dictionary.main.main;

import android.content.Context;

import com.fahimshahrierrasel.dictionary.main.BasePresenter;
import com.fahimshahrierrasel.dictionary.main.BaseView;
import com.fahimshahrierrasel.dictionary.main.data.model.NewsPaper;

import java.util.List;

public interface MainContract {
    interface Presenter extends BasePresenter {
        void getNewspapers();

        void onNewsPaperItemClicked(NewsPaper newsPaper);
    }

    interface View extends BaseView<MainContract.Presenter> {
        void populateNewspaperRecyclerView(List<NewsPaper> newsPapers);

        Context getFragmentContext();

        void openNewsPaperWebView(NewsPaper newsPaper);
    }
}
