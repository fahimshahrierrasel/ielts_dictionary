package com.fahimshahrierrasel.dictionary.main;

public interface BaseView<T> {
    void setFragmentTitle(String title);
    void setPresenter(T presenter);
}
