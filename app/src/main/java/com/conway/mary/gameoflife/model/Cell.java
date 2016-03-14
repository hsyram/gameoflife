package com.conway.mary.gameoflife.model;

import android.util.Log;

import com.conway.mary.gameoflife.util.Constant;

/**
 * Created by mary on 2/28/2016.
 */
public class Cell {
    boolean isAlive;
    int row;
    int column;

    public Cell(boolean isAlive, int row, int column) {
        this.isAlive = isAlive;
        this.row = row;
        this.column = column;
    }

    public Cell() {
    }

    protected boolean isAlive() {
        return isAlive;
    }

    protected void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


}
