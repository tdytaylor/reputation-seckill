package com.taylor.reputation.seckill.redis;

/**
 * @author taylor
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
