package seedu.address.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.MailUtil.MAIL_TO;
import static seedu.address.commons.util.MailUtil.isDesktopCompatible;
import static seedu.address.commons.util.MailUtil.isValidURI;
import static seedu.address.commons.util.MailUtil.preprocessEmailAddresses;

public class MailUtilTest {
    private final String[] sampleEmails = new String[] { "rgarton@yahoo.ca",
            "bonmots@icloud.com", "jespley@aol.com", "suresh@comcast.net", "martyloo@yahoo.ca" };

    @Test
    public void mailUriTest_noExceptionThrown() {
        String uriSampleOne = preprocessEmailAddresses(sampleEmails);
        String uriSampleTwo = preprocessEmailAddresses(sampleEmails);

        assert uriSampleOne.contains(MAIL_TO);

        assert uriSampleOne.startsWith(MAIL_TO);

        assertEquals(uriSampleOne, uriSampleTwo);
    }

    @Test
    public void desktopCompatibilityTest_noExceptionThrown() {
        assertTrue(isDesktopCompatible());
    }

    @Test
    public void uriSyntaxCheck_noExceptionThrown() {
        assertFalse(isValidURI(sampleEmails[0]));

        assertFalse(isValidURI("mail"));

        assertFalse(isValidURI(""));

        assertFalse(isValidURI("@@@@@"));
    }

}
