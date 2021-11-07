package error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MyError {
  private Object typeOfError;
  private String message;

  public MyError(Object typeOfError, String message) {
    this.typeOfError = typeOfError;
    this.message = message;
  }
}
