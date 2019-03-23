package com.example.mp_wortspiel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Progress_Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress__graph);
        TextView text = (TextView) findViewById(R.id.graph_text);
        text.setText(getIntent().getExtras().getString("Progress"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.progress_logout, menu);
        return true;
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, Wortspiel_Game.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Back:
                Intent intent = new Intent(this, Wortspiel_Game.class);
                this.startActivity(intent);
                break;
            case R.id.Home:
                Intent home = new Intent(this, MainActivity.class);
                this.startActivity(home);
                break;
                

        }
        return super.onOptionsItemSelected(item);
    }

}
