package com.nbe2.domain.auth;

import com.nbe2.common.properties.OAuthProperty;

import static com.nbe2.common.properties.OAuthProperty.KAKAO_OAUTH_QUERY_STRING;

public interface OAuthClient {

    default String getConnectionUrl() {
        return OAuthProperty.Companion.getBASE_URL() + String.format(KAKAO_OAUTH_QUERY_STRING, OAuthProperty.Companion.getCLIENT_ID(), OAuthProperty.Companion.getREDIRECT_URI());
    }

    OAuthProfile getOAuthProfile(String code);
}
