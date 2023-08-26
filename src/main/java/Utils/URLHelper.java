package Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

// This class will create the base webapp url to access any resources (css, jsp, js and other classes) regardless of the installation path of project
public class URLHelper {
    public static String urlBuild(HttpServletRequest req) throws ServletException, IOException{
        StringBuffer url = req.getRequestURL();
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String baseURL = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";

        req.setAttribute("baseURL", baseURL);

        return baseURL;
    }
}
