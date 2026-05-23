import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 1024 * 1024 * 5,    // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class SecureFileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "C:/secure-uploads/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("file");

        // ✅ Get submitted file name safely
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // ✅ Validate file name
        if (fileName.contains("..")) {
            response.getWriter().println("Invalid file name!");
            return;
        }

        // ✅ Allow only specific extensions
        String[] allowedExtensions = { ".png", ".jpg", ".jpeg", ".pdf" };
        boolean validExtension = false;

        for (String ext : allowedExtensions) {
            if (fileName.toLowerCase().endsWith(ext)) {
                validExtension = true;
                break;
            }
        }

        if (!validExtension) {
            response.getWriter().println("File type not allowed!");
            return;
        }

        // ✅ Validate MIME type (basic check)
        String mimeType = filePart.getContentType();
        if (!(mimeType.equals("image/png") ||
              mimeType.equals("image/jpeg") ||
              mimeType.equals("application/pdf"))) {

            response.getWriter().println("Invalid file content!");
            return;
        }

        // ✅ Create upload directory if not exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // ✅ Generate safe unique file name
        String safeFileName = System.currentTimeMillis() + "_" + fileName;

        String filePath = UPLOAD_DIR + safeFileName;

        // ✅ Save file
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, Paths.get(filePath));
        }

        response.getWriter().println("File uploaded securely: " + safeFileName);
    }
}