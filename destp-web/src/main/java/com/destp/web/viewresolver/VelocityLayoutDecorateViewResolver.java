package com.destp.web.viewresolver;

import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 * Created by Administrator on 2016/6/10 0010.
 */
public class VelocityLayoutDecorateViewResolver extends VelocityLayoutViewResolver {

    @Override
    protected Class<?> requiredViewClass() {
        return VelocityLayoutDecorateView.class;
    }
}
