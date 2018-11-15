package edu.training.controllers.servlets;

import edu.training.controllers.servlets.commands.Command;
import edu.training.controllers.servlets.commands.SessionStartCommand;
import edu.training.controllers.servlets.commands.UnknownCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("")
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Command command;
        try {
            command = getCommand(req);
            command.process(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Command getCommand(HttpServletRequest req) throws InstantiationException, IllegalAccessException {
        try {
            return (Command) getCommandClass(req).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Class getCommandClass(HttpServletRequest req) {
        Class commandClazz;
        if (req.getParameter("command") == null) {
            commandClazz = SessionStartCommand.class;
        } else {
            try {
                String classFullName = "edu.training.controllers.servlets.commands." + req.getParameter("command") + "Command";
                commandClazz = Class.forName(classFullName);
            } catch (ClassNotFoundException e) {
                commandClazz = UnknownCommand.class;
            }
        }
        return commandClazz;
    }


}