

public interface SymbolTableInterface {

    /**
     * Starts a new subroutine scope (i.e. resets the subroutine’s symbol table.)
     */
    void startSubroutine();

    /**
     * Ends the current subroutine's scope.
     */
    void endSubroutine();

    /**
     * Defines a new identifier of a given name, type, and kind and assigns it a running
     * index. STATIC and FIELD identifiers have a class scope, while ARG and VAR identifiers have a
     * subroutine scope.
     *
     * @param name The name of the new defined identifier.
     * @param type The type of the new defined identifier.
     * @param kind The kind of the new defined identifier.
     */
    void define(String name, String type, SymbolTable.Kind kind);

    /**
     * @param kind The kind to count.
     * @return The number of variables of the given kind already defined in the current scope.
     */
    int varCount(SymbolTable.Kind kind);

    /**
     * @param name The name of the identifier.
     * @return The kind of the named identifier in the current scope. If the identifier is unknown in the
     * current scope, returns NONE.
     */
    String kindOf(String name);

    /**
     * @param name The name of the identifier.
     * @return The type of the named identifier in the current scope.
     */
    String typeOf(String name);

    /**
     * @param name The name of the identifier.
     * @return The index assigned to the named identifier.
     */
    int indexOf(String name);


}


