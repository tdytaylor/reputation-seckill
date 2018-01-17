package com.taylor.reputation.seckill.redis;

/**
 * @author taylor
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
