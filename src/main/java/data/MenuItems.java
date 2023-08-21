package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuItems {
    ELEMENTS("Elements"),
    FORMS("Forms"),
    ALERTS_FRAME_WINDOWS("Alerts, Frame & Windows"),
    WIDGETS("Widgets"),
    INTERACTIONS("Interactions"),
    BOOK_STORE_APPLICATION("Book Store Application");

    @Getter
    public String name;
}
