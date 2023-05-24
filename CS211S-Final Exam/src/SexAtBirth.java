public enum SexAtBirth {
	MALE("male"), FEMALE("female"), OTHER("other");

	private String display;

	private SexAtBirth(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return this.display;
	}
}