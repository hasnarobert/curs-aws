package cursaws.webexample.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Counter implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Long counterValue;

  @Column(nullable = false)
  private String name;

  public Long getCounterValue() {
    return counterValue;
  }

  public void setCounterValue(Long counterValue) {
    this.counterValue = counterValue;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Counter{" +
           "id=" + id +
           ", counterValue=" + counterValue +
           ", name='" + name + '\'' +
           '}';
  }
}
