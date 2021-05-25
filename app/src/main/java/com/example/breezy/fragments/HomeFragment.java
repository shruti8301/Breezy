package com.example.breezy.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.breezy.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {//implements OnChartGestureListener, OnChartValueSelectedListener {

    @BindView(R.id.username) TextView username;
    @BindView(R.id.sleep_no) TextView sleep_no;
    @BindView(R.id.sleep_decrease) Button sleep_decrease;
    @BindView(R.id.sleep_increase) Button sleep_increase;
    @BindView(R.id.graphView) LineChart graphView;

    int sleep_hours;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, root);

        SharedPreferences userPrefs = getContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);
        username.setText(userPrefs.getString("Name", "Name"));

        sleep_hours = 0;
        sleep_decrease.setEnabled(false);

        sleep_increase.setOnClickListener(view -> {
            sleep_decrease.setEnabled(true);
            sleep_no.setText(String.valueOf(++sleep_hours));
        });

        sleep_decrease.setOnClickListener(view -> {
            sleep_no.setText(String.valueOf(--sleep_hours));
            if (sleep_hours == 0)
                sleep_decrease.setEnabled(false);
        });

        graphView.setDragEnabled(true);
        graphView.setScaleEnabled(true);
        graphView.getAxisRight().setDrawGridLines(false);
        graphView.getAxisLeft().setDrawGridLines(false);
        graphView.getXAxis().setDrawGridLines(false);

        List<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 30f));
        yValues.add(new Entry(4, 50f));
        yValues.add(new Entry(5, 60f));
        yValues.add(new Entry(6, 65f));

        LineDataSet set = new LineDataSet(yValues, "Mood Graph");
        set.setFillAlpha(110);

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_graph);
        set.setDrawFilled(true);
        set.setFillDrawable(drawable);
        set.setColor(Color.BLACK);
        set.setLineWidth(1.5f);
        set.enableDashedLine(4f, 2.5f, 0f);
        set.setCircleColor(Color.BLACK);
        set.setCircleHoleColor(Color.WHITE);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        LineData data = new LineData(dataSets);

        graphView.setData(data);

        return root;
    }
}