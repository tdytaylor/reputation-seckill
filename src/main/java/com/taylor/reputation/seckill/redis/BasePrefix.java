package com.taylor.reputation.seckill.redis;

/**
 * @author taylor
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        // 0 express never lose
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String str = getClass().getSimpleName();
        return str + ":" + prefix;
    }
}
