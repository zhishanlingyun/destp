package com.destp.acm.tree;

/**
 * Created by zsly on 17-11-7.
 */
public interface Visit<T> {

    public void onVisit(Tree<T> node);

}
