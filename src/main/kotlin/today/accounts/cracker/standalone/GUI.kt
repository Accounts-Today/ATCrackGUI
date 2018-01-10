package today.accounts.cracker.standalone

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.Hyperlink
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import today.accounts.cracker.standalone.config.Config
import today.accounts.cracker.standalone.options.*
import today.accounts.cracker.standalone.options.api.Option
import today.accounts.cracker.standalone.util.Console
import java.awt.Desktop
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URI
import java.util.*

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class GUI : Application()
{
    val version = "3.0";
    var running: Process? = null;
    @FXML
    lateinit var start: Button;
    val options = listOf(
            BackconnectOption(),
            CombosOption(),
            FinishedOption(),
            NotifyOption(),
            ProxyOption(),
            FullAccuracyOption(),
            MFAOption(),
            SFAOption(),
            UsernameOption(),
            OptifineOption(),
            HypixelOption(),
            SkipProxyOption(),
            UnmigOption(),
            NocheckOption(),
            RebruteOption(),
            MineconOption()

    )

    override fun start(primaryStage: Stage)
    {
        if (!File(ProgramStarter.PATH).exists())
        {
            val alert = Alert(Alert.AlertType.ERROR);
            alert.title = "ATCrackGUI"
            alert.headerText = "Could not find Cracker.jar!"
            alert.contentText = "Could not find Cracker.jar! Please ensure this and Cracker.jar are in the same directory!"
            alert.showAndWait()
            System.exit(1)
        }
        val loader = FXMLLoader(Main::class.java.classLoader.getResource("GUI.fxml"));
        loader.setController(this)
        primaryStage.title = "Accounts Today Cracker GUI | By: Jp78 | Version: $version";
        primaryStage.icons.add(Image("https://avatars2.githubusercontent.com/u/32889071?s=200&v=4"))
        primaryStage.isResizable = false;
        val root: AnchorPane = loader.load()
        val scene = Scene(root, root.prefWidth, root.prefHeight);
        primaryStage.scene = scene;
        primaryStage.onCloseRequest = EventHandler {
            running?.destroy()
            System.exit(0)
        }
        options.forEach { it.init(root.children) }
        primaryStage.show()
        val link: Hyperlink = Option.find(root.children, "link")
        link.onAction = EventHandler {
            Desktop.getDesktop().browse(URI("https://atcrack.io/knowledge-base"))
        }
    }

    fun start()
    {
        if (start.text == "Stop")
        {
            running?.destroy();
            System.exit(0)
        }
        else
        {
            //Start up sequence.
            val args = LinkedList<String>()

            for (o in options)
            {
                if (o.required() && !o.isPresent())
                {
                    val alert = Alert(Alert.AlertType.ERROR);
                    alert.title = "AT Crack GUI"
                    alert.headerText = "Option Error!"
                    alert.contentText = o.error();
                    alert.showAndWait();
                    return;
                }
                if (o.isPresent()) args.add(o.line())
            }
            Config.save();
            val alert = Alert(Alert.AlertType.CONFIRMATION)
            alert.title = "AT Crack GUI"
            alert.headerText = "Started!"
            alert.contentText = "Program started!"
            alert.show();
            this.running = ProgramStarter.start(args.toTypedArray())
            Thread {
                running!!.waitFor()
                System.exit(0)
            }.start()
            start.text = "Stop";
            startConsoleOutput()
        }
    }

    fun startConsoleOutput()
    {
    //    val console = ConsoleView();
        Console.init()
        val thread = Thread {
            try
            {
                while (true)
                {
                    val reader = BufferedReader(InputStreamReader(running!!.inputStream))

                    while (true)
                    {
                        val line = reader.readLine() ?: continue
                       // console.out.print(line + "\n")
                        Console.write(line)
                    }
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }

        }
        val error = Thread {
            try
            {
                while (true)
                {
                    val reader = BufferedReader(InputStreamReader(running!!.errorStream))

                    while (true)
                    {
                        val line = reader.readLine() ?: continue
                     //   console.out.print(line + "\n")
                        Console.write(line)

                    }
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }

        }
    //    createSceneAndStage(console).show()
        thread.isDaemon = true;
        error.isDaemon = true;
        thread.start();
        error.start()
    }

    fun createSceneAndStage(root: BorderPane): Stage
    {
        val stage = Stage()
        stage.title = "Cracker Output"
        stage.onCloseRequest = EventHandler {
            System.exit(0)
        }
        val scene = Scene(root, root.prefWidth, root.prefHeight)
        scene.stylesheets.add(GUI::class.java.classLoader.getResource("style.css").toString())
        stage.scene = scene
        return stage;
    }
}