package bot;

import commands.CommandName;

public abstract class BotStateManager {

    private enum BotState {
        WAITING, WORKING
    }

    private static CommandName command = null;

    private static BotState state = BotState.WAITING;

    public static Boolean isWaiting() {
        return state.equals(BotState.WAITING);
    }

    public static void setCommand(CommandName command) {
        BotStateManager.state = BotState.WORKING;
        BotStateManager.command = command;
    }

    public static CommandName getCommand() {
        return command;
    }

    public static void resetState() {
        BotStateManager.state = BotState.WAITING;
        BotStateManager.command = null;
    }
}
