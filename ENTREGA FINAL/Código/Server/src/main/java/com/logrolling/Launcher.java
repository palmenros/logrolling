package com.logrolling;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

public class Launcher {

    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet";

    public static void main(String[] args) throws Exception {
        new Launcher().start();
    }

    void start() throws Exception {

        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        String contextPath = "";
        String appBase = ".";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);

        Context context = tomcat.addContext(contextPath, appBase);

        ServletContainer servletContainer = new ServletContainer(new JerseyConfiguration());
        Tomcat.addServlet(context, JERSEY_SERVLET_NAME, servletContainer);

        context.addServletMappingDecoded("/*", JERSEY_SERVLET_NAME);
        context.addApplicationListener("com.logrolling.server.Main");

        tomcat.start();
        tomcat.getServer().await();
    }
}
