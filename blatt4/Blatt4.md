<!-- (c) https://github.com/MontiCore/monticore -->
# SWT - Blatt 4

> **DEADLINE**: Mittwoch, 13.11.2024 um 16:00 Uhr

## Vorraussetzung

1. Konsole verfügbar (Bash, Tcsh, Powershell, Cygwin o.Ä.)
2. Git installiert & auf dem Pfad (`$PATH`)

Testen Sie Ihre Installation, indem Sie `git --version` in die Konsole Ihrer Wahl eingeben. Beispiel-Output:

```bash
$ git --version
git version 2.34.1
```

## Einleitung

Sie haben zu dieser Aufgabe zwei Java-Klassen im Unterordner `code` bekommen.
Die Klasse `TransitiveClosure` beinhaltet eine Methode `closure` zur Berechnung
der transitiven Hülle eines Graphen. Die Klasse `Main` enthält eine ausführbare
`main`-Methode zum Ausprobieren der `TransitiveClosure`-Implementierung

Sie könnten die Klasse kompilieren, indem Sie in den Unterordner `code`
wechseln:

```bash
cd code
```

Und, vorrausgesetzt, dass Sie Java 11 installiert haben, folgenden Konsolenbefehl ausführen:

```bash
javac TransitiveClosure.java Main.java
```

In der `main`-Methode haben wir für Sie einen kleinen Test angelegt. Die Hülle
der Adjazenzmatrix `[[0,1],[1,0]]` wird berechnet. Sie könnten die
`main`-Methode ausführen, nachdem Sie wie oben beschrieben beide Klassen
kompiliert haben. Zur Ausführung könnten Sie folgenden Befehl in die Konsole
eingeben:

```bash
java -cp . Main
```

Diese Art des manuellen Testens skaliert nicht. Wir haben für Sie ein
Gradle-Skript erstellt, dass die Verwaltung der Tests übernimmt. Das Skript
finden Sie neben diesem Übungsblatt in der Datei `build.gradle`.

Gradle kümmert sich automatisch um Kompilation des Codes und der Tests.
Ausserdem ist Gradle in der Lage die Tests auszuführen. Mit dem Befehl `gradle
compileJava` werden alle gefundenen Klassen kompiliert. Die generierten
`class`-Files landen in Gradle's Default Output, `build/`.

Vorrausgesetzt Sie haben Java und Gradle installiert, können Sie die Kompilation lokal testen. Dazu geben Sie folgenden Befehl in die Konsole ein:

```bash
gradle compileJava
```

## Aufgabe 4.1 (3 Punkte)

Wir werden wir in dieser Aufgabe das Kompilieren durch Gitlab's CI/CD erledigen
lassen. Sie haben als Teil dieser Aufgabe eine Datei `.gitlab-ci.yml` im
Stammverzeichnis der SWT-Aufgaben erhalten. Diese YAML-Datei spezifiziert die
Aufgaben, die automatisiert bei jedem Commit ausgeführt werden sollen. Die Datei beinhaltet folgenden Block:

```yml
Blatt 4:
  trigger:
    include: blatt4/.gitlab-ci.yml
  rules:
    - changes:
        - blatt4/**/*
```

Diese Datei `.gitlab-ci.yml` im Stammverzeichnis der SWT-Aufgaben definiert
einen Job namens `Blatt 4`. Dieser Job `trigger`'t die Ausführung eines weiteren
Skripts `blatt4/.gitlab-ci.yml`, dass Sie im Folgenden anlegen werden. Weiterhin
ist eine Regel angegeben, die beschreibt wann der Job `Blatt 4` ausgeführt wird:
Jede Änderung an Dateien die unter `blatt4` liegen (siehe `changes`) sorgen für
die Ausführung des Jobs. Die Sterne sind *Wildcards* für Dateien (`*`) und Pfad
(`**`).

Legen Sie die YAML-Datei `blatt4/.gitlab-ci.yml` an. Denken Sie daran die Datei
einzuchecken. zB:
* `git add blatt4/.gitlab-ci.yml` und
* `git commit -m '.gitlab-ci.yml hinzugefügt'`, 
* `git push`.

Definieren Sie in der von Ihnen angelegten `.gitlab-ci.yml`-Datei einen eigenen
Job namens "`Kompilieren mit Gradle`", der wie oben beschrieben den
`compileJava`-Task mittels Gradle ausführt. Benutzen Sie folgendes Image für den
Job:
`registry.git.rwth-aachen.de/monticore/container-registry/gradle:7.4.2-jdk11`.

Denken Sie daran die veränderte `blatt4/.gitlab-ci.yml` einzuchecken. zB:
* `git add blatt4/.gitlab-ci.yml` und
* `git commit -m 'Job zum Kompilieren hinzugefügt'`, 
* `git push`.

Prüfen Sie die korrekte Ausführung Ihres Jobs, indem Sie zur Übersicht der
Pipelines navigieren und sicherstellen, dass alle Jobs erfolgreich waren
(Trigger-Job `Blatt 4` sowie der resultierende `Kompilieren mit Gradle`-Job).

> **Hinweis**: Sie erhalten die Punkte nur, wenn der von Ihnen erstellte Job den
richtigen Namen hat, erfolgreich terminiert und die Klassen kompiliert!

