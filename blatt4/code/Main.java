/* (c) https://github.com/MontiCore/monticore */

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    /*
     * / 0, 1 \
     * \ 1, 0 /
     */
    ArrayList<List<Boolean>> m = new ArrayList<>();
    m.add(new ArrayList<>(List.of(false, true)));
    m.add(new ArrayList<>(List.of(true, false)));

    var t = new TransitiveClosure();
    System.out.println(t.closure(m));
  }

}
