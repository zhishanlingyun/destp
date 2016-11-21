package com.destp.common.ui.web.interceptor;

import com.destp.common.ui.web.util.WebUtils;
import com.destp.common.ui.web.view.RequestResult;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public class PageUIDecorate implements HandlerInterceptor,ApplicationContextAware,InitializingBean {


    private final static Logger logger = Logger.getLogger(PageUIDecorate.class);

    private ApplicationContext applicationContext;

    private LocaleResolver localeResolver;

    private ViewResolver viewResolver;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(null!=modelAndView){
            List<String> topeles = new ArrayList<String>();
            topeles.add("message");
            topeles.add("bbb");
            topeles.add("user");
            modelAndView.addObject("pageui", topeles);
            return;
        }
        /*if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if(handlerMethod.getMethod().getReturnType()==RequestResult.class){
                //handler.get
                JsonPage jsonPage = handlerMethod.getMethodAnnotation(JsonPage.class);
                if(null!=jsonPage){
                    String viewpage = jsonPage.page();
                    logger.error("@@@@@@@ view = "+viewpage);
                    render(viewpage,null,request,response);
                }
            }
        }*/

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println();
        /*if(null!=request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE)){
            Exception exception = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
            if(exception instanceof BindException){
                response.setStatus(200);
            }
        }*/

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void afterPropertiesSet() throws Exception {
        initLocaleResolver(applicationContext);
        initViewResolvers(applicationContext);
    }
    /**
     * Initialize the LocaleResolver used by this class.
     * <p>If no bean is defined with the given name in the BeanFactory for this namespace,
     * we default to AcceptHeaderLocaleResolver.
     */
    private void initLocaleResolver(ApplicationContext context) {
        try {
            this.localeResolver = new AcceptHeaderLocaleResolver();
            if (logger.isDebugEnabled()) {
                logger.debug("Using LocaleResolver [" + this.localeResolver + "]");
            }
        }
        catch (NoSuchBeanDefinitionException ex) {
            // We need to use the default.
            //this.localeResolver = getDefaultStrategy(context, LocaleResolver.class);
            //if (logger.isDebugEnabled()) {
            logger.error("Unable to locate LocaleResolver with name '" + DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME +
                        "': using default [" + this.localeResolver + "]");
            //}
        }
    }

    private void initViewResolvers(ApplicationContext context) {
        List<ViewResolver> viewResolvers = null;
        Map<String, ViewResolver> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(context, ViewResolver.class, true, false);
        if (!matchingBeans.isEmpty()) {
            viewResolvers = new ArrayList<ViewResolver>(matchingBeans.values());
            // We keep ViewResolvers in sorted order.
            AnnotationAwareOrderComparator.sort(viewResolvers);
            this.viewResolver = viewResolvers.get(0);
        }
        if (this.viewResolver == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("No ViewResolvers found in servlet '" + "': using default");
            }
        }
    }
    protected void render(String viewName,RequestResult requestResult,HttpServletRequest request,HttpServletResponse response) throws Exception {
        // Determine locale for request and apply it to the response.

        VelocityConfigurer cfg = applicationContext.getBean(VelocityConfigurer.class);
        VelocityEngine engine = cfg.getVelocityEngine();
        VelocityContext context = new VelocityContext(WebUtils.mergePageResult(request,requestResult));
        StringWriter writer = new StringWriter();
        engine.getTemplate(viewName).merge(context, writer);
        String page = writer.toString();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@\n"+page+"\n@@@@@@@@@@@@@@@@@@@@@@@");

    }


}