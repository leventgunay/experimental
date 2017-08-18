stream-sampler
-----

This is an experimental implementation of [reservoir sampling](http://en.wikipedia.org/wiki/Reservoir_sampling) over three possible input streams. The implementation requires and includes **Java 8** features, basic **UNIX terminal** interaction and **IO operations**.



### How to run

To run the program, there is a shell script controls interaction through terminal as shown below;

```bash
~$ cd <project-folder>
```
```bash
~$ bin/stream-sampler <sample-size> [input-stream] [-random/-http] [<remote-http-url>]
```

```bash
//random generated input
$ bin/stream-sampler 4   
$ bin/stream-sampler 4 -random
```

```bash
$ bin/stream-sampler 4 THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG
```

```bash
$ cat path/to/text/input.txt | bin/stream-sampler 4 
```

```bash
$ bin/stream-sampler 4 -http "https://www.random.org/strings/?num=10&len=10&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new"
```


### How to test
```bash
~$ cd <project-folder>
~$ bin/stream-sampler test
```