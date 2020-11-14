package main.model;

public enum QualificationCode {

    FirstRound(TableName.NotAssigned,0),
    A1(TableName.A,1),
    A2(TableName.A,2),
    B1(TableName.B,1),
    B2(TableName.B,2),
    C1(TableName.C,1),
    C2(TableName.C,2),
    D1(TableName.D,1),
    D2(TableName.D,2),
    E1(TableName.E,1),
    E2(TableName.E,2),
    F1(TableName.F,1),
    F2(TableName.F,2),
    G1(TableName.G,1),
    G2(TableName.G,2),
    H1(TableName.H,1),
    H2(TableName.H,2);

    public final TableName tableName;
    public final int place;

    QualificationCode(TableName tableName, int place){
        this.tableName = tableName;
        this.place = place;
    }
}
