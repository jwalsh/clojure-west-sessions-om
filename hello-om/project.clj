(defproject hello-om "0.1.0-SNAPSHOT"
  :description "Clojure/West Sessions: Om"
  :url "http://wal.sh/conferences"

  :dependencies [[org.clojure/clojure "1.7.0-beta1"]
                 [org.clojure/clojurescript "0.0-3208"]
                 [org.omcljs/om "0.8.8"]]

  :plugins [[lein-cljsbuild "1.0.5"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["out/hello_om" "out/hello_om.js"]

  :cljsbuild {
    :builds [{:id "hello-om"
              :source-paths ["src"]
              :compiler {
                :main hello-om.core
                :output-to "out/hello_om.js"
                :output-dir "out"
                :optimizations :none
                :verbose true}}]})
