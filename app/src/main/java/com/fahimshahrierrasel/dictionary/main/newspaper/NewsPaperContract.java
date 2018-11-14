package com.fahimshahrierrasel.dictionary.main.newspaper;

import android.os.Bundle;

import com.fahimshahrierrasel.dictionary.main.BasePresenter;
import com.fahimshahrierrasel.dictionary.main.BaseView;

public interface NewsPaperContract {
    interface Presenter extends BasePresenter {
        String getNewsPaperUrl();
    }
    interface View extends BaseView<NewsPaperContract.Presenter> {
        void setNewsPaperUrl(String url);
        Bundle getBundleArguments();
    }
}
