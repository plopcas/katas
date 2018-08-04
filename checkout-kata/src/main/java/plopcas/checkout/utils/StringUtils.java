package plopcas.checkout.utils;

/**
 * Utility class with static methods to deal with strings. It is a stripped down fork of the
 * StringUtils class in org.apache.commons:commons-lang3 library.
 */
public class StringUtils {

  public static final String EMPTY = "";

  private StringUtils() {}
  
  public static boolean isBlank(final CharSequence cs) {
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isNotBlank(final CharSequence cs) {
    return !isBlank(cs);
  }

}
