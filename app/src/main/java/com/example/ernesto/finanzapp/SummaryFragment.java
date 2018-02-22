package com.example.ernesto.finanzapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {


    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        BarChart chart = (BarChart) view.findViewById(R.id.barChart);

        List<BarEntry> ingresos = new ArrayList<>();
        ingresos.add(new BarEntry(0f, 30f));
        ingresos.add(new BarEntry(1f, 100f));
        ingresos.add(new BarEntry(2f, 60f));
        ingresos.add(new BarEntry(3f, 50f));
        // gap of 2f
        ingresos.add(new BarEntry(5f, 70f));
        ingresos.add(new BarEntry(6f, 60f));

        List<BarEntry> gastos = new ArrayList<>();
        gastos.add(new BarEntry(0f, 40f));
        gastos.add(new BarEntry(1f, 50f));
        gastos.add(new BarEntry(2f, 20f));
        gastos.add(new BarEntry(3f, 80f));
        // gap of 2f
        gastos.add(new BarEntry(5f, 25f));
        gastos.add(new BarEntry(6f, 40f));

        BarDataSet gastosDataSet = new BarDataSet(ingresos, "Ingresos");
        gastosDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarDataSet ingresosDataSet = new BarDataSet(gastos, "Gastos");
        ingresosDataSet.setColor(R.color.colorAccent);

        BarData data = new BarData(gastosDataSet, ingresosDataSet);
        data.setBarWidth(0.45f);
        chart.setData(data);
        chart.groupBars(0, 0.1f, 0.02f);

        //BarData data = new BarData(gastosDataSet);
        //data.setBarWidth(0.9f);
        //chart.setData(data);
        //chart.setFitBars(true);

        // Styling

        // X axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


        // Y axis
        YAxis left = chart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setDrawAxisLine(true);
        chart.getAxisRight().setEnabled(false);

        chart.animateY(700);

        chart.invalidate();
    }
}
