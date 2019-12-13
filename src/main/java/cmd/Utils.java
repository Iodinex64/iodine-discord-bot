package cmd;

class Utils {

    static String RemoveTagSurrounds(String id) {
        String newID;
            newID = id.replace("<", "").replace("@", "").replace(">", "");
            return newID;
    }

    static String RemoveWholeTag(String message) {
        return message.replaceAll("<[@#][!&]?[0-9]+>", "");
    }
}
