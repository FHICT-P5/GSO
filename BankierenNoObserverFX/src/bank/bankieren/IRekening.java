package bank.bankieren;

import java.io.Serializable;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;

public interface IRekening extends Serializable {
  int getNr();
  Money getSaldo();
  IKlant getEigenaar();
  int getKredietLimietInCenten();
}

