package com.kbakkoyungmail.notebook;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by asus-pc on 8.7.2017.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatTextView{


    public CustomEditText(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(),"loginfont.ttf");
        this.setTypeface(face);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(),"loginfont.ttf");
        this.setTypeface(face);

    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(),"loginfont.ttf");
        this.setTypeface(face);
    }
}
