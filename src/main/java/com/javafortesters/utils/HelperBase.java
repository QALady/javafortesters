package com.javafortesters.utils;


import org.openqa.selenium.WebDriver;

/**
 * Created by Alena on 1/17/2015.
 */
public abstract class HelperBase {
    protected final WebDriver driver;
    protected ApplicationManager manager;


    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
        this.driver = manager.driver;
    }


}
