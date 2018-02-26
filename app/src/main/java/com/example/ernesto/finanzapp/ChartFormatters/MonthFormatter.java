package com.example.ernesto.finanzapp.ChartFormatters;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by ernesto on 2/23/18.
 */

public class MonthFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public MonthFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        if(value < 0 || value >= mValues.length ) {
            return "";
        } else {
            return mValues[(int) value];
        }

    }

    public int getDecimalDigits() {
        return 0;
    }

}
