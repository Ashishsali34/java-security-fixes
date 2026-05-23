import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;

public class ESAPIEncodingExample {

    public static void main(String[] args) {

        // Simulated user input
        String userInput = "<script>alert('XSS')</script>";

        // Get ESAPI encoder
        Encoder encoder = ESAPI.encoder();

        // ✅ Encode for HTML context
        String safeHtml = encoder.encodeForHTML(userInput);

        // ✅ Encode for HTML attribute
        String safeAttr = encoder.encodeForHTMLAttribute(userInput);

        // ✅ Encode for JavaScript context
        String safeJS = encoder.encodeForJavaScript(userInput);

        // ✅ Encode for URL
        String safeURL = encoder.encodeForURL(userInput);

        // Output results
        System.out.println("Original: " + userInput);
        System.out.println("HTML Encoded: " + safeHtml);
        System.out.println("HTML Attribute Encoded: " + safeAttr);
        System.out.println("JavaScript Encoded: " + safeJS);
        System.out.println("URL Encoded: " + safeURL);
    }
}