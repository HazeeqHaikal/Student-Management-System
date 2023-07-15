import java.io.IOException;

public abstract class User {
    // attributes
    private String ID;
    private String password;

    // default constructor
    public User() {
        this.ID = "";
        this.password = "";
    }

    // normal constructor
    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    // getter and setter methods
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // abstract method
    public abstract void createAccount(String name) throws IOException;

    // makes the text green in the console for successful operations
    public void greenText(String text) {
        String[] splitText = text.split("");
        // no matter how long the text is, it will always take 1.5 seconds to print
        long sleepTime = (long) (1.5 / splitText.length * 1000);
        for (int i = 0; i < splitText.length; i++) {
            System.out.printf("\u001B[32m" + splitText[i] + "\u001B[0m");
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    // makes the text red in the console for failed operations
    public void redText(String text) {
        String[] splitText = text.split("");
        // no matter how long the text is, it will always take 1.5 seconds to print
        long sleepTime = (long) (1.5 / splitText.length * 1000);
        for (int i = 0; i < splitText.length; i++) {
            System.out.printf("\u001B[31m" + splitText[i] + "\u001B[0m");
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    // tostring
    public String toString() {
        return "ID: " + ID + "\nPassword: " + password;
    }

}