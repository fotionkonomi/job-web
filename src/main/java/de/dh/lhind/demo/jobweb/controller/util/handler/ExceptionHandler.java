package de.dh.lhind.demo.jobweb.controller.util.handler;

import com.google.gson.Gson;
import de.dh.lhind.demo.jobweb.models.beException.HttpErrorResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j
public class ExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler(HttpStatusCodeException.class)
    public ModelAndView handleServiceExceptions(HttpStatusCodeException exception, HttpServletRequest request) {
        log.error("Exception from BE: " + exception);
        String responseBody = exception.getResponseBodyAsString();
        Gson g = new Gson();
        HttpErrorResponse httpErrorResponse = g.fromJson(responseBody, HttpErrorResponse.class);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionMessage", httpErrorResponse.getLocalizedMessage());
        modelAndView.addObject("url", request.getRequestURI());

        modelAndView.setViewName("error");
        return modelAndView;
    }
}
