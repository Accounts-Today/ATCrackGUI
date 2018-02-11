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
class HypixelOption : Option<String>
{
    lateinit var node: TextField;

    override fun error(): String
    {
        return "You MUST supply combos!"
    }

    override fun isPresent(): Boolean
    {
        if (!node.text.isNullOrEmpty())
        {
            val file = File(node.text)
            if (!file.exists()) file.createNewFile();
            node.text = file.absolutePath
            return file.exists()
        }
        return false
    }

    override fun line(): String
    {
        Config.put("hypixel", node.text)
        return "-hypixel \"${node.text}\""
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
        this.node = find(n, "hypixel");
        node.promptText = "Click here to open a file chooser!"
        node.onMouseClicked = EventHandler {
            val file = TextFileChooser.create("Pick a file for hypixel ranks to be exported to.").showSaveDialog(node.scene.window) ?: return@EventHandler;
            node.text = file.absolutePath;
        }
        if (get("hypixel") != null)
        {
            node.text = get("hypixel");
        }
    }
}