package com.kevinpernia.inventory_system.domain.shared;

import com.kevinpernia.inventory_system.domain.shared.exceptions.DomainException;
import lombok.Getter;

public abstract class Result<T> {
  public abstract boolean isSuccess();

  public abstract T getOrThrow();

  public static <T> Result<T> success(T value) {
    return new Success<>(value);
  }

  public static <T> Result<T> failure(DomainException exception) {
    return new Failure<>(exception);
  }

  public static final class Success<T> extends Result<T> {
    private final T value;

    public Success(T value) {
      this.value = value;
    }

    @Override
    public boolean isSuccess() {
      return true;
    }

    @Override
    public T getOrThrow() {
      return value;
    }
  }

  @Getter
  public static final class Failure<T> extends Result<T> {
    private final DomainException error;

    public Failure(DomainException error) {
      this.error = error;
    }

    @Override
    public boolean isSuccess() {
      return false;
    }

    @Override
    public T getOrThrow() {
      throw error;
    }
  }
}
