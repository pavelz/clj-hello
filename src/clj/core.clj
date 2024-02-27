(ns clj.core
  (:require [io.pedestal.http :as http]          ;; <2>
            [io.pedestal.http.route :as route]
            [clj-postgresql.core :as pg]
            [selmer.parser :as sel]
            [clojure.java.io :as io]
            [clojure.java.jdbc :as jdbc])
)

(defn foo [[name lastname]]
  (println "hello" name)
  )

(defn foo2
  "I don't do a whole lot."
  [x]
  (foo x)
  (println x "Hello, World!")
  (println "hey")
)
(defn render-template [name]
    (let [template (slurp (io/resource "template.html"))
        rendered (sel/render template {:title "Welcome" :greeting "Hello" :name name})]
    rendered
    ))
(defn respond-hello [request]
  (foo2 "bom")
  {:status 200 :body (render-template "World")})

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]}))

(defn create-server []
  (http/create-server
   {::http/routes routes
    ::http/type   :jetty
    ::http/port   8890}))

(defn start []
  (http/start (create-server)))