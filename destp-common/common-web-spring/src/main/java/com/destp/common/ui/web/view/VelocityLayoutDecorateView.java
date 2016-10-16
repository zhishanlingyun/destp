package com.destp.common.ui.web.view;


import com.destp.common.ui.web.util.PatternUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;

/**
 * Created by Administrator on 2016/6/10 0010.
 */
public class VelocityLayoutDecorateView extends VelocityLayoutView {

    private String layoutUrl = DEFAULT_LAYOUT_URL;

    private String layoutKey = DEFAULT_LAYOUT_KEY;

    private String screenContentKey = DEFAULT_SCREEN_CONTENT_KEY;

    @Override
    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
        super.setLayoutUrl(layoutUrl);
    }

    @Override
    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
        super.setLayoutKey(layoutKey);
    }

    @Override
    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
        super.setScreenContentKey(screenContentKey);
    }

    @Override
    protected void doRender(Context context, HttpServletResponse response) throws Exception {
        renderScreenContent(context);
        String layoutUrlToUse = (String) context.get(this.layoutUrl);
        if (layoutUrlToUse != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Screen content template has requested layout [" + layoutUrlToUse + "]");
            }
        }
        else {
            // No explicit layout URL given -> use default layout of this view.
            layoutUrlToUse = this.layoutUrl;
        }

        String content = (String)context.get(this.screenContentKey);
        if(StringUtils.isNotEmpty(content)){
            String head = PatternUtil.getHtmlTag(content,"head");
            String title = PatternUtil.getHtmlTag(head, "title");
            String body = PatternUtil.getHtmlTag(content, "body");
            context.put("head",head);
            context.put("title",title);
            context.put("body",body);
            context.remove(this.screenContentKey);
        }

        mergeTemplate(getTemplate(layoutUrlToUse), context, response);
    }

    private void renderScreenContent(Context velocityContext) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Rendering screen content template [" + getUrl() + "]");
        }

        StringWriter sw = new StringWriter();
        Template screenContentTemplate = getTemplate(getUrl());
        screenContentTemplate.merge(velocityContext, sw);
        velocityContext.put(this.screenContentKey, sw.toString());
    }


}
