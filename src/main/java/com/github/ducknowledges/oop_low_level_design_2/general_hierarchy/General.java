package com.github.ducknowledges.oop_low_level_design_2.general_hierarchy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public abstract class General implements Serializable {

  public void copyTo(General target) throws IOException, ClassNotFoundException {
    if (target == null) {
      throw new IllegalArgumentException("Target cannot be null");
    }

    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);
      out.writeObject(this);
      out.flush();

      ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
      ObjectInputStream in = new ObjectInputStream(bis);
      General copy = (General) in.readObject();
      target.mergeFrom(copy);
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  protected abstract void mergeFrom(General other);

  public General cloneDeep() {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);
      out.writeObject(this);
      out.flush();

      ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
      ObjectInputStream in = new ObjectInputStream(bis);
      return (General) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  public String serialize() throws IOException {
    return Base64.getEncoder().encodeToString(serializeToBytes());
  }

  public General deserialize(String data) {
    try {
      byte[] bytes = Base64.getDecoder().decode(data);
      ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
      return (General) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public String print() {
    return this.toString();
  }

  public Class<?> getRealType() {
    return this.getClass();
  }

  public boolean isInstanceOf(Class<?> type) {
    return type.isInstance(this);
  }

  private byte[] serializeToBytes() throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    new ObjectOutputStream(bos).writeObject(this);
    return bos.toByteArray();
  }
}

class Any extends General {
  @Override
  protected void mergeFrom(General other) {}
}
