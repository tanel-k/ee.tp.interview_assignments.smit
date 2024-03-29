package ee.tp.interview_assignments.smit.cli;

/**
 * Base class for defining command-line interface contracts.
 *
 * @see ArgumentArrayParser
 */
public abstract class OptionsBean {
    private boolean helpRequest;

    /**
     * @return true, if the user only wishes to see a help message
     */
    public boolean isHelpRequest() {
        return helpRequest;
    }

    public void setHelpRequest(boolean helpRequest) {
        this.helpRequest = helpRequest;
    }
}
