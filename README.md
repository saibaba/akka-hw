# akka-hw

Learning to use Akka framework from within Clojure.
This is an attempt to reimplement of Akka Hello World official tutorial in Clojure.

* messages.clj - contains messages
* actors - contains actor code and their onReceive handlers
* hello.clj - main driver

## Usage

```
$ lein repl
  ; compile and run
  user=> (compile 'akka-hw.hello)
  user=> (akka-hw.hello/sample)
```

## Ref
* http://gettingclojure.wikidot.com/articles:extending-java-classes-using-proxy
* https://github.com/daveray/clojak/blob/master/src/clojak/core.clj
* http://www.typesafe.com/activator/template/hello-akka?_ga=1.222290776.1168684417.1436224761#code/src/main/java/HelloAkkaJava.java
* http://kotka.de/blog/2010/03/proxy_gen-class_little_brother.html
* http://clojure.github.io/clojure/clojure.core-api.html#clojure.core/gen-class
* https://blog.8thlight.com/colin-jones/2010/12/05/clojure-libs-and-namespaces-require-use-import-and-ns.html
