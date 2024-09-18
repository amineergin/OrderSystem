package com.example.qkare.CustomerOrders.Model.Entity.Attributes;

import java.util.Arrays;
import java.util.List;

public class AttributeValidator {
    private static final List<String> VALID_CLOTHING_SIZES = Arrays.asList("S", "M", "L", "XL", "XXL");
    private static final List<Integer> VALID_SHOE_SIZES = Arrays.asList(36, 37, 38, 39, 40, 41, 42, 43, 44, 45);

    public static boolean validateAttribute(String attributeName, String attributeValue, Category category) {
        switch (attributeName.toLowerCase()) {
            case "size":
                return validateSize(attributeValue, category);
            case "color":
                return validateColor(attributeValue);
            default:
                throw new IllegalArgumentException("Unknown attribute: " + attributeName);
        }
    }

    // Size validation
    private static boolean validateSize(String attributeValue, Category category) {
        if (category == Category.CLOTHING) {
            return VALID_CLOTHING_SIZES.contains(attributeValue);
            //.contains(attributeValue) ifadesi attributeValue değişkeninin VALID_CLOTHING_SIZES listesinin içinde olup olmadığını kontrol eder.
        } else if (category == Category.SHOES) {
            try {
                int shoeSize = Integer.parseInt(attributeValue);
                return VALID_SHOE_SIZES.contains(shoeSize);
            } catch (NumberFormatException e) {
                return false; // Eğer integer değilse geçersiz kabul et.
            }
        }
        return false;
    }

    // Color validation (örnek olarak, renklerin listeye eklenmesi sağlanabilir)
    private static boolean validateColor(String attributeValue) {
        return attributeValue != null && !attributeValue.trim().isEmpty();
    }

    //.trim() -> stringin başındaki ve sonundaki boşlukları siler.
}
