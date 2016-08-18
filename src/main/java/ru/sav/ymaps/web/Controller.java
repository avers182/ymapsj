package ru.sav.ymaps.web;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import ru.sav.ymaps.DAO.DAO;
import ru.sav.ymaps.model.Track;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Controller {
    private DAO dao;
//    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void listTracksPlain(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("<table><thead><tr><th>Name</th><th>Distance</th><th>Start time</th><th>End time</th></tr></thead><tbody>");
        for (Track t: dao.listTracks()) {
//            sb.append(String.format("<tr><td>%s</td><td>%f</td><td>%s</td><td>%s</td></tr>", t.getName(), t.getDistance(), fmt.format(t.getStartTime()), fmt.format(t.getEndTime())));
            sb.append(String.format("<tr><td>%s</td><td>%f</td><td>%s</td><td>%s</td></tr>", t.getName(), t.getDistance(), t.getStartTimeS(), t.getEndTimeS()));
        }
        sb.append("</tbody></table>");

        response.getWriter().print(sb.toString());
    }

    public void listTracksThyme(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext webContext = new WebContext(request, response, request.getServletContext());
        webContext.setVariable("tracks", dao.listTracks());

        TemplateEngine templateEngine = (TemplateEngine) request.getServletContext().getAttribute("templateEngine");
        templateEngine.process("list", webContext, response.getWriter());
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
}
