package com.solidbeans.core.security.jwt.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Claims configuration how security filter should handle claims property
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class ClaimsConfig {

    public static ClaimsConfig createDefaultClaimsConfig() {
        return createTolerantClaimsConfig();
    }

    public static ClaimsConfig createTolerantClaimsConfig() {
        return new ClaimsConfig();
    }

    public static ClaimsConfig createStrictClaimsConfig() {
       ClaimsConfig claimsConfig = createTolerantClaimsConfig();

        claimsConfig.setIssNotNull(true);
        claimsConfig.setIssRequiredValid(true);
        claimsConfig.setSubNotNull(true);
        claimsConfig.setAudNotNull(true);
        claimsConfig.setIatNotNull(true);
        claimsConfig.setExpNotNull(true);
        claimsConfig.setNbfNotNull(true);
        claimsConfig.setJtiNotNull(true);
        claimsConfig.setJtiType(JtiType.UUID);

       return claimsConfig;
    }

    public enum JtiType {
        NONE, IP, UUID
    }

    private JtiType jtiType;
    private long cacheMaxSize;
    private long cacheTime;
    private TimeUnit cacheTimeUnit;

    private boolean isIssNotNull;
    private boolean isIssRequiredValid;
    private boolean isSubNotNull;
    private boolean isAudNotNull;
    private boolean isExpNotNull;
    private boolean isIatNotNull;
    private boolean isNbfNotNull;
    private boolean isJtiNotNull;
    private final List<String> validIssuers;

    private ClaimsConfig() {
        this.isIssNotNull = false;
        this.isIssRequiredValid = false;
        this.isSubNotNull = false;
        this.isAudNotNull = false;
        this.isExpNotNull = false;
        this.isIatNotNull = false;
        this.isNbfNotNull = false;
        this.isJtiNotNull = false;
        this.validIssuers = new ArrayList<>();
        this.jtiType = JtiType.NONE;
        this.cacheMaxSize = 1000000L;
        this.cacheTime = 1L;
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

    public JtiType getJtiType() {
        return jtiType;
    }

    public void setJtiType(JtiType jtiType) {
        this.jtiType = jtiType;
    }

    public long getCacheMaxSize() {
        return cacheMaxSize;
    }

    public void setCacheMaxSize(long cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }

    public TimeUnit getCacheTimeUnit() {
        return cacheTimeUnit;
    }

    public void setCacheTimeUnit(TimeUnit cacheTimeUnit) {
        this.cacheTimeUnit = cacheTimeUnit;
    }
}
