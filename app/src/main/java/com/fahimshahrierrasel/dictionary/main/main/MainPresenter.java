package com.fahimshahrierrasel.dictionary.main.main;

import com.fahimshahrierrasel.dictionary.R;
import com.fahimshahrierrasel.dictionary.main.data.model.NewsPaper;
import com.fahimshahrierrasel.dictionary.main.data.source.local.NewsPaperData;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mainView;
    private NewsPaperData newsPaperData;
    private List<NewsPaper> newsPapers;

    MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        this.mainView.setPresenter(this);
    }

    @Override
    public void getNewspapers() {
        newsPaperData = new NewsPaperData(mainView.getFragmentContext());
        newsPapers = newsPaperData.getNewsPapers();
    }

    @Override
    public void onNewsPaperItemClicked(NewsPaper newsPaper) {
        mainView.openNewsPaperWebView(newsPaper);
    }

    @Override
    public void addFragmentTitle() {
        mainView.setFragmentTitle(
                mainView.getFragmentContext()
                        .getResources()
                        .getString(R.string.app_name)
        );
    }

    @Override
    public void start() {
        addFragmentTitle();
        getNewspapers();
        mainView.populateNewspaperRecyclerView(newsPapers);
    }

    @Override
    public void destroy() {
        mainView = null;
    }
}
