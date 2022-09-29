package com.shasha.gson;

import com.shasha.gson.model.Label;
import com.shasha.gson.repository.gson.GsonLabelRepositoryImpl;
import com.shasha.gson.view.MainMenu;
import com.shasha.gson.view.MainView;

import java.util.Arrays;

public class AppRunner {
    public static void main(String[] args) {
        MainMenu start = new MainMenu();
        start.performMainMenu();
    }
}
