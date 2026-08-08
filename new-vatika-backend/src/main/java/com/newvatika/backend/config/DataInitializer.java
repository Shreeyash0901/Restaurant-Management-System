package com.newvatika.backend.config;

import com.newvatika.backend.entity.*;
import com.newvatika.backend.repository.CategoryRepository;
import com.newvatika.backend.repository.MenuItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.newvatika.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    @PostConstruct
    public void seed() {
        createAdminUser();
        if (categoryRepository.count() > 0) return;

        Category noodles = saveCategory("Noodles", "नूडल्स", "noodles", "🍜", MenuTab.CHINESE, 1);
        Category starter = saveCategory("Chinese Starter", "चाइनीज़ स्टार्टर", "starter", "🥢", MenuTab.CHINESE, 2);
        Category rice = saveCategory("Chinese Rice", "चाइनीज़ राइस", "rice", "🍚", MenuTab.CHINESE, 3);
        Category momos = saveCategory("Momos", "मोमोज़", "momos", "🥟", MenuTab.CHINESE, 4);
        Category soups = saveCategory("Soups", "सूप", "soups", "🍲", MenuTab.CHINESE, 5);
        Category pizza = saveCategory("Pizza", "पिज़्ज़ा", "pizza", "🍕", MenuTab.FAST_FOOD, 6);
        Category sandwich = saveCategory("Sandwich", "सैंडविच", "sandwich", "🥪", MenuTab.FAST_FOOD, 7);
        Category burger = saveCategory("Burger", "बर्गर", "burger", "🍔", MenuTab.FAST_FOOD, 8);
        Category fries = saveCategory("Fries", "फ्राइज़", "fries", "🍟", MenuTab.FAST_FOOD, 9);
        Category pasta = saveCategory("Pasta", "पास्ता", "pasta", "🍝", MenuTab.FAST_FOOD, 10);
        Category maggi = saveCategory("Maggie", "मैगी", "maggi", "🍛", MenuTab.FAST_FOOD, 11);
        Category colddrinks = saveCategory("Cold Beverages", "कोल्ड बेवरेजेज़", "colddrinks", "🧋", MenuTab.DRINKS, 12);
        Category mocktails = saveCategory("Mocktails", "मॉकटेल्स", "mocktails", "🍹", MenuTab.DRINKS, 13);
        Category shakes = saveCategory("Shakes", "शेक्स", "shakes", "🥤", MenuTab.DRINKS, 14);

        int i = 0;
        saveItem(noodles, "Veg Noodles", "वेज नूडल्स", 140.0, i++);
        saveItem(noodles, "Hakka Noodles", "हक्का नूडल्स", 140.0, i++);
        saveItem(noodles, "Sechzwan Noodles", "सेजवान नूडल्स", 150.0, i++);
        saveItem(noodles, "Chilli Garlic Noodles", "चिल्ली गार्लिक नूडल्स", 140.0, i++);
        saveItem(noodles, "Chinese Bhel", "चाइनीज़ भेल", 150.0, i++);
        saveItem(noodles, "Manchurian Noodles", "मंचूरियन नूडल्स", 150.0, i++);

        i = 0;
        saveItemWithNote(starter, "Veg Manchurian Dry / Gravy", "वेज मंचूरियन ड्राय / ग्रेवी", 140.0, "Available Dry or Gravy", i++);
        saveItemWithNote(starter, "Paneer Manchurian Dry / Gravy", "पनीर मंचूरियन ड्राय / ग्रेवी", 180.0, "Available Dry or Gravy", i++);
        saveItemWithNote(starter, "Paneer Chilli Dry / Gravy", "पनीर चिल्ली ड्राय / ग्रेवी", 200.0, "Available Dry or Gravy", i++);
        saveItem(starter, "Mushroom Chilli", "मशरूम चिल्ली", 190.0, i++);
        saveItem(starter, "Paneer 65", "पनीर 65", 200.0, i++);
        saveItem(starter, "Spring Roll", "स्प्रिंग रोल", 120.0, i++);
        saveItem(starter, "Babycorn Chilli", "बेबीकॉर्न चिल्ली", 190.0, i++);

        i = 0;
        saveItem(rice, "Veg Fried Rice", "वेज फ्राइड राइस", 140.0, i++);
        saveItem(rice, "Paneer Fried Rice", "पनीर फ्राइड राइस", 180.0, i++);
        saveItem(rice, "Schezwan Fried Rice", "सेजवान फ्राइड राइस", 150.0, i++);
        saveItem(rice, "Chilli Garlic Rice", "चिल्ली गार्लिक राइस", 150.0, i++);
        saveItem(rice, "Manchurian Rice", "मंचूरियन राइस", 150.0, i++);
        saveItem(rice, "Vatika Special Rice", "वाटिका स्पेशल राइस", 210.0, i++);

        i = 0;
        saveItem(momos, "Veg Steam Momos", "वेज स्टीम मोमोज़", 100.0, i++);
        saveItem(momos, "Paneer Steam Momos", "पनीर स्टीम मोमोज़", 120.0, i++);
        saveItem(momos, "Veg Fried Momos", "वेज फ्राइड मोमोज़", 100.0, i++);
        saveItem(momos, "Paneer Fried Momos", "पनीर फ्राइड मोमोज़", 120.0, i++);
        saveItem(momos, "Veg Kurkure Momos", "वेज कुरकुरे मोमोज़", 140.0, i++);
        saveItem(momos, "Paneer Kurkure Momos", "पनीर कुरकुरे मोमोज़", 160.0, i++);
        saveItem(momos, "Veg Afgani Momos", "वेज अफगानी मोमोज़", 150.0, i++);
        saveItem(momos, "Paneer Afgani Momos", "पनीर अफगानी मोमोज़", 170.0, i++);
        saveItem(momos, "Veg Chilly Momos", "वेज चिल्ली मोमोज़", 150.0, i++);
        saveItem(momos, "Paneer Chilly Momos", "पनीर चिल्ली मोमोज़", 170.0, i++);

        i = 0;
        saveItem(soups, "Manchow Soup", "मंचाव सूप", 100.0, i++);
        saveItem(soups, "Hot N Sour Soup", "हॉट एंड सॉर सूप", 100.0, i++);
        saveItem(soups, "Lemon Coriander Soup", "लेमन धनिया सूप", 100.0, i++);
        saveItem(soups, "Sweet Corn Soup", "स्वीट कॉर्न सूप", 110.0, i++);
        saveItem(soups, "Special Soup", "स्पेशल सूप", 130.0, i++);
        saveItem(soups, "Tamato Soup", "टमाटर सूप", 90.0, i++);

        i = 0;
        saveItemHalfFull(pizza, "Veggie Pizza", "वेजी पिज़्ज़ा", 180.0, 230.0, i++);
        saveItemHalfFull(pizza, "Margerita Pizza", "मार्गेरिटा पिज़्ज़ा", 150.0, 200.0, i++);
        saveItemHalfFull(pizza, "Cheese Corn Pizza", "चीज़ कॉर्न पिज़्ज़ा", 180.0, 270.0, i++);
        saveItemHalfFull(pizza, "Veg Tandoori Pizza", "वेज तंदूरी पिज़्ज़ा", 200.0, 270.0, i++);
        saveItemHalfFull(pizza, "Paneer Tandoori Pizza", "पनीर तंदूरी पिज़्ज़ा", 200.0, 250.0, i++);
        saveItemHalfFull(pizza, "Cheesy Nachos Pizza", "चीज़ी नाचोज़ पिज़्ज़ा", 170.0, 230.0, i++);
        saveItemHalfFull(pizza, "Schezwan Paneer Pizza", "सेजवान पनीर पिज़्ज़ा", 230.0, 300.0, i++);
        saveItemHalfFull(pizza, "Paneer Chilli Pizza", "पनीर चिल्ली पिज़्ज़ा", 230.0, 300.0, i++);
        saveItemHalfFull(pizza, "Full Topping Pizza", "फुल टॉपिंग पिज़्ज़ा", 230.0, 300.0, i++);
        saveItemHalfFull(pizza, "Vatika Special Pizza", "वाटिका स्पेशल पिज़्ज़ा", 250.0, 320.0, i++);
        saveItemHalfFull(pizza, "Pasta Pizza", "पास्ता पिज़्ज़ा", 230.0, 290.0, i++);
        saveItemHalfFull(pizza, "Momos Pizza", "मोमोज़ पिज़्ज़ा", 230.0, 290.0, i++);

        i = 0;
        saveItem(sandwich, "Veg Grill Sandwich", "वेज ग्रिल सैंडविच", 70.0, i++);
        saveItem(sandwich, "Veg Cheese Sandwich", "वेज चीज़ सैंडविच", 80.0, i++);
        saveItem(sandwich, "Cheese Corn Sandwich", "चीज़ कॉर्न सैंडविच", 80.0, i++);
        saveItemWithNote(sandwich, "Veg / Paneer Tandoori Sandwich", "वेज / पनीर तंदूरी सैंडविच", 80.0, "Available Veg or Paneer", i++);
        saveItem(sandwich, "Veg Club Sandwich", "वेज क्लब सैंडविच", 100.0, i++);
        saveItem(sandwich, "Vatika Special Sandwich", "वाटिका स्पेशल सैंडविच", 140.0, i++);

        i = 0;
        saveItem(burger, "Veg Burger", "वेज बर्गर", 70.0, i++);
        saveItem(burger, "Veg Cheese Burger", "वेज चीज़ बर्गर", 80.0, i++);
        saveItem(burger, "Paneer Crispy Burger", "पनीर क्रिस्पी बर्गर", 110.0, i++);
        saveItem(burger, "Veg Crispy Burger", "वेज क्रिस्पी बर्गर", 90.0, i++);
        saveItem(burger, "Double Tikki Burger", "डबल टिक्की बर्गर", 100.0, i++);
        saveItem(burger, "Vatika Special Burger", "वाटिका स्पेशल बर्गर", 130.0, i++);

        i = 0;
        saveItem(fries, "Peri Peri Fries", "पेरी पेरी फ्राइज़", 100.0, i++);
        saveItem(fries, "Salted Fries", "साल्टेड फ्राइज़", 70.0, i++);
        saveItem(fries, "Cheese Loaded Fries", "चीज़ लोडेड फ्राइज़", 110.0, i++);
        saveItem(fries, "Peri Peri Cheese Fries", "पेरी पेरी चीज़ फ्राइज़", 120.0, i++);

        i = 0;
        saveItem(pasta, "White Sauce Pasta", "व्हाइट सॉस पास्ता", 120.0, i++);
        saveItem(pasta, "Red Sauce Pasta", "रेड सॉस पास्ता", 120.0, i++);
        saveItem(pasta, "Vatika Special Pasta", "वाटिका स्पेशल पास्ता", 210.0, i++);

        i = 0;
        saveItem(maggi, "Plain Maggi", "प्लेन मैगी", 50.0, i++);
        saveItem(maggi, "Butter Maggi", "बटर मैगी", 60.0, i++);
        saveItem(maggi, "Corn Cheese Maggi", "कॉर्न चीज़ मैगी", 80.0, i++);
        saveItem(maggi, "Cheese Paneer Maggi", "चीज़ पनीर मैगी", 90.0, i++);
        saveItem(maggi, "All Mix Maggi", "ऑल मिक्स मैगी", 100.0, i++);

        i = 0;
        saveItem(colddrinks, "Cold Coffee", "कोल्ड कॉफी", 50.0, i++);
        saveItem(colddrinks, "Thick Cold Coffee", "थिक कोल्ड कॉफी", 60.0, i++);
        saveItem(colddrinks, "Cold Coffee with Ice Cream", "कोल्ड कॉफी विद आइसक्रीम", 80.0, i++);
        saveItemWithNote(colddrinks, "Hazelnut Cold Coffee / Thick", "हेज़लनट कोल्ड कॉफी / थिक", 90.0, "Available regular or thick", i++);
        saveItemWithNote(colddrinks, "Caramel Cold Coffee / Thick", "कैरेमल कोल्ड कॉफी / थिक", 90.0, "Available regular or thick", i++);
        saveItem(colddrinks, "Hot Chocolate", "हॉट चॉकलेट", 60.0, i++);

        i = 0;
        saveItem(mocktails, "Mint Mojito", "मिंट मोजिटो", 70.0, i++);
        saveItem(mocktails, "Blue Berry", "ब्लू बेरी", 70.0, i++);
        saveItem(mocktails, "Watermelon", "तरबूज़", 70.0, i++);
        saveItem(mocktails, "Kala Khatta", "काला खट्टा", 70.0, i++);
        saveItem(mocktails, "Strawberry", "स्ट्रॉबेरी", 70.0, i++);
        saveItem(mocktails, "Green Apple", "ग्रीन एप्पल", 70.0, i++);

        i = 0;
        saveItem(shakes, "Kitkat Shake", "किटकैट शेक", 120.0, i++);
        saveItem(shakes, "Chocolate Shake", "चॉकलेट शेक", 120.0, i++);
        saveItem(shakes, "Oreo Shake", "ओरियो शेक", 120.0, i++);
        saveItem(shakes, "Butter Scotch Shake", "बटर स्कॉच शेक", 130.0, i++);
        saveItem(shakes, "Mango Shake", "मैंगो शेक", 130.0, i++);
        saveItem(shakes, "Strawberry Shake", "स्ट्रॉबेरी शेक", 130.0, i++);
        saveItem(shakes, "Biscoff Shake", "बिस्कॉफ शेक", 160.0, i++);
    }

    private Category saveCategory(String en, String hi, String slug, String icon, MenuTab tab, int order) {
        return categoryRepository.save(Category.builder()
                .nameEn(en).nameHi(hi).slug(slug).icon(icon).tab(tab)
                .displayOrder(order).active(true).build());
    }

    private void saveItem(Category category, String en, String hi, Double price, int order) {
        menuItemRepository.save(MenuItem.builder()
                .nameEn(en).nameHi(hi).price(price)
                .isVeg(true).isAvailable(true).displayOrder(order)
                .category(category).build());
    }

    private void saveItemHalfFull(Category category, String en, String hi, Double half, Double full, int order) {
        menuItemRepository.save(MenuItem.builder()
                .nameEn(en).nameHi(hi).priceHalf(half).priceFull(full)
                .isVeg(true).isAvailable(true).displayOrder(order)
                .category(category).build());
    }

    private void saveItemWithNote(Category category, String en, String hi, Double price, String note, int order) {
        menuItemRepository.save(MenuItem.builder()
                .nameEn(en).nameHi(hi).price(price)
                .descriptionEn(note).descriptionHi(note)
                .isVeg(true).isAvailable(true).displayOrder(order)
                .category(category).build());
    }

    private void createAdminUser() {

    if (userRepository.findByUsername("admin").isPresent()) {
        return;
    }

    User admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123"))
            .role(Role.ADMIN)
            .build();

    userRepository.save(admin);

    System.out.println("=================================");
    System.out.println("Admin user created");
    System.out.println("Username : admin");
    System.out.println("Password : admin123");
    System.out.println("=================================");
}
}