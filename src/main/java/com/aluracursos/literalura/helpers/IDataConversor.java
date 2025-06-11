package com.aluracursos.literalura.helpers;

public interface IDataConversor {
    <T> T getData(String json, Class<T> tClass);
}
