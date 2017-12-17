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
class UnmigOption : Option<Boolean>
{
    lateinit var node : CheckBox;

    override fun isPresent(): Boolean
    {
        return node.isSelected;
    }

    override fun line(): String
    {
        Config.put("unmig", node.isSelected.toString())
        return "-unmig"
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
        node = find(n,"unmig")
        node.isSelected = Config.get("unmig")?.equals("true") ?: false;
    }
}