(defproject clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :main clj.core/start
  :dependencies [
                  [org.clojure/clojure          "1.10.1"]
                  [io.pedestal/pedestal.service "0.5.7"]
                  [io.pedestal/pedestal.route   "0.5.7"]
                  [io.pedestal/pedestal.jetty   "0.5.7"]
                  [org.slf4j/slf4j-simple       "1.7.28"]
                  [org.clojure/tools.namespace  "1.4.4"]
                  [clj-postgresql "0.7.0"]
                  [selmer         "1.12.31"]
                  [org.clojure/tools.trace "0.7.9"]

                ]

  :plugins [[cider/cider-nrepl "0.24.0"]]
  :repl-options {
                 :init-ns clj.core
                 :prompt (fn [ns] (str "your command for <" ns ">? " ))
                 })
