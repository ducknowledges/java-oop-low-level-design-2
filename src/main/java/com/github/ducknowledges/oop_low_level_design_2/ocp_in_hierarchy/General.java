package com.github.ducknowledges.oop_low_level_design_2.ocp_in_hierarchy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class General implements Serializable {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public final <T extends General> void copyTo(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }

        try {
            String json = OBJECT_MAPPER.writeValueAsString(this);
            General copy = OBJECT_MAPPER.readValue(json, this.getClass());
            target.mergeFrom(copy);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to copy object", e);
        }
    }

    public final <T extends General> T cloneDeep(Class<T> clazz) {
        if (!clazz.isAssignableFrom(this.getClass())) {
            throw new IllegalArgumentException("Cannot clone to incompatible type");
        }
        try {
            String json = OBJECT_MAPPER.writeValueAsString(this);
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to clone object", e);
        }
    }

    public final String serialize() throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(this);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    @JsonIgnore
    public final Class<?> getType() {
        return this.getClass();
    }

    public final boolean isInstanceOf(Class<?> type) {
        return type.isInstance(this);
    }

    protected abstract <T extends General> void mergeFrom(T other);

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private byte[] serializeToBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(this);
        return bos.toByteArray();
    }
}

class Any extends General {

    @Override
    protected <T extends General> void mergeFrom(T other) {

    }
}

