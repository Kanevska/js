package web;

import metadata.Path;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller implements HttpRequestHandler {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Command command;
        String URI = httpServletRequest.getRequestURI();

        command = (Command) applicationContext.getBean(URI);
        try{
            command.execute(httpServletRequest, httpServletResponse);
        }catch(NoSuchBeanDefinitionException e){
            httpServletResponse.sendRedirect(Path.ERROR_PAGE);
        }

    }
}

