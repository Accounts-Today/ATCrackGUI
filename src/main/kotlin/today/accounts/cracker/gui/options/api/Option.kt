package today.accounts.cracker.gui.options.api

import javafx.scene.Node

/**
 * Created for Accounts Today. All rights reserved/retained
 *
 * Represents a GUI option
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
interface Option<out T>
{
    /**
     * Whether this option is required
     */
    fun required() : Boolean;

    /**
     * Whether this option is present
     */
    fun isPresent() : Boolean;

    /**
     * The current option value
     */
    fun value() : T;

    /**
     * Initiate this option with the given nodes
     * @param n The nodes to select from
     */
    fun init(n : List<Node>);

    /**
     * Get the line this will append to the start args for the cracker
     * @return The argument
     */
    fun line() : String;

    /**
     * Get the line that will be used for showing an error if this is not present or some check failed.
     * @return the error;
     */
    fun error() : String;

    fun <T : Node> find(nodes : List<Node>, name: String): T
    {
        return nodes.stream().filter { n ->
            if (n.id == null) return@filter false
            return@filter n.id == name
        }.findFirst().orElseThrow { NullPointerException(name + "does not exist!") } as T

    }
}