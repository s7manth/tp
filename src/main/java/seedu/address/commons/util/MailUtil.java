package seedu.address.commons.util;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Email;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MailUtil {

    public static final String MAIL_TO = "mailto:";

    public static final String URI_SYNTAX_ERROR_MESSAGE = "The URI syntax used is incorrect";

    public static final String DESKTOP_NOT_SUPPORTED_MESSAGE = "The desktop you are using is not supported";

    public static final String INTERNAL_DESKTOP_ERROR = "Internal desktop error";

    /**
     * Launches the system default email application.
     * @param emailList The list of receiver's email addresses.
     * @throws CommandException if any errors occur while launching the mail application.
     */
    public static void launchMail(Email... emailList) throws CommandException {
        Desktop desktop;
        StringBuilderUtil stringBuilderUtil = StringBuilderUtil.getInstance();

        try {
            if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                stringBuilderUtil.append(MAIL_TO);

                for (int i = 0; i < emailList.length; ++i) {
                    stringBuilderUtil.append(emailList[i]);

                    if (i + 1 < emailList.length) {
                        stringBuilderUtil.append(",");
                    }
                }

                URI mailto = new URI(stringBuilderUtil.getFormattedOutput());
                desktop.mail(mailto);
            } else {
                throw new CommandException(DESKTOP_NOT_SUPPORTED_MESSAGE);
            }
        } catch (URISyntaxException uriSyntaxException) {
            throw new CommandException(URI_SYNTAX_ERROR_MESSAGE);
        } catch (IOException ioException) {
            throw new CommandException(INTERNAL_DESKTOP_ERROR);
        }
    }
}
