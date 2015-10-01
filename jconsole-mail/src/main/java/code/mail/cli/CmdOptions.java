package code.mail.cli;

import org.apache.commons.cli.*;

/**
 * Command line option parser using apache commons-cli API
 * @author <a href="mailto:me.hemant.available@gmail.com">Hikmat Dhamee</a>
 * @since 0.1
 * @version 0.1
 */
public class CmdOptions {
    // command-line options
    public Option help;
    public Option from;
    public Option to;
    public Option smtp;

    // option object
    protected Options options;

    public CmdOptions(){

    }

    /**
     * Creates Options
     * @return cmdOptions
     */
    public static CmdOptions createOptions() {
        CmdOptions cmdOptions = new CmdOptions();
        cmdOptions.initializeOptions();
        return (cmdOptions);
    }

    /**
     * Initializes options
     */
    protected void initializeOptions() {
        help = addOption("help", "Prints help");
        from   = addOptionWithArg("from", "Specify sender email address");
        to = addOptionWithArg("to", "Specify receiver email address");
        smtp = addOptionWithArg("smtp", "Specify smtp host address;default localhost");

        options = new Options();

        options.addOption(help);
        options.addOption(from);
        options.addOption(to);
        options.addOption(smtp);
    }

    /**
     * Adds option without argument
     * @param option
     * @param description
     * @return argOption
     */
    private Option addOption(String option, String description) {

        OptionBuilder.withArgName(option);
        OptionBuilder.withDescription(description);
        Option argOption = OptionBuilder.create(option);
        return (argOption);
    }

    /**
     * Adds option and argument to option object
     * @param option
     * @param description
     * @return argOption
     */
    private Option addOptionWithArg(String option, String description) {

        OptionBuilder.withArgName(option);
        OptionBuilder.withDescription(description);
        OptionBuilder.hasArg();
        Option argOption = OptionBuilder.create(option);
        return (argOption);
    }

    /**
     * Parses arguments
     * @param args
     * @return commandLine
     */
    public CommandLine parse(String[] args) {
        try {
            GnuParser parser = new GnuParser();
            CommandLine commandLine = parser.parse(options, args);
            return (commandLine);
        } catch (ParseException pe) {
            System.out.println(pe);
            return (null);
        }
    }

    /**
     * Prints help information
     */
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar jconsole-mail.jar", options);
    }
}
