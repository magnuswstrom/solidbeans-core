package com.solidbeans.core.security.jwt.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtConfig {

    public enum JwtJtiType {
        NONE, IP, UUID
    }

    private JwtJtiType jtiType;
    private long cacheMaxSize;
    private int cacheTime;
    private TimeUnit cacheTimeUnit;

    private boolean isIssNotNull;
    private boolean isIssRequiredValid;
    private boolean isSubNotNull;
    private boolean isAudNotNull;
    private boolean isExpNotNull;
    private boolean isIatNotNull;
    private boolean isNbfNotNull;
    private boolean isJtiNotNull;
    private List<String> validIssuers;

    public JwtConfig() {
        this.isIssNotNull = false;
        this.isIssRequiredValid = false;
        this.isSubNotNull = false;
        this.isAudNotNull = false;
        this.isExpNotNull = false;
        this.isIatNotNull = false;
        this.isNbfNotNull = false;
        this.isJtiNotNull = false;
        this.validIssuers = new ArrayList<>();
        this.jtiType = JwtJtiType.NONE;
        this.cacheMaxSize = 1000000L;
        this.cacheTime = 1;
        this.cacheTimeUnit = TimeUnit.HOURS;
    }

    public boolean isIssNotNull() {
        return isIssNotNull;
    }

    public void setIssNotNull(boolean issNotNull) {
        isIssNotNull = issNotNull;
    }

    public boolean isIssRequiredValid() {
        return isIssRequiredValid;
    }

    public void setIssRequiredValid(boolean issRequiredValid) {
        isIssRequiredValid = issRequiredValid;
    }

    public boolean isSubNotNull() {
        return isSubNotNull;
    }

    public void setSubNotNull(boolean subNotNull) {
        isSubNotNull = subNotNull;
    }

    public boolean isAudNotNull() {
        return isAudNotNull;
    }

    public void setAudNotNull(boolean audNotNull) {
        isAudNotNull = audNotNull;
    }

    public boolean isExpNotNull() {
        return isExpNotNull;
    }

    public void setExpNotNull(boolean expNotNull) {
        isExpNotNull = expNotNull;
    }

    public boolean isIatNotNull() {
        return isIatNotNull;
    }

    public void setIatNotNull(boolean iatNotNull) {
        isIatNotNull = iatNotNull;
    }

    public boolean isNbfNotNull() {
        return isNbfNotNull;
    }

    public void setNbfNotNull(boolean nbfNotNull) {
        isNbfNotNull = nbfNotNull;
    }

    public boolean isJtiNotNull() {
        return isJtiNotNull;
    }

    public void setJtiNotNull(boolean jtiNotNull) {
        isJtiNotNull = jtiNotNull;
    }

    public List<String> getValidIssuers() {
        return validIssuers;
    }

    public JwtJtiType getJtiType() {
        return jtiType;
    }

    public void setJtiType(JwtJtiType jtiType) {
        this.jtiType = jtiType;
    }

    public long getCacheMaxSize() {
        return cacheMaxSize;
    }

    public void setCacheMaxSize(long cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public TimeUnit getCacheTimeUnit() {
        return cacheTimeUnit;
    }

    public void setCacheTimeUnit(TimeUnit cacheTimeUnit) {
        this.cacheTimeUnit = cacheTimeUnit;
    }
}
