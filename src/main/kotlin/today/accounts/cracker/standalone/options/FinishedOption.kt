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
class FinishedOption : Option<String>
{
    lateinit var node : TextField;
    override fun required(): Boolean
    {
        return true;
    }

    override fun isPresent(): Boolean
    {
        return File(node.text).exists()
    }

    override fun error(): String
    {
        return "Your MUST supply a file to write the cracked alts to!"
    }

    override fun line(): String
    {
        Config.put("finished", node.text)
        return "-f \"${node.text}\""
    }

    override fun value(): String
    {
        return node.text;
    }

    override fun init(n: List<Node>)
    {
        this.node = find(n,"finished")
        node.promptText = "Click here to open a file chooser!"
        node.onMouseClicked = EventHandler {
            val file = TextFileChooser.create("Pick a file to output to.").showOpenDialog(node.scene.window) ?: return@EventHandler;
            node.text = file.absolutePath;
        }
        if (get("finished") != null)
        {
            node.text = get("finished");
        }
    }

}