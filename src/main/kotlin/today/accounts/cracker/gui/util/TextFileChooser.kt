package today.accounts.cracker.gui.util

import javafx.stage.FileChooser

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
object TextFileChooser
{
    fun create(title: String): FileChooser
    {
        val chooser = FileChooser();
        chooser.title = title;
        chooser.extensionFilters.add(FileChooser.ExtensionFilter("Text Files","*.txt"))
        return chooser;
    }
}