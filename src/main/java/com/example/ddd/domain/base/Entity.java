package com.example.ddd.domain.base;

import java.util.Objects;

public abstract class Entity<ID> {
  private final ID id;

  protected Entity(ID id) {
    this.id = id;
  }

  public ID getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Entity<?> entity = (Entity<?>) o;
    return Objects.equals(getId(), entity.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
