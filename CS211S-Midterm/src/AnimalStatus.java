public enum AnimalStatus {
    NEW(false),
    SHORT_TERM_RESIDENT(true),
    LONG_TERM_RESIDENT(false);

    private boolean isAdoptable;

    AnimalStatus(boolean isAdoptable) {
        this.isAdoptable = isAdoptable;
    }

    public boolean isAdoptable() {
        return isAdoptable;
    }
}