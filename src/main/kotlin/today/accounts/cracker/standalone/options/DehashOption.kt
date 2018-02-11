package today.accounts.cracker.standalone.options

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.TextField
import today.accounts.cracker.standalone.config.Config
import today.accounts.cracker.standalone.options.api.Option
import today.accounts.cracker.standalone.util.TextFileChooser
import java.io.File

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Sunday, October 2017
 */
class DehashOption : Option<String>
{
    lateinit var node : TextField;

    override fun error(): String
    {
        return "Well this should never happen"
    }

    override fun isPresent(): Boolean
    {
        return File(node.text).exists()
    }

    override fun line(): String
    {
        Config.put("dehash", node.text)
        return "-dehash \"${node.text}\""
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
        this.node = find(n,"dehash");
        node.promptText = "Click here to open a file chooser!"
        node.onMouseClicked = EventHandler {
            val file = TextFileChooser.create("Pick an email list.").showOpenDialog(node.scene.window) ?: return@EventHandler;
            node.text = file.absolutePath;
        }
        if (Config.get("dehash") != null)
        {
            node.text = Config.get("dehash");
        }
    }
}