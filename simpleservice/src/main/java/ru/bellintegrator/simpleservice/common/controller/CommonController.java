package ru.bellintegrator.simpleservice.common.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.simpleservice.common.Dto.DataDto;
import ru.bellintegrator.simpleservice.common.Dto.ErrorDto;

@RestControllerAdvice
public class CommonController implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(returnType.getGenericParameterType().getTypeName().equals("void")){
            body = "success";
        }
        if (body.getClass().equals(ErrorDto.class)) {
            return body;
        }
        DataDto data = new DataDto();
        data.setData(body);
        return data;
    }
}
