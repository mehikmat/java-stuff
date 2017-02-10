package cc.scrub.cli;

import org.apache.commons.cli.*;

/**
 * Command line option parser using apache commons-cli API
 *
 * @author Hikmat Dhamee
 * @email hikmatdhamee@gmail.com
 *
 */
public class CmdOptions {
    // command-line options // could be added more here later on
    public Option help;
    public Option inputPath;
    public Option outputPath;

    // option object
    protected Options options;

    public CmdOptions(){
    }

    public static CmdOptions createOptions() {
        CmdOptions cmdOptions = new CmdOptions();
        cmdOptions.initializeOptions();
        return cmdOptions;
    }

    protected void initializeOptions() {
        help =  addOption("help", "Prints help");
        inputPath =  addOptionWithArg("inputPath", "Input file/folder path");
        outputPath = addOptionWithArg("outputPath", "Output file/folder path");

        options = new Options();

        options.addOption(help);
        options.addOption(inputPath);
        options.addOption(outputPath);
    }

    private Option addOption(String option, String description) {
        OptionBuilder.withArgName(option);
        OptionBuilder.withDescription(description);
        Option argOption = OptionBuilder.create(option);
        return (argOption);
    }

    private Option addOptionWithArg(String option, String description) {
        OptionBuilder.withArgName(option);
        OptionBuilder.withDescription(description);
        OptionBuilder.hasArg();
        Option argOption = OptionBuilder.create(option);
        return (argOption);
    }

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

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("hadoop jar WarcUrlExtractor-1.0.jar -inputPath cc_input -outputPath cc_output", options);
        System.exit(1);
    }
}