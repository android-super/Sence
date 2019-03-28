package com.sence.net.manager;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.orhanobut.logger.Logger;
import com.sence.net.bean.ErrorConstants;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

import static com.sence.net.bean.ErrorConstants.error_content;

class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        error_content = value.string();
        ResponseBody body = ResponseBody.create(MediaType.parse("application/json"), error_content);
        JsonReader jsonReader = gson.newJsonReader(body.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            Logger.e("result=======" + error_content);
            value.close();
        }
    }
}