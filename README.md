Aufgabe 1:

A1PuzzleGenerator.java    generiert ein Puzzle und speichert es in einer .txt-Datei (puzzle.txt).

A1PuzzleSolver.java       liest ein Puzzle aus einer .txt-Datei ein, generiert alle möglichen Lösungen und speichert diese in einer neuen .txt-Datei (output.txt).

PuzzleSolverConsole.java  ist eine vereinfachte Version des PuzzleSolvers. Anstatt die Lösung in eine .txt-Datei zu speichern, gibt diese Programm die Lösung in der                               Console aus.
puzzle                    Das Puzzle wird als ein String gespeichert. Dieser besteht aus 9 Substrings, getrennt mittles Whitespace. Die Reihenfolge ist wie folgt:

                          1 + 2 = 3
                          4 + 5 = 6
                          7 + 8 = 9
                          
                          Somit wäre im String "A B C D E F G H I", A die Variable 1, B die 2 usw.
                          
                          
Aufgabe 2:

gRPC_Server.java          ist der Server welcher von dem Client aufgerufen wird.

PuzzleSolver.java         ist der Service welcher von dem Server angeboten wird. Hier wird ein vom Server empfangenes Puzzle gelöst. 
                          Ein Problem meiner Lösung ist, dass ich es nicht geschafft habe, die Lösung als Liste aller möglichen Lösungen zu erstellen. Wie in Aufgabe 1,                           die Lösung wird entweder nacheinander in der Console ausgegeben oder in einer .txt-Datei ergänzt. Mir ist es nicht gelungen alle Lösungen                                 zusammen zu fassen. Hätte ich dies geschafft könnte ich die Lösung an den Client in der response zurück schicken. Hinzu kommt das Problem, dass                           der service keine Console hat, somit ist es nicht möglich die Lösung zu sehen.
                          
gRPC_Client.java          ist der Client, der ein Puzzle generiert und dieses an den Server schickt.
