package com.javafortesters.utils;


import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alena on 1/17/2015.
 */
public abstract class HelperBase {
    private static Logger LOG = LoggerFactory.getLogger(HelperBase.class.getName());

    protected final WebDriver driver;
    protected ApplicationManager manager;


    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
        this.driver = manager.driver;
    }


}
