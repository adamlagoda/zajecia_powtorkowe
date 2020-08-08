package concurrency.guardedlock;

public class Message {
    private String text;
    private boolean empty = true;

    public synchronized String take() {
        return null;
    }

    public synchronized void put(String text) {

    }
}

