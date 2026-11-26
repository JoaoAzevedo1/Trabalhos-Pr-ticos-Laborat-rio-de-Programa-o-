Compilação e execução

Com o JDK 17 instalado, execute no PowerShell a partir da raiz do projeto:

```powershell
$arquivosJava = Get-ChildItem -Recurse -Filter *.java src
javac -encoding UTF-8 -d out $arquivosJava.FullName
java -cp out app.Main
java -cp out test.BibliotecaTest
```

Resultado esperado dos testes: `Todos os 14 testes passaram.`
