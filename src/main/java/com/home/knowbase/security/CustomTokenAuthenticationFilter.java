package com.home.knowbase.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//public class CustomTokenAuthenticationFilter extends GenericFilterBean {
public class CustomTokenAuthenticationFilter {

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
//
//        if (!hasText(token)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String authorization = request.getHeader(AUTHORIZATION);
//        if (hasText(authorization) && authorization.startsWith(BEARER)) {
//            return authorization.substring(BEARER.length()).trim();
//        }
//        return null;
//    }
}
