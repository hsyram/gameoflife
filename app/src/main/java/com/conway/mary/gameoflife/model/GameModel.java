package com.conway.mary.gameoflife.model;

import java.util.List;

/**
 * Created by mary on 3/4/2016.
 */
public class GameModel {
    private int rows;
    private int columns;

    private Cell[] cells;

    public GameModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = new Cell[rows*columns];
        int count =0;
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                cells[count] = new Cell(false, i,j);
                count++;
            }

        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public boolean isAlive(Cell cell) {
        return !isOutOfMap(cell) && cell.isAlive();
    }

    public boolean isAlive(int pos) {
        return isAlive(cells[pos]);
    }

    public void makeAlive(Cell cell) {
        if (!isOutOfMap(cell)) {
            for (Cell c: cells){
                if(c.getColumn() == cell.getColumn() && c.getRow() == cell.getRow()){
                    c.setIsAlive(true);
                    break;
                }
            }
        }
    }

    public void makeAlive(int pos) {
        makeAlive(cells[pos]);
    }

    public void makeDead(Cell cell) {
        if (!isOutOfMap(cell)) {
            for (Cell c: cells){
                if(c.getColumn() == cell.getColumn() && c.getRow() == cell.getRow()){
                    c.setIsAlive(false);
                    break;
                }
            }
        }
    }
    public void makeDead(int pos) {
        makeDead(cells[pos]);
    }

    public boolean willLive(Cell cell) {
        int aliveNeighbours = 0;
        for (int i = cell.getRow() - 1; i <= cell.getRow() + 1; i++) {
            for (int j = cell.getColumn() - 1; j <= cell.getColumn() + 1; j++) {
                if (!(i == cell.getRow() && j == cell.getColumn())) {
                    for (Cell c: cells){
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

    public void next() {
        Cell[] newCells = new Cell[cells.length];
        for(int i=0; i<cells.length; i++) {
            Cell cell = new Cell(willLive(cells[i]),cells[i].getRow(), cells[i].getColumn());
            newCells[i] = cell;
        }

        cells = newCells;
    }

    private boolean isOutOfMap(Cell cell) {
        return cell.getRow() < 0 || cell.getRow() > rows - 1 || cell.getColumn() < 0 || cell.getColumn() > columns - 1;
    }
}
