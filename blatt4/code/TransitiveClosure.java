/* (c) https://github.com/MontiCore/monticore */

import java.util.ArrayList;
import java.util.List;

public class TransitiveClosure {

  /**
   * Bildet die transitive Hülle eines Graphen.
   *
   * @param m die Adjazenzmatrix des Graphen.
   * @return die transitive Hülle des Graphen oder eine leere Liste für
   * fehlerhafte Eingaben.
   */
  public List<List<Boolean>> closure(List<List<Boolean>> m) {
    // Überprüfe, ob eine quadratische Matrix vorliegt
    for (List<Boolean> n : m) {
      if (m.size() != n.size()) {
        // Leere Liste
        return List.of();
      }
    }

    int length = m.size();
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        // Überprüfe, ob es eine Kante von i nach j gibt
        if (m.get(i).get(j)) {
          for (int k = 0; k < length; k++) {
            // Überprüfe, ob es eine Kante von j nach k gibt
            if (m.get(j).get(k)) {
              // Füge eine Kante von i nach k ein
              m.get(i).set(k, true);
            }
          }
        }
      }
    }

    // Gibt die komplettierte Adjazenzmatrix zurück
    return m;
  }
}
