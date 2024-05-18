package JavaFiles;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SessionManager {
    private static final ObjectProperty<User> currentUser = new SimpleObjectProperty<>();

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static ObjectProperty<User> currentUserProperty() {
        return currentUser;
    }
}
