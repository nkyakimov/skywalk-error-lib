package com.skywalk.error.lib.filter;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import com.skywalk.error.lib.exception.InternalErrorException;
import com.skywalk.error.lib.exception.SkywalkException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

@Order(HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@Component
@Slf4j
public class SkywalkErrorHandlingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SkywalkException e) {
            handleSkywalkException(response, e);
        } catch (NestedServletException e) {
            if (e.getCause() == null) {
                handleSkywalkException(response, new InternalErrorException(e));
            } else if (e.getCause() instanceof  SkywalkException) {
                handleSkywalkException(response, (SkywalkException) e.getCause());
            } else {
                handleSkywalkException(response, new InternalErrorException(e.getCause()));
            }
        } catch (Throwable e) {
            handleSkywalkException(response, new InternalErrorException(e));
        }
    }

    private void handleSkywalkException(HttpServletResponse response, SkywalkException skywalkException)
            throws IOException {
        log.error(skywalkException.getDetailedMessage(), skywalkException);

        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(skywalkException.getHttpStatus());
        objectMapper.writeValue(response.getWriter(), skywalkException.toErrorResource());
        response.getWriter().close();
    }

}
