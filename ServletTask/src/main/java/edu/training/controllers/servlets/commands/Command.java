package edu.training.controllers.servlets.commands;

import edu.training.controllers.servlets.InitContext;
import edu.training.model.services.PublicationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class Command {
    PublicationService service;

    Command() { this.service= new PublicationService(InitContext.getDataSource());
    }

    public abstract void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}