import java.util.Hashtable;

/**
 * A symbol table, manges the symbols of the jack compiler.
 */

public class SymbolTable implements SymbolTableInterface{
    private boolean inSubroutine = false;
    private int staticCounter = 0, fieldCounter = 0, argCounter = 0, varCounter = 0;

    enum Kind {
        STATIC("static"), FIELD("field"), ARG("argument"), VAR("var"), NONE("none");

        String strRep;

        Kind(String strRep) {
            this.strRep = strRep;
        }

        public String getStrRep() {
            return strRep;
        }
    }

    private Hashtable<String, TableTuple> classScope;

    public String getClassName() {
        return className;
    }

    private String className;
    private Hashtable<String, TableTuple> subroutineScope  = new Hashtable<>();

    /**
     * Creates a new empty symbol table.
     */
    public SymbolTable(String className) {
        this.className = className;
        classScope = new Hashtable<>();
    }

    /**
     * Starts a new subroutine scope (i.e. resets the subroutine’s symbol table.)
     */
    public void startSubroutine() {
        inSubroutine = true;
        argCounter = 0;
        varCounter = 0;
        subroutineScope.clear();
    }

    /**
     * Ends the current subroutine's scope.
     */
    public void endSubroutine() {
        inSubroutine = false;
    }

    /**
     * Defines a new identifier of a given name, type, and kind and assigns it a running
     * index. STATIC and FIELD identifiers have a class scope, while ARG and VAR identifiers have a
     * subroutine scope.
     *
     * @param name The name of the new defined identifier.
     * @param type The type of the new defined identifier.
     * @param kind The kind of the new defined identifier.
     */
    public void define(String name, String type, Kind kind) {
        int counter = 0;
        switch (kind) {
            case STATIC:
                counter = staticCounter;
                staticCounter++;
                break;
            case FIELD:
                counter = fieldCounter;
                fieldCounter++;
                break;
            case ARG:
                counter = argCounter;
                argCounter++;
                break;
            case VAR:
                counter = varCounter;
                varCounter++;
                break;
        }

        TableTuple newTuple = new TableTuple(kind, type, counter);
        if (inSubroutine) {
            subroutineScope.put(name, newTuple);
        } else {
            classScope.put(name, newTuple);
        }
    }

    /**
     * @param kind The kind to count.
     * @return The number of variables of the given kind already defined in the current scope.
     */
    public int varCount(Kind kind) {
        int kindCounter = 0;
        switch (kind) {
            case STATIC:
                kindCounter = staticCounter;
                break;
            case FIELD:
                kindCounter = fieldCounter;
                break;
            case ARG:
                kindCounter = argCounter;
                break;
            case VAR:
                kindCounter = varCounter;
                break;
        }
        return kindCounter;

    }

    /**
     * @param name The name of the identifier.
     * @return The kind of the named identifier in the current scope. If the identifier is unknown in the
     * current scope, returns NONE.
     */
    public String kindOf(String name) {
        TableTuple element = null;

        if (inSubroutine) {
            element = subroutineScope.get(name);
        }

        if (element == null) {
            element = classScope.get(name);
        }

        if (element == null) {
                return Kind.NONE.getStrRep();
        }

        return element.getKind().getStrRep();
    }

    /**
     * @param name The name of the identifier.
     * @return The type of the named identifier in the current scope.
     */
    public String typeOf(String name) {
        TableTuple element = null;

        if (inSubroutine) {
            element = subroutineScope.get(name);
        }

        if (element == null) {
            element = classScope.get(name);
        }

        if (element == null) {
            return null;
        }

        return element.getType();
    }

    /**
     * @param name The name of the identifier.
     * @return The index assigned to the named identifier.
     */
    public int indexOf(String name) {
        TableTuple element = null;

        if (inSubroutine) {
            element = subroutineScope.get(name);
        }

        if (element == null) {
            element = classScope.get(name);
        }

        return element.getIndex();
    }

    public void printClassScope() {
        System.out.println("classScope:\nname\ttype\tkind\t\tindex\t");
        for (String name : classScope.keySet()) {
            System.out.print(name + "\t\t");
            classScope.get(name).printTuple();
            System.out.println();
        }
    }

    public void printSubroutineScope() {
        System.out.println("subroutineScope:\nname\ttype\tkind\t\tindex\t");

        for (String name : subroutineScope.keySet()) {
            System.out.print(name + "\t");
            subroutineScope.get(name).printTuple();
            System.out.println();
        }
    }
    /**
     * Represents a tuple in the table.
     */
    private class TableTuple {
        private SymbolTable.Kind kind;
        private String type;
        private int index;

        TableTuple(SymbolTable.Kind kind, String type, int index) {
            this.kind = kind;
            this.type = type;
            this.index = index;
        }

        SymbolTable.Kind getKind() {
            return kind;
        }

        String getType() {
            return type;
        }

        int getIndex() {
            return index;
        }

        void printTuple() {
            System.out.println(type + "\t\t" + kind + "\t\t" + index);
        }
    }


}
