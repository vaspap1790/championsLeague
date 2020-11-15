package main.model;

import java.util.Arrays;
import java.util.List;

public enum TableName {

    A, B, C, D, E, F, G, H, NotAssigned;

    public static List<TableName> getTableNames(){
        return Arrays.asList(A, B, C, D, E, F, G, H);
    }

}
