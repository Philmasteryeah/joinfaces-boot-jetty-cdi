package org.philmaster.boot.views;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EditorView {

    private String text;
    
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }
}
