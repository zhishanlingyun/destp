package com.destp.ds;

import java.util.Iterator;

/**
 * Created by liuli10 on 2017/1/19.
 */
public abstract class AbstractList<E> implements IList<E>{

    public Iterable iterable() {
        return null;
    }

    private class Itr implements Iterator<E>{

        public boolean hasNext() {
            return false;
        }

        public E next() {
            return null;
        }

        public void remove() {

        }
    }

}
