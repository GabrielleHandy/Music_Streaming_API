package com.example.musicstreamingapi.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.Column;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
}
