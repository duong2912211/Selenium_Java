package helper;

import org.openqa.selenium.*;

public class Helper {
    /**
     * Check if element is not blocked by any other element at its coordinates.
     *
     * @return
     */
    public static boolean checkElementNotBlocked(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Point location = element.getLocation();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean isBlocked = (Boolean) js.executeScript(
                    "var elem = document.elementFromPoint(arguments[0], arguments[1]);" +
                            "return elem !== arguments[2];",
                    location.getX() + 1, // +1 to ensure we click inside the element
                    location.getY() + 1,
                    element
            );

            return Boolean.FALSE.equals(isBlocked);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static String convertTextToId(String text) {
        return text.toLowerCase().replace(" ", "").trim();
    }

    public static By byIdIgnoreCaseAndSpaces(String htmlTag, String attribute, String idValue) {
        // Remove all spaces from the input and make lowercase
        String normalized = idValue.replaceAll("\\s+", "").toLowerCase();

        // Create the XPath expression for case-insensitive ID
        String translateIdToLower = "translate(@" + attribute + ", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')";

        // Build the full XPath using the variable
        String xpath = "//" + htmlTag + "[" + translateIdToLower + " = '" + normalized + "']";

        return By.xpath(xpath);
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // convert sec → ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
