package helper;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneDE {
    private static final PhoneNumberUtil UTIL = PhoneNumberUtil.getInstance();

    // For comparing with UI that shows spaced international format (e.g., "+49 641 7468838")
    public static String formatForUi(String raw) {
        if (raw == null) return "";
        String cleaned = raw.replace('\u00A0', ' ').replaceAll("[^+\\d]", ""); // strip () - spaces, keep + and digits
        if (cleaned.startsWith("00")) cleaned = "+" + cleaned.substring(2);    // handle 00-intl prefix
        try {
            PhoneNumber n = UTIL.parse(cleaned, "DE");
            if (!UTIL.isValidNumberForRegion(n, "DE")) throw new IllegalArgumentException("Invalid DE number: " + raw);
            return formatPhoneNumber(UTIL.format(n, PhoneNumberFormat.INTERNATIONAL));             // -> "+49 641 7468838"
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Cannot parse: " + raw, e);
        }
    }

    // For strict logical equality (ignore spaces/formatting)
    public static String toE164(String raw) {
        if (raw == null) return "";
        String cleaned = raw.replace('\u00A0', ' ').replaceAll("[^+\\d]", "");
        if (cleaned.startsWith("00")) cleaned = "+" + cleaned.substring(2);
        try {
            PhoneNumber n = UTIL.parse(cleaned, "DE");
            if (!UTIL.isValidNumberForRegion(n, "DE")) throw new IllegalArgumentException("Invalid DE number: " + raw);
            return formatPhoneNumber(UTIL.format(n, PhoneNumberFormat.E164));                      // -> "+49 6417 468838"
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Cannot parse: " + raw, e);
        }
    }

    public static String formatPhoneNumber(String phoneNumber) {
        // Remove any existing spaces
        String cleaned = phoneNumber.replaceAll("\\s+", "");

        // Format: +49 9564 3023188
        if (cleaned.startsWith("+49") && cleaned.length()==14) {
            return cleaned.substring(0, 3) + " " +
                    cleaned.substring(3, 7) + " " +
                    cleaned.substring(7);
        } else if (cleaned.startsWith("+49") && cleaned.length()==13) {
            return cleaned.substring(0, 3) + " " +
                    cleaned.substring(3, 6) + " " +
                    cleaned.substring(6);
        }
        return phoneNumber; // Return original if doesn't match pattern
    }

    // Convenience: compare two inputs regardless of how they’re written
    public static boolean equalsIgnoringFormatting(String a, String b) {
        return toE164(a).equals(toE164(b));
    }
}

