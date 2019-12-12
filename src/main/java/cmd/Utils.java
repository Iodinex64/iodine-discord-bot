package cmd;

class Utils {

    static String RemoveTagSurrounds(String id) {
        String newID;
            newID = id.replace("<", "").replace("@", "").replace(">", "");
            return newID;
    }

    static String RemoveWholeTag(String message) {
        String newMessage;
        newMessage = message.replaceAll("<[@#][!&]?[0-9]+>", "");
        return newMessage;
    }
}
