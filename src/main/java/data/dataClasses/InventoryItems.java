package data.dataClasses;

import data.models.InventoryItem;
import utils.BaseTest;

public class InventoryItems {

    public static InventoryItem sauceLabsBackpack =
            InventoryItem.builder()
                    .header("Sauce Labs Backpack")
                    .description("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.")
                    .price(29.99)
                    .imgPath(BaseTest.pathToSauceBackpack)
                    .build();

    public static InventoryItem sauceLabsBikeLight =
            InventoryItem.builder()
                    .header("Sauce Labs Bike Light")
                    .description("A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.")
                    .price(9.99)
                    .imgPath(BaseTest.pathToBikeLight)
                    .build();

    public static InventoryItem sauceLabsBoltTShirt =
            InventoryItem.builder()
                    .header("Sauce Labs Bolt T-Shirt")
                    .description("Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.")
                    .price(15.99)
                    .imgPath(BaseTest.pathToBoltShirt)
                    .build();

    public static InventoryItem sauceLabsFleeceJacket =
            InventoryItem.builder()
                    .header("Sauce Labs Fleece Jacket")
                    .description("It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.")
                    .price(49.99)
                    .imgPath(BaseTest.pathToSaucePullover)
                    .build();

    public static InventoryItem SauceLabsOnesie =
            InventoryItem.builder()
                    .header("Sauce Labs Onesie")
                    .description("Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.")
                    .price(7.99)
                    .imgPath(BaseTest.pathToRedOnesie)
                    .build();

    public static InventoryItem testAllTheThingsTShirtRed =
            InventoryItem.builder()
                    .header("Test.allTheThings() T-Shirt (Red)")
                    .description("This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.")
                    .price(15.99)
                    .imgPath(BaseTest.pathToRedTatt)
                    .build();
}
