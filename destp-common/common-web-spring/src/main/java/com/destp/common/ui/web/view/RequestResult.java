package com.destp.common.ui.web.view;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class RequestResult {

    private boolean state;

    private String viewName;

    private String content;

    private String pagehtml;

    private ModelMap model;

    public RequestResult() {
    }

    public RequestResult(boolean state) {
        this.state = state;
    }


    public ModelMap getModelMap() {
        if (this.model == null) {
            this.model = new ModelMap();
        }
        return this.model;
    }

    public RequestResult(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        if (model != null) {
            getModelMap().addAllAttributes(model);
        }
    }

    public RequestResult(Map<String, ?> model) {
        if (model != null) {
            getModelMap().addAllAttributes(model);
        }
    }

    public RequestResult addObject(String attributeName, Object attributeValue) {
        getModelMap().addAttribute(attributeName, attributeValue);
        return this;
    }

    /**
     * Add an attribute to the model using parameter name generation.
     * @param attributeValue the object to add to the model (never {@code null})
     * @see ModelMap#addAttribute(Object)
     * @see #getModelMap()
     */
    public RequestResult addObject(Object attributeValue) {
        getModelMap().addAttribute(attributeValue);
        return this;
    }

    /**
     * Add all attributes contained in the provided Map to the model.
     * @param modelMap a Map of attributeName -> attributeValue pairs
     * @see ModelMap#addAllAttributes(Map)
     * @see #getModelMap()
     */
    public RequestResult addAllObjects(Map<String, ?> modelMap) {
        getModelMap().addAllAttributes(modelMap);
        return this;
    }


    public void clear() {
        this.model = null;
    }

    public Map<String, Object> getModel() {
        return getModelMap();
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPagehtml() {
        return pagehtml;
    }

    public void setPagehtml(String pagehtml) {
        this.pagehtml = pagehtml;
    }
}
