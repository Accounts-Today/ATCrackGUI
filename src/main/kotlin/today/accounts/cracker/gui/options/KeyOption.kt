package today.accounts.cracker.gui.options

import javafx.scene.Node
import javafx.scene.control.TextField
import today.accounts.cracker.gui.options.api.Option

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class KeyOption : Option<String>
{
    lateinit var node : TextField

    override fun error(): String
    {
        return "You need a key to use the cracker!"
    }

    override fun isPresent(): Boolean
    {
        return node.text != null;
    }

    override fun required(): Boolean
    {
        return true
    }

    override fun value(): String
    {
        return node.text;
    }

    override fun line(): String
    {
        return "-k " + node.text
    }

    override fun init(n: List<Node>)
    {
        node = find(n,"key")
    }
}