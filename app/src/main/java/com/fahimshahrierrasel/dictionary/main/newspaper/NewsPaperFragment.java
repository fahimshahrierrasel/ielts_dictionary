package com.fahimshahrierrasel.dictionary.main.newspaper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fahimshahrierrasel.dictionary.R;
import com.fahimshahrierrasel.dictionary.main.main.MainActivity;

public class NewsPaperFragment extends Fragment implements NewsPaperContract.View {
    /**
     * Android Views
     **/
    WebView webViewNewspaper;
    private String TAG = getClass().getSimpleName();
    private String url;
    private NewsPaperContract.Presenter newsPaperPresenter;

    /**
     * Android Views
     **/

    public NewsPaperFragment() {
    }

    public static NewsPaperFragment newInstance(String title, String url) {
        NewsPaperFragment newsPaperFragment = new NewsPaperFragment();
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        args.putString("URL", url);
        newsPaperFragment.setArguments(args);
        return newsPaperFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_newspaper, container, false);
        setHasOptionsMenu(true);
        bindViews(root);
        return root;
    }

    /**
     * Binds XML views
     * Call this function after layout is ready.
     **/
    private void bindViews(View rootView) {
        webViewNewspaper = rootView.findViewById(R.id.webView_newspaper);
        setUpWebViewDefaults(webViewNewspaper);
        webViewNewspaper.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                Log.d(TAG, "onPermissionRequest");
                getActivity().runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        if (request.getOrigin().toString().equals(url)) {
                            request.grant(request.getResources());
                        } else {
                            request.deny();
                        }
                    }
                });
            }
        });
        webViewNewspaper.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) view;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.newspaper, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_close_newspaper:
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return false;
    }

    @Override
    public void setNewsPaperUrl(String url) {
        this.url = url;
        webViewNewspaper.loadUrl(url);
    }

    @Override
    public Bundle getBundleArguments() {
        return getArguments();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        // Allow use of Local Storage
        settings.setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        webView.setWebViewClient(new WebViewClient());

        // AppRTC requires third party cookies to work
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webViewNewspaper, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        newsPaperPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        webViewNewspaper.evaluateJavascript("if(window.localStream){window.localStream.stop();}", null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsPaperPresenter.destroy();
    }

    @Override
    public void setFragmentTitle(String title) {
        if (getActivity() != null)
            ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void setPresenter(NewsPaperContract.Presenter presenter) {
        newsPaperPresenter = presenter;
    }
}
