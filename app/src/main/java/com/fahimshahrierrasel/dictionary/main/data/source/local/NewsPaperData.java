package com.fahimshahrierrasel.dictionary.main.data.source.local;

import android.content.Context;
import android.content.res.Resources;

import com.fahimshahrierrasel.dictionary.R;
import com.fahimshahrierrasel.dictionary.main.data.model.NewsPaper;

import java.util.ArrayList;
import java.util.List;

public class NewsPaperData {
    private Context context;
    private List<NewsPaper> newsPapers;

    public NewsPaperData(Context context) {
        this.newsPapers = new ArrayList<>();
        this.context = context;
        populateNewsPaper();
    }

    private void populateNewsPaper() {
        Resources resource = context.getResources();
        newsPapers.add(new NewsPaper(resource.getString(R.string.prothom_alo),
                resource.getString(R.string.prothom_alo_desc), resource.getString(R.string.prothom_alo_url),
                R.drawable.prothomalo));

        newsPapers.add(new NewsPaper(resource.getString(R.string.the_daily_star),
                resource.getString(R.string.the_daily_star_desc), resource.getString(R.string.the_daily_star_url),
                R.drawable.thedailystar));

        newsPapers.add(new NewsPaper(resource.getString(R.string.the_independent),
                resource.getString(R.string.the_independent_desc), resource.getString(R.string.the_independent_url),
                R.drawable.theindependent));

    }

    public List<NewsPaper> getNewsPapers() {
        return newsPapers;
    }

    public void setNewsPapers(List<NewsPaper> newsPapers) {
        this.newsPapers = newsPapers;
    }
}
