package main.model;

public class QualificationCode {

    //Member Variables
    private TableName tableName;
    private int rank;

    //Constructor
    public QualificationCode(TableName tableName, int rank) {
        this.tableName = tableName;
        this.rank = rank;
    }

    //Getters-Setters
    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
