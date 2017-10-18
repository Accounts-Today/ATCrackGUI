package today.accounts.cracker.gui.options

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.TextField
import today.accounts.cracker.gui.options.api.Option
import today.accounts.cracker.gui.util.TextFileChooser
import java.io.File

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class UsernameOption : Option<String>
{
    lateinit var node : TextField;

    override fun error(): String
    {
        throw IllegalStateException(); //This should never happen!
    }

    override fun isPresent(): Boolean
    {
        return File(node.text).exists()
    }

    override fun line(): String
    {
        return "-u ${node.text}"
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
        this.node = find(n, "username");
        node.promptText = "Click here to open a file chooser!"
        node.onMouseClicked = EventHandler {
            val file = TextFileChooser.create("Pick an output file for usernames.").showOpenDialog(node.scene.window) ?: return@EventHandler;
            node.text = file.absolutePath;
        }
    }
}