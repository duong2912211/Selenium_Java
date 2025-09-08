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
            return UTIL.format(n, PhoneNumberFormat.INTERNATIONAL);             // -> "+49 641 7468838"
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
            return UTIL.format(n, PhoneNumberFormat.E164);                      // -> "+496417468838"
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Cannot parse: " + raw, e);
        }
    }

    // Convenience: compare two inputs regardless of how they’re written
    public static boolean equalsIgnoringFormatting(String a, String b) {
        return toE164(a).equals(toE164(b));
    }
}

