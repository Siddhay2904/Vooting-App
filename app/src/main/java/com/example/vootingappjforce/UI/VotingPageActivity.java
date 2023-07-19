package com.example.vootingappjforce.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.vootingappjforce.Adapters.CandidateModel;
import com.example.vootingappjforce.Adapters.DBHelper;
import com.example.vootingappjforce.R;

import java.util.ArrayList;

public class VotingPageActivity extends AppCompatActivity {
    RadioButton rBtn1,rBtn2,rBtn3,rBtn4;
    Button voteBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page);
        rBtn1 = findViewById(R.id.RBtn1);
        rBtn2 = findViewById(R.id.RBtn2);
        rBtn3 = findViewById(R.id.RBtn3);
        rBtn4 = findViewById(R.id.RBtn4);

        DB = new DBHelper(this);

        CandidateModel model = new CandidateModel();

/*        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.RBtn1)
                {
                    if(rBtn1.isChecked()){
                        model.votes = "1";
                        DB.updateVotes(model);
                    }
                }

                if(view.getId()==R.id.RBtn2)
                {
                    if(rBtn2.isChecked()){
                        model.votes = "1";
                        DB.updateVotes(model);
                    }
                }

                if(view.getId()==R.id.RBtn3)
                {
                    if(rBtn3.isChecked()){
                        model.votes = "1";
                        DB.updateVotes(model);
                    }
                }

                if(view.getId()==R.id.RBtn4)
                {
                    if(rBtn4.isChecked()){
                        model.votes = "1";
                        DB.updateVotes(model);
                    }
                }
        });

*/
        ArrayList<CandidateModel> arrycandidate = DB.ChkCandidate();

        for(int i=0; i<arrycandidate.size(); i++)
            Log.d("Candidates List","Candidate:"+arrycandidate.get(i).candidate + ", Votes:"+arrycandidate.get(i).votes);
    }


}