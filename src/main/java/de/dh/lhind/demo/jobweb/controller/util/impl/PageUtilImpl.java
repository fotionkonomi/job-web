package de.dh.lhind.demo.jobweb.controller.util.impl;

import de.dh.lhind.demo.jobweb.controller.util.PageUtil;
import org.springframework.stereotype.Component;

@Component
public class PageUtilImpl implements PageUtil {

    @Override
    public <T> String getContextPage(Class<T> clazz) {
        String classSimpleName = clazz.getSimpleName();
        return classSimpleName.replace("Controller", "").toLowerCase();
    }

    @Override
    public <T> String getAddPage(Class<T> clazz) {
        return getContextPage(clazz).toLowerCase();
    }
}
