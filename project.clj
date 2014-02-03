(defproject newnewtab "0.1.0-SNAPSHOT"
  :description "A new newtab page for Chromium"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "RELEASE"]
                 [org.clojure/clojurescript "0.0-2156"]]

  :plugins [[lein-cljsbuild "RELEASE"]]

  :source-paths ["src"]

  :profiles {:dev {:plugins [[com.cemerick/austin "0.1.3"]]}}

  :cljsbuild
  {:builds [{:id "newnewtab"
             :source-paths ["src"]
             :compiler {:output-to "newnewtab.js"
                        :output-dir "out"
                        :optimizations :whitespace
;                        :source-map "newnewtab.js.map"
                        }}]})
