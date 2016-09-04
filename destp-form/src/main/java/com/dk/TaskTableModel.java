package com.dk;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by liuli10 on 2016/7/6.
 */
public class TaskTableModel extends AbstractTableModel {

    private String[] tableTitle = {"任务名称","执行时间","操作"};

    private Vector<Task> data = new Vector<Task>();

    public TaskTableModel(){}

    public TaskTableModel(String[] tableTitle,Vector<Task> data){
        this.tableTitle = tableTitle;
        this.data = data;
    }

    public TaskTableModel(String[] tableTitle){
        this.tableTitle = tableTitle;
    }

    public TaskTableModel(Vector<Task> data){
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.isEmpty()?0:data.size();
    }

    @Override
    public int getColumnCount() {
        return tableTitle.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = data.get(rowIndex);
        String[] colum = new String[tableTitle.length];
        colum[0] = task.getTaskName();
        colum[1] = task.getExeTime();
        colum[2] = task.getState()+"";
        return colum[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return tableTitle[column];
    }


}
