package com.example.mars.pdmchat2.Login;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.easyandroidanimations.library.BounceAnimation;
import com.example.mars.pdmchat2.ChannelList.ChannelListModel;
import com.example.mars.pdmchat2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private int[] yData = {250, 150, 66, 244, 546, 216, 123};
    private String[] xData = {"Open Channel", "Canalul" , "Open Channel" , "Open Channel", "Open Channel", "Open Channel", "Open Channel"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Messages Chart");
        pieChart.setCenterTextSize(12);

        ChannelListModel model = ViewModelProviders.of(this).get(ChannelListModel.class);
        model.getChannels().observe(this, openChannels -> {
            addDataSet(yData, xData);
            //setupRecyclerView((RecyclerView) recyclerView, openChannels.getChannels());
        });

        //addDataSet(yData, xData);

        new BounceAnimation(pieChart)
                .setBounceDistance(500)
                .setDuration(5000)
                .animate();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);
                int y = sales.indexOf("y");
                String messages = sales.substring(y + 3);

                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == Float.parseFloat(messages)){
                        pos1 = i;
                        break;
                    }
                }
                String channel = xData[pos1 + 1];
                Toast.makeText(ChartActivity.this, "" + channel + "\n" + "Messages: " + messages, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {}
        });
    }

    private void addDataSet(int[] yData, String[] xData) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Channel Messages");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
