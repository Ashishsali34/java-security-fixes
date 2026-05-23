import org.owasp.encoder.Encode;

public class XSSFixExample {

    public static void main(String[] args) {
        // Simulated user input (could be from request parameter)
        String userInput = "<script>alert('XSS')</script>";

        // Fix: Encode before displaying
        String safeOutput = Encode.forHtml(userInput);

        // Safe rendering (in real app, this goes to response)
        System.out.println("<h1>Hello " + safeOutput + "</h1>");
    }
}