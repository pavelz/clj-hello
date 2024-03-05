(ns clj.core
  (:require [io.pedestal.http :as http]          ;; <2>
            [io.pedestal.http.route :as route]
            [clj-postgresql.core :as pg]
            [selmer.parser :as sel]
            [clojure.java.io :as io]
            [clojure.tools.trace :as tr]
            [clojure.java.jdbc :as jdbc])
)

(defn foo [[name lastname]]
  (println "hello" name)
  (println "hello" lastname)
  )

(defn foo2
  "I don't do a whole lot."
  [x]
  (foo x)
  (println x "Hello, World!")
  (println "hey")
)

(defn is_html [s] (re-matcher ".html$" s))

(defn render-template [request name]
    (let [
        nm (get-in request [:params (keyword(str name))])
        template (slurp (io/resource nm))
        rendered (if (re-find #".html$" nm)
            (sel/render template {:title "Welcome" :greeting "Hello" :name "bob"})
            (slurp (io/resource nm))
            )]
    rendered
    ))

;;; detect filetype and reflect that
(defn content_type [request name]
    (let [
        nm (get-in request [:params (keyword(str name))])
    type (if (re-find #".html$" nm)
        "text/html"
        "binary/image"
        )
    ]
    type)
    )

(defn respond-hello [request]
  (foo2 "bom")
  {:status 200 :body (render-template request "name") :headers {"Content-Type" (content_type request "name")}}
  )

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
