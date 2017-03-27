import java.util.HashMap;

/**
 * This class is a symbol table.
 * It manages a table that holds all the symbols from the code and their value.
 */
public class SymbolTable {
    private static final int NOT_IN_TABLE = -1;
    private HashMap<String, Integer> symbolTable = new HashMap<>();

    /**
     * Adds a symbol to the table if it doesn't exist already.
     *
     * @param key   The key of the symbol.
     * @param value The value of the symbol.
     * @return The value of the given key in the table.
     */
    public int addSymbol(String key, int value) {
        int tableVal = lookup(key);
        if (tableVal == NOT_IN_TABLE) {
            symbolTable.put(key, value);
        }

        return (tableVal == NOT_IN_TABLE) ? value : tableVal;
    }

    /**
     * Looks up a key in the table.
     *
     * @param key The key to look up.
     * @return The value of the key if the key exists, null if it doesn't.
     */
    public int lookup(String key) {
        Object value = symbolTable.get(key);
        return (value == null) ? NOT_IN_TABLE : (int) value;
    }
}
