package com.example.breezy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity {

    @BindView(R.id.question_view)
    TextView question_view;
    @BindView(R.id.question_number)
    TextView question_number;
    @BindView(R.id.question_progress)
    ProgressBar question_progress;
    @BindView(R.id.options_group)
    RadioGroup options_group;
    @BindView(R.id.previous_ques_btn)
    TextView prev_btn;
    @BindView(R.id.next_ques_btn)
    Button next_btn;

    private int position;
    private JSONArray basicQuestions = null;
    private JSONObject currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        Map<String, Integer> basicQuesPoints = new HashMap<>();
        basicQuesPoints.put("Schizophrenia", 0);
        basicQuesPoints.put("PTSD", 0);
        basicQuesPoints.put("Anxiety", 0);
        basicQuesPoints.put("OCD", 0);
        basicQuesPoints.put("Depression", 0);

        try {
            basicQuestions = new JSONArray(getResources().getString(R.string.basicQues));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        position = 0;

        question_progress.setMax(basicQuestions.length());
        question_progress.setProgress(position + 1);
        question_number.setText("Question " + (position + 1) + "/" + basicQuestions.length());

        try {
            currentQuestion = new JSONObject(basicQuestions.get(position).toString());
            question_view.setText(currentQuestion.get("ques").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        next_btn.setOnClickListener(view -> {
            try {
                String code = currentQuestion.get("code").toString();
                if (findViewById(options_group.getCheckedRadioButtonId()).getTag().toString().equals("yes"))
                    basicQuesPoints.put(code, basicQuesPoints.get(code) + 1);

                if (position == 4) {
                    startActivity(new Intent(QuestionActivity.this, SplashActivity.class));
                    finish();
                }
                currentQuestion = new JSONObject(basicQuestions.get(++position).toString());
                question_view.setText(currentQuestion.get("ques").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            question_number.setText("Question " + (position + 1) + "/5");
            question_progress.setProgress(position + 1);

            Log.e("Info", basicQuesPoints.toString());
        });
    }
}