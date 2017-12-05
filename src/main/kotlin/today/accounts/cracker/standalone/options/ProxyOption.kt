package today.accounts.cracker.standalone.options

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.TextField
import today.accounts.cracker.standalone.config.Config
import today.accounts.cracker.standalone.config.Config.get
import today.accounts.cracker.standalone.options.api.Option
import today.accounts.cracker.standalone.util.TextFileChooser
import java.io.File

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class ProxyOption : Option<String>
{
    lateinit var node : TextField;

    override fun error(): String
    {
        return "You MUST supply https porixes!"
    }

    override fun isPresent(): Boolean
    {
        return File(node.text).exists()
    }

    override fun line(): String
    {
        Config.put("proxy", node.text)
        return "-p \"${node.text}\""
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
        this.node = find(n, "proxies");
        node.promptText = "Click here to open a file chooser!"
        node.onMouseClicked = EventHandler {
            val file = TextFileChooser.create("Pick a proxy list.").showOpenDialog(node.scene.window) ?: return@EventHandler;
            node.text = file.absolutePath;
        }
        if (get("proxy") != null)
        {
            node.text = Config.get("proxy");
        }
    }
}