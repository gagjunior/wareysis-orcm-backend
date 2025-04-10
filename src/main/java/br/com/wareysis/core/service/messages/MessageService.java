package br.com.wareysis.core.service.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageService {

    private static final String BUNDLE_BASE = "messages.messages";

    public String getMessage(String key, Object... args) {

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE, new Locale("pt", "BR"));
        String message = bundle.getString(key);
        return MessageFormat.format(message, args);

    }
}