## Aufgabe 4.2 (3 Punkte)

Legen Sie einen JUnit-Test an, der das ausprobieren durch die `main`-Methode
ersetzt. Erstellen Sie dazu eine Datei namens `MyFirstTest.java`, die Sie unter
`test/` ablegen. Sie dürfen dazu folgendes Template verwenden:

```java
/* (c) https://github.com/MontiCore/monticore */
import org.junit.jupiter.api.Test;

public class MyFirstTest {

  /** 
   * Überprüft die korrekte Funktionsweise der Berechnung der transitiven
   * Hülle anhand eines simplen Beispiels.
   */
  @Test
  public void testSimple() {
    // TODO Hier einen Test schreiben
  }

}
```

Denken Sie daran die Testklasse einzuchecken. zB:
* `git add blatt4/test/MyFirstTest.java` und
* `git commit -m 'Testklasse hinzugefügt'`, 
* `git push`.

Definieren Sie einen JUnit-Testfall, der den gleichen Code wie die
`main`-Methode enthält. Verwenden Sie ausserdem JUnit-Assert-Statements um das
Ergebnis der Berechnung zu prüfen.

> **Hinweis**: Verwenden Sie nicht das `assert`-Keyword, sondern JUnit's eigene
> Assert-Statements (beispielsweise `assertTrue()`, `assertEquals()`). Weitere
> Informationen finden Sie in der [JUnit-Dokumentation][junit].

Denken Sie daran die veränderte Testklasse einzuchecken. zB:
* `git add blatt4/test/MyFirstTest.java` und
* `git commit -m 'Test implementiert'`, 
* `git push`.

Das vorgefertige Gradle-Skript erlaubt das Ausführen der Tests mittels `test`-Task:

```bash
gradle test
```

Wir werden in der folgenden Aufgabe Gitlab's CI/CD nutzen, um diesen Task
automatisch und ohne lokale Installation ausführen zu lassen.

> **Hinweis**: Sie können die Punkte nur erhalten, wenn die automatische
> Korrektur in der Lage ist den `test`-Task erfolgreich auszuführen. Sie können
> sich diesbezüglich absichern, indem Sie Aufgabe 4.3 erfolgreich umsetzen.

## Aufgabe 4.3 (3 Punkte)

Definieren Sie in der von Ihnen bereits angelegten `.gitlab-ci.yml`-Datei aus
Aufgabe 4.1 einen neuen Job namens "`Testen mit Gradle`". Der Job soll wie oben
beschrieben den `test`-Task mit Gradle ausführen. Benutzen Sie das folgende Image für den Job:
`registry.git.rwth-aachen.de/monticore/container-registry/gradle:7.4.2-jdk11`.

Denken Sie daran die veränderte `blatt4/.gitlab-ci.yml` einzuchecken. zB:
* `git add blatt4/.gitlab-ci.yml` und
* `git commit -m 'Job zum Testen mit JUnit hinzugefügt'`, 
* `git push`.

Prüfen Sie die korrekte Ausführung Ihres Jobs, indem Sie zur Übersicht der
Pipelines navigieren und sicherstellen, dass alle Jobs erfolgreich waren
(Trigger-Job `Blatt 4` sowie der resultierende `Testen mit Gradle`-Job).

> **Hinweis**: Sie erhalten die Punkte nur, wenn der von Ihnen erstellte Job den
richtigen Namen hat, erfolgreich terminiert und den Test aus der `main`-Methode
immitiert!

## Aufgabe 4.4 (1 Punkt)

Fügen Sie der Testklasse einen weiteren JUnit-Testfall hinzu. Der Testfall soll
den Algorithmus bei einer inkorrekt geformten Eingabe prüfen. Dabei soll
sichergestellt werden, dass bei einer solchen Eingabe das Resultat eine leere
Liste ist. Der Name des Testfalls spielt keine Rolle.

Denken Sie daran die veränderte Testklasse einzuchecken. zB:
* `git add blatt4/test/MyFirstTest.java` und
* `git commit -m 'Testfall für inkorrekte Eingaben'`, 
* `git push`.

Versichern Sie sich, dass der in Aufgabe 4.3 bereits erstellte Job Ihren
zusätzlichen Test ausführt. Beispiel-Output:

```
> Task :test

MyFirstTest > testSimple() PASSED

MyFirstTest > testIncorrectInput() PASSED
```

> **Hinweis**: Nutzen Sie das Material aus dem Selbstlernkurs [Werkzeuge für die
> Softwareentwicklung][swtools]. Bedenken Sie, dass Gitlab CI-Skripte immer vom
> Stammverzeichnis des Projekts ausgehen. Sie müssen also als Teil des
> Job-Skripts zunächst in das richtige Unterverzeichnis wechseln.

[swtools]: https://moodle.rwth-aachen.de/course/view.php?id=24730
[coverage]: https://en.wikipedia.org/wiki/Code_coverage
[jacoco]: https://docs.gradle.org/current/userguide/jacoco_plugin.html
[hub]: https://hub.docker.com/_/ubuntu
[junit]: https://junit.org/junit5/docs/current/user-guide/#writing-tests
