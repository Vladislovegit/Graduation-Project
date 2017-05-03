package commands.impl;

import bot.BotStateManager;
import commands.Command;
import dao.DaoFactory;
import exception.DAOException;
import exception.IncorrectBotNameException;
import model.JiraProject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.List;

public class GetJiraProjectsCommand implements Command {
    private static final String LOG_TAG = GetJiraProjectsCommand.class.getSimpleName();

    @Override
    public SendMessage execute(Message message) throws IncorrectBotNameException {
        SendMessage sendMessage = new SendMessage().enableHtml(true);
        try {
//            JiraController controller = new JiraController(message.getChatId());
//            List<JiraProject> projects = controller.getProjects();
            List<JiraProject> projects = DaoFactory.getJiraProjectDao().getAll(message.getChatId());

            StringBuilder stringBuilder = new StringBuilder("JIRA Projects: \n\n");

            for (JiraProject project : projects) {
                stringBuilder.append("<b>")
                        .append(project.getName())
                        .append("</b> [id:")
                        .append(project.getJiraId())
                        .append(",key:")
                        .append(project.getKey())
                        .append("]\n")
                        .append("Description: ")
                        .append(project.getDescription() == null ? "-" : project.getDescription())
                        .append("\n\n");
            }

            sendMessage.setText(stringBuilder.toString());
        } catch (DAOException e) {
            BotLogger.error(LOG_TAG, e);
            sendMessage.setChatId("Some errors occurred. Can't load JIRA projects. " + e.getMessage());
        }

        BotStateManager.resetState();
        return sendMessage;
    }
}
