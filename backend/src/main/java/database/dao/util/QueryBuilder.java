package database.dao.util;

import java.util.*;

public class QueryBuilder {
    private ArrayList<String> columns = new ArrayList<>();
    private ArrayList<String> tables = new ArrayList<>();
    private ArrayList<Quartlet<String, Sign, Object, Operators>> whereParams = new ArrayList<>();


    public void addColumn(String... columns) {
        this.columns.addAll(Arrays.asList(columns));
    }

    public void addTable(String... tables) {
        this.tables.addAll(Arrays.asList(tables));
    }

    public void addWhereParams(Quartlet... whereParams) {
        for (Quartlet whereParam:whereParams){
            this.whereParams.add(whereParam);
        }

    }


    public String buildSelectRequest() {
        StringBuilder selectRequest = new StringBuilder(Operation.SELECT.toString());
        if (columns.isEmpty()) {
            selectRequest.append("*");
        } else {
            appendArrayList(selectRequest, columns, "", ", ");
        }
        appendArrayList(selectRequest, tables, " FROM ", ", ");
        appendWhereParams(selectRequest);
        selectRequest.append(";");
        return selectRequest.toString();
    }



    private void appendArrayList(StringBuilder selectRequest, ArrayList<?> list, String operation, String separator) {
        boolean flagIsFirst = true;
        if (!list.isEmpty()) {
            for (Object s : list) {
                if (flagIsFirst) {
                    selectRequest.append(operation);
                } else {
                    selectRequest.append(separator);
                }
                if (list.get(0) instanceof String) {
                    selectRequest.append((String) s);
                } else if (list.get(0) instanceof Integer) {
                    selectRequest.append((String) s);
                } else if (list.get(0) instanceof Pair) {
                    Pair p = (Pair) s;
                    selectRequest.append(p.getValue1()).append(" ");
                    selectRequest.append(p.getValue2());

                }

                flagIsFirst = false;

            }

        }
    }

    private void appendWhereParams(StringBuilder selectRequest) {
        if (!whereParams.isEmpty()) {
            selectRequest.append(" WHERE ");
            for (Quartlet quartlet : whereParams) {
                selectRequest.append(quartlet.getValue1());
                Sign sign = (Sign) quartlet.getValue2();
                if (sign.equals(Sign.BETWEEN)) {
                    selectRequest.append("? AND ?");
                } else {
                    selectRequest.append(sign.toString());
                    if (quartlet.getValue3() != null) {
                        if (quartlet.getValue3() instanceof Operators) {
                            selectRequest.append(" ? ");
                            Operators operator = (Operators) quartlet.getValue3();
                            selectRequest.append(operator.toString());

                        } else {
                            selectRequest.append((String) quartlet.getValue3());
                        }
                    } else selectRequest.append(" ? ");
                }
                if (quartlet.getValue4() != null) {
                    Operators operator = (Operators) quartlet.getValue4();
                    selectRequest.append(operator.toString());

                }
            }
        }
    }

    public String buildInsertRequest(String values) {
        StringBuilder insertRequest = new StringBuilder(Operation.INSERT.toString());
        appendArrayList(insertRequest, tables, "", "");
        if (!columns.isEmpty()) {
            appendArrayList(insertRequest, columns, "(", ", ");
            insertRequest.append(") ");
        }
        insertRequest.append(" VALUES (");
        insertRequest.append(values);
        insertRequest.append(");");
        return insertRequest.toString();
    }

    public String buildUpdateRequest(LinkedHashMap<String, String> values) {
        StringBuilder updateRequest = new StringBuilder(Operation.UPDATE.toString());
        appendArrayList(updateRequest, tables, "", "");
        updateRequest.append(" SET ");

        for (Map.Entry<String, String> value : values.entrySet()) {
            updateRequest.append(value.getKey()).append(" = ").append(value.getValue()).append(", ");
        }
        updateRequest = new StringBuilder(updateRequest.toString().substring(0, (updateRequest.toString().length() - 2)));
        appendWhereParams(updateRequest);
        updateRequest.append(";");
        return updateRequest.toString();
    }

    public String buildDeleteRequest() {
        StringBuilder deleteRequest = new StringBuilder(Operation.DELETE.toString());
        appendArrayList(deleteRequest, tables, "", "");
        appendWhereParams(deleteRequest);
        deleteRequest.append(";");
        return deleteRequest.toString();
    }


    public enum Sign {
        EQUAL("="), LESS_OR_EQUAL("<="), MORE_OR_EQUAL(">="), MORE(">"), LESS("<"), BETWEEN("BETWEEN"), IN(" IN ");

        private String value;

        Sign(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Operation {
        SELECT("SELECT "),
        DELETE("DELETE FROM "),
        INSERT("INSERT INTO "),
        UPDATE("UPDATE ");

        private String value;

        Operation(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }


    public enum Operators {
        AND(" AND "), OR(" OR "), NOT(" NOT ");
        private String value;

        Operators(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    class Pair<V1, V2> {

        private V1 value1;
        private V2 value2;

        Pair() {
            this.value1 = null;
            this.value2 = null;
        }

         Pair(V1 value1, V2 value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        V1 getValue1() {
            return value1;
        }

        V2 getValue2() {
            return value2;
        }
    }

    public class Quartlet<V1, V2, V3, V4> extends Pair<V1, V2> {

        private V3 value3;
        private V4 value4;

       Quartlet() {
            super();
            value3 = null;
            value4 = null;
        }

        Quartlet(V1 value1, V2 value2) {
            super(value1, value2);
            value3 = null;
        }

         Quartlet(V1 value1, V2 value2, Object value) {
            super(value1, value2);

            this.value3 = (V3) value;
            this.value4 = null;

        }

         Quartlet(V1 value1, V2 value2, V3 value3, V4 value4) {
            super(value1, value2);
            this.value3 = value3;
            this.value4 = value4;
        }

        public V3 getValue3() {
            return value3;
        }

        public V4 getValue4() {
            return value4;
        }
    }

    public Quartlet setQuartlet() {
        return new Quartlet();
    }

    public Quartlet setQuartletTwoParams(String str, Sign sign) {
        return new Quartlet<>(str, sign);
    }

    public Quartlet setQuartletThreeParams(String str, Sign sign, Object op) {
        return new Quartlet(str, sign, op);
    }

    public Quartlet<String, Sign, String, Operators> setQuartletFourParams(String str1, Sign sign, String str2, Operators op) {
        return new Quartlet<>(str1, sign, str2, op);
    }

}