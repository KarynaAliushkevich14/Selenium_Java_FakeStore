package com.example.testelkafakestore.domain;

import com.example.testelkafakestore.enums.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class BrowserActions {

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    private final JavascriptExecutor javascriptExecutor;
    private final String baseUrl;


    public BrowserActions(WebDriver webDriver, WebDriverWait webDriverWait, JavascriptExecutor javascriptExecutor) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.javascriptExecutor = javascriptExecutor;
        this.baseUrl = Constants.BASE_URL;
    }

    public BrowserActions goToUrl(String uri) {
        webDriver.navigate().refresh();
        String url = baseUrl + uri;
        webDriver.navigate().to(url);
        return this;
    }

    public BrowserActions refreshPage() {
        ((JavascriptExecutor) webDriver).executeScript("location.reload()");
        /** could be also:
         * webDriver.navigate().refresh();
         * webDriver.get(webDriver.getCurrentUrl());
         * */
        return this;
    }
}
