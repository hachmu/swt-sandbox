/* (c) https://github.com/MontiCore/monticore */
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyFirstTest {

  /**
   * Überprüft die korrekte Funktionsweise der Berechnung der transitiven
   * Hülle anhand eines simplen Beispiels.
   */
  @Test
  public void testSimple() {
    /*
     * / 0, 1 \
     * \ 1, 0 /
     */
    ArrayList<List<Boolean>> m = new ArrayList<>();
    m.add(new ArrayList<>(List.of(false, true)));
    m.add(new ArrayList<>(List.of(true, false)));

    var t = new TransitiveClosure();
    List<List<Boolean>> result = t.closure(m);

    /*
     * Erwartetes Ergebnis:
     * / 1, 1 \
     * \ 1, 1 /
     */

    /* 
     * Methode 1: 
     * Erstelle die erwartete Adjazenzmatrix und vergleiche diese
     * mit der von t.closure(m) zurückgegebenen Adjazenzmatrix.
     */
    ArrayList<List<Boolean>> expectedResult = new ArrayList<>();
    expectedResult.add(new ArrayList<>(List.of(true, true)));
    expectedResult.add(new ArrayList<>(List.of(true, true)));
    
    assertEquals(expectedResult, result);

    /* 
     * Methode 2:
     * Prüfe, ob alle Einträge der von t.closure(m) zurückgegebenen
     * Adjazenzmatrix eine 1 (bzw. true als Boolean) sind.
     */
    for(List<Boolean> n : result) {
        for(Boolean b : n) {
            assertTrue(b);
        }
    }

    System.out.println(result);
  }

  /**
   * Überprüft die korrekte Funktionsweise der Berechnung der transitiven
   * Hülle anhand einer inkorrekt geformten Eingabe.
   */
  @Test
  public void testIncorrectInput() {
    /*
     * / 0, 1 \
     * | 1, 0 |
     * \ 1, 0 /
     */
    ArrayList<List<Boolean>> m = new ArrayList<>();
    m.add(new ArrayList<>(List.of(false, true)));
    m.add(new ArrayList<>(List.of(true, false)));
    m.add(new ArrayList<>(List.of(true, false)));

    var t = new TransitiveClosure();
    List<List<Boolean>> result = t.closure(m);

    /*
     * Erwartetes Ergebnis:
     * []
     */

    /* 
     * Methode 1: 
     * Erstelle die erwartete Adjazenzmatrix und vergleiche diese
     * mit der von t.closure(m) zurückgegebenen Adjazenzmatrix.
     */
    ArrayList<List<Boolean>> expectedResult = new ArrayList<>();
    
    assertEquals(expectedResult, result);

    /* 
     * Methode 2:
     * Prüfe, ob t.closure(m) eine leere Liste zurückgibt.
     */
    assertTrue(result.isEmpty());

    System.out.println(result);
  }
} 
