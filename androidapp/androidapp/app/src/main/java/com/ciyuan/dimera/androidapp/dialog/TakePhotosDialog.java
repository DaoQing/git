package com.ciyuan.dimera.androidapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.ciyuan.dimera.androidapp.R;

/**
 * ClassName : TakePhotosDialog
 * Author   : 史翔宇
 * Time     : 2015/12/30
 * Desc     :
 */

public class TakePhotosDialog extends Dialog {




    public TakePhotosDialog(Context context) {
        super(context);
        this.show();
    }

    public TakePhotosDialog(Context context, int theme) {
        super(context, theme);
        this.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_takephotos);
    }


}


