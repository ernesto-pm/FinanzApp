package com.example.ernesto.finanzapp;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ernesto.finanzapp.ChartFormatters.MonthFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    String[] months = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);


    }

    public void populatePieChart(PieChart pieChart) {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        PieData data = new PieData(set);

        set.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    public void createSummaryBarChart(BarChart barChart) {
        List<BarEntry> ingresos = new ArrayList<>();
        ingresos.add(new BarEntry(0f, 30f));
        ingresos.add(new BarEntry(1f, 100f));
        ingresos.add(new BarEntry(2f, 60f));
        ingresos.add(new BarEntry(3f, 50f));
        ingresos.add(new BarEntry(4f, 30f));
        ingresos.add(new BarEntry(5f, 100f));
        ingresos.add(new BarEntry(6f, 60f));
        ingresos.add(new BarEntry(7f, 50f));
        ingresos.add(new BarEntry(8f, 30f));
        ingresos.add(new BarEntry(9f, 100f));
        ingresos.add(new BarEntry(10f, 100f));
        ingresos.add(new BarEntry(11f, 100f));


        List<BarEntry> gastos = new ArrayList<>();
        gastos.add(new BarEntry(0f, 0f));
        gastos.add(new BarEntry(1f, 0f));
        gastos.add(new BarEntry(2f, 0f));
        gastos.add(new BarEntry(3f, 0f));
        gastos.add(new BarEntry(4f, 0f));
        gastos.add(new BarEntry(5f, 0f));
        gastos.add(new BarEntry(6f, 0f));
        gastos.add(new BarEntry(7f, 0f));
        gastos.add(new BarEntry(8f, 0f));
        gastos.add(new BarEntry(9f, 0f));
        gastos.add(new BarEntry(10f, 0f));
        gastos.add(new BarEntry(11f, 0f));


        BarDataSet gastosDataSet = new BarDataSet(ingresos, "Ingresos");
        gastosDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        BarDataSet ingresosDataSet = new BarDataSet(gastos, "Gastos");
        ingresosDataSet.setColors(Color.RED);

        BarData data = new BarData(ingresosDataSet, gastosDataSet);
        data.setBarWidth(0.46f);
        barChart.setData(data);
        barChart.groupBars(0, 0.04f, 0.02f);
        barChart.setFitBars(true);



        // Styling

        // X axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setTextSize(5);
        xAxis.setValueFormatter(new MonthFormatter(months));


        // Y axis
        YAxis left = barChart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setDrawAxisLine(true);
        left.setAxisMinimum(0f);
        //left.setValueFormatter(new MonthFormatter(months));

        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        barChart.animateY(700);

        barChart.invalidate();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        BarChart barChart = (BarChart) view.findViewById(R.id.barChart);
        PieChart pieChart = (PieChart) view.findViewById(R.id.pieChart);

        createSummaryBarChart(barChart);
        populatePieChart(pieChart);
    }


}
