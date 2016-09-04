package com.destp.view;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class LogForm extends AppenderSkeleton {

    JdScareBuy form = JdScareBuy.getInstance();
    JTextArea textArea = form.getControlArea();

    /**
     * 最多显示条数
     */
    protected int maxEntries = 100;
    /**
     * 已经在JTextArea上显示的条数
     */
    private int entries = 0;

    @Override
    protected void append(LoggingEvent event) {
        String text = this.layout.format(event);
        Document doc = null;
        try {
            doc = textArea.getDocument();
            if (entries >= maxEntries) {
                int endOfs = textArea.getLineEndOffset(entries - maxEntries);
                doc.remove(0, endOfs);
                entries = entries - 1;
            }
            entries = entries + 1;
            textArea.append(text);
            textArea.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        textArea.setText("");
        entries = 0;
    }

    public boolean requiresLayout() {
        return true;
    }
}
