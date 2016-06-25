package com.vjimbei.backbase_hackathon_android.ui.utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by vjimbei on 6/25/2016.
 */
public class CustomValueFormatter extends DefaultValueFormatter {
    private DecimalFormat mFormat;

    /**
     * Constructor that specifies to how many digits the value should be
     * formatted.
     *
     * @param digits
     */
    public CustomValueFormatter(int digits) {
        super(digits);
        mFormat = new DecimalFormat("###");
    }


    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        // put more logic here ...
        // avoid memory allocations here (for performance reasons)

        return mFormat.format(value);
    }
}
