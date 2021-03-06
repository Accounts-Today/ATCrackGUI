package today.accounts.cracker.standalone.options

import javafx.scene.Node
import javafx.scene.control.TextField
import today.accounts.cracker.standalone.config.Config
import today.accounts.cracker.standalone.config.Config.get
import today.accounts.cracker.standalone.options.api.Option

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class ThreadOption : Option<String>
{
    lateinit var node: TextField;

    override fun error(): String
    {
        throw IllegalStateException("Well, this should never happen")
    }

    override fun isPresent(): Boolean
    {
        return true;
    }

    override fun line(): String
    {
        Config.put("threads", node.text)
        return "-t \"${node.text}\""
    }

    override fun value(): String
    {
        return node.text;
    }

    override fun required(): Boolean
    {
        return false;
    }

    override fun init(n: List<Node>)
    {
        this.node = find(n, "threads");
        if (get("threads") != null)
        {
            node.text = get("threads");
        }
    }
}