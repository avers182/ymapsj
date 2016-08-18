package ru.sav.ymaps;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class Servlet extends HttpServlet {
    private final static String HTMLHEADER = "<html><head></head><body>";
    private final static String HTMLFOOTER = "</body></html>";

    private final ServletContextInitializer initializer = new ServletContextInitializer();

    public Servlet() throws Exception {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initializer.init(this.getServletContext());
    }

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        boolean got = false;

        Map<String, ServletContextInitializer.Controller> controllers = (Map) this.getServletContext().getAttribute("controllers");
        if (controllers != null) {
            for (Map.Entry<String, ServletContextInitializer.Controller> e: controllers.entrySet()) {
                if (request.getServletPath().equals(e.getKey())) {
                    try {
                        e.getValue().getMethod().invoke(e.getValue().getObject(), request, response);
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }

                    got = true;
                    break;
                }
            }
        }

        if (!got) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println(HTMLHEADER);
            response.getWriter().print("<div>Not found</div>");
            response.getWriter().println(HTMLFOOTER);
        }
    }


    public void destroy() {
        initializer.destroy();
    }
}
