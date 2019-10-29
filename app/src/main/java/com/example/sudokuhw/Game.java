package com.example.sudokuhw;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

class Game extends BaseAdapter {

    private Context mContext;
    private final Integer mRows = 9,
            mCols = 9;
    public int numberArray[][] = new int[mRows][mCols];

    public Game(Context mContext) {
        this.mContext = mContext;
        arrPict = new ArrayList<>(mCols*mRows);
        mRes = mContext.getResources();
    }

    private Resources mRes;
    private ArrayList<String> arrPict;
    // массив незаблокированных позиций
    int  unblockPositions[] = new int[mRows*mCols];


    @Override
    public int getCount() {
        return mRows*mCols;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView imageView;

        if (view == null)
            imageView = new ImageView(mContext);
        else
            imageView = (ImageView) view;

        Integer drawableId = mRes.getIdentifier(arrPict.get(position),"drawable", mContext.getPackageName());
        imageView.setImageResource(drawableId);

        return imageView;
    }

    private void createField() {
        // init array

        // shift numbers

        // transpose array

        // shake numbers

        // transpose array

        // add pictures number to field
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                arrPict.add("n" + numberArray[i][j]);
            }
        }
    }
}
