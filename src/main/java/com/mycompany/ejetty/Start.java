/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 *
 * @author erich
 */
public class Start {
    	public static void main(String[] args) {
		Server server = new Server();
		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setOutputBufferSize(32768);
		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(8080);
		http.setIdleTimeout(1000 * 60 * 60);
		server.addConnector(http);
                ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
                sch.addServlet(DefaultServlet.class, "/*");
                sch.setResourceBase(".");
                HandlerCollection handlerCollection = new HandlerCollection();
                handlerCollection.setHandlers(new Handler[] {sch});
                server.setHandler(handlerCollection);
		try {
                    server.start();
                    server.join();
		} catch (Exception e) {
                    System.exit(100);
		}
	}
}
