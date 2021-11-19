# backend

A webservice to provide access to the "scramble" feature, which detects
if a scrambled-word is a "superset" of a word. The "superset" would be
considering the "Multiset" mathematical concept because in words
the letters can be repeated. See [multiset](https://en.wikipedia.org/wiki/Multiset).

## TL;DR:

```sh
lein ring server
```

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

