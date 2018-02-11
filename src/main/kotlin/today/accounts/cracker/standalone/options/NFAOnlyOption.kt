package today.accounts.cracker.standalone.options

import javafx.scene.Node
import javafx.scene.control.CheckBox
import today.accounts.cracker.standalone.config.Config
import today.accounts.cracker.standalone.options.api.Option

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class NFAOnlyOption : Option<Boolean>
{
    lateinit var node : CheckBox;

    override fun isPresent(): Boolean
    {
        return node.isSelected;
    }

    override fun line(): String
    {
        Config.put("nfa", node.isSelected.toString())
        return "-nfa"
    }

    override fun error(): String
    {
        throw IllegalAccessError(); //THIS SHOULD NEVER BE CALLED
    }

    override fun required(): Boolean
    {
        return false;
    }

    override fun value(): Boolean
    {
        return node.isSelected;
    }
    override fun init(n: List<Node>)
    {
        node = find(n,"nfa")
        node.isSelected = Config.get("nfa")?.equals("true") ?: false;
    }
}