package com.android.imdbsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText Name;
    //EditText Author;
    ProgressBar progressBar;
    //ImageButton back, forward, stop, refresh, homeButton;
    Button SearchA;
    Button SearchB;
    Button Caution;
    Button Clear;
    ImageButton Lens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name =findViewById(R.id.name);
        //Author=findViewById(R.id.author);
        SearchA = findViewById(R.id.searcha);
        SearchB = findViewById(R.id.searchg);
        progressBar =findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);
        webView =findViewById(R.id.webView);
        Caution=findViewById(R.id.caution);
        Clear=findViewById(R.id.clear);
        Lens=findViewById(R.id.lens);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(Name.getWindowToken(), 0);
        //InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromWindow(Author.getWindowToken(), 0);
        //final int numa = Integer.parseInt(Name.getText().toString());
        //final int numb = Integer.parseInt(Author.getText().toString());



        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setSupportMultipleWindows(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    progressBar.setProgress(newProgress);
                    if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                    }
                    if (newProgress == 100) {
                        progressBar.setVisibility(ProgressBar.GONE);
                    }else{
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                    }
                }
            });
        }

        webView.setWebViewClient(new MyWebViewClient());
        SearchA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(!NetworkState.connectionAvailable(MainActivity.this)) {
                        //webView.setText("Error");
                        //Toast.makeText(MainActivity.this, R.string.check_connection, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                    }
                    else {


                            //webView.loadUrl("https://www.goodreads.com/search?q=" + Name.getText().toString());
                            //Name.setText("");
                            webView.loadUrl("https://www.amazon.in/s?k=" + Name.getText().toString()+"&i=digital-text&crid=372NH80S4GANE&sprefix=Alice%2Cdigital-text%2C471&ref=nb_sb_ss_i_1_5");
                            //Name.setText("");

                        }


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        SearchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(!NetworkState.connectionAvailable(MainActivity.this)) {
                        //webView.setText("Error");
                        //Toast.makeText(MainActivity.this, R.string.check_connection, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                    }
                    else {


                        webView.loadUrl("https://www.goodreads.com/search?q=" + Name.getText().toString());
                        //Name.setText("");
                        //webView.loadUrl("https://www.amazon.in/s?k=" + Name.getText().toString()+"&i=digital-text&crid=372NH80S4GANE&sprefix=Alice%2Cdigital-text%2C471&ref=nb_sb_ss_i_1_5");
                        //Name.setText("");



                        // my code
                        //webView.loadUrl("https://www.goodreads.com/search?q=" + Name.getText().toString());
                        //String text = "<html><body>Please Enter a <b>Title</b>.</body></html>";
                        //webView.loadData(text, "text/html; charset=utf-8", "utf-8");


                    }


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Caution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String data = "<html>\n" +
                            "<body>\n" +
                            "<h1>Caution!</h1>\n" +
                            "<p><h3>1. Do not submit passwords through this app.</h3></p>\n" +
                            "<p><h3>2. Do not make a purchase from this app.</h3></p>\n" +
                            "<p></p>\n" +
                            "</body>\n" +
                            "</html>\n";
                    webView.loadData(data, "text/html", "UTF-8");


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    webView.loadUrl("about:blank");


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Lens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(!NetworkState.connectionAvailable(MainActivity.this)) {
                        Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.ar.lens");
                        if (launchIntent != null) {
                            startActivity(launchIntent);//null pointer check in case package name was not found
                        }
                    }


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




    }
}
//TODO: Free click on button fix.
//TODO: XD integration - rounded webview.
//TODO: Camera icon
//prsentaion color change