package today.accounts.cracker.standalone.config

import today.accounts.cracker.standalone.ProgramStarter
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.concurrent.ConcurrentHashMap

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author PSW22 (Windward)
 * @since Thursday, November 2017
 */
object Config
{

    /**
     * The "config" file path
     */
    private val config = File(File(ProgramStarter.javaClass.protectionDomain.codeSource.location.toURI()).parentFile.absolutePath + File.separator + "config.txt");
    /**
     * A map of all the keys and inputted options;
     */
    private var options: MutableMap<String, String?> = ConcurrentHashMap();


    /**
     * Grab the provided option's value given its key
     * @param option The option key;
     * @return The option value (if present)
     */
    fun get(option: String): String?
    {
        return options[option];
    }

    /**
     * Put the provided option in our options list
     * @param option the option key
     * @param value the option value
     */
    fun put(option: String, value: String)
    {
        options[option] = value;
    }

    /**
     * Save the options
     */
    fun save()
    {
        config.delete();
        config.createNewFile();
        val lines: List<String> = options.map { "${it.key}=${it.value}" }
        Files.write(config.toPath(), lines, StandardOpenOption.APPEND)
    }

    init
    {
        if (!config.exists()) config.createNewFile();
        config.readLines().forEach {
            options.put(it.split("=")[0],it.split("=")[1])
        }
    }
}