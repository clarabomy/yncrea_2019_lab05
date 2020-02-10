package yncrea.lab05.web;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import yncrea.lab05.core.config.AppConfig;
import yncrea.lab05.core.config.DBConfig;
import yncrea.lab05.web.config.WsConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class, DBConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WsConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/services/*"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addServlet("cxfServlet", CXFServlet.class);
    }
}
