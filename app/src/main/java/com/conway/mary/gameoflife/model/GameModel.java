package com.conway.mary.gameoflife.model;

/**
 * Created by mary on 3/4/2016.
 */
public class GameModel {
    private int mRows;
    private int mColumns;

    private Cell[] mCells;

    private boolean mIsRun;

    public GameModel(int rows, int columns) {
        this.mRows = rows;
        this.mColumns = columns;
        mCells = new Cell[rows*columns];
        int count =0;
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                mCells[count] = new Cell(false, i,j);
                count++;
            }

        }
    }

    public int getRows() {
        return mRows;
    }

    public int getColumns() {
        return mColumns;
    }

    public Cell[] getCells() {
        return mCells;
    }

    public void setCells(Cell[] mCells) {
        this.mCells = mCells;
    }

    public boolean isAlive(Cell cell) {
        return !isOutOfMap(cell) && cell.isAlive();
    }

    public boolean isAlive(int pos) {
        return isAlive(mCells[pos]);
    }

    public boolean isRun() {
        return mIsRun;
    }

    public void setIsRun(boolean isRun) {
        this.mIsRun = isRun;
    }

    public void makeAlive(Cell cell) {
        if (!isOutOfMap(cell)) {
            for (Cell c: mCells){
                if(c.getColumn() == cell.getColumn() && c.getRow() == cell.getRow()){
                    c.setIsAlive(true);
                    break;
                }
            }
        }
    }

    public void makeAlive(int pos) {
        makeAlive(mCells[pos]);
    }

    public void makeDead(Cell cell) {
        if (!isOutOfMap(cell)) {
            for (Cell c: mCells){
                if(c.getColumn() == cell.getColumn() && c.getRow() == cell.getRow()){
                    c.setIsAlive(false);
                    break;
                }
            }
        }
    }
    public void makeDead(int pos) {
        makeDead(mCells[pos]);
    }

    public boolean willLive(Cell cell) {
        int aliveNeighbours = 0;
        for (int i = cell.getRow() - 1; i <= cell.getRow() + 1; i++) {
            for (int j = cell.getColumn() - 1; j <= cell.getColumn() + 1; j++) {
                if (!(i == cell.getRow() && j == cell.getColumn())) {
                    for (Cell c: mCells){
                        if(c.getColumn() == j && c.getRow() == i){
                            if(c.isAlive())
                                aliveNeighbours++;
                            break;
                        }
                    }
                }
            }
        }
        if (cell.isAlive()) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        }
        return aliveNeighbours == 3;
    }
    public void reset(){
        for (Cell mCell : mCells) mCell.setIsAlive(false);
        setIsRun(false);
    }

    public void next() {
        if(!isRun())
            setIsRun(true);

        Cell[] newCells = new Cell[mCells.length];
        for(int i=0; i< mCells.length; i++) {
            Cell cell = new Cell(willLive(mCells[i]), mCells[i].getRow(), mCells[i].getColumn());
            newCells[i] = cell;
        }

        mCells = newCells;
    }

    private boolean isOutOfMap(Cell cell) {
        return cell.getRow() < 0 || cell.getRow() > mRows - 1 || cell.getColumn() < 0 || cell.getColumn() > mColumns - 1;
    }
}
