package com.yan.shiro.common;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ��֤���������˵�¼����У�鹦��
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);
    
    //��Ⱥ�п��ܻᵼ�³�����֤���5�ε�������ΪAtomicIntegerֻ�ܱ�֤���ڵ㲢��
    private Cache<String, AtomicInteger> lgoinRetryCache;
 
    private int maxRetryCount = 5;
 
    private String lgoinRetryCacheName;
 
    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
 
    public RetryLimitCredentialsMatcher(CacheManager cacheManager,String lgoinRetryCacheName) {
        this.lgoinRetryCacheName = lgoinRetryCacheName;
        lgoinRetryCache = cacheManager.getCache(lgoinRetryCacheName);
    }
 
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	System.out.println("�Ѿ�����doCredentialsMatch����");
        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = lgoinRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            lgoinRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            log.warn("username: " + username + " tried to login more than 5 times in period");
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period"
            );
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry data
            lgoinRetryCache.remove(username);
        }
        return matches;
    }
}
