import java.awt.event.ActionEvent;

public interface CalcListener {
    void digitEvent(ActionEvent evt);
    void operationEvent(ActionEvent evt);
    void deletingEvent(ActionEvent evt);
    void functionEvent(ActionEvent evt);
    void bracketEvenet(ActionEvent evt);
  
}
