package commands;

import commands.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static final Map<CommandName, Command> commands = new HashMap<>();

    static {
        commands.put(CommandName.NON_COMMAND, new NonCommand());
        commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
        commands.put(CommandName.AUTHENTICATE_TEAMCITY, new AuthenticateTeamCityCommand());
        commands.put(CommandName.START, new StartCommand());
        commands.put(CommandName.AUTHENTICATE_JIRA, new AuthenticateJiraCommand());
        commands.put(CommandName.HELP, new HelpCommand());
        commands.put(CommandName.GET_JIRA_PROJECTS, new GetJiraProjectsCommand());
    }

    public static Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
