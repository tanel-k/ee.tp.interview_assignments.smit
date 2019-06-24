package ee.tp.interview_assignments.smit.cli.options;

public abstract class CommandLineOptionsBean {
	private boolean helpRequest;

	public boolean isHelpRequest() {
		return helpRequest;
	}

	public void setHelpRequest(boolean helpRequest) {
		this.helpRequest = helpRequest;
	}
}
