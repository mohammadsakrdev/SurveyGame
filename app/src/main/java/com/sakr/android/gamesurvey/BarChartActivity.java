package com.sakr.android.gamesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity
{
    ArrayList<String> mLabels;

    ArrayList<BarEntry> mList ;

    BarDataSet mBarDataSet;

    BarChart mChart;

    BarData mBarData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent data = getIntent();
        if (data != null)
        {
            mList = new ArrayList<>();
            mList.add(new BarEntry(data.getIntExtra(SurveyFragment.Q_1_ANSWER,0), 0));
            mList.add(new BarEntry(data.getIntExtra(SurveyFragment.Q_2_ANSWER,0), 1));
            mList.add(new BarEntry(data.getIntExtra(SurveyFragment.Q_3_ANSWER,0), 2));
            mList.add(new BarEntry(data.getIntExtra(SurveyFragment.Q_4_ANSWER,0), 3));
        }

        mLabels = new ArrayList<>();
        mLabels.add(getString(R.string.question1_txt));
        mLabels.add(getString(R.string.question2_txt));
        mLabels.add(getString(R.string.question3_txt));
        mLabels.add(getString(R.string.question4_txt));


        mBarDataSet = new BarDataSet(mList,"Grade");
        mChart = new BarChart(this);
        mBarData = new BarData(mLabels, mBarDataSet);
        mChart.setData(mBarData);
        setContentView(mChart);
    }

}
