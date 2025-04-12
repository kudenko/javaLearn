package app.library.editor;

import java.beans.PropertyEditorSupport;

public class CustomIntegerEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.trim().isEmpty()) {
            setValue(null);
        } else {
            try {
                setValue(Integer.parseInt(text));
            } catch (NumberFormatException e) {
                setValue(null);
            }
        }
    }
}
