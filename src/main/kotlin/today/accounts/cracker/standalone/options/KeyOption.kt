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
class KeyOption : Option<String>
{
    lateinit var node: TextField;

    override fun error(): String
    {
        return "You must use a key!"
    }

    override fun isPresent(): Boolean
    {
        return !node.text.isNullOrEmpty()
    }

    override fun line(): String
    {
        Config.put("key", node.text)
        return "-k \"${node.text}\""
    }

    override fun value(): String
    {
        return node.text;
    }

    override fun required(): Boolean
    {
        return true;
    }

    override fun init(n: List<Node>)
    {
        this.node = find(n, "key");
        if (get("key") != null)
        {
            node.text = get("key");
        }
    }
}