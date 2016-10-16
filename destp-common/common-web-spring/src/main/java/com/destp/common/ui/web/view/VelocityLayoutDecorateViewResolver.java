package com.destp.common.ui.web.view;

import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public class VelocityLayoutDecorateViewResolver extends VelocityLayoutViewResolver {

    @Override
    protected Class<?> requiredViewClass() {
        return VelocityLayoutDecorateView.class;
    }
}