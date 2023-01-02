package com.servletsimple;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8999);
        tomcat.getConnector();
        Context context = tomcat.addWebapp("", new File("servlet-simple/src/main/webapp").getAbsolutePath());
        WebResourceRoot webResourceRoot = new StandardRoot(context);
        webResourceRoot.addPreResources(new DirResourceSet(webResourceRoot, "/WEB-INF/classes",
                new File("servlet-simple/target/classes").getAbsolutePath(), "/"));
        context.setResources(webResourceRoot);
        tomcat.start();
        tomcat.getServer().await();
    }
}
