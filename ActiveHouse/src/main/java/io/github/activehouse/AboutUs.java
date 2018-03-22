//AH Studios
//ActiveHouseV2 Project
package io.github.activehouse;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView ryanMunro = (TextView)findViewById(R.id.ryanMunro);
        TextView oliverMunro = (TextView)findViewById(R.id.oliverMunro);
        TextView patrickMunro = (TextView)findViewById(R.id.patrickMunro);
        ryanMunro.setClickable(true);
        oliverMunro.setClickable(true);
        patrickMunro.setClickable(true);
        ryanMunro.setMovementMethod(LinkMovementMethod.getInstance());
        oliverMunro.setMovementMethod(LinkMovementMethod.getInstance());
        patrickMunro.setMovementMethod(LinkMovementMethod.getInstance());
        String link = "<a href='http://munro.humber.ca/~n01100308/index.html'> Ryan Website </a>";
        String link1 = "<a href='http://munro.humber.ca/~n01044719/index.html'> Oliver Website </a>";
        String link2 = "<a href='http://munro.humber.ca/~n01104570/index.html'> Patrick Website </a>";
        ryanMunro.setText(Html.fromHtml(link));
        oliverMunro.setText(Html.fromHtml(link1));
        patrickMunro.setText(Html.fromHtml(link2));


    }
}
