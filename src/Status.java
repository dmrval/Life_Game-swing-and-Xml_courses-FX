public class Status {
    private String currentStatus;

    public Status() {
        this.currentStatus = "no";
    }

    public Status level1(int neighbors) {
        if (currentStatus.equals("no")) {
            if (neighbors == 3) {
                currentStatus = "born";
            }
        }
        if (currentStatus.equals("live")) {
            if (neighbors == 1 || neighbors >= 4) {
                currentStatus = "die";
            }
        }
        return this;
    }

    public void level2() {
        if (currentStatus.equals("born")) {
            currentStatus = "live";
        }
        if (currentStatus.equals("die")) {
            currentStatus = "no";
        }
    }


    public String getCurrentStatus() {
        return currentStatus;
    }

    public boolean isAlive() {
        return currentStatus.equals("live")
                || currentStatus.equals("die");
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
