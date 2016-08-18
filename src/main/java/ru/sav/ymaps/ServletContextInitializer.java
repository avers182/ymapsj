package ru.sav.ymaps;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import ru.sav.ymaps.DAO.DAO;
import ru.sav.ymaps.DAO.SqliteDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServletContextInitializer {
    private ServletContext servletContext;

    public void init(ServletContext servletContext) {
        this.servletContext = servletContext;

        DAO dao = null;
        try {
            dao = new SqliteDAO("jdbc:sqlite::resource:TracksDB.sqlite");
//            DAO dao = new HDAO();
            servletContext.setAttribute("dao", dao);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ru.sav.ymaps.web.Controller controller = new ru.sav.ymaps.web.Controller();
        controller.setDao(dao);

        Map<String, Controller> controllers = new LinkedHashMap<>();
        try {
            controllers.put("/noth", new Controller(controller, ru.sav.ymaps.web.Controller.class.getMethod("listTracksPlain", HttpServletRequest.class, HttpServletResponse.class)));
            controllers.put("/th", new Controller(controller, ru.sav.ymaps.web.Controller.class.getMethod("listTracksThyme", HttpServletRequest.class, HttpServletResponse.class)));
            controllers.put("/", new Controller(controller, ru.sav.ymaps.web.Controller.class.getMethod("listTracksThyme", HttpServletRequest.class, HttpServletResponse.class)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("controllers", controllers);


        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        servletContext.setAttribute("templateEngine", templateEngine);

    }

    public void destroy() {
        DAO dao = (DAO) this.servletContext.getAttribute("dao");
        if (dao != null) {
            dao.close();
        }
    }

    public static class Controller {
        private Object object;
        private Method method;

        public Controller(Object object, Method method) {
            this.object = object;
            this.method = method;
        }

        public Object getObject() {
            return object;
        }

        public Method getMethod() {
            return method;
        }
    }
}
