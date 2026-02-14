package com.example;

public enum MainMenu {
    BENEFITS("קצבאות והטבות"),
    INSURANCE("דמי ביטוח"),
    COLLECTION("גבייה ואכיפה"),
    RIGHTS("זכויות במידע"),
    EMPLOYERS("מעסיקים");

    private final String menuText;

    MainMenu(String menuText) {
        this.menuText = menuText;
    }

    public String getMenuText() {
        return menuText;
    }
}
