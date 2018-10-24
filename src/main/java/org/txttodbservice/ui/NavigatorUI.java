package org.txttodbservice.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SpringUI
@Theme("valo")
public class NavigatorUI extends UI {

    private MainMenuForm mainMenuForm = new MainMenuForm();
    Navigator navigator;

    public static final String MAIN_MENU_FORM = "mainMenuForm";



    @Autowired
    public NavigatorUI() {

    }

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        navigator.addView(MAIN_MENU_FORM, mainMenuForm);
        navigator.navigateTo(MAIN_MENU_FORM);

    }

    public Navigator getNavigator() {
        return navigator;
    }
}
