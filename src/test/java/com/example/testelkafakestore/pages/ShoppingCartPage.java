package com.example.testelkafakestore.pages;

import com.example.testelkafakestore.domain.BrowserActions;
import com.example.testelkafakestore.domain.DriverManager;
import com.example.testelkafakestore.domain.BasePage;
import com.example.testelkafakestore.serviceHelper.ServiceHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ShoppingCartPage extends BasePage {
    private final DriverManager driverManager;
    private final BrowserActions browserActions;
    private final PageElements pageElements;

    public Double summOfPrices = 0.00;
    public final String uri = "cart/";

    @Autowired
    protected ShoppingCartPage(DriverManager driverManager, BrowserActions browserActions) {
        super(driverManager, browserActions);

        this.driverManager = driverManager;
        this.browserActions = browserActions;
        this.pageElements = new PageElements();

        Init();
    }

    protected void Init() {
        PageFactory.initElements(driverManager.localWebDriver, pageElements);
    }

    public Double summaryPriceOfAllProductsInCart() {
        for (WebElement item : pageElements.listOfAllChosenProducts) {
            WebElement priceElement = item.findElement(By.xpath(".//td[contains(@class, 'product') and contains(@data-title, 'Price')]//span[contains(@class, 'woocommerce-Price-amount')]"));
            Double price = ServiceHelper.parseStringToDouble(priceElement.getText());
            summOfPrices += price;
        }
        return summOfPrices;
    }


    protected class PageElements {

        @FindBy(how = How.XPATH, using = "//tr[contains(@class, 'woocommerce-cart-form__cart-item cart_item')]")
        private List<WebElement> listOfAllChosenProducts;

    }
}
