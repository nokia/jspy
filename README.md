# JSpy
**JSpy** is a tool that displays the component properties of any java Swing application in the simplest way. To automate Swing applications, You generally need to venture into the application's sources to retrieve the component's properties. That's very time consuming and frustrating or even impossible. Using JSpy all You need to do to get the component's properties is to hover the coursor over the component.
![](https://raw.githubusercontent.com/nokia/jspy/master/doc/img/jspy_big.png "JSpy Big")

## Dependencies
**JSpy** is operating system independent — it just needs Java 8 or newer.

## Building from sources
We’ll go with IntelliJ for compiling this
First clone the repository

```sh
git clone github.com/nokia/JSpy.git
```

Open cloned JSpy directory in IntelliJ. Go to File\>Project Structure\>Artifact Add a jar artifact, point it to manifest file and set `spyGui.SpyMain` as Main Class
![](https://raw.githubusercontent.com/nokia/jspy/master/doc/img/project_structure.png "IntelliJ Project Structure")

To compile build the jar Artifact with attached manifest.
![](https://raw.githubusercontent.com/nokia/jspy/master/doc/img/build_artifacts.png "Build Artifacts")

## Requirements
If you want to use JSpy with Java WebStart applications, you should create `.java.policy` file in your home directory.
Content of the file should be:
```
grant {
    permission java.security.AllPermission;
};
```

## Running
To execute jSpy call it from the console:
```sh
java -jar JSpy.jar
```

## Usage
When started go into File \> Execute Command


Execute Command: `javaws C:\path\to\file.jnlp`


## License

This project is licensed under the Apache-2.0 license - see the [LICENSE](https://github.com/nokia/jspy/blob/master/licence.txt).