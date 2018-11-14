package com.fahimshahrierrasel.dictionary.main.newspaper;

import android.os.Bundle;

public class NewsPaperPresenter implements NewsPaperContract.Presenter {
    private NewsPaperContract.View newsPaperView;

    public NewsPaperPresenter(NewsPaperContract.View newsPaperView) {
        this.newsPaperView = newsPaperView;
        this.newsPaperView.setPresenter(this);
    }

    @Override
    public String getNewsPaperUrl() {
        Bundle args = newsPaperView.getBundleArguments();
        return args.getString("URL");
    }

    @Override
    public void addFragmentTitle() {
        Bundle args = newsPaperView.getBundleArguments();
        String title = args.getString("TITLE");
        newsPaperView.setFragmentTitle(title);
    }

    @Override
    public void start() {
        addFragmentTitle();
        String url = getNewsPaperUrl();
        if(url != null)
            newsPaperView.setNewsPaperUrl(url);
    }

    @Override
    public void destroy() {
        newsPaperView = null;
    }
}
